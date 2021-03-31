package com.todo.service.tasklist_service;

import com.todo.exception.task.TaskException;
import com.todo.exception.tasklist.*;
import com.todo.model.Task;
import com.todo.model.TaskList;
import com.todo.repository.TaskListRepository;
import com.todo.service.task_service.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;
    private final TaskServiceImpl taskService;

    @Override
    public List<TaskList> getAllTaskList() {
        List<TaskList> taskLists = taskListRepository.findAll();

        if (taskLists.isEmpty()) {
            throw new TaskListException("No task lists");
        }

        return taskLists;
    }

    @Override
    public TaskList findByTitle(String title) {

        if (title == null) {
            throw new TaskListException("No task list title");
        }

        if (title.isEmpty()) {
            throw new TaskListException("Task list title is empty");
        }

        TaskList taskList = taskListRepository.findByTitle(title);

        if (taskList == null) {
            throw new TaskListException("There is no such task list");
        }

        return taskList;
    }

    @Override
    public void addTaskToListByTitle(String titleList, Task task) {
        if (task == null) {
            throw new TaskException("No task");
        }

        TaskList taskList = findByTitle(titleList);
        List<Task> tasks = taskList.getTasks();

        Task taskForDb = tasks.stream().filter(s -> s.getTitle().equals(task.getTitle()))
                .findFirst().orElse(null);

        if (taskForDb != null) {
            taskForDb.setText(task.getText());
        } else {
            taskForDb = task;
        }

        tasks.add(taskForDb);
        taskList.setTasks(tasks);

        taskListRepository.save(taskList);
    }

    @Override
    public void removeTask(String taskTitle) {
        taskService.delete(taskTitle);
    }

    @Override
    public void addTaskList(TaskList taskList) {

        if (taskList == null) {
            throw new TaskListException("No task list");
        }

        taskListRepository.save(taskList);
    }

    @Override
    public void removeTaskList(String title) {
        TaskList taskList = findByTitle(title);

        taskListRepository.delete(taskList);
    }
}
