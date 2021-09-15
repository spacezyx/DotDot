package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.RobotDao;
import com.example.dotdot.entity.Robot;
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

public class RobotServiceImplTest {
    // 要mock的对象
    @Mock
    private RobotDao robotDao;

    // 被mock的对象的使用者
    @InjectMocks
    private RobotServiceImpl robotService;


    @Before
    public void setUp() throws Exception{
        // 对所有注解了@Mock的对象进行模拟
        MockitoAnnotations.initMocks(this);
        Robot f1 = new Robot(1,1,"机器人创建成功","测试机1号机器人创建成功","2021-04-08 11:48:24.933164","last","establish",false,200,1);

        List<Robot> u = new ArrayList<>();
        u.add(f1);
        Mockito.when(robotDao.getRobots(1)).thenReturn(u);
        Mockito.when(robotDao.getInvalidRobots(1)).thenReturn(u);
        Mockito.when(robotDao.getAPISecretByApikey("apikey")).thenReturn("string");
        Mockito.when(robotDao.getTimesByApikey("apikey")).thenReturn(200);
        Mockito.when(robotDao.getTypeByApikey("apikey")).thenReturn(1);
        Mockito.when(robotDao.getValidByApikey("apikey")).thenReturn(true);
    }

    @Test
    public void getRobots() {
        Robot f1 = new Robot(1,1,"机器人创建成功","测试机1号机器人创建成功","2021-04-08 11:48:24.933164","last","establish",false,200,1);

        List<Robot> u = new ArrayList<>();
        u.add(f1);
        List<Robot> testUser = robotService.getRobots(1);
        // 检查结果
        Assert.assertThat(testUser, Matchers.is(u));
        Mockito.verify(robotDao).getRobots(1);
    }

    @Test
    public void getInvalidRobots() {
        Robot f1 = new Robot(1,1,"机器人创建成功","测试机1号机器人创建成功","2021-04-08 11:48:24.933164","last","establish",false,200,1);

        List<Robot> u = new ArrayList<>();
        u.add(f1);
        List<Robot> testUser = robotService.getInvalidRobots(1);
        // 检查结果
        Assert.assertThat(testUser, Matchers.is(u));
        Mockito.verify(robotDao).getInvalidRobots(1);
    }

    @Test
    public void deleteOne() {
        robotService.deleteOne(1);
        Mockito.verify(robotDao).deleteOne(1);
    }

    @Test
    public void addRobot() {
        robotService.addRobot(1,"name","apikey","last","establish",true,200,1);
        Mockito.verify(robotDao).addRobot(1,"name","apikey","last","establish",true,200,1);
    }

    @Test
    public void modifyRobot() {
        robotService.modifyRobot(1,"name",true,"last");
        Mockito.verify(robotDao).modifyRobot(1,"name",true,"last");
    }

    @Test
    public void updateAPISecret() {
        robotService.updateAPISecret(1,"apisecret");
        Mockito.verify(robotDao).updateAPISecret(1,"apisecret");
    }

    @Test
    public void getAPISecretByApikey() {
        String testUser = robotService.getAPISecretByApikey("apikey");
        Assert.assertThat(testUser, Matchers.is("string"));
        Mockito.verify(robotDao).getAPISecretByApikey("apikey");
    }

    @Test
    public void getValidByApikey() {
        Boolean testUser = robotService.getValidByApikey("apikey");
        Assert.assertThat(testUser, Matchers.is(true));
        Mockito.verify(robotDao).getValidByApikey("apikey");
    }

    @Test
    public void getTimesByApikey() {
        Integer testUser = robotService.getTimesByApikey("apikey");
        Assert.assertThat(testUser, Matchers.is(200));
        Mockito.verify(robotDao).getTimesByApikey("apikey");
    }

    @Test
    public void decreaseTimesByApikey() {
        robotService.decreaseTimesByApikey("apikey");
        Mockito.verify(robotDao).decreaseTimesByApikey("apikey");
    }

    @Test
    public void updateLastTimeByApikey() {
        robotService.UpdateLastTimeByApikey("apikey","time");
        Mockito.verify(robotDao).UpdateLastTimeByApikey("apikey","time");
    }

    @Test
    public void getTypeByApikey() {
        Integer testUser = robotService.getTypeByApikey("apikey");
        Assert.assertThat(testUser, Matchers.is(1));
        Mockito.verify(robotDao).getTypeByApikey("apikey");
    }
}