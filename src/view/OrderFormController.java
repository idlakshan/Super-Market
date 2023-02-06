package view;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class OrderFormController {
    public ComboBox cmbCusIds;
    public ComboBox cmbItemIds;
    public TextField txtCusName;
    public TextField txtAddress;
    public TextField txtTp;
    public TextField txtItemName;
    public TextField txtQtyOnHand;
    public TextField txtPrice;
    public TextField txtQty;
    public TableView tblOrder;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn colTotal;
    public Label lblDate;
    public Label lblTime;


    public void clearOnAction(ActionEvent event) {
    }

    public void addCartOnAction(ActionEvent event) {
    }


    public void placeOrderOnAction(ActionEvent event) {
    }
}
