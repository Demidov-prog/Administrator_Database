package com.admin.database.service;

import com.admin.database.entity.Trainer;
import com.admin.database.repository.ClientRepository;
import com.admin.database.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, ClientRepository clientRepository) {
        this.trainerRepository = trainerRepository;
        this.clientRepository = clientRepository;
    }

    public List<Trainer> getAll() {
        return trainerRepository.findAll();
    }

    public Trainer findByName(String name) {
        return trainerRepository.findByName(name);
    }

    public void save(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public List<Trainer> filter(String name) {
        return trainerRepository.findAllByName(name);
    }

    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElse(null);
        if (trainer == null)
            return;
        clientRepository.deleteAllByTrainer(trainer);
        trainerRepository.deleteById(id);
    }

    public Trainer findById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }
}
