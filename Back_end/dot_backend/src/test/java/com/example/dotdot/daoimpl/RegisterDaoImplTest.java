package com.example.dotdot.daoimpl;

import com.example.dotdot.entity.Register;
import com.example.dotdot.repository.RegisterRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RegisterDaoImplTest {
    // 要mock的对象
    @Mock
    private RegisterRepository registerDao;

    // 被mock的对象的使用者
    @InjectMocks
    private RegisterDaoImpl registerService;


    @Before
    public void setUp() throws Exception{
        // 对所有注解了@Mock的对象进行模拟
        MockitoAnnotations.initMocks(this);
        Register u = new Register(1,"username","checkcode","timestamp");
        Mockito.when(registerDao.checkRegister("username","checkcode")).thenReturn(u);
    }

    @Test
    public void checkRegister() {
        Register tests = registerService.checkRegister("username","checkcode");
        Register u = new Register(1,"username","checkcode","timestamp");
        Assert.assertThat(tests, Matchers.is(u));
        // 验证调用上面的service 方法后是否 demoDao.getInfo(1) 调用过
        Mockito.verify(registerDao).checkRegister("username","checkcode");
    }

    @Test
    public void addRegister() {
        registerService.addRegister("username","checkcode","timestamp");
        Mockito.verify(registerDao).addRegister("username","checkcode","timestamp");
    }

    @Test
    public void deleteRegister() {
        registerService.deleteRegister("username");
        Mockito.verify(registerDao).deleteRegister("username");
    }
}