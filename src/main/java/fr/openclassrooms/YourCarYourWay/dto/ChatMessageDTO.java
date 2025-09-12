package fr.openclassrooms.YourCarYourWay.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatMessageDTO {
    private Integer fromId;
    private Integer toId;
    private String text;
}