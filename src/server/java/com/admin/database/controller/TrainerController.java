package com.admin.database.controller;

import com.admin.database.entity.Trainer;
import com.admin.database.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("trainers", trainerService.getAll());
        model.addAttribute("filter", new Trainer());
        model.addAttribute("newTrainer", new Trainer());
        return "trainer/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("newTrainer") Trainer trainer) {
        trainerService.save(trainer);
        return "redirect:/trainer";
    }

    @PostMapping ("/filter")
    public String filter(@ModelAttribute("filter") Trainer trainer, Model model) {
        List<Trainer> trainerList = trainerService.filter(trainer.getName());
        if (trainerList.isEmpty()) {
            model.addAttribute("trainers", trainerService.getAll());
            model.addAttribute("error", "Тренера с таким именем в базе данных не найдено. Добавьте новую запись в таблице Trainer или введите доступное имя.");
        }
        else
            model.addAttribute("trainers", trainerList);
        model.addAttribute("newTrainer", new Trainer());
        model.addAttribute("filter", trainer);
        return "trainer/index";
    }

    @DeleteMapping("/{id}")
    public String getAll(@PathVariable("id") Long id) {
        trainerService.delete(id);
        return "redirect:/trainer";
    }

    @GetMapping("/getById")
    public String getById(Model model, @RequestParam(value = "idValue") Long id) {
        Trainer trainer = trainerService.findById(id);
        if (trainer == null) {
            model.addAttribute("trainers", trainerService.getAll());
            model.addAttribute("error", "Тренера с таким id в базе данных не найдено. Добавьте новую запись в таблице Trainer или введите доступный id.");
        }
        else
            model.addAttribute("trainers", trainer);
        model.addAttribute("filter", new Trainer());
        model.addAttribute("newTrainer", new Trainer());
        return "trainer/index";
    }

}
