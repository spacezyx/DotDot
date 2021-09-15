package com.example.dotdot.controller;


import com.example.dotdot.entity.Announcement;
import com.example.dotdot.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 返回所有公告 无参数
     * @return List<Announcement>
     * Announcement:int id,String title,String content,String timestamp
     */
    @RequestMapping(value = "/getAnnouncements",produces = {"application/json;charset=UTF-8"})
    public List<Announcement> getAnnouncements() {
        System.out.println("www");
        return announcementService.getAnnouncements();
    }
}
