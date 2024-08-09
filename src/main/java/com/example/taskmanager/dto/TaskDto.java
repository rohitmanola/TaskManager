package com.example.taskmanager.dto;

import com.example.taskmanager.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private String title;
    private String description;
    private TaskStatus status;
    private String priority;
    private LocalDateTime dueDate;
}
