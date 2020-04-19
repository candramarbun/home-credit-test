package com.candramarbun.homecredit.dto;

import java.util.List;

public class UserGroupRequest {
    private String name;
    private List<Long> modules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getModules() {
        return modules;
    }

    public void setModules(List<Long> modules) {
        this.modules = modules;
    }
}
