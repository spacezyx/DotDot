package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.AnnouncementDao;
import com.example.dotdot.entity.Announcement;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


public class AnnouncementServiceImplTest {
    // 要mock的对象
    @Mock
    private AnnouncementDao announcementDao;

    // 被mock的对象的使用者
    @InjectMocks
    private AnnouncementServiceImpl announcementService;


    @Before
    public void setUp() throws Exception{
        // 对所有注解了@Mock的对象进行模拟
        MockitoAnnotations.initMocks(this);
        Announcement f1 = new Announcement(1,"点宝今天成立啦~","点宝Dotdot于2021年7月成立","2021-07-08 11:48:24.933164");
        Announcement f2 = new Announcement(2,"第一次测试公告","点宝Dotdot将于7月18日开始测试","2021-07-18 11:48:24.933164");

        List<Announcement> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);
        Mockito.when(announcementDao.getAnnouncements()).thenReturn(u);

    }

    @Test
    public void getAnnouncements(){
        Announcement f1 = new Announcement(1,"点宝今天成立啦~","点宝Dotdot于2021年7月成立","2021-07-08 11:48:24.933164");
        Announcement f2 = new Announcement(2,"第一次测试公告","点宝Dotdot将于7月18日开始测试","2021-07-18 11:48:24.933164");

        List<Announcement> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);
        List<Announcement> testUser = announcementService.getAnnouncements();
        // 检查结果
        Assert.assertThat(testUser, Matchers.is(u));
        Mockito.verify(announcementDao).getAnnouncements();
    }
}