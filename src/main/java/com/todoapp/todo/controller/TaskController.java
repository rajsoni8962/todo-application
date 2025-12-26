package com.todoapp.todo.controller;

import java.util.List;
import org.springframework.ui.Model;
import com.todoapp.todo.models.Task;
import com.todoapp.todo.services.TaskServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping
    public String getTasks(Model model){
        List<Task> tasks = taskServices.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "index.html";
    }

    @PostMapping
    public String createTask(@RequestParam String title){
        taskServices.createTask(title);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteTasks(@PathVariable Long id){
        taskServices.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id) throws IllegalAccessException {
        taskServices.toggleTask(id);
        return "redirect:/";
    }
}
