package gang.kaewwan.kaewwanbackend.rest.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gang.kaewwan.kaewwanbackend.rest.entity.StorageFileDTO;
import gang.kaewwan.kaewwanbackend.rest.util.StorageHelper;
import jakarta.annotation.PostConstruct;

@RestController
public class BucketController {
    @Autowired
    private StorageHelper storageHelper;

    @PostConstruct
    private void init() {
        System.out.println("BucketController --------------------");
        System.out.println(storageHelper);
    }

    @PostMapping(value = "uploadfile")
    public StorageFileDTO uploadFile(@RequestPart(value = "file") MultipartFile file)
            throws IOException, InterruptedException, ExecutionException {
        return storageHelper.getStorageFileDTO(file);
    }

}
