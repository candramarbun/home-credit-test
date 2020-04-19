package com.candramarbun.homecredit.data;

import com.candramarbun.homecredit.entities.Module;
import com.candramarbun.homecredit.entities.User;
import com.candramarbun.homecredit.entities.UserGroup;
import com.candramarbun.homecredit.entities.UserGroupModules;
import com.candramarbun.homecredit.exception.DataNotFoundException;
import com.candramarbun.homecredit.repositories.ModuleRepository;
import com.candramarbun.homecredit.repositories.UserGroupRepository;
import com.candramarbun.homecredit.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SeedData implements CommandLineRunner {
    private static Logger LOGGER = LoggerFactory.getLogger(SeedData.class);

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<String> masterModule = Arrays.asList("Promo","Category","FlashSale","History","News");
        List<String> masterGroup = Arrays.asList("UserA","UserB","UserC");
        List<String> moduleUserA = Arrays.asList("PromoCard","CategoryCard","FlashSaleCard","HistoryCard","NewsCard");
        List<String> moduleUserB = Arrays.asList("PromoCard","NewsCard","FlashSaleCard","CategoryCard","HistoryCard");
        List<String> moduleUserC = Arrays.asList("PromoCard","FlashSaleCard","CategoryCard","NewsCard","HistoryCard");

        try {
//            seed master module
            masterModule.forEach(s -> {
                LOGGER.info("seeding {}",s);
                Module module = new Module();
                module.setModuleName(s+"Card");
                module.setTitle(s);
                moduleRepository.save(module);
            });

//            seed master userGrup
            masterGroup.forEach(s -> {
                LOGGER.info("seeding {}",s);
                UserGroup userGroup = new UserGroup();
                userGroup.setGroupName(s);
                userGroupRepository.save(userGroup);
            });

            //seed groupA module
            List<UserGroupModules> modulesA = new ArrayList<>();
            UserGroup userA = userGroupRepository.findByGroupName("UserA").orElseThrow(() -> new DataNotFoundException("UserA not Found!"));

            AtomicInteger orderA = new AtomicInteger(1);
            moduleUserA.forEach(s-> {
                LOGGER.info("seeding {} ordr {}",s,orderA.get());
                Module module = moduleRepository.findByModuleName(s).orElseThrow(() -> new DataNotFoundException(s+ " not Found!"));
                UserGroupModules userGroupModules = new UserGroupModules();
                userGroupModules.setModule(module);
                userGroupModules.setUserGroup(userA);
                userGroupModules.setModuleOrder(orderA.get());
                modulesA.add(userGroupModules);
                orderA.getAndIncrement();
            });
            userA.setModules(modulesA);
            userGroupRepository.save(userA);

            //seed groupA module
            List<UserGroupModules> modulesB = new ArrayList<>();
            UserGroup userB = userGroupRepository.findByGroupName("UserB").orElseThrow(() -> new DataNotFoundException("UserA not Found!"));

            AtomicInteger orderB = new AtomicInteger(1);
            moduleUserB.forEach(s-> {
                LOGGER.info("seeding {} ordr {}",s,orderB.get());
                Module module = moduleRepository.findByModuleName(s).orElseThrow(() -> new DataNotFoundException(s+ " not Found!"));
                UserGroupModules userGroupModules = new UserGroupModules();
                userGroupModules.setModule(module);
                userGroupModules.setUserGroup(userB);
                userGroupModules.setModuleOrder(orderB.get());
                modulesB.add(userGroupModules);
                orderB.getAndIncrement();
            });
            userB.setModules(modulesB);
            userGroupRepository.save(userB);

            //seed groupA module
            List<UserGroupModules> modulesC = new ArrayList<>();
            UserGroup userC = userGroupRepository.findByGroupName("UserC").orElseThrow(() -> new DataNotFoundException("UserA not Found!"));

            AtomicInteger orderC = new AtomicInteger(1);
            moduleUserC.forEach(s-> {
                LOGGER.info("seeding {} ordr {}",s,orderA.get());
                Module module = moduleRepository.findByModuleName(s).orElseThrow(() -> new DataNotFoundException(s+ " not Found!"));
                UserGroupModules userGroupModules = new UserGroupModules();
                userGroupModules.setModule(module);
                userGroupModules.setUserGroup(userC);
                userGroupModules.setModuleOrder(orderC.get());
                modulesC.add(userGroupModules);
                orderC.getAndIncrement();
            });
            userC.setModules(modulesC);
            userGroupRepository.save(userC);

//            seed user data
            List<User> userData = Arrays.asList(new User("Candra Marbun","candramarbun13@gmail.com",userA),
                    new User("Candra Marbun2","candramarbun2",userB),
                    new User("Candra Marbun3","candramarbun3",userC));

            userData.forEach(user -> {
                LOGGER.info("seed user data {}",user.getUsername());
                userRepository.save(user);
            });

        }catch (Exception ex){
            throw ex;
        }
    }
}
