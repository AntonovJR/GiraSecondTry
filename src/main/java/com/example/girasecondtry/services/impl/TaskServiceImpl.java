package com.example.girasecondtry.services.impl;

import com.example.girasecondtry.models.binding.TaskViewModel;
import com.example.girasecondtry.models.entities.Progress;
import com.example.girasecondtry.models.entities.Task;
import com.example.girasecondtry.repositories.TaskRepository;
import com.example.girasecondtry.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addTask(Task taskToAdd) {
        taskRepository.save(taskToAdd);
    }

    @Override
    public List<TaskViewModel> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskViewModel> taskViewModels = new ArrayList<>();
        for (Task task : taskList) {
            TaskViewModel mappedViewModel = modelMapper.map(task, TaskViewModel.class);
            mappedViewModel.setClassificationName(task.getClassification().getClassificationName().name());
            mappedViewModel.setAssigned(task.getUser().getUsername());
            taskViewModels.add(mappedViewModel);

        }
        return taskViewModels;
    }

    @Override
    @Modifying
    public void updateProgress(Long id) {
        Task byId = taskRepository.findById(id).orElse(null);
        Progress progress = null;

        if (byId.getProgress().equals(Progress.COMPLETED)) {
            taskRepository.deleteById(id);
        } else {

            switch (byId.getProgress().name()) {
                case "OPEN" -> progress = Progress.valueOf("IN_PROGRESS");
                case "IN_PROGRESS" -> progress = Progress.valueOf("COMPLETED");
            }
            byId.setProgress(progress);
            taskRepository.save(byId);
        }

    }

}
