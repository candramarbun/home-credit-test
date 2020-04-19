package com.candramarbun.homecredit.dto;

public class UserModuleResponse {
    private String moduleName;
    private int moduleOrder;

    public UserModuleResponse(String moduleName, int moduleOrder) {
        this.moduleName = moduleName;
        this.moduleOrder = moduleOrder;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleOrder() {
        return moduleOrder;
    }

    public void setModuleOrder(int moduleOrder) {
        this.moduleOrder = moduleOrder;
    }
}
