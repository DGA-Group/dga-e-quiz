package com.dga.game;

import com.dga.equiz.model.Profile;
import com.dga.equiz.utils.ApplicationData;
import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageRequest;
import com.dga.game.EquizPacket.Room.JoinRoom.JoinRoomRequest;
import com.dga.game.EquizPacket.Room.LeaveRoom.LeaveRoomRequest;
import com.dga.game.EquizPacket.Room.OpenRoom.OpenRoomRequest;
import com.dga.game.EquizPacket.Room.ShowRoom.ShowRoomRequest;
import com.dga.game.EquizPacket.Room.StartRoom.StartRoomRequest;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHelperRequest {

    public static ObjectOutputStream objectOutputStream = null;

    public static void sendRequest(EquizPacket request) {
        Socket socket = ApplicationData.getInstance().socket;

        if (socket == null || !socket.isConnected()) {
            return;
        }

        try {
            if (objectOutputStream == null) {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            }
            objectOutputStream.writeObject(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendShowRoomRequest() {
        EquizPacket request = new ShowRoomRequest();
        sendRequest(request);
    }

    public static void sendCreateRoomRequest(String roomName, boolean requirePassword,
                                             String roomPassword, int playerLimit) {
        EquizPacket request = new OpenRoomRequest(roomName, requirePassword, roomPassword, playerLimit);
        sendRequest(request);
    }

    public static void sendJoinRoomRequest(String roomId, String roomPassword) {
        EquizPacket request = new JoinRoomRequest(roomId, roomPassword);
        sendRequest(request);
    }

    public static void sendMessageRequest(String message) {
        EquizPacket request = new MessageRequest(message);
        sendRequest(request);
    }

    public static void sendStartGameRequest() {
        EquizPacket request = new StartRoomRequest("red_tea");
        sendRequest(request);
    }

    public static void sendLeaveRoomRequest(){
        Profile profile = ApplicationData.getInstance().profile;
        int userId = profile.getID();
        EquizPacket request = new LeaveRoomRequest(userId);
        sendRequest(request);
    }

}
