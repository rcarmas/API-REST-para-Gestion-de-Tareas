package com.example.GestiondeTareas.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskAplication> getTasks() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Object> createTask(TaskAplication task) {
        Optional<TaskAplication> existingTask = taskRepository.findTaskAplicationByName(task.getName());
        HashMap<String, Object> datos = new HashMap<>();

        if (existingTask.isPresent()) {
            datos.put("data", false);
            datos.put("message", "El usuario ya existe");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        } else {
            TaskAplication savedTask = taskRepository.save(task);
            datos.put("data", savedTask);
            datos.put("message", "Usuario creado");

            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Object> updateTask(TaskAplication task) {
        Optional<TaskAplication> existingTask = taskRepository.findById(task.getId());
        HashMap<String, Object> datos = new HashMap<>();

        if (existingTask.isPresent()) {
            TaskAplication savedTask = taskRepository.save(task);
            datos.put("data", savedTask);
            datos.put("message", "Usuario actualizado");

            return new ResponseEntity<>(datos, HttpStatus.OK);
        } else {
            datos.put("data", false);
            datos.put("message", "El usuario con ID " + task.getId() + " no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        HashMap<String, Object> datos = new HashMap<>();

        if (!exists) {
            datos.put("data", false);
            datos.put("message", "El usuario con id " + taskId + " no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }

        taskRepository.deleteById(taskId);
        datos.put("data", true);
        datos.put("message", "El usuario con id " + taskId + " ha sido eliminado");
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }
}