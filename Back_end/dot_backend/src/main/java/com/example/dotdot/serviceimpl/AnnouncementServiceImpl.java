package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.AnnouncementDao;
import com.example.dotdot.entity.Announcement;
import com.example.dotdot.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementDao announcementDao;

    @Override
    public List<Announcement> getAnnouncements() {
        System.out.println("service");
        return announcementDao.getAnnouncements();
    }
}
