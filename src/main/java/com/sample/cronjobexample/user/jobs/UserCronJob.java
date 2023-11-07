package com.sample.cronjobexample.user.jobs;

import com.sample.cronjobexample.user.UserService;
import com.sample.cronjobexample.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnNotWebApplication
@Slf4j
public class UserCronJob implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public UserCronJob(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        log.info("Cronjob started to log user names");
        userService.createTestUsers(5);
        List<User> users = userService.getUserEntities();
        users.forEach(user -> log.info(user.getName()));
        log.info("Cronjob completed to log user names");
    }
}
