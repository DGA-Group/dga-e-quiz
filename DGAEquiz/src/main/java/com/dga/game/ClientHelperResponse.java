package com.dga.game;

import com.dga.equiz.controller.game.ChatRoomController;
import com.dga.equiz.controller.game.GameController;
import com.dga.equiz.controller.game.LobbyController;
import com.dga.equiz.utils.ApplicationEnum.MessageAlignment;
import com.dga.equiz.utils.ControllerManager;
import com.dga.equiz.utils.EquizUtils;
import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageResponse;
import com.dga.game.EquizPacket.PacketResponse;
import com.dga.game.EquizPacket.Room.JoinRoom.JoinRoomResponse;
import com.dga.game.EquizPacket.Room.LeaveRoom.LeaveRoomResponse;
import com.dga.game.EquizPacket.Room.OpenRoom.OpenRoomResponse;
import com.dga.game.EquizPacket.Room.ShowRoom.RoomWrapper;
import com.dga.game.EquizPacket.Room.ShowRoom.ShowRoomResponse;
import com.dga.game.EquizPacket.Room.StartRoom.StartRoomResponse;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class ClientHelperResponse {
    public static void handleResponse(EquizPacket packet) {
        if (packet == null) {
            return;
        }

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
                handleStartRoomResponse((StartRoomResponse) packet);
                break;
            case "leave_room_response":
                handleLeaveRoomResponse((LeaveRoomResponse) packet);
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
        String roomPassword = packet.roomPassword;

        ChatRoomController controller = ControllerManager.getInstance().chatRoomController;
        controller.clearMessageList();

        ClientHelperRequest.sendJoinRoomRequest(roomId, roomPassword);
    }

    private static void handleJoinRoomResponse(JoinRoomResponse packet) {
        if (packet.status != PacketResponse.OK) {
            EquizUtils.showAlert("Error", "There is no room with id: " + packet.roomId,
                    "Please re check the room id", AlertType.WARNING);
        } else {
            GameController controller = ControllerManager.getInstance().gameController;
            controller.chatRoomView.show();

            ChatRoomController chatRoomController = ControllerManager.getInstance().chatRoomController;
            String currentPlayerCount = "Current player: " + packet.playerCount + '/' + packet.playerLimit;
            chatRoomController.updatePlayerCount(currentPlayerCount);
            EquizUtils.callFuncDelay(() -> {
                chatRoomController.addMyMessage(packet.message, MessageAlignment.Middle);
            }, 1000);
        }
    }

    private static void handleShowRoomResponse(ShowRoomResponse packet) {
        List<RoomWrapper> roomWrappers = packet.roomList;
        LobbyController lobbyController = ControllerManager.getInstance().lobbyController;
        lobbyController.clearRoomList();
        for (RoomWrapper room : roomWrappers) {
            lobbyController.addRoomToList(room);
        }
    }

    public static void handleLeaveRoomResponse(LeaveRoomResponse packet) {
        ChatRoomController chatRoomController = ControllerManager.getInstance().chatRoomController;
        String currentPlayerCount = "Current player: " + packet.playerCount + '/' + packet.playerLimit;
        chatRoomController.updatePlayerCount(currentPlayerCount);
        chatRoomController.addMyMessage(packet.message, MessageAlignment.Middle);
    }

    public static void handleStartRoomResponse(StartRoomResponse packet) {
        if (packet.status == PacketResponse.ERROR) {
            EquizUtils.showAlert("Error", null, packet.message, AlertType.ERROR);
        }
    }

}
