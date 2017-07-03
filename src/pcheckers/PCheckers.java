/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcheckers;

import com.jfoenix.controls.JFXButton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        makeMenu(primaryStage);
//        makeBoard(root, 50,2,0,0);
        primaryStage.setTitle("PCheckers | by Piker");
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void makeBoard(Stage ps) {
        Group root = new Group();
        ps.getScene().setRoot(root);
        
        int list = (ps.getScene().getHeight()<ps.getScene().getWidth()) ? (int)ps.getScene().getHeight() : (int)ps.getScene().getWidth();
        int tileSize=(list-8*2)/8;
        int tileSpace=2;
        int initX=0;
        int initY=0;
        Image whiteTile = new Image("white_tile.png");
        Image blackTile = new Image("black_tile.png");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ImageView temp = new ImageView((((i + j) % 2 == 0) ? whiteTile : blackTile));
                temp.setX(initX + j * tileSize + tileSpace * j);
                temp.setY(initY + i * tileSize + tileSpace * i);
                temp.setFitHeight(tileSize);
                temp.setFitWidth(tileSize);
                root.getChildren().add(temp);
            }
        }

    }

    public static void makeMenu(Stage primaryStage) {
        StackPane root = new StackPane();
        FadeTransition rootFade = new FadeTransition(Duration.seconds(6), root);
        rootFade.setFromValue(0.0);
        rootFade.setToValue(1.0);
        rootFade.play();
        Scene menuScene = new Scene(root, 500, 500);
        primaryStage.setScene(menuScene);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);
        Text t = new Text();
        t.setFont(Font.loadFont("file:resources/fonts/Stya.ttf", 70));
        t.setText("PCheckers");
        t.setX(200);
        t.setBoundsType(TextBoundsType.VISUAL);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), t);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setAutoReverse(true);
        ft.setCycleCount(-1);
        ft.play();
        FillTransition fit = new FillTransition(Duration.millis(2000), t, Color.RED, Color.DIMGREY);
        fit.setAutoReverse(true);
        fit.setCycleCount(-1);
        fit.play();
        JFXButton play = new JFXButton("Lets Play !");
        play.setRipplerFill(Color.WHITE);
        play.setButtonType(JFXButton.ButtonType.RAISED);
        play.setFont(Font.loadFont("file:resources/fonts/Stya.ttf", 20));
        RotateTransition rt = new RotateTransition(Duration.millis(3000), play);
        rt.setFromAngle(-8);
        rt.setToAngle(8);
        rt.setCycleCount(-1);
        rt.setAutoReverse(true);
        rt.play();
        root.getChildren().addAll(t, play);
        StackPane.setAlignment(t, Pos.CENTER);
        StackPane.setAlignment(play, Pos.BOTTOM_CENTER);
        root.setMargin(play, new Insets(20));
        play.setOnMouseClicked(a -> {
            Stage stage = (Stage) play.getScene().getWindow();
            makeTypeOfPlayerWindow(stage);
        });
    }

    public static void makeTypeOfPlayerWindow(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        FadeTransition ft = new FadeTransition(Duration.millis(500), root);
        ft.setFromValue(0.0);
        ft.setToValue(0.5);
        ft.setAutoReverse(true);
        ft.setCycleCount(2);
        ft.play();
        ft.setOnFinished(a -> {
            root.setBackground(null);
            FadeTransition f = new FadeTransition(Duration.millis(1), root);
            f.setFromValue(1.0);
            f.setToValue(1.0);
            f.setAutoReverse(true);
            f.setCycleCount(2);
            f.play();
            VBox playerBox = new VBox();
            playerBox.setAlignment(Pos.CENTER);
            Text onePlayer = new Text("1 Player");
            onePlayer.setFont(Font.loadFont("file:resources/fonts/Stya.ttf", 50));
            onePlayer.setBoundsType(TextBoundsType.VISUAL);
            onePlayer.setOnMouseEntered(b -> {
                onePlayer.setFill(Color.RED);
                RotateTransition rt = new RotateTransition(Duration.millis(3000), onePlayer);
                rt.setFromAngle(0);
                rt.setToAngle(8);
                rt.setCycleCount(2);
                rt.setAutoReverse(true);
                rt.play();
            });
            onePlayer.setBoundsType(TextBoundsType.VISUAL);

            onePlayer.setOnMouseExited(c -> {
                onePlayer.setFill(Color.BLACK);

            });
            onePlayer.setOnMouseClicked(h -> {
                makeBoard(primaryStage);
            });
            Text twoPlayer = new Text("2 Player");
            twoPlayer.setFont(Font.loadFont("file:resources/fonts/Stya.ttf", 50));
            twoPlayer.setOnMouseEntered(b -> {
                twoPlayer.setFill(Color.RED);
                RotateTransition rt = new RotateTransition(Duration.millis(3000), twoPlayer);
                rt.setFromAngle(0);
                rt.setToAngle(-8);
                rt.setCycleCount(2);
                rt.setAutoReverse(true);
                rt.play();

            });
            twoPlayer.setOnMouseExited(c -> {
                twoPlayer.setFill(Color.BLACK);
            });
            twoPlayer.setBoundsType(TextBoundsType.VISUAL);

            playerBox.getChildren().addAll(onePlayer, twoPlayer);
            root.getChildren().add(playerBox);

            root.setMargin(onePlayer, new Insets(20));
            root.setMargin(twoPlayer, new Insets(20));

            StackPane.setAlignment(playerBox, Pos.CENTER);
            JFXButton back = new JFXButton("back !");
            back.setTextFill(Color.RED);
            back.setButtonType(JFXButton.ButtonType.RAISED);
            back.setFont(Font.loadFont("file:resources/fonts/Stya.ttf", 20));
            RotateTransition rt = new RotateTransition(Duration.millis(3000), back);
            rt.setFromAngle(-8);
            rt.setToAngle(8);
            rt.setCycleCount(-1);
            rt.setAutoReverse(true);
            rt.play();
            StackPane.setAlignment(back, Pos.BOTTOM_CENTER);
            root.getChildren().add(back);
            root.setMargin(back, new Insets(20));
            back.setOnMouseClicked(e -> {
                new AnimationTimer() {
                    long last = 0;

                    @Override
                    public void handle(long l) {
                        if (last == 0) {
                            last = l;
                        } else {
                            if (l - last > 2000000000) {
                                this.stop();
                            }
                        }
                    }
                }.start();
                makeMenu(primaryStage);
            });
        });

        primaryStage.getScene().setRoot(root);

    }
}
