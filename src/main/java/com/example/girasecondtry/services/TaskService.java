package com.example.girasecondtry.services;

import com.example.girasecondtry.models.binding.TaskViewModel;
import com.example.girasecondtry.models.entities.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task taskToAdd);

    List<TaskViewModel> getAllTasks();

   void updateProgress(Long id);

}
