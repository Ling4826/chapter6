package se233.chapter6;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se233.chapter6.controller.GameLoop;
import se233.chapter6.model.Food;
import se233.chapter6.model.Snake;
import se233.chapter6.view.GameStage;

public class Launcher extends Application {

    // เพิ่ม stage เป็น static field
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage; // เก็บ stage ไว้ใน static field

        GameStage gameStage = new GameStage();
        Snake snake = new Snake(new Point2D(GameStage.WIDTH / 2, GameStage.HEIGHT / 2));
        Food food = new Food();
        // ส่ง primaryStage เข้าไปใน GameLoop ด้วย
        GameLoop gameLoop = new GameLoop(gameStage, snake, food, primaryStage);

        Scene scene = new Scene(gameStage, GameStage.WIDTH * GameStage.TILE_SIZE, GameStage.HEIGHT * GameStage.TILE_SIZE);
        scene.setOnKeyPressed(event -> gameStage.setKey(event.getCode()));
        scene.setOnKeyReleased(event -> gameStage.setKey(null));

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        (new Thread(gameLoop)).start();
    }

    public static void reset(Stage stage) {
        // สร้างทุกอย่างขึ้นมาใหม่หมด
        GameStage newGameStage = new GameStage();
        Snake newSnake = new Snake(new Point2D(GameStage.WIDTH / 2, GameStage.HEIGHT / 2));
        Food newFood = new Food();
        GameLoop newGameLoop = new GameLoop(newGameStage, newSnake, newFood, stage);

        Scene newScene = new Scene(newGameStage, GameStage.WIDTH * GameStage.TILE_SIZE, GameStage.HEIGHT * GameStage.TILE_SIZE);
        newScene.setOnKeyPressed(event -> newGameStage.setKey(event.getCode()));
        newScene.setOnKeyReleased(event -> newGameStage.setKey(null));

        // สั่งให้ stage ใช้ scene ใหม่
        stage.setScene(newScene);
        (new Thread(newGameLoop)).start();
    }

    public static void main(String[] args) {
        launch(args);
    }


}