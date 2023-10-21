package gang.kaewwan.kaewwanbackend.rest.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gang.kaewwan.kaewwanbackend.rest.entity.StorageFileDTO;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseClientBuilder;
import io.github.jan.supabase.serializer.JacksonSerializer;
import io.github.jan.supabase.storage.BucketApi;
import io.github.jan.supabase.storage.Storage;
import io.github.jan.supabase.storage.StorageKt;
import jakarta.annotation.PostConstruct;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StorageHelper {
    SupabaseClient supabaseClient;
    BucketApi bucket;
    @Value("${application.supabase.anon-key}")
    String anonKey;
    @Value("${application.supabase.url}")
    String url;

    @PostConstruct
    public void init() {
        SupabaseClientBuilder builder = new SupabaseClientBuilder(url, anonKey);
        builder.setDefaultSerializer(new JacksonSerializer());
        builder.install(Storage.Companion, (Storage.Config config) -> {
            return null;
        });
        supabaseClient = builder.build();
        bucket = StorageKt.getStorage(supabaseClient).from("uploads");
    }

    public String uploadFile(MultipartFile file) throws IOException, InterruptedException, ExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dtString = sdf.format(new Date());
        final String fileName = URLEncoder.encode(String.format("%s-%s", dtString, file.getOriginalFilename()),
                "UTF-8");
        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] readBuf = new byte[4096];
        while (inputStream.available() > 0) {
            int bytesRead = inputStream.read(readBuf);
            byteArrayOutputStream.write(readBuf, 0, bytesRead);
        }
        log.info(String.format("Starting Upload. %d bytes", byteArrayOutputStream.toByteArray().length));
        String upload = BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, (scope, continuation) -> {
            return bucket.upload(fileName, byteArrayOutputStream.toByteArray(), true,
                    (Continuation<String>) continuation);
        });
        log.info(String.format("Finished Uploading to %s", upload));
        return bucket.publicUrl(fileName);
    }

    public StorageFileDTO getStorageFileDTO(MultipartFile file)
            throws IOException, InterruptedException, ExecutionException {
        String fileName = URLEncoder.encode(file.getOriginalFilename(), "UTF-8");
        log.info(fileName);
        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
            String urlString = uploadFile(file);
            return StorageFileDTO.builder().url(urlString).build();
        }
        return null;
    }
}
