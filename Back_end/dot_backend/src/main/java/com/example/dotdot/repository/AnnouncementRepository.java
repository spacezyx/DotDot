package com.example.dotdot.repository;

import com.example.dotdot.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer>{
    @Query(value = "select * from dotdot.announcement", nativeQuery = true)
    List<Announcement> getAnnouncements();
}
