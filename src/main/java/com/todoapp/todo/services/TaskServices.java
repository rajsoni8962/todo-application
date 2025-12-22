package com.todoapp.todo.services;

import com.todoapp.todo.models.Task;
import com.todoapp.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServices {

    private final TaskRepository taskRepository;

    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) throws IllegalAccessException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessException("Invalid Task Id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);

    }
}
