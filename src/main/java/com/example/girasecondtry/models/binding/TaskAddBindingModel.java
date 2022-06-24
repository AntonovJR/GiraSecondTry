package com.example.girasecondtry.models.binding;

import javax.validation.constraints.NotBlank;

public class TaskAddBindingModel {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String dueDate;
    @NotBlank
    private String classification;

    public TaskAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
