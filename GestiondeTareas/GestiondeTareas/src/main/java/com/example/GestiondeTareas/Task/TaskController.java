package com.example.GestiondeTareas.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskAplication> getAllTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public ResponseEntity<Object> crearTask(@RequestBody TaskAplication task) {
        return taskService.createTask(task);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarTask(@RequestBody TaskAplication task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public ResponseEntity<Object> eliminarTask(@PathVariable("taskId") Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
