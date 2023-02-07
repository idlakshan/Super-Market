package view;

import controller.CustomerController;
import controller.ItemController;
import controller.OrderController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import view.tm.CartTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class OrderFormController {
    public ComboBox<String> cmbCusIds;
    public ComboBox<String> cmbItemIds;
    public TextField txtCusName;
    public TextField txtAddress;
    public TextField txtTp;
    public TextField txtItemName;
    public TextField txtQtyOnHand;
    public TextField txtPrice;
    public TextField txtQty;
    public TableView<CartTm> tblOrder;
    public TableColumn colCode;
    public TableColumn colCusId;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn colTotal;
    public Label lblDate;
    public Label lblTime;
    public Label lblTotal;



    int removeRow=-1;

    public void initialize(){

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        try {
            loadCustomerIds();
            loadItemIds();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCusIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
           removeRow=(int)newValue;
            //System.out.println("new value "+removeRow);
        });


        }

    private void setItemData(String itemId) throws SQLException, ClassNotFoundException {
        Item item = ItemController.searchItem(itemId);

        if (item==null){
            new Alert(Alert.AlertType.WARNING,"Empty results set");
        }else{
            txtItemName.setText(item.getName());
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            txtPrice.setText(String.valueOf(item.getPrice()));
        }
    }


    private void setCustomerData(String cusId) throws SQLException, ClassNotFoundException {
        Customer customer = CustomerController.searchCustomer(cusId);
        if (customer==null){
            new Alert(Alert.AlertType.WARNING,"Empty results set");
        }else {
           txtCusName.setText(customer.getName());
           txtAddress.setText(customer.getAddress());
           txtTp.setText(customer.getTp());


        }
    }


    private void loadDateAndTime() {
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO,event -> {
            LocalTime currentTime=LocalTime.now();
            lblTime.setText(
            currentTime.getHour()+" : "+ currentTime.getMinute()+" : "+ currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public void clearOnAction(ActionEvent event) {
     if (removeRow==-1){
         new Alert(Alert.AlertType.WARNING,"please select a row").show();
     }else {
         arrayList.remove(removeRow);
         calculateCost();
         tblOrder.refresh();
       }

    }

    //ObservableList<CartTm> observableList= FXCollections.observableArrayList();
    ArrayList<CartTm> arrayList=new ArrayList<>();

    public void addCartOnAction(ActionEvent event) {
        String name=txtItemName.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double price = Double.parseDouble(txtPrice.getText());
        int qty= Integer.parseInt(txtQty.getText());
        double total=price*qty;

        if (qtyOnHand<qty){
         new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
         return;
        }

      /*  CartTm cartTm=new CartTm(
                cmbItemIds.getValue(),
                cmbCusIds.getValue(),
                name,
                qty,
                price,
                total
        );*/
        String itemIds = cmbItemIds.getValue();
        String cusIds = cmbCusIds.getValue();

        CartTm cartTm=new CartTm();

        cartTm.setCode(itemIds);
        cartTm.setCusId(cusIds);
        cartTm.setName(name);
        cartTm.setQty(qty);
        cartTm.setPrice(price);
        cartTm.setTotal(total);


        int rowNumber= isExits(cartTm);
        System.out.println("Row No eka : "+rowNumber);


        if (rowNumber==-1){
           // observableList.add(cartTm);
            arrayList.add(cartTm);
        }else {
          // CartTm cartTm1=observableList.get(rowNumber);
            CartTm cartTm1 = arrayList.get(rowNumber);
            CartTm newTm=new CartTm(
                    cartTm1.getCode(),
                    cartTm1.getCusId(),
                    cartTm1.getName(),
                   cartTm1.getQty()+qty,
                    cartTm1.getPrice(),
                   total+cartTm1.getTotal()
           );
            if (qtyOnHand<cartTm1.getQty()+qty){
                new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
                return;
            }

           //observableList.remove(rowNumber);
            arrayList.remove(rowNumber);
         // observableList.add(newTm);
            arrayList.add(newTm);

        }

       // tblOrder.setItems(observableList);
        tblOrder.setItems(FXCollections.observableArrayList(arrayList));
        calculateCost();
    }


     public int isExits(CartTm cartTm){
        for (int i = 0; i <arrayList.size() ; i++) {
            if (cartTm.getCode().equals(arrayList.get(i).getCode())){
                System.out.println("i wala agaya "+i);
                return i;

            }
        }
             return -1;
    }

    void calculateCost(){
        double total=0.0;
        for (CartTm cartTm:arrayList
             ) {
                total=total+cartTm.getTotal();
        }
       lblTotal.setText(total+"/=");
    }





    public void placeOrderOnAction(ActionEvent event) {
        ArrayList<ItemDetails> items=new ArrayList<>();
        double total = 0;


        for (CartTm cartTm:arrayList
             ) {
            
            total=total+cartTm.getTotal();
            
            items.add(new ItemDetails(
                        cartTm.getCode(),
                        cartTm.getQty(),
                        cartTm.getPrice()

            ));

        }
        Order order=new Order(
                "O-001",
                cmbCusIds.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items

        );
        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Error").show();
        }

    }

    public void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = CustomerController.loadCustomerIds();
        cmbCusIds.getItems().setAll(customerIds);

    }
    public void loadItemIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemIds= ItemController.loadItemIds();
        cmbItemIds.getItems().setAll(itemIds);
    }

}
