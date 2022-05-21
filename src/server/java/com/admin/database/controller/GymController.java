package com.admin.database.controller;

import com.admin.database.entity.Gym;
import com.admin.database.entity.Trainer;
import com.admin.database.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gym")
public class GymController {
    private final GymService gymService;

    @Autowired
    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("gyms", gymService.getAll());
        model.addAttribute("filter", new Gym());
        model.addAttribute("newGym", new Gym());
        return "gym/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("newGym") Gym gym) {
        gymService.save(gym);
        return "redirect:/gym";
    }

    @PostMapping ("/filter")
    public String filter(@ModelAttribute("filter") Gym gym, Model model) {
        if (gym.getName().isEmpty()) {
            return "redirect:/gym";
        }
        List<Gym> gymList = gymService.filter(gym.getName());
        if (gymList.isEmpty()) {
            model.addAttribute("gyms", gymService.getAll());
            model.addAttribute("error", "Спортзал с таким именем не в базе не найден. Добавьте новую запись  таблице или введите существующие данные.");
        }
        else
            model.addAttribute("gyms", gymList);
        model.addAttribute("newGym", new Gym());
        model.addAttribute("filter", gym);
        return "gym/index";
    }

    @DeleteMapping("/{id}")
    public String getAll(@PathVariable("id") Long id) {
        gymService.delete(id);
        return "redirect:/gym";
    }

    @GetMapping("/getById")
    public String getById(Model model, @RequestParam(value = "idValue") Long id) {
        Gym gym = gymService.findById(id);
        if (gym == null) {
            model.addAttribute("gyms", gymService.getAll());
            model.addAttribute("error", "Спортзала с таким id в базе данных не найдено. Добавьте новую запись в таблице или введите доступный id.");
        }
        else
            model.addAttribute("gyms", gym);
        model.addAttribute("filter", new Gym());
        model.addAttribute("newGym", new Gym());
        return "gym/index";
    }
}
