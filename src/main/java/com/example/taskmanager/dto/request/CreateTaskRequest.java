package com.example.taskmanager.dto.request;

import com.example.taskmanager.enums.Priority;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data                // Lombok: generates getters, setters, equals, hashCode, toString
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;    // optional field — no @NotBlank

    private Priority priority;     // optional — defaults to MEDIUM in @PrePersist

    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;     // optional
}