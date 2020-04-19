package com.candramarbun.homecredit.repositories;

import com.candramarbun.homecredit.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    boolean existsByUsername(String username);
}
