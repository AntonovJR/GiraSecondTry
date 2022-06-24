package com.example.girasecondtry.repositories;

import com.example.girasecondtry.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);


}
