package controller;

import dao.CrudDAO;
import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
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
   private final ItemDAO itemDAO = new ItemDAOImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllItems();
    }

    public void itemSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price=Double.parseDouble(txtPrice.getText());

       /* ItemDTO item=new ItemDTO();
        item.setCode(code);
        item.setName(name);
        item.setQtyOnHand(qty);
        item.setPrice(price);*/

        try {
            boolean isSaved = itemDAO.save(new ItemDTO(code, name, qty, price));

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
            ItemDTO itemDTO = (ItemDTO) itemDAO.search(id);
            // ItemDTO itemDTO = ItemDAOImpl.searchItem(id);

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

       /* ItemDTO item=new ItemDTO();
        item.setCode(code);
        item.setName(name);
        item.setQtyOnHand(qty);
        item.setPrice(price);*/
        try {
            boolean isUpdated = itemDAO.update(new ItemDTO(code, name, qty, price));
            // boolean isUpdated = ItemDAOImpl.updateItem(new ItemDTO(code,name,qty,price));

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
            boolean isDeleted = itemDAO.delete(id);
            // boolean isDeleted = ItemDAOImpl.deleteItem(id);

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
            ArrayList<ItemDTO> itemDTOs = itemDAO.loadAll();
           // ArrayList<ItemDTO> itemDTOS = ItemDAOImpl.loadAllItems();

            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            tblItem.getColumns().setAll(colCode,colName,colQty,colPrice);
            tblItem.setItems(FXCollections.observableArrayList(itemDTOs));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
