package ru.example.sweater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.sweater.domain.Message;
import ru.example.sweater.repos.MessageRepo;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World")
                    String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@RequestParam String text,
                             @RequestParam String tag,
                             Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model) {
        List<Message> messagesList = messageRepo.findByTag(filter);
        model.put("messages", messagesList);
        return "main";
    }

}
