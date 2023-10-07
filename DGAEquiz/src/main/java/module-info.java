module dgaequiz{
    exports com.dga.equiz;
    exports com.dga.equiz.controller;
    opens com.dga.equiz;
    opens com.dga.equiz.controller;
    exports com.dga.equiz.utils;
    opens com.dga.equiz.utils;
    exports com.dga.equiz.controller.campaign;
    opens com.dga.equiz.controller.campaign;
    exports com.dga.equiz.controller.home;
    opens com.dga.equiz.controller.home;
    exports com.dga.equiz.model.home;
    opens com.dga.equiz.model.home;
    exports com.dga.equiz.model.campaign;
    opens com.dga.equiz.model.campaign;
    exports com.dga.equiz.model.nodeObject;
    opens com.dga.equiz.model.nodeObject;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires jackson.annotations;
}