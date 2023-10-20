package gang.kaewwan.kaewwanbackend.rest.entity;

import lombok.Builder;
import lombok.Data;


// Save into table or only url for annoucements :thumbsup:
@Data
@Builder
public class StorageFileDTO {
    String fileName;
    String url;
}
