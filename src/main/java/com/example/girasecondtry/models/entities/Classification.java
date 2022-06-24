package com.example.girasecondtry.models.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "classifications")
public class Classification extends BaseEntity{

    @Column(name = "classification_name", unique=true)
    @Enumerated(EnumType.STRING)
    private ClassificationName classificationName;


    @Column(columnDefinition = "TEXT")
    private String description;

    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
