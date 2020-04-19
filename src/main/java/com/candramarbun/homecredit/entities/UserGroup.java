package com.candramarbun.homecredit.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    @OneToMany(mappedBy = "userGroup",cascade = CascadeType.ALL)
    private List<UserGroupModules> modules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<UserGroupModules> getModules() {
        return modules;
    }

    public void setModules(List<UserGroupModules> modules) {
        this.modules = modules;
    }
}
