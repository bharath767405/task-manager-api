package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.Priority;
import com.example.taskmanager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // All tasks belonging to a specific user
    List<Task> findByUserId(Long userId);

    // Tasks for a user filtered by status
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);

    // Tasks for a user filtered by priority
    List<Task> findByUserIdAndPriority(Long userId, Priority priority);

    // Find one task by id AND verify it belongs to this user (security check)
    Optional<Task> findByIdAndUserId(Long id, Long userId);

    // Count how many tasks a user has with a given status
    long countByUserIdAndStatus(Long userId, TaskStatus status);
}