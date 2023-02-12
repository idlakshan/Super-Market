package lk.ijse.pos.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingPageFormController implements Initializable {

    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void LoginOnAction(ActionEvent event) {
       makeFadeOut();
    }

    public void makeFadeOut(){
        FadeTransition fadeTransition=new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(root);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginDashboard();
            }
        });
    }

    public void loginDashboard(){
        try {
            Parent parent= FXMLLoader.load(this.getClass().getResource("../view/DashboardForm.fxml"));
            Stage primaryStage= (Stage) root.getScene().getWindow();
            Scene scene=new Scene(parent);
            primaryStage.setScene(scene);
            // primaryStage.setTitle("Login Form");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

