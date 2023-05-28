module accountprojectfx {

    requires org.hibernate.orm.core;
    requires hibernate.entitymanager;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.persistence;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens model.beans to org.hibernate.orm.core;
}