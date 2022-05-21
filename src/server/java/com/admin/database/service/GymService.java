package com.admin.database.service;

import com.admin.database.entity.Gym;
import com.admin.database.repository.GymRepository;
import com.admin.database.repository.PassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {
    private final GymRepository gymRepository;
    private final PassRepository passRepository;

    @Autowired
    public GymService(GymRepository gymRepository, PassRepository passRepository) {
        this.gymRepository = gymRepository;
        this.passRepository = passRepository;
    }

    public List<Gym> getAll() {
        return gymRepository.findAll();
    }

    public Gym findByName(String name) {
        return gymRepository.findByName(name);
    }

    public void save(Gym gym) {
        gymRepository.save(gym);
    }

    public List<Gym> filter(String name) {
        return gymRepository.findAllByName(name);
    }

    public void delete(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);
        if (gym == null)
            return;
        passRepository.deleteAllByGym(gym);
        gymRepository.deleteById(id);
    }

    public Gym findById(Long id) {
        return gymRepository.findById(id).orElse(null);
    }
}
