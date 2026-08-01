package com.example.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")               // table name is "users" not "user"
@Getter
@Setter                      // Lombok: generates getters and setters
@NoArgsConstructor                   // Lombok: generates empty constructor (required by JPA)
@AllArgsConstructor                  // Lombok: generates constructor with all fields
@Builder                             // Lombok: lets you use User.builder().name("x").build()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-increment: 1, 2, 3...
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)   // no two users share an email
    private String email;

    @Column(nullable = false)
    private String password;                   // will be stored as BCrypt hash, never plain text

    @Column(updatable = false)                 // set once at creation, never changed
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;                  // one user has many tasks

    @PrePersist                                // runs automatically just before saving to DB
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}