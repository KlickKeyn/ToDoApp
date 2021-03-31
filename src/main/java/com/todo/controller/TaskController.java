package com.todo.controller;

import com.todo.model.Task;
import com.todo.service.task_service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/getAll")
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping
    public Task getTask(@RequestParam("title") String title) {
        return taskService.findByTitle(title);
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        taskService.save(task);

        return task;
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task) {
        taskService.update(task);

        return task;
    }

    @DeleteMapping
    public void delete(@RequestParam("title") String title) {
        taskService.delete(title);
    }

}