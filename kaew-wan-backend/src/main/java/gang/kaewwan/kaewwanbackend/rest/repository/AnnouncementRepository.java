package gang.kaewwan.kaewwanbackend.rest.repository;

import gang.kaewwan.kaewwanbackend.rest.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findByTeacher_Id(Long id , Pageable pageable);
}
