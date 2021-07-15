package com.aldrich_ryan.controller;

import com.aldrich_ryan.Main;
import com.aldrich_ryan.dao.UserDaoImpl;
import com.aldrich_ryan.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    private UserDaoImpl userDao;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private Button loginBtn;

    @FXML
    private void loginAction(ActionEvent actionEvent) throws IOException {
        if(txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username atau Password salah");
            alert.showAndWait();
        }
        else {
            userDao = new UserDaoImpl();
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            if(userDao.login(username, password).size() > 0){
                loginBtn.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("view/admin_view.fxml"));
                BorderPane root = loader.load();
                adminController controller = loader.getController();

                Stage adminStage = new Stage();
                adminStage.setTitle("Admin");
                adminStage.setScene(new Scene(root));
                adminStage.initOwner(rootAnchorPane.getScene().getWindow());
                adminStage.initModality(Modality.WINDOW_MODAL);
                adminStage.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Login gagal");
                alert.showAndWait();
            }
        }
    }
}
