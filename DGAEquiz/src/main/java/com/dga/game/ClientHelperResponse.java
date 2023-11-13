package com.dga.game;

import com.dga.equiz.controller.game.ChatRoomController;
import com.dga.equiz.controller.game.GameController;
import com.dga.equiz.controller.game.LobbyController;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageResponse;
import com.dga.game.EquizPacket.PacketResponse;
import com.dga.game.EquizPacket.Room.JoinRoom.JoinRoomResponse;
import com.dga.game.EquizPacket.Room.OpenRoom.OpenRoomResponse;
import com.dga.game.EquizPacket.Room.ShowRoom.RoomWraper;
import com.dga.game.EquizPacket.Room.ShowRoom.ShowRoomResponse;

import java.util.List;

public class ClientHelperResponse {
    public static void handleResponse(EquizPacket packet) {
        switch (packet.getType()) {
            case "message_response":
                handleMessageResponse((MessageResponse) packet);
                break;
            case "open_room_response":
                handleOpenRoomResponse((OpenRoomResponse) packet);
                break;
            case "join_room_response":
                handleJoinRoomResponse((JoinRoomResponse) packet);
                break;
            case "show_room_response":
                handleShowRoomResponse((ShowRoomResponse) packet);
                break;
            case "start_room_response":
                break;
            default:
                break;
        }
    }

    private static void handleMessageResponse(MessageResponse packet) {
        ChatRoomController chatRoomController = ControllerManager.getInstance().chatRoomController;
        chatRoomController.addMessage(packet);

    }

    private static void handleOpenRoomResponse(OpenRoomResponse packet) {
        String roomId = packet.roomId;
        ClientHelperRequest.sendJoinRoomRequest(roomId, "");
    }

    private static void handleJoinRoomResponse(JoinRoomResponse packet) {
        if (packet.status != PacketResponse.OK) {
            EquizUtils.showAlert("There is no room with id: " + packet.roomId);
        } else {
            GameController controller = ControllerManager.getInstance().gameController;
            controller.chatRoomView.show();

            ChatRoomController chatRoomController = ControllerManager.getInstance().chatRoomController;
            chatRoomController.clearMessageList();
        }
    }

    private static void handleShowRoomResponse(ShowRoomResponse packet) {
        List<RoomWraper> roomWrapers = packet.roomList;
        LobbyController lobbyController = ControllerManager.getInstance().lobbyController;
        lobbyController.clearRoomList();
        for (RoomWraper room : roomWrapers) {
            lobbyController.addRoomToList(room);
        }
    }

}
