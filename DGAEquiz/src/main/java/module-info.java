module dgaequiz {
    exports com.dga.equiz;
    exports com.dga.equiz.controller;
    opens com.dga.equiz;
    opens com.dga.equiz.controller;
    exports com.dga.equiz.utils;
    opens com.dga.equiz.utils;
    exports com.dga.equiz.controller.campaign;
    opens com.dga.equiz.controller.campaign;
    exports com.dga.equiz.model.nodeObject;
    opens com.dga.equiz.model.nodeObject;
    exports com.dga.equiz.model.word;
    opens com.dga.equiz.model.word;
    exports com.dga.equiz.controller.question;
    opens com.dga.equiz.controller.question;
    exports com.dga.equiz.model;
    opens com.dga.equiz.model;
    exports com.dga.equiz.controller.editProfile;
    opens com.dga.equiz.controller.editProfile;
    exports com.dga.equiz.controller.game;
    opens com.dga.equiz.controller.game;
    exports com.dga.equiz.controller.login;
    opens com.dga.equiz.controller.login;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.controlsfx.controls;
    requires jackson.annotations;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires okhttp;
    requires com.fasterxml.jackson.core;
    requires javafx.media;
}