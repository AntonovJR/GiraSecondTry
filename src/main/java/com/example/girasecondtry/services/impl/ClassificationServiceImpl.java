package com.example.girasecondtry.services.impl;

import com.example.girasecondtry.models.entities.Classification;
import com.example.girasecondtry.models.entities.ClassificationName;
import com.example.girasecondtry.repositories.ClassificationRepository;
import com.example.girasecondtry.services.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public Classification getClassificationByName(String classification) {

        return classificationRepository.findByClassificationName(ClassificationName.valueOf(classification));
    }
}
