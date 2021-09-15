package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.MessageDao;
import com.example.dotdot.entity.Message;
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

public class MessageServiceImplTest {
    // 要mock的对象
    @Mock
    private MessageDao messageDao;

    // 被mock的对象的使用者
    @InjectMocks
    private MessageServiceImpl messageService;


    @Before
    public void setUp() throws Exception{
        // 对所有注解了@Mock的对象进行模拟
        MockitoAnnotations.initMocks(this);
        Message f1 = new Message(1,1,"机器人创建成功","测试机1号机器人创建成功","2021-04-08 11:48:24.933164",false);

        List<Message> u = new ArrayList<>();
        u.add(f1);
        Mockito.when(messageDao.getMessages(1)).thenReturn(u);

    }

    @Test
    public void getMessages() {
        Message f1 = new Message(1,1,"机器人创建成功","测试机1号机器人创建成功","2021-04-08 11:48:24.933164",false);

        List<Message> u = new ArrayList<>();
        u.add(f1);
        List<Message> tests = messageService.getMessages(1);
        // 检查结果
        Assert.assertThat(tests, Matchers.is(u));
        // 验证调用上面的service 方法后是否 demoDao.getInfo(1) 调用过
        Mockito.verify(messageDao).getMessages(1);
    }

    @Test
    public void changeType(){
        messageService.changeType(2);
        Mockito.verify(messageDao).changeType(2);
    }
}