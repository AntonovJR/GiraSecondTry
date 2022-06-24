package com.example.girasecondtry.init;

import com.example.girasecondtry.models.entities.Classification;
import com.example.girasecondtry.models.entities.ClassificationName;
import com.example.girasecondtry.models.entities.User;
import com.example.girasecondtry.repositories.ClassificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final ClassificationRepository classificationRepository;

    public DatabaseInit(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeClassifications();

    }


    private void initializeClassifications() {
        if (classificationRepository.count() == 0) {
            Arrays.stream(ClassificationName.values()).forEach(nameEnum -> {
                Classification classification = new Classification();
                classification.setClassificationName(nameEnum);
                classification.setDescription(nameEnum + " Loooong looooooong description");
                classificationRepository.save(classification);
            });
        }

    }
}
