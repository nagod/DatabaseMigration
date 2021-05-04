package main.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    Stage primaryStage;

    // Singleton
    private static  App instance;
    public static App getInstance(){
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        // set instance !
        instance = this;
        // load fist view to be rendered
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/OracleLogin.fxml")); /* /fxml/PgLogin.fxml, /fxml/OracleLogin.fxml*/
        // update Stage obj
        primaryStage = stage;
        stage.setTitle("Oracle Login");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
    public void loadWindow(String url, String title){
        primaryStage.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        try {
            primaryStage.setTitle(title);
            primaryStage.setResizable(true);
            primaryStage.setScene(new Scene(fxmlLoader.load()));
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
