package fr.openclassrooms.YourCarYourWay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;


@Entity
@Table(name = "t_messageDirect_mdt", indexes = {
        @Index(name = "idx_mdt_conversation", columnList = "mdt_expediteur, mdt_destinataire, mdt_createdAt")
})
@Getter
@Setter
public class MessageDirect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mdt_id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mdt_expediteur", nullable = false)
    private User expediteur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mdt_destinataire", nullable = false)
    private User destinataire;

    @Lob
    @Column(name = "mdt_texte", nullable = false, columnDefinition = "TEXT")
    private String texte;

    @CreationTimestamp
    @Column(name = "mdt_createdAt")
    private LocalDateTime createdAt;
}
