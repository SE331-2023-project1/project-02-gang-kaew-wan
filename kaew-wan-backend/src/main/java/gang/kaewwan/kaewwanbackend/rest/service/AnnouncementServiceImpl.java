package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.dao.AnnouncementDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Announcement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    final AnnouncementDao announcementDao;

    @Override
    public Integer getAnnouncementCount() {
        return announcementDao.getAnnouncementCount();
    }

    @Override
    public Page<Announcement> getAnnouncements() {
        return announcementDao.getAnnouncements();
    }

    @Override
    public Announcement getAnnouncementById(Long id) {
        return announcementDao.getAnnouncementById(id);
    }

    @Override
    public Page<Announcement> getAnnouncementByTeacherId(Long id) {
        return announcementDao.getAnnouncementByTeacherId(id);
    }

    @Override
    public Announcement postAnnouncement(Announcement announcement) {
        return announcementDao.postAnnouncement(announcement);
    }
}
