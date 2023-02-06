package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    public ToggleGroup groupOne;
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerManage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void customerManage(ActionEvent event) throws IOException {
       customerManage();
    }

    public void itemManage(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/ItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void placeOrder(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/OrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void customerManage() throws IOException {
        URL resource = getClass().getResource("../view/CustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }


}
