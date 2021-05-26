package main.presentation.controller.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.application.App;
import main.application.service.LoginService;
import main.bdo.User.PgUser;

import java.net.URL;
import java.util.ResourceBundle;

public class PgLogin implements Initializable {

    @FXML
    private TextField pgUsernameTextfield;

    @FXML
    private PasswordField pgPasswordField;

    private PgUser pgUser;
    private LoginService loginService;

    @FXML
    private ChoiceBox connection_chooser;

    // Presentation layer calls application layer
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pgUser = PgUser.getInstance();
        loginService = LoginService.getInstance();
    }
    @FXML
    void login(ActionEvent event) {
        String connectionString = connection_chooser.getValue().toString();
            // check if Input fields are not empty
            if(isValid()){
                // init pgUser with data from input fields
                pgUser.setData(pgUsernameTextfield.getText(),pgPasswordField.getText());
                if(loginService.login("postgres", connectionString)){
                    System.out.println("Login at PostgresDB was successful");
                    App.getInstance().loadWindow("/fxml/tmp.fxml", "HI");
                }else{
                    pgUsernameTextfield.setStyle("-fx-border-color: red;");
                    pgPasswordField.setStyle("-fx-border-color: red;");
                }
        }
    }
    @FXML
    void quit(ActionEvent event) {
        System.exit(1);
    }

    boolean isValid(){
        return !pgUsernameTextfield.getText().isEmpty() && !pgPasswordField.getText().isEmpty() && !connection_chooser.getValue().toString().equals("select connection");
    }
}
