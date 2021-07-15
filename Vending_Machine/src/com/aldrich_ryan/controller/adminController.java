package com.aldrich_ryan.controller;

import com.aldrich_ryan.Main;
import com.aldrich_ryan.dao.ProductDaoImpl;
import com.aldrich_ryan.entity.Product;
import com.aldrich_ryan.util.MySQLConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class adminController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtPrice;
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
    private Product selectedProduct;
    private ObservableList<ImageView> imageViews;
    private ObservableList<TextField> textFields;
    private ObservableList<Product> products;
    private ProductDaoImpl productDao;
    @FXML
    private BorderPane rootBorderPane;
    private File fileGambar;
    private FileChooser fileChooser;
    private int selectedIndex;

    @FXML
    private void adminLogoutAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Aplikasi akan dimuat ulang, anda yakin ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(1);
        }

//        Stage stage = (Stage) txtImage1.getScene().getWindow();
//        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Main.class.getResource("view/main_view.fxml"));
//        BorderPane root = loader.load();
//        mainController controller = loader.getController();
//
//        Stage loginStage = new Stage();
//        loginStage.setTitle("Login Form");
//        loginStage.setScene(new Scene(root));
//        loginStage.initOwner(rootBorderPane.getScene().getWindow());
//        loginStage.initModality(Modality.WINDOW_MODAL);
//        loginStage.showAndWait();
//        refreshData();
    }

    public void refreshData() {
        txtImage1.clear();
        txtImage2.clear();
        txtImage3.clear();
        txtImage4.clear();
        txtImage5.clear();
        txtImage6.clear();
        txtImage7.clear();
        txtImage8.clear();
        txtImage9.clear();
        products.clear();
        products.addAll(productDao.fetchAll());
        textFields.addAll(txtImage1,txtImage2,txtImage3,txtImage4,txtImage5,txtImage6,txtImage7,txtImage8,txtImage9);
        for(int i = 0; i<9 ; i++) {
            textFields.get(i).setText(products.get(i).getNameproduct());
        }
    }

    @FXML
    private void topSellingReport(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call(){
                HashMap<String, Object> parameter = new HashMap<>();
                JasperPrint jp = null;
                try {
                    jp = JasperFillManager.fillReport("Vending_Machine/reports/report_top3.jasper", parameter, MySQLConnection.createConnection());
                } catch (JRException | SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }

    @FXML
    private void monthlySellingReport(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call(){
                HashMap<String, Object> parameter = new HashMap<>();
                JasperPrint jp = null;
                try {
                    jp = JasperFillManager.fillReport("Vending_Machine/reports/report_bulanann.jasper", parameter, MySQLConnection.createConnection());
                } catch (JRException | SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }

    @FXML
    private void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Anda login sebagai Admin, Jika anda telah login sebagai admin, anda harus memuat ulang aplikasi untuk kembali ke tampilan awal");
        alert.showAndWait();
    }

    @FXML
    private void chooseFileAction(ActionEvent actionEvent){
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        fileGambar = file;
}

    @FXML
    private void updateAction(ActionEvent actionEvent) throws IOException {
        Product product = new Product();
        selectedProduct.setNameproduct(txtName.getText().trim());
        selectedProduct.setPrice(Integer.parseInt(txtPrice.getText().trim()));
        selectedProduct.setStock(Integer.parseInt(txtStock.getText().trim()));
        String foto = selectedProduct.getImage();
        if(fileChooser == null){
            selectedProduct.setImage(foto);
        }
        else {
            Path targetFile = Paths.get("../pbo220201-groupassignment-kelompok_1872019_1872058/Vending_Machine/src/com/aldrich_ryan/image/" + fileGambar.getName());
            Files.copy(fileGambar.toPath(), targetFile);
            Image image = new Image(targetFile.toUri().toString());
            selectedProduct.setImage(String.valueOf(fileGambar.getName()));
//        Image image = new Image(fileGambar.toURI().toString());

            if (productDao.udpateData(selectedProduct) == 1) {
                products.clear();
                products.addAll(productDao.fetchAll());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Gambar Produk terupdate");
                alert.showAndWait();
                imageViews.get(selectedIndex).setImage(image);
                refreshData();
            }
        }
        if (productDao.udpateData(selectedProduct) == 1) {
            products.clear();
            products.addAll(productDao.fetchAll());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data Produk terupdate");
            alert.showAndWait();
            refreshData();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productDao = new ProductDaoImpl();
        imageViews = FXCollections.observableArrayList();
        textFields = FXCollections.observableArrayList();
        products = FXCollections.observableArrayList();
        products.addAll(productDao.fetchAll());
        imageViews.addAll(image1,image2,image3,image4,image5,image6,image7,image8,image9);
        textFields.addAll(txtImage1,txtImage2,txtImage3,txtImage4,txtImage5,txtImage6,txtImage7,txtImage8,txtImage9);
        for(int i = 0; i<9 ; i++){
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
        txtName.setText(selectedProduct.getNameproduct());
        txtPrice.setText(String.valueOf(selectedProduct.getPrice()));
        txtStock.setText(String.valueOf(selectedProduct.getStock()));
        selectedIndex = index;
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
}
