package com.example.girasecondtry.repositories;

import com.example.girasecondtry.models.entities.Classification;
import com.example.girasecondtry.models.entities.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    Classification findByClassificationName(ClassificationName classificationName);

}
