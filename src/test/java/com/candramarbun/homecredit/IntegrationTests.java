package com.candramarbun.homecredit;

import com.candramarbun.homecredit.controllers.UserController;
import com.candramarbun.homecredit.entities.User;
import com.candramarbun.homecredit.entities.UserGroup;
import com.candramarbun.homecredit.repositories.ModuleRepository;
import com.candramarbun.homecredit.repositories.UserGroupModuleRepository;
import com.candramarbun.homecredit.repositories.UserGroupRepository;
import com.candramarbun.homecredit.repositories.UserRepository;
import com.candramarbun.homecredit.services.UserServices;
import com.candramarbun.homecredit.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class IntegrationTests {

    private static Long USER_EXIST_ID = 1L;
    private static Long USER_UNEXIST_ID = 999L;
    private static UserGroup USER_GROUP = new UserGroup();
    private static User USER = new User("CandraTest","candratest",USER_GROUP);
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    private UserRepository userRepository;
    private ModuleRepository moduleRepository;
    private UserGroupModuleRepository userGroupModuleRepository;

    private UserController userController;
    private UserServices userServices;

    @Before
    public void setup() {
        userRepository = userRepository();
        moduleRepository = moduleRepository();
        userGroupModuleRepository = userGroupModuleRepository();
        userServices = userServices(userGroupModuleRepository,moduleRepository,userRepository);
        userController = userController(userServices);
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    //    mock config
    private UserRepository userRepository() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        Mockito.when(mock.findById(USER_EXIST_ID)).thenReturn(Optional.of(USER));
        Mockito.when(mock.findById(USER_UNEXIST_ID)).thenReturn(null);
        return mock;
    }

    private ModuleRepository moduleRepository() {
        ModuleRepository mock = Mockito.mock(ModuleRepository.class);
        return mock;
    }

    private UserGroupModuleRepository userGroupModuleRepository() {
        UserGroupModuleRepository mock = Mockito.mock(UserGroupModuleRepository.class);
        return mock;
    }

    private UserServices userServices(UserGroupModuleRepository userGroupModuleRepository,
                                      ModuleRepository moduleRepository, UserRepository userRepository) {
        UserServices mock = new UserServiceImpl(userRepository,moduleRepository,userGroupModuleRepository);
        return mock;
    }

    private UserController userController(UserServices userServices) {
        return new UserController(userServices);
    }

    //TEST
    @Test
    public void testSuccessGetModuleByUserId(){
        try {
            this.mvc.perform(get("/api/"+USER_EXIST_ID+"/modules").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("code").value(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataNotFoundGetModuleByUserId(){
        try {
            this.mvc.perform(get("/api/"+USER_UNEXIST_ID+"/modules").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("code").value(404));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
