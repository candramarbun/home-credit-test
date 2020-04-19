package com.candramarbun.homecredit.repositories;

import com.candramarbun.homecredit.entities.UserGroupModules;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserGroupModuleRepository extends CrudRepository<UserGroupModules,Long> {
    Optional<UserGroupModules> findByModuleIdAndUserGroupId(Long moduleId,Long userGroupId);
}
