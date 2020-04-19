package com.candramarbun.homecredit.services;

import com.candramarbun.homecredit.dto.UserGroupRequest;
import com.candramarbun.homecredit.dto.UserModuleRequest;
import com.candramarbun.homecredit.dto.UserModuleResponse;
import com.candramarbun.homecredit.dto.UserRequest;

import java.util.List;

public interface UserServices {
    List<UserModuleResponse> findUserModulById(Long userId);
    void storeUserModule(Long userId,List<UserModuleRequest> requests);
    void storeUserGroup(UserGroupRequest userGroupRequest);
    void storeUser(UserRequest userRequest);
}
