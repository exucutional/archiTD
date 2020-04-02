package org;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import managers.AssetManager;
import managers.ControlManager;
import controllers.MainController;

public class App extends Application {

    private String title = "archiTD";
    private AnimationTimer mainLoop;
    private FXMLLoader mainLoader = new FXMLLoader();
    private ControlManager controlManager = new ControlManager();
    private AssetManager assetManager = new AssetManager();
    private MainController mainController;
    private Parent root;

    @Override
    public void init() throws Exception {
        super.init();
        mainLoader.setLocation(getClass().getResource("/fxml/MainScene.fxml"));
        root = mainLoader.load();
        assetManager.init();
        mainController = mainLoader.getController();
        mainController.init(controlManager, assetManager);
        mainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mainController.tick();
            }
        };
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(title);
        stage.setScene(new Scene(root));

        mainLoop.start();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}