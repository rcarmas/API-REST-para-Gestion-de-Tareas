package com.example.GestiondeTareas.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskAplication, Long> {

    Optional<TaskAplication> findTaskAplicationByName(String name);

}

