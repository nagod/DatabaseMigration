package main.presentation.controller.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.application.service.LoginService;
import main.data.dao.DBConnectionDAO;
import main.data.daoImpl.Login.DBConnectionHandler;
import main.data.daoImpl.SchemaMigration.GenerateOracleDatabaseTablesDAO;
import main.data.daoImpl.SchemaMigration.PostgreSQLSchema;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class tmpController implements Initializable {

    LoginService loginService;
    DBConnectionHandler handler;
    DBConnectionDAO post;
    Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginService = LoginService.getInstance();
        handler = DBConnectionHandler.getInstance();
        post = handler.getConnection("postgres");
        connection = post.getConnection();
    }

    @FXML
    void printMetha(ActionEvent event) throws SQLException {
        PostgreSQLSchema s = new PostgreSQLSchema();
        s.getAlTableInfo();
        s.printTypes();
    }
    @FXML
    void makeTable(ActionEvent event) {
        PostgreSQLSchema pg = new PostgreSQLSchema();
        GenerateOracleDatabaseTablesDAO or = new GenerateOracleDatabaseTablesDAO();
        or.createTable(pg.getAlTableInfo());
    }
}
