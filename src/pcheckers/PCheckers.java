/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcheckers;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author piker
 */
public class PCheckers extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Group root = new Group();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 500, 500);
//        makeBoard(root, 50,2,0,0);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void makeBoard(Group root,int tileSize,int tileSpace,int initX,int initY) {
        Image whiteTile = new Image("white_tile.png");
        Image blackTile = new Image("black_tile.png");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ImageView temp = new ImageView((((i+j)%2==0) ? whiteTile : blackTile));
                temp.setX(initX+j*tileSize + tileSpace*j);
                temp.setY(initY+i*tileSize + tileSpace*i);
                temp.setFitHeight(tileSize);
                temp.setFitWidth(tileSize);
                root.getChildren().add(temp);
            }
        }
        
    }
    public static void makeMenu(Group root){
        
    }
}
