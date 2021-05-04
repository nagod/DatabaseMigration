package main.presentation.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.application.App;
import main.application.service.LoginService;
import main.bdo.OracleUser;

import java.net.URL;
import java.util.ResourceBundle;

public class OracleLogin implements Initializable{

    @FXML
    private TextField oracleUsernameTextfield;

    @FXML
    private TextField oraclePasswordTextfield;

    OracleUser oracleUser;
    LoginService loginService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginService = LoginService.getInstance();
        oracleUser = OracleUser.getInstance();
    }
    @FXML
    void login(ActionEvent event) {
        if(isValid()){
            oracleUser.setData(oracleUsernameTextfield.getText(), oraclePasswordTextfield.getText());
            if(loginService.login(oracleUser,"oracle")){
                System.out.println("Login at Oracle was successful");
                App.getInstance().loadWindow("/fxml/PgLogin.fxml", "PG Login");
            }
        }

    }
    @FXML
    void quit(ActionEvent event) {
        System.exit(1);
    }

    boolean isValid(){
        if(oraclePasswordTextfield.getText().isEmpty() || oraclePasswordTextfield.getText().isEmpty()){
            return false;
        }
        return true;
    }
}
