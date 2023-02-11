package controller;

import bo.custom.impl.ItemBOImpl;
import bo.custom.ItemBO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.ItemDTO;

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


    //----Loose Coupling---------
    //----Property Injection-----
    ItemBO itemBO =new ItemBOImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllItems();
    }

    public void itemSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price=Double.parseDouble(txtPrice.getText());

        try {

            if ( itemBO.saveItem(new ItemDTO(code, name, qty, price))){
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

            ItemDTO itemDTO = itemBO.searchItem(id);

            txtCode.setText(itemDTO.getCode());
            txtName.setText(itemDTO.getName());
            txtQty.setText(String.valueOf(itemDTO.getQtyOnHand()));
            txtPrice.setText(String.valueOf(itemDTO.getPrice()));

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

        try {

            if (itemBO.updateItem(new ItemDTO(code, name, qty, price))){
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
           // loose Couping,DI


            if ( itemBO.deleteItem(id)){
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
           // loose coupling,DI

            ArrayList<ItemDTO> allItems = itemBO.getAllItems();


            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            tblItem.getColumns().setAll(colCode,colName,colQty,colPrice);
            tblItem.setItems(FXCollections.observableArrayList(allItems));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
