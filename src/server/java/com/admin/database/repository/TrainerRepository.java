package com.admin.database.repository;

import com.admin.database.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByName(String name);

    List<Trainer> findAllByName(String name);
}
