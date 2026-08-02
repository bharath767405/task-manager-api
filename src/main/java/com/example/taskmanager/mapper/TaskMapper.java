package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.request.CreateTaskRequest;
import com.example.taskmanager.dto.response.TaskResponse;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    // Convert a CreateTaskRequest DTO + User into a Task entity
    public Task toEntity(CreateTaskRequest request, User user) {
        return Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .user(user)
                .build();
        // status and createdAt are set by @PrePersist — no need to set here
    }

    // Convert a Task entity into a TaskResponse DTO
    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .ownerName(task.getUser().getName())    // pull just the name from User
                .ownerEmail(task.getUser().getEmail())  // pull just the email from User
                .build();
    }
}