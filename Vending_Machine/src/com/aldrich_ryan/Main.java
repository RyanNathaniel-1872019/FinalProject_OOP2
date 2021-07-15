package com.aldrich_ryan;

import com.sun.deploy.cache.BaseLocalApplicationProperties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String music = "../pbo220201-groupassignment-kelompok_1872019_1872058/Vending_Machine/src/com/aldrich_ryan/music/music.mp3";
        Media media = new Media(Paths.get(music).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        Parent root = FXMLLoader.load(getClass().getResource("view/main_view.fxml"));
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
