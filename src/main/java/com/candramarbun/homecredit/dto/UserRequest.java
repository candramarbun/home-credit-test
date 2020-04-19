package com.candramarbun.homecredit.dto;

public class UserRequest {
    private String fullname;
    private String username;
    private Long userGroupId;

    public UserRequest() {
    }

    public UserRequest(String fullname, String username, Long userGroupId) {
        this.fullname = fullname;
        this.username = username;
        this.userGroupId = userGroupId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }
}
