package com.candramarbun.homecredit.repositories;

import com.candramarbun.homecredit.entities.Module;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModuleRepository extends CrudRepository<Module,Long> {
    Optional<Module> findByModuleName(String moduleName);
}
