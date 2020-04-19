package com.candramarbun.homecredit.services.impl;

import com.candramarbun.homecredit.dto.UserGroupRequest;
import com.candramarbun.homecredit.dto.UserModuleRequest;
import com.candramarbun.homecredit.dto.UserModuleResponse;
import com.candramarbun.homecredit.dto.UserRequest;
import com.candramarbun.homecredit.entities.Module;
import com.candramarbun.homecredit.entities.User;
import com.candramarbun.homecredit.entities.UserGroup;
import com.candramarbun.homecredit.entities.UserGroupModules;
import com.candramarbun.homecredit.exception.DataNotFoundException;
import com.candramarbun.homecredit.repositories.ModuleRepository;
import com.candramarbun.homecredit.repositories.UserGroupModuleRepository;
import com.candramarbun.homecredit.repositories.UserGroupRepository;
import com.candramarbun.homecredit.repositories.UserRepository;
import com.candramarbun.homecredit.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices {
    private UserRepository userRepository;
    private UserGroupRepository userGroupRepository;
    private ModuleRepository moduleRepository;
    private UserGroupModuleRepository userGroupModuleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserGroupRepository userGroupRepository,
                           ModuleRepository moduleRepository,UserGroupModuleRepository userGroupModuleRepository) {
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
        this.moduleRepository = moduleRepository;
        this.userGroupModuleRepository = userGroupModuleRepository;
    }

    @Override
    public List<UserModuleResponse> findUserModulById(Long userId) {
        List<UserModuleResponse> resp = new ArrayList<>();
        User user = this.userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User Not Found gaess!"));
        try {
            List<UserGroupModules> modules = user.getUserGroup().getModules();
            modules.forEach(module -> {
                resp.add(new UserModuleResponse(module.getModule().getModuleName(),module.getModuleOrder()));
            });
        } catch (Exception ex){
            throw new DataNotFoundException("This User doesn't have any modules gaess!");
        }
        return resp;
    }

    @Override
    public void storeUserModule(Long userId, List<UserModuleRequest> requests) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User Not Found gaess!"));
            requests.forEach(request -> {
                Module module = this.moduleRepository.findById(request.getModuleId()).orElseThrow(() -> new DataNotFoundException("Module not found!"));
                UserGroupModules userGroupModules = this.userGroupModuleRepository.findByModuleIdAndUserGroupId(request.getModuleId(),user.getUserGroup().getId()).orElse( new UserGroupModules());
                userGroupModules.setUserGroup(user.getUserGroup());
                userGroupModules.setModule(module);
                userGroupModules.setModuleOrder(request.getOrderNumber());
                this.userGroupModuleRepository.save(userGroupModules);
            });
        } catch (Exception ex){
            throw new DataNotFoundException("Oops... Something wrong gaess!!");
        }
    }

    @Override
    public void storeUserGroup(UserGroupRequest userGroupRequest) {
        try {
            UserGroup userGroup = new UserGroup();
            userGroup.setGroupName(userGroupRequest.getName());
            userGroupRepository.save(userGroup);
        } catch (Exception ex){
            throw new DataNotFoundException("Oops.. something wrong gaess!"+ex.getMessage());
        }
    }

    @Override
    public void storeUser(UserRequest userRequest) {
        if(this.userRepository.existsByUsername(userRequest.getUsername())){
            throw new DataNotFoundException("This Username already taken bro sis!");
        }
        try {
            UserGroup userGroup = this.userGroupRepository.findById(userRequest.getUserGroupId()).orElseThrow(() -> new DataNotFoundException("User Group not found!"));
            User user = new User();
            user.setUserGroup(userGroup);
            user.setUsername(userRequest.getUsername());
            user.setFullname(userRequest.getFullname());
            this.userRepository.save(user);
        } catch (Exception ex){
            throw new DataNotFoundException("Something wromg gaess !!"+ ex.getMessage());
        }
    }
}
