package com.todo.service.task_service;

import com.todo.exception.task.*;
import com.todo.model.Task;
import com.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    //TODO добавить больше бизнесовых исключений, которые будут высирться при null

    private final TaskRepository taskRepository;

    @Override
    public Task findByTitle(String title) {

        if (title == null) {
            throw new TaskException("No task title");
        }

        if (title.isEmpty()) {
            throw new TaskException("Task title is empty");
        }

        Task task = taskRepository.findByTitle(title);

        if (task == null) {
            throw new TaskException("There is no such task");
        }

        return task;
    }

    @Override
    public void save(Task task) {
        if (task == null) {
            throw new TaskException("No task");
        }

        if (task.getTitle() == null) {
            throw new TaskException("No task title");
        }

        if (task.getTitle().isEmpty()) {
            throw new TaskException("Task title is empty");
        }

        Task taskInDb = taskRepository.findByTitle(task.getTitle());

        if (taskInDb != null) {
            throw new TaskException("There is task with this title");
        }

        taskRepository.save(task);
    }

    @Override
    public void update(Task task) {
        Task taskFromDb = findByTitle(task.getTitle());

        taskFromDb.setText(task.getText());

        save(taskFromDb);
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            throw new TaskException("No tasks"); // тут высираешь исключения. чтобы эти исключения нормально отдавались фронту разберись с ExceptionHandler
        }

        return tasks;
    }

    @Override
    public void delete(String title) {
        Task task = findByTitle(title);

        taskRepository.delete(task);
    }
}
