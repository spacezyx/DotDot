package com.example.dotdot.daoimpl;

import com.example.dotdot.dao.AnnouncementDao;
import com.example.dotdot.entity.Announcement;
import com.example.dotdot.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> getAnnouncements() {
        System.out.println("dao");
        return announcementRepository.getAnnouncements();
    }
}
