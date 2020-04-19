package com.candramarbun.homecredit.dto;

public class UserModuleRequest {
    private Long moduleId;
    private int orderNumber;

    public UserModuleRequest() {
    }

    public UserModuleRequest(Long moduleId, int orderNumber) {
        this.moduleId = moduleId;
        this.orderNumber = orderNumber;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
