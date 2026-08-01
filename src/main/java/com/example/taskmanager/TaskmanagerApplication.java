package com.example.taskmanager;

import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskmanagerApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(TaskmanagerApplication.class, args);

        UserRepository userRepo = context.getBean(UserRepository.class);
        TaskRepository taskRepo = context.getBean(TaskRepository.class);

        // Test: does findByEmail work?
        userRepo.findByEmail("alice@example.com")
                .ifPresent(u -> System.out.println("Found user: " + u.getName()));

        // Test: does findByUserId work?
        var tasks = taskRepo.findByUserId(1L);
        System.out.println("Alice has " + tasks.size() + " tasks");

        // Test: filter by status
        var inProgress = taskRepo.findByUserIdAndStatus(1L, TaskStatus.IN_PROGRESS);
        System.out.println("In progress: " + inProgress.size());
    }
}