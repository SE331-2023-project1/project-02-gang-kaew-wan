package gang.kaewwan.kaewwanbackend.rest.controller;

import gang.kaewwan.kaewwanbackend.rest.entity.Announcement;
import gang.kaewwan.kaewwanbackend.rest.entity.AnnouncementDTO;
import gang.kaewwan.kaewwanbackend.rest.service.AnnouncementService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnnouncementController {
    final AnnouncementService announcementService;
    @GetMapping("announcements")
    public List<AnnouncementDTO> getAnnouncementLists() {
        Page<Announcement> result = announcementService.getAnnouncements();
        return RestMapper.INSTANCE.getAnnouncementDTO(result.getContent());
    }

    @GetMapping("announcements/{personId}")
    public List<AnnouncementDTO> getAnnouncementLists(@PathVariable("personId") Long id) {
        Page<Announcement> result = announcementService.getAnnouncementByTeacherId(id);
        return RestMapper.INSTANCE.getAnnouncementDTO(result.getContent());
    }

    @GetMapping("announcement/{id}")
    public AnnouncementDTO getAnnouncementById(@PathVariable("id") Long id) {
        Announcement result = announcementService.getAnnouncementById(id);
        return RestMapper.INSTANCE.getAnnouncementDTO(result);
    }

    @PostMapping("announcements")
    public AnnouncementDTO addAnnouncement(@RequestBody Announcement announcement){
        return RestMapper.INSTANCE.getAnnouncementDTO(announcementService.postAnnouncement(announcement));
    }

}
