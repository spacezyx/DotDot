package com.example.dotdot.daoimpl;

import com.example.dotdot.entity.User;
import com.example.dotdot.repository.UserRepository;
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

public class UserDaoImplTest {

    // 要mock的对象
    @Mock
    private UserRepository userDao;

    // 被mock的对象的使用者
    @InjectMocks
    private UserDaoImpl userService;


    @Before
    public void setUp() throws Exception{
        // 对所有注解了@Mock的对象进行模拟
        MockitoAnnotations.initMocks(this);

        User user = new User(1,"username","password","email",1,true,"image",2000);
        List<User> u = new ArrayList<>();
        u.add(user);
        Mockito.when(userDao.getUsers()).thenReturn(u);
        Mockito.when(userDao.checkUserByUsername("username","password")).thenReturn(user);
        Mockito.when(userDao.checkUserByEmail("email","password")).thenReturn(user);
        Mockito.when(userDao.alreadyHasName("username")).thenReturn(user);
        Mockito.when(userDao.alreadyHasEmail("username")).thenReturn(user);
        Mockito.when(userDao.getUserIdByUsername("username")).thenReturn(1);

    }


    @Test
    public void getUsers() {
        User user = new User(1,"username","password","email",1,true,"image",2000);
        List<User> u = new ArrayList<>();
        u.add(user);
        List<User> testUser = userService.getUsers();
        Assert.assertThat(testUser, Matchers.is(u));
        Mockito.verify(userDao).getUsers();
    }

    @Test
    public void register() {
        userService.Register("username","password",1,"email");
        Mockito.verify(userDao).userRegister("username","password",1,"email");
    }


    @Test
    public void checkUserByUsername() {
        User user = new User(1,"username","password","email",1,true,"image",2000);
        User testUser = userService.checkUserByUsername("username","password");
        Assert.assertThat(testUser, Matchers.is(user));
        Mockito.verify(userDao).checkUserByUsername("username","password");
    }

    @Test
    public void checkUserByEmail() {
        User user = new User(1,"username","password","email",1,true,"image",2000);
        User testUser = userService.checkUserByEmail("email","password");
        Assert.assertThat(testUser, Matchers.is(user));
        Mockito.verify(userDao).checkUserByEmail("email","password");
    }

    @Test
    public void modifyImg() {
        userService.modifyImg(1,"image");
        Mockito.verify(userDao).modifyImg(1,"image");
    }

    @Test
    public void modifyPassword() {
        User user = new User(1,"username","password","email",1,true,"image",2000);
        User testUser = userService.modifyPassword("username","password");
        Assert.assertThat(testUser, Matchers.is(user));
        Mockito.verify(userDao).modifyPassword("username","password");
    }

    @Test
    public void alreadyHasName() {
        User user = new User(1,"username","password","email",1,true,"image",2000);
        User testUser = userService.alreadyHasName("username");
        Assert.assertThat(testUser, Matchers.is(user));
        Mockito.verify(userDao).alreadyHasName("username");
    }

    @Test
    public void alreadyHasEmail() {
        User user = new User(1,"username","password","email",1,true,"image",2000);
        User testUser = userService.alreadyHasEmail("username");
        Assert.assertThat(testUser, Matchers.is(user));
        Mockito.verify(userDao).alreadyHasEmail("username");
    }

    @Test
    public void getUserIdByUsername() {
        Integer testUser = userService.getUserIdByUsername("username");
        Assert.assertThat(testUser, Matchers.is(1));
        Mockito.verify(userDao).getUserIdByUsername("username");
    }
}