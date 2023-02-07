package view;

import controller.CustomerController;
import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Item;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public AnchorPane root;
    public TextField txtCode;
    public TextField txtName;
    public TextField txtQty;
    public TextField txtPrice;
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colPrice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllItems();
    }

    public void itemSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price=Double.parseDouble(txtPrice.getText());

        Item item=new Item();
        item.setCode(code);
        item.setName(name);
        item.setQtyOnHand(qty);
        item.setPrice(price);

        try {
            boolean isSaved = ItemController.saveItem(item);

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"saved successfully").show();
                loadAllItems();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void itemSearchOnAction(ActionEvent event) {
        String id = txtCode.getText();

        try {
            Item item = ItemController.searchItem(id);

            txtCode.setText(item.getCode());
            txtName.setText(item.getName());
            txtQty.setText(String.valueOf(item.getQtyOnHand()));
            txtPrice.setText(String.valueOf(item.getPrice()));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    public void itemUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price=Double.parseDouble(txtPrice.getText());

        Item item=new Item();
        item.setCode(code);
        item.setName(name);
        item.setQtyOnHand(qty);
        item.setPrice(price);
        try {
            boolean isUpdated = ItemController.updateItem(item);

            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Updated successfully").show();
                loadAllItems();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void itemDeleteOnAction(ActionEvent event) {
        String id = txtCode.getText();

        try {
            boolean isDeleted = ItemController.deleteItem(id);

            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Deleted successfully").show();
                loadAllItems();

            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadAllItems(){

        try {
            ArrayList<Item> items = ItemController.loadAllItems();

            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            tblItem.getColumns().setAll(colCode,colName,colQty,colPrice);
            tblItem.setItems(FXCollections.observableArrayList(items));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
