package com.admin.database.controller;

import com.admin.database.entity.Client;
import com.admin.database.entity.Pass;
import com.admin.database.service.ClientService;
import com.admin.database.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/clientPassList")
public class ClientPassController {
    private final PassService passService;
    private final ClientService clientService;

    @Autowired
    public ClientPassController(PassService passService, ClientService clientService) {
        this.passService = passService;
        this.clientService = clientService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Client> clientList = clientService.getAll();
        model.addAttribute("clients", clientList);
        return "clientAndPass/index";
    }

    @PostMapping()
    public String create(@RequestParam(value = "idClient") Long idClient,
                         @RequestParam(value = "idPass") Long idPass, Model model) {
        Client client = clientService.findById(idClient);
        Pass pass = passService.findById(idPass);
        if (client == null || pass == null) {
            model.addAttribute("clients", clientService.getAll());
            model.addAttribute("error", "Тренера с таким id в базе данных не найдено. Добавьте новую запись в таблице Trainer или введите доступный id.");
            return "clientAndPass/index";
        }
        else {
            List<Pass> passList = client.getPassList();
            passList.add(pass);
            client.setPassList(passList);

            List<Client> clientList = pass.getClientList();
            clientList.add(client);
            pass.setClientList(clientList);

            clientService.save(client);
            passService.save(pass);
        }

        return "redirect:/clientPassList";
    }

    @DeleteMapping()
    public String delete(@RequestParam(value = "idClient") Long idClient,
                         @RequestParam(value = "idPass") Long idPass, Model model) {
        Client client = clientService.findById(idClient);
        Pass pass = passService.findById(idPass);
        if (client == null || pass == null) {
            model.addAttribute("clients", clientService.getAll());
            model.addAttribute("error", "Тренера с таким id в базе данных не найдено. Добавьте новую запись в таблице Trainer или введите доступный id.");
            return "clientAndPass/index";
        }
        else {
            List<Pass> passList = client.getPassList();
            int length = passList.size();

            passList.removeIf(p -> pass.getId() == p.getId());
            client.setPassList(passList);

            if (length == passList.size()) {
                model.addAttribute("clients", clientService.getAll());
                model.addAttribute("error", "Тренера с таким id в базе данных не найдено. Добавьте новую запись в таблице Trainer или введите доступный id.");
                return "clientAndPass/index";
            }

            List<Client> clientList = pass.getClientList();
            clientList.removeIf(cl -> client.getId() == cl.getId());
            pass.setClientList(clientList);

            clientService.save(client);
            passService.save(pass);
        }
        return "redirect:/clientPassList";
    }
}
