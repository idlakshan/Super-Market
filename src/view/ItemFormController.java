package view;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ItemFormController {
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

    public void itemSaveOnAction(ActionEvent event) {
    }

    public void itemSearchOnAction(ActionEvent event) {
    }

    public void itemUpdateOnAction(ActionEvent event) {
    }

    public void itemDeleteOnAction(ActionEvent event) {
    }
}
