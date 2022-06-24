package com.example.girasecondtry.services;

import com.example.girasecondtry.models.entities.Classification;

public interface ClassificationService {
    Classification getClassificationByName(String classification);
}
