package com.todo.controller;

import com.todo.model.Task;
import com.todo.model.TaskList;
import com.todo.service.tasklist_service.TaskListService;
import com.todo.service.task_service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskService taskService;
    private final TaskListService taskListService;

    @GetMapping("/getAll")
    public List<TaskList> getAllTaskList() {
        return taskListService.getAllTaskList();
    }

    @PostMapping("/{title}")
    public void addTaskToList(@PathVariable(name = "title") String title, @RequestBody Task task){
        taskListService.addTaskToListByTitle(title, task);
    }

    @PostMapping("/create/{title}")
    public void createTaskList(@PathVariable(name = "title") String title) {
        TaskList taskList = new TaskList();
        taskList.setTitle(title);

        taskListService.addTaskList(taskList);
    }

}