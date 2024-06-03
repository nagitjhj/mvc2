package com.hi.mvc2basic.websocket.v3;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Bubble {
    private String sender;
    private Set<String> recipients = new HashSet<>();

    public void setRecipient(String recipient){
        recipients.add(recipient);
    }
}
