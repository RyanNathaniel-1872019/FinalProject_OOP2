package com.aldrich_ryan.controller;

import com.aldrich_ryan.Main;
import com.aldrich_ryan.entity.Product;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class changeController implements Initializable {
    @FXML
    private ImageView uang100;
    @FXML
    private ImageView uang50;
    @FXML
    private ImageView uang20;
    @FXML
    private ImageView uang10;
    @FXML
    private ImageView uang5;
    @FXML
    private TextField txtUang100;
    @FXML
    private TextField txtUang50;
    @FXML
    private TextField txtUang20;
    @FXML
    private TextField txtUang10;
    @FXML
    private TextField txtUang5;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button backBtn;
    private mainController mainController;
    private BorderPane rootBorderPane2;
    private ObservableList<Product> products;


    @FXML
    private void backAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();

    }

    public Image image1 = new Image(String.valueOf(getClass().getResource("../image/100rb.jpeg")));
    public Image image2 = new Image(String.valueOf(getClass().getResource("../image/50rb.jpg")));
    public Image image3 = new Image(String.valueOf(getClass().getResource("../image/20rb.png")));
    public Image image4 = new Image(String.valueOf(getClass().getResource("../image/10rb.jpg")));
    public Image image5 = new Image(String.valueOf(getClass().getResource("../image/5rb.jpg")));

    public void transferMessage(int message){
        int bayar = message;
        int seratus = bayar/100000;
        int sisa_seratus = bayar%100000;
        int limapuluh = sisa_seratus/50000;
        int sisa_limapuluh = sisa_seratus%50000;
        int duapuluh = sisa_limapuluh/20000;
        int sisa_duapuluh = sisa_limapuluh%20000;
        int sepuluh = sisa_duapuluh/10000;
        int sisa_sepuluh = sisa_duapuluh%10000;
        int lima = sisa_sepuluh/5000;

        txtUang100.setText(String.valueOf(seratus));
        txtUang50.setText(String.valueOf(limapuluh));
        txtUang20.setText(String.valueOf(duapuluh));
        txtUang10.setText(String.valueOf(sepuluh));
        txtUang5.setText(String.valueOf(lima));
        txtTotal.setText(String.valueOf(message));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uang100.setImage(image1);
        uang50.setImage(image2);
        uang20.setImage(image3);
        uang10.setImage(image4);
        uang5.setImage(image5);
    }
}
