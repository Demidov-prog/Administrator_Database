package com.admin.database.service;


import com.admin.database.entity.Pass;
import com.admin.database.repository.PassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassService {
    private final PassRepository passRepository;

    @Autowired
    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public List<Pass> getAll() {return passRepository.findAll();}

    public void save(Pass pass) {
        passRepository.save(pass);
    }

    public void delete(Long id) { passRepository.deleteById(id);}

    public List<Pass> filter(Integer price) {
        return passRepository.findAllByPrice(price);
    }

    public Pass findById(Long id) {
        return passRepository.findById(id).orElse(null);
    }
}
