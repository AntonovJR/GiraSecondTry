package com.example.girasecondtry.web;

import com.example.girasecondtry.CurrentUser;
import com.example.girasecondtry.models.binding.TaskAddBindingModel;
import com.example.girasecondtry.models.binding.TaskViewModel;
import com.example.girasecondtry.models.entities.Progress;
import com.example.girasecondtry.models.entities.Task;
import com.example.girasecondtry.services.ClassificationService;
import com.example.girasecondtry.services.TaskService;
import com.example.girasecondtry.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;
    private final ClassificationService classificationService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public TaskController(TaskService taskService, ModelMapper modelMapper, ClassificationService classificationService, UserService userService, CurrentUser currentUser) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
        this.classificationService = classificationService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public TaskAddBindingModel taskAddBindingModel() {
        return new TaskAddBindingModel();
    }

    @GetMapping("/add")
    public String addTask() {
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@Valid TaskAddBindingModel taskAddBindingModel, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel", bindingResult);

            return "redirect:add";
        }

        Task task = modelMapper.map(taskAddBindingModel, Task.class);
        task.setClassification(classificationService.getClassificationByName(taskAddBindingModel.getClassification()));
        task.setProgress(Progress.OPEN);
        task.setUser(userService.findUserById(currentUser.getId()).orElse(null));
        taskService.addTask(task);
        return "redirect:home";
    }

    @GetMapping("/home")
    public String showTasks(Model model) {
        if (currentUser.getId() == null) {
            return "/index";
        }
        model.addAttribute("tasks", taskService.getAllTasks());
        return "/home";
    }

    @GetMapping("/{id}")
    public String changeProgress(@PathVariable Long id) {
        taskService.updateProgress(id);
        return "redirect:home";
    }
}
