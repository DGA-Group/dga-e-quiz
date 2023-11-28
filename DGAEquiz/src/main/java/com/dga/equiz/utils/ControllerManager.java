package com.dga.equiz.utils;

import com.dga.equiz.controller.*;
import com.dga.equiz.controller.editProfile.EditAccController;
import com.dga.equiz.controller.editProfile.EditInforController;
import com.dga.equiz.controller.editProfile.ProfileController;
import com.dga.equiz.controller.game.ChatRoomController;
import com.dga.equiz.controller.game.CreateRoomController;
import com.dga.equiz.controller.game.GameController;
import com.dga.equiz.controller.game.LobbyController;
import com.dga.equiz.controller.login.LoginController;
import com.dga.equiz.controller.login.RankController;

public class ControllerManager {
    private static ControllerManager instance;

    private ControllerManager() {

    }

    public static ControllerManager getInstance() {
        if (instance == null) {
            instance = new ControllerManager();
        }
        return instance;
    }

    public ProfileController profileController;
    public GameController gameController;
    public LobbyController lobbyController;
    public CreateRoomController createRoomController;
    public ChatRoomController chatRoomController;
    public LoginController loginController;
    public MyApplicationController myApplicationController;
    public HomeController homeController;
    public RankController rankController;
    public FlashCardController flashCardController;
    public DictionaryController dictionaryController;
    public OfflineDictionaryController offlineDictionaryController;
}
