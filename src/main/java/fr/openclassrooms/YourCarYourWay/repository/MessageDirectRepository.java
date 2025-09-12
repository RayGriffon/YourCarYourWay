package fr.openclassrooms.YourCarYourWay.repository;

import fr.openclassrooms.YourCarYourWay.model.MessageDirect;
import fr.openclassrooms.YourCarYourWay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDirectRepository extends JpaRepository<MessageDirect, Integer> {
    List<MessageDirect> findByExpediteurAndDestinataireOrderByCreatedAtAsc(User from, User to);
}