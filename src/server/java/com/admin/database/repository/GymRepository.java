package com.admin.database.repository;

import com.admin.database.entity.Gym;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym findByName(String name);

    List<Gym> findAllByName(String name);
}
