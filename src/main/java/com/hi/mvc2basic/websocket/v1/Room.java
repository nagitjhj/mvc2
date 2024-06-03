package com.hi.mvc2basic.websocket.v1;

import jakarta.websocket.Session;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class Room {
    private String id;
    private Set<Session> sessions = new HashSet<>();

    public void setSession(Session session){
        sessions.add(session);
    }

//    private String user1;
//    private String user2;


//    public Room(String id, List<Session> sessions) {
//        this.id = id;
//        this.sessions = sessions;
//    }
}
