module dgaequiz{
    exports com.dga.equiz;
    exports com.dga.equiz.controller;
    exports com.dga.equiz.model;
    opens com.dga.equiz;
    opens com.dga.equiz.controller;
    opens com.dga.equiz.model;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.controlsfx.controls;
}