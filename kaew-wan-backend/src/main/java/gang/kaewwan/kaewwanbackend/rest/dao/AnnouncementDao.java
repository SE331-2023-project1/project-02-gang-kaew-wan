package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Announcement;
import org.springframework.data.domain.Page;

public interface AnnouncementDao {
    Integer getAnnouncementCount();
    Page<Announcement> getAnnouncements();
    Announcement getAnnouncementById(Long id);
    Page<Announcement> getAnnouncementByTeacherId(Long id);

    Announcement postAnnouncement(Announcement announcement);

}
