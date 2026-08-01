package com.example.taskmanager.entity;

import com.example.taskmanager.enums.Priority;
import com.example.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")   // allows long descriptions
    private String description;

    @Enumerated(EnumType.STRING)         // stores "HIGH" not 2 — always do this
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    private LocalDate dueDate;           // just a date, no time component

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)           // many tasks belong to one user
    @JoinColumn(name = "user_id", nullable = false)  // foreign key column in TASKS table
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = TaskStatus.TODO;      // sensible default
        if (this.priority == null) this.priority = Priority.MEDIUM;  // sensible default
    }

    @PreUpdate                           // runs automatically before every UPDATE
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}