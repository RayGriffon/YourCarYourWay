package fr.openclassrooms.YourCarYourWay.controller;

import fr.openclassrooms.YourCarYourWay.model.MessageDirect;
import fr.openclassrooms.YourCarYourWay.model.User;
import fr.openclassrooms.YourCarYourWay.service.MessageService;
import fr.openclassrooms.YourCarYourWay.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final MessageService messageService;

    public UserRestController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/users")
    public List<User> allUsers() { return userService.findAll(); }

    @GetMapping("/conversations")
    public ResponseEntity<List<MessageDirect>> getConversation(@RequestParam Integer a, @RequestParam Integer b) {
        Optional<User> ua = userService.findById(a);
        Optional<User> ub = userService.findById(b);
        if (ua.isEmpty() || ub.isEmpty()) return ResponseEntity.notFound().build();
        var conv1 = messageService.getConversation(ua.get(), ub.get());
        var conv2 = messageService.getConversation(ub.get(), ua.get());
        conv1.addAll(conv2);
        conv1.sort((m1, m2) -> m1.getCreatedAt().compareTo(m2.getCreatedAt()));
        return ResponseEntity.ok(conv1);
    }
}