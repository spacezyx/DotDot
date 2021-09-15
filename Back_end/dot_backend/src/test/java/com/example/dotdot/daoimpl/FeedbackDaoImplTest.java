package com.example.dotdot.daoimpl;


import com.example.dotdot.entity.Feedback;
import com.example.dotdot.repository.FeedbackRepository;
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

public class FeedbackDaoImplTest {
    // 要mock的对象
    @Mock
    private FeedbackRepository feedbackRepository;

    // 被mock的对象的使用者
    @InjectMocks
    private FeedbackDaoImpl feedbackDao;

    @Before
    public void setUp() throws Exception{
        // 对所有注解了@Mock的对象进行模拟
        MockitoAnnotations.initMocks(this);
        Feedback f1 = new Feedback(1,1,"用户反馈可以返回处理结果","2021-04-08 11:48:24.933164",false);
        Feedback f2 = new Feedback(2,1,"用户帮助可以明晰一些","2021-04-11 11:48:24.933164",false);

        List<Feedback> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);
        Mockito.when(feedbackRepository.getFeedbacks(1)).thenReturn(u);

    }

    @Test
    public void getFeedbacks() {
        Feedback f1 = new Feedback(1,1,"用户反馈可以返回处理结果","2021-04-08 11:48:24.933164",false);
        Feedback f2 = new Feedback(2,1,"用户帮助可以明晰一些","2021-04-11 11:48:24.933164",false);

        List<Feedback> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);
        List<Feedback> tests = feedbackDao.getFeedbacks(1);
        // 检查结果
        Assert.assertThat(tests, Matchers.is(u));
        // 验证调用上面的service 方法后是否 demoDao.getInfo(1) 调用过
        Mockito.verify(feedbackRepository).getFeedbacks(1);
    }

    @Test
    public void deleteOne() {
        feedbackDao.deleteOne(2);
        Mockito.verify(feedbackRepository).deleteOne(2);
    }

    @Test
    public void addFeedback() {
        feedbackDao.addFeedback(1,"feedback","time");
        Mockito.verify(feedbackRepository).addFeedback(1,"feedback","time");
    }

    @Test
    public void changeType() {
        feedbackDao.changeType(2);
        Mockito.verify(feedbackRepository).changeType(2);
    }
}