package com.candramarbun.homecredit.repositories;

import com.candramarbun.homecredit.entities.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserGroupRepository extends CrudRepository<UserGroup,Long> {
    Optional<UserGroup> findByGroupName(String groupName);
}
