package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unused")
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        com.example.taskmanager.entity.User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(TaskStatus.TODO);
        task.setPriority(taskDto.getPriority());
        task.setDueDate(taskDto.getDueDate());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(user);

        taskRepository.save(task);

        return ResponseEntity.ok("Task created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) LocalDateTime dueDate,
            @RequestParam(required = false) String search
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        com.example.taskmanager.entity.User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        List<Task> tasks;

        if (status != null) {
            tasks = taskRepository.findByUserIdAndStatus(user.getId(), status);
        } else if (priority != null) {
            tasks = taskRepository.findByUserIdAndPriority(user.getId(), priority);
        } else if (dueDate != null) {
            tasks = taskRepository.findByUserIdAndDueDateBefore(user.getId(), dueDate);
        } else if (search != null) {
            tasks = taskRepository.findByUserIdAndTitleContainingOrDescriptionContaining(user.getId(), search, search);
        } else {
            tasks = taskRepository.findAll();
        }

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setPriority(taskDto.getPriority());
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);

        return ResponseEntity.ok("Task updated successfully");
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);

        return ResponseEntity.ok("Task deleted successfully");
    }
}
