package com.admin.database.controller;

;
import com.admin.database.entity.Pass;
import com.admin.database.service.GymService;
import com.admin.database.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pass")
public class PassController {
    private final PassService passService;
    private final GymService gymService;

    @Autowired
    public PassController(PassService passService, GymService gymService) {
        this.passService = passService;
        this.gymService = gymService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("pass", passService.getAll());
        model.addAttribute("filter", new Pass());
        model.addAttribute("newPass", new Pass());
        return "pass/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("newPass") Pass pass, Model model) {
        String nameGym = pass.getGym().getName();

        if (gymService.findByName(nameGym) == null) {
            model.addAttribute("pass", passService.getAll());
            model.addAttribute("filter", new Pass());
            model.addAttribute("newPass", new Pass());
            model.addAttribute("error", "Название спортзала не найдено в базе данных. Добавьте новую запись в таблице Gym или введите доступное название.");
            return "pass/index";
        }

        pass.setGym(gymService.findByName(nameGym));
        passService.save(pass);
        return "redirect:/pass";
    }

    @PostMapping ("/filter")
    public String filter(@ModelAttribute("filter") Pass pass, Model model) {
        if (pass.getPrice() == null) {
            return "redirect:/pass";
        }
        List<Pass> passList = passService.filter(pass.getPrice());
        if (passList.isEmpty()) {
            model.addAttribute("pass", passService.getAll());
            model.addAttribute("error", "Абонемента с такой ценой не найдено в базе данных. Добавьте новую запись в таблице или введите доступную цену.");
        }
        else
            model.addAttribute("pass", passList);
        model.addAttribute("newPass", new Pass());
        model.addAttribute("filter", pass);
        return "pass/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        passService.delete(id);
        return "redirect:/pass";
    }

    @GetMapping("/getById")
    public String getById(Model model, @RequestParam(value = "idValue") Long id) {
        Pass pass = passService.findById(id);
        if (pass == null) {
            model.addAttribute("pass", passService.getAll());
            model.addAttribute("error", "Абонемента с таким id в базе данных не найдено. Добавьте новую запись в таблице или введите доступный id.");
        }
        else
            model.addAttribute("pass", pass);
        model.addAttribute("filter", new Pass());
        model.addAttribute("newPass", new Pass());
        return "pass/index";
    }
}

