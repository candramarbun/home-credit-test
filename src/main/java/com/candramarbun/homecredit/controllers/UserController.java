package com.candramarbun.homecredit.controllers;

import com.candramarbun.homecredit.dto.GeneralResponse;
import com.candramarbun.homecredit.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{userId}/modules")
    public GeneralResponse getModulesByUser(@RequestParam Long userId){
        return GeneralResponse.response(this.userServices.findUserModulById(userId));
    }
}
