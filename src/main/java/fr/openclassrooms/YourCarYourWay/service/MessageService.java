package fr.openclassrooms.YourCarYourWay.service;

import fr.openclassrooms.YourCarYourWay.model.MessageDirect;
import fr.openclassrooms.YourCarYourWay.model.User;
import fr.openclassrooms.YourCarYourWay.repository.MessageDirectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageDirectRepository repo;

    public MessageService(MessageDirectRepository repo) {
        this.repo = repo;
    }

    public MessageDirect save(MessageDirect m) { return repo.save(m); }

    public List<MessageDirect> getConversation(User a, User b) {
        return repo.findByExpediteurAndDestinataireOrderByCreatedAtAsc(a, b);
    }
}