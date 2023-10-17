package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.entity.Announcement;
import org.springframework.data.domain.Page;

public interface AnnouncementService {
    Integer getAnnouncementCount();
    Page<Announcement> getAnnouncements();
    Announcement getAnnouncementById(Long id);
    Page<Announcement> getAnnouncementByTeacherId(Long id);

    Announcement postAnnouncement(Announcement announcement);

}
