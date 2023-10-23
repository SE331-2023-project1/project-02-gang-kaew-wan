package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Announcement;
import gang.kaewwan.kaewwanbackend.rest.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class AnnouncementDaoImpl implements AnnouncementDao{
    final AnnouncementRepository announcementRepository;
    @Override
    public Integer getAnnouncementCount() {
        return Math.toIntExact(announcementRepository.count());
    }

    @Override
    public Page<Announcement> getAnnouncements() {
        return announcementRepository.findAll(Pageable.unpaged());
    }

    @Override
    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Announcement> getAnnouncementByTeacherId(Long id) {
        return announcementRepository.findByTeacher_Id(id, Pageable.unpaged());
    }

    @Override
    public Announcement postAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }
}
