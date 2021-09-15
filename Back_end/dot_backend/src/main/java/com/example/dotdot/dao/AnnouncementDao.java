package com.example.dotdot.dao;

import com.example.dotdot.entity.Announcement;

import java.util.List;

public interface AnnouncementDao {
    List<Announcement> getAnnouncements();
}
