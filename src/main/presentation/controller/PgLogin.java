package main.presentation.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.application.App;
import main.application.service.LoginService;
import main.bdo.PgUser;

import java.net.URL;
import java.util.ResourceBundle;

public class PgLogin implements Initializable {

    @FXML
    private TextField pgUsernameTextfield;

    @FXML
    private TextField pgPasswordTextfield;

    private PgUser pgUser;
    private LoginService loginService;

    // Presentation layer calls application layer
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pgUser = PgUser.getInstance();
        loginService = LoginService.getInstance();
    }
    @FXML
    void login(ActionEvent event) {
        pgUser.setData(pgUsernameTextfield.getText(),pgPasswordTextfield.getText());
        if(loginService.login(pgUser,"postgres")){
            System.out.println("Login at PostgresDB was successful");
            App.getInstance().loadWindow("/fxml/tmp.fxml", "HI");

            //load next view
        }
    }
    @FXML
    void quit(ActionEvent event) {
        System.exit(1);
    }
}
