package org;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import managers.AssetManager;
import managers.ControlManager;
import managers.EventManager;
import managers.ObjectManager;
import controllers.MainController;
import events.EventType;

public class App extends Application {

    private String title = "archiTD";
    private AnimationTimer mainLoop;
    private FXMLLoader mainLoader = new FXMLLoader();
    private EventManager eventManager = new EventManager();
    private ControlManager controlManager = new ControlManager();
    private AssetManager assetManager = new AssetManager();
    private ObjectManager objectManager = new ObjectManager();
    private MainController mainController;
    private Parent root;
    private long prevNanos = 0;
    private double spawnRate = 3;
    private double acc = 10.1;

    @Override
    public void init() throws Exception {
        super.init();
        mainLoader.setLocation(getClass().getResource("/fxml/MainScene.fxml"));
        root = mainLoader.load();
        Platform.runLater(new Runnable() {
            public void run() {
                assetManager.init();
            }
        });
        mainController = mainLoader.getController();
        mainController.init(controlManager, assetManager, objectManager, eventManager);

        mainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (prevNanos == 0) {
                    prevNanos = now;
                    return;
                }
                long deltaNanos = now - prevNanos;
                prevNanos = now;
                double dt = deltaNanos / 1.0e9;
                acc += dt;
                if (acc > spawnRate) {
                    acc = 0;
                    mainController.eventManager.publish(EventType.SPAWN_ENEMY);
                }
                objectManager.update(dt);
                mainController.render();
            }
        };
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(title);
        stage.setScene(new Scene(root, Settings.get().getWindowWidth(), Settings.get().getWindowHeight()));

        mainLoop.start();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}