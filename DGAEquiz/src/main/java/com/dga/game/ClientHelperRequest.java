package com.dga.game;

import com.dga.equiz.utils.ApplicationData;
import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageRequest;
import com.dga.game.EquizPacket.Room.JoinRoom.JoinRoomRequest;
import com.dga.game.EquizPacket.Room.OpenRoom.OpenRoomRequest;
import com.dga.game.EquizPacket.Room.ShowRoom.ShowRoomRequest;
import com.dga.game.EquizPacket.Room.StartRoom.StartRoomRequest;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHelperRequest {

    static ObjectOutputStream objectOutputStream = null;

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


    public static EquizPacket handleRequest(String request) {
        if (request.startsWith("/show_room")) {
            return createShowRoomRequest();
        }

        if (request.startsWith("/join")) {
            return createJoinRoomRequest(request);
        }

        if (request.startsWith("/open")) {
            return createOpenRoomRequest(request);
        }

        if (request.startsWith("/start_game")) {
            return createStartGameRequest(request);
        }

        return createMessageRequest(request);
    }

    private static JoinRoomRequest createJoinRoomRequest(String request) {
        String[] res = request.split(" ");
        if (res.length != 2) {
            System.out.println("Invalid join room request!");
            return null;
        }
        return new JoinRoomRequest(res[1], "");
    }

    private static OpenRoomRequest createOpenRoomRequest(String request) {
        String[] res = request.split(" ");
        if (res.length != 4) {
            System.out.println("Invalid open room request!");
            return null;
        }
        return new OpenRoomRequest(res[1], res[2], Integer.parseInt(res[3]));
    }

    private static MessageRequest createMessageRequest(String message) {
        return new MessageRequest(message);
    }

    private static ShowRoomRequest createShowRoomRequest() {
        return new ShowRoomRequest();
    }

    private static StartRoomRequest createStartGameRequest(String request) {
        return new StartRoomRequest("red_tea");
    }

    public static void sendShowRoomRequest() {
        EquizPacket request = createShowRoomRequest();
        sendRequest(request);
    }

    public static void sendCreateRoomRequest(String roomName, String roomPassword, int playerLimit) {
        EquizPacket request = new OpenRoomRequest(roomName, roomPassword, playerLimit);
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

}
