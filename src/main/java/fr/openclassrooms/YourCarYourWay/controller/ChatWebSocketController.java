package fr.openclassrooms.YourCarYourWay.controller;

import fr.openclassrooms.YourCarYourWay.dto.ChatMessageDTO;
import fr.openclassrooms.YourCarYourWay.model.MessageDirect;
import fr.openclassrooms.YourCarYourWay.model.User;
import fr.openclassrooms.YourCarYourWay.service.MessageService;
import fr.openclassrooms.YourCarYourWay.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ChatWebSocketController {

    private final SimpMessagingTemplate template;
    private final UserService userService;
    private final MessageService messageService;

    public ChatWebSocketController(SimpMessagingTemplate template, UserService userService, MessageService messageService) {
        this.template = template;
        this.userService = userService;
        this.messageService = messageService;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessageDTO dto) {
        if (dto == null || dto.getFromId() == null || dto.getToId() == null || dto.getText() == null) return;

        Optional<User> fromOpt = userService.findById(dto.getFromId());
        Optional<User> toOpt = userService.findById(dto.getToId());
        if (fromOpt.isEmpty() || toOpt.isEmpty()) return;

        MessageDirect m = new MessageDirect();
        m.setExpediteur(fromOpt.get());
        m.setDestinataire(toOpt.get());
        m.setTexte(dto.getText());

        MessageDirect saved = messageService.save(m);

        String destination = "/queue/messages-" + toOpt.get().getId();
        template.convertAndSend(destination, saved);
    }
}