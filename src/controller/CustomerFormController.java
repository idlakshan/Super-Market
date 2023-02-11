package controller;

import bo.CustomerBOImpl;
import dao.CrudDAO;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.CustomerDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    public AnchorPane root;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTp;
    public TableView tblCustomer;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTp;

    //--------------Loose Coupling------------------
    //--------------Property Injection--------------
  //  private final CustomerDAO customerDAO=new CustomerDAOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         loadAllCustomers();
    }


    public void customerSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tp = txtTp.getText();

        try {

            //Loose Coupling
            //DI
            CustomerBOImpl customerBOImpl=new CustomerBOImpl();


            if (customerBOImpl.saveCustomer(new CustomerDTO(id, name, address, tp))){
                new Alert(Alert.AlertType.INFORMATION,"saved successfully").show();
                loadAllCustomers();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void customerSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
             //loose coupling
            //DI
            CustomerBOImpl customerBOImpl=new CustomerBOImpl();
            CustomerDTO customerDTO = customerBOImpl.searchCustomer(id);

            txtId.setText(customerDTO.getId());
            txtName.setText(customerDTO.getName());
            txtAddress.setText(customerDTO.getAddress());
            txtTp.setText(customerDTO.getTp());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void customerUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tp = txtTp.getText();

        try {
            //loose Coupling
            //DI
            CustomerBOImpl customerBOImpl=new CustomerBOImpl();

            if ( customerBOImpl.updateCustomer(new CustomerDTO(id, name, address, tp))){
                new Alert(Alert.AlertType.INFORMATION,"Updated successfully").show();
                loadAllCustomers();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void customerDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            //loose Coupling
            //DI
            CustomerBOImpl customerBOImpl=new CustomerBOImpl();

            if (customerBOImpl.deleteCustomer(id)){
                new Alert(Alert.AlertType.INFORMATION,"Deleted successfully").show();
                loadAllCustomers();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadAllCustomers(){

        try {
            //loose Coupling
            //DI
            CustomerBOImpl customerBOImpl=new CustomerBOImpl();
            ArrayList<CustomerDTO> allCustomers = customerBOImpl.getAllCustomers();

            colCode.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colTp.setCellValueFactory(new PropertyValueFactory<>("tp"));


            tblCustomer.getColumns().setAll(colCode,colName,colAddress,colTp);
            tblCustomer.setItems(FXCollections.observableArrayList(allCustomers));


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
