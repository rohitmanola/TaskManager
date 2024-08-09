package com.example.taskmanager.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "tasks")


public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}
