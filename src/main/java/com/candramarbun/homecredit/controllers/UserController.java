package com.candramarbun.homecredit.controllers;

import com.candramarbun.homecredit.dto.GeneralResponse;
import com.candramarbun.homecredit.dto.UserModuleRequest;
import com.candramarbun.homecredit.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{userId}/modules")
    public GeneralResponse getModulesByUser(@PathVariable Long userId){
        return GeneralResponse.response(this.userServices.findUserModulById(userId));
    }

    @PutMapping("/{userId}/update-module")
    public GeneralResponse updateModule(@PathVariable Long userId,@RequestBody List<UserModuleRequest> requests){
        try {
            this.userServices.storeUserModule(userId,requests);
        } catch (Exception ex){
            return GeneralResponse.dialog(400,ex.getMessage());
        }
        return GeneralResponse.dialog(200,"success");
    }
}
