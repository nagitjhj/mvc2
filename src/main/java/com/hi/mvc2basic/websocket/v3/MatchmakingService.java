package com.hi.mvc2basic.websocket.v3;

import com.hi.mvc2basic.websocket.v1.Room;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MatchmakingService {
//    private static final Map<String, String> chatMap = new ConcurrentHashMap<>();
//    private static Set<Room> ROOM = Collections.synchronizedSet(new HashSet<>());
    private static Map<String, Bubble> BUBBLE = new ConcurrentHashMap<>();

    public void setRecipient(String sender, String recipient) {
        //대기중인 사용자 목록에서 랜덤하게 선택
//        String recipient = chatMap.get("waitingUser");
//        if(recipient == null) {
//            chatMap.put("waitingUser", sender);
//            return null;
//        } else {
//            chatMap.remove("waitingUser");
//            return recipient;
//        }
        if(!BUBBLE.containsKey(sender)){
            Bubble bubble = new Bubble();
            bubble.setSender(sender);
            bubble.setRecipient(recipient);
            BUBBLE.put(sender, bubble);
        }else{
            Bubble bubble = BUBBLE.get(sender);
            bubble.setRecipient(recipient);
        }

    }

//    public Long getRoomId(){
////        ROOM.stream().filter(room -> {
////            if(room.getUser1() == null || room.getUser2() == null){
////                return room.getId();
////            }
////        });
//
//        Iterator<Room> iterator = ROOM.iterator();
//        while (iterator.hasNext()) {
//            Room room = iterator.next();
//            if(room.getUser1()==null || room.getUser2()==null){
//                return room.getId();
//            }
//        }
//        return null;
//    }
}
