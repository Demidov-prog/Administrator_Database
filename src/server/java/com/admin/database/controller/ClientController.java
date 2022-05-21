package com.admin.database.controller;

import com.admin.database.entity.Client;
import com.admin.database.entity.Trainer;
import com.admin.database.service.ClientService;
import com.admin.database.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final TrainerService trainerService;

    @Autowired
    public ClientController(ClientService clientService, TrainerService trainerService) {
        this.clientService = clientService;
        this.trainerService = trainerService;
    }


    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("filter", new Client());
        model.addAttribute("newClient", new Client());
        return "client/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("newClient") Client client, Model model) {
        String nameTrainer = client.getTrainer().getName();

        if (trainerService.findByName(nameTrainer) == null) {
            model.addAttribute("clients", clientService.getAll());
            model.addAttribute("filter", new Client());
            model.addAttribute("newClient", new Client());
            model.addAttribute("error", "Тренера с таким именем в базе данных не найдено. Добавьте новую запись в таблице Trainer или введите доступное имя.");
            return "client/index";
        }

        client.setTrainer(trainerService.findByName(nameTrainer));
        clientService.save(client);

        return "redirect:/client";
    }

    @PostMapping ("/filter")
    public String filter(@ModelAttribute("filter") Client client, Model model) {
        List<Client> clientList = clientService.filter(
                client.getFirstName(),
                client.getLastName(),
                client.getTelephone(),
                client.getTrainer().getName());
        if (clientList.isEmpty())
            model.addAttribute("error", "Клиента с такими фильтрами не существует. Добавьте новую запись или введите существующие данные.");
        else
            model.addAttribute("clients",clientList);
        model.addAttribute("newClient", new Client());
        model.addAttribute("filter", client);
        return "client/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        clientService.delete(id);
        return "redirect:/client";
    }

    @GetMapping("/getById")
    public String getById(Model model, @RequestParam(value = "idValue") Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            model.addAttribute("clients", clientService.getAll());
            model.addAttribute("error", "Клиента с таким id в базе данных не найдено. Добавьте новую запись в таблице или введите доступный id.");
        }
        else
            model.addAttribute("clients", client);
        model.addAttribute("filter", new Client());
        model.addAttribute("newClient", new Client());
        return "client/index";
    }
}
