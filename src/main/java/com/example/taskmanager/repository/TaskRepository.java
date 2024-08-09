package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);
    List<Task> findByUserIdAndPriority(Long userId, String priority);
    List<Task> findByUserIdAndDueDateBefore(Long userId, LocalDateTime dueDate);
    List<Task> findByUserIdAndTitleContainingOrDescriptionContaining(Long userId, String title, String description);
}
