package com.aldrich_ryan.controller;

import com.aldrich_ryan.Main;
import com.aldrich_ryan.dao.ProductDaoImpl;
import com.aldrich_ryan.dao.TransactionDaoImpl;
import com.aldrich_ryan.entity.Product;
import com.aldrich_ryan.entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtNominal;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;
    @FXML
    private ImageView image9;
    @FXML
    private TextField txtImage1;
    @FXML
    private TextField txtImage2;
    @FXML
    private TextField txtImage3;
    @FXML
    private TextField txtImage4;
    @FXML
    private TextField txtImage5;
    @FXML
    private TextField txtImage6;
    @FXML
    private TextField txtImage7;
    @FXML
    private TextField txtImage8;
    @FXML
    private TextField txtImage9;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    private Button btnPay;
    private ProductDaoImpl productDao;
    private TransactionDaoImpl transactionDao;
    private ObservableList<Product> products;
    private ObservableList<Transaction> transactions;
    private ObservableList<ImageView> imageViews;
    private ObservableList<TextField> textFields;
    private Product selectedProduct;
    @FXML
    private MenuBar myMenuBar;
    @FXML
    private MenuItem adminLogin;


    @FXML
    private void closeAction(ActionEvent actionEvent) {
        System.exit(1);
    }

    @FXML
    private void adminAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnPay.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/login_view.fxml"));
        AnchorPane root = loader.load();
        loginController controller = loader.getController();

        Stage loginStage = new Stage();
        loginStage.setTitle("Login Form");
        loginStage.setScene(new Scene(root));
        loginStage.initOwner(rootBorderPane.getScene().getWindow());
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.showAndWait();
    }


    @FXML
    private void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Selamat datang di Aldry Vending Machine, Selamat Berberlanja");
        alert.showAndWait();
    }

    @FXML
    private void payAction(ActionEvent actionEvent) throws IOException {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        if(txtQty.getText().trim().isEmpty() || txtStock.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tidak valid");
            alert.show();
        }
        else if(Integer.parseInt(txtQty.getText().trim()) > Integer.parseInt(txtStock.getText().trim())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Stock tidak mencukupi");
            alert.show();
        }
        else if((selectedProduct.getPrice() * Integer.parseInt(txtQty.getText().trim())) > Double.parseDouble(txtNominal.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Uang dikembalikan karena nominal tidak mencukupi");
            alert.show();
        }
        else if(!txtNominal.getText().equals("100000") && !txtNominal.getText().equals("50000") && !txtNominal.getText().equals("20000") && !txtNominal.getText().equals("10000") && !txtNominal.getText().equals("5000")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nominal tidak dapat terdeteksi");
            alert.show();
        }
        else {
//            Timestamp date = new Timestamp(System.currentTimeMillis());
//            System.out.println(date);
            Transaction transaction = new Transaction();
            transactionDao = new TransactionDaoImpl();
            transaction.setQty(Integer.parseInt(txtQty.getText().trim()));
            transaction.setPrice(Integer.parseInt(txtNominal.getText().trim()));
            transaction.setTransactiondate(date);
            transaction.setProduct(selectedProduct);

            int a = Integer.parseInt(txtStock.getText().trim());
            int b = Integer.parseInt(txtQty.getText().trim());

            selectedProduct.setStock(a-b);

            if (transactionDao.addData(transaction) == 1) {
                transactions.clear();
                transactions.addAll(transactionDao.fetchAll());
            }
            if (productDao.udpateData(selectedProduct) == 1) {
                products.clear();
                products.addAll(productDao.fetchAll());
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/change_view.fxml"));
            AnchorPane root = loader.load();
            changeController controller = loader.getController();
            controller.transferMessage(Integer.parseInt(txtNominal.getText()) - (Integer.parseInt(txtQty.getText().trim())
                    * Integer.parseInt(txtPrice.getText().trim())));

            Stage loginStage = new Stage();
            loginStage.setTitle("Transaksi");
            loginStage.setScene(new Scene(root));
            loginStage.initOwner(rootBorderPane.getScene().getWindow());
            loginStage.initModality(Modality.WINDOW_MODAL);
            loginStage.showAndWait();
            refreshData();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productDao = new ProductDaoImpl();
        transactionDao = new TransactionDaoImpl();
        imageViews = FXCollections.observableArrayList();
        textFields = FXCollections.observableArrayList();
        products = FXCollections.observableArrayList();
        transactions = FXCollections.observableArrayList();
        transactions.addAll(transactionDao.fetchAll());
        products.addAll(productDao.fetchAll());

        imageViews.addAll(image1,image2,image3,image4,image5,image6,image7,image8,image9);
        textFields.addAll(txtImage1,txtImage2,txtImage3,txtImage4,txtImage5,txtImage6,txtImage7,txtImage8,txtImage9);
        for(int i = 0; i<9; i++){
            Image image = null;
            try {
                image = new Image(getClass().getResource("../image/" + products.get(i).getImage()).toURI().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            imageViews.get(i).setImage(image);
            textFields.get(i).setText(products.get(i).getNameproduct());
        }
    }

    private void txt(int index){
        selectedProduct = products.get(index);
        txtPrice.setText(String.valueOf(selectedProduct.getPrice()));
        txtStock.setText(String.valueOf(selectedProduct.getStock()));
        if(selectedProduct.getStock() == 0){
            txtQty.setDisable(true);
            txtNominal.setDisable(true);
            btnPay.setDisable(true);
        }
        else{
            txtQty.setDisable(false);
            txtNominal.setDisable(false);
            btnPay.setDisable(false);
        }
    }

    @FXML
    private void image1ProductClicked(MouseEvent mouseEvent) {
        txt(0);
    }

    @FXML
    private void image2ProductClicked(MouseEvent mouseEvent) {
        txt(1);
    }

    @FXML
    private void image3ProductClicked(MouseEvent mouseEvent) {
        txt(2);
    }

    @FXML
    private void image4ProductClicked(MouseEvent mouseEvent) {
        txt(3);
    }

    @FXML
    private void image5ProductClicked(MouseEvent mouseEvent) {
        txt(4);
    }

    @FXML
    private void image6ProductClicked(MouseEvent mouseEvent) {
        txt(5);
    }

    @FXML
    private void image7ProductClicked(MouseEvent mouseEvent) {
        txt(6);
    }

    @FXML
    private void image8ProductClicked(MouseEvent mouseEvent) {
        txt(7);
    }

    @FXML
    private void image9ProductClicked(MouseEvent mouseEvent) {
        txt(8);
    }

    public void refreshData(){
        products.clear();
        products.addAll(productDao.fetchAll());
    }
}
