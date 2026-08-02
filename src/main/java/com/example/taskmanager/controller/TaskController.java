package com.example.taskmanager.controller;

import com.example.taskmanager.dto.request.CreateTaskRequest;
import com.example.taskmanager.dto.request.UpdateStatusRequest;
import com.example.taskmanager.dto.request.UpdateTaskRequest;
import com.example.taskmanager.dto.response.TaskResponse;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // POST /api/tasks?userId=1
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @RequestParam Long userId,
            @Valid @RequestBody CreateTaskRequest request) {

        TaskResponse response = taskService.createTask(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);  // 201
    }

    // GET /api/tasks?userId=1
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @RequestParam Long userId) {

        return ResponseEntity.ok(taskService.getAllTasksForUser(userId));  // 200
    }

    // GET /api/tasks/1?userId=1
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long id,
            @RequestParam Long userId) {

        return ResponseEntity.ok(taskService.getTaskById(id, userId));    // 200
    }

    // PUT /api/tasks/1?userId=1
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody UpdateTaskRequest request) {

        return ResponseEntity.ok(taskService.updateTask(id, userId, request));  // 200
    }

    // PATCH /api/tasks/1/status?userId=1
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody UpdateStatusRequest request) {

        return ResponseEntity.ok(taskService.updateStatus(id, userId, request));  // 200
    }

    // DELETE /api/tasks/1?userId=1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            @RequestParam Long userId) {

        taskService.deleteTask(id, userId);
        return ResponseEntity.noContent().build();   // 204 — success but no body
    }
}