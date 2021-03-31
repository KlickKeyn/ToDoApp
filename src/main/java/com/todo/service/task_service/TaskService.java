package com.todo.service.task_service;

import com.todo.model.Task;

import java.util.List;

public interface TaskService {

    Task findByTitle(String title);

    void save(Task task);

    void update(Task task);

    List<Task> getAll();

    void delete(String title);
}
