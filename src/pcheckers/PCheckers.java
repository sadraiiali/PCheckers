/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcheckers;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.scene.control.SelectedCellsMap;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;

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
import javafx.scene.shape.Circle;
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

    public static ImageView selected = null;
    public static ImageView destination = null;
    public static boolean setPlay = true;
    public static FadeTransition selectedFade = new FadeTransition();
    public static Image blackBead = new Image("black.png");
    public static Image redBead = new Image("red.png");
    public static ImageView[] ways = new ImageView[2];
    public static ArrayList<ImageView> beads = new ArrayList<ImageView>();
    public static ArrayList<ImageView> tiles = new ArrayList<ImageView>();
    public static int tileSize;
    public static int tileSpace;
    public static FadeTransition selectedTileF1 = new FadeTransition();
    public static FadeTransition selectedTileF2 = new FadeTransition();
    public static int redBeadsNumber = 12;
    public static int blackBeadsNumber = 12;

    private static void moveSelected(ImageView dest) {
//        TranslateTransition goTo = new TranslateTransition(Duration.seconds(0.5), selected);
//        goTo.setByX(dest.getX() - selected.getLayoutX());
//        goTo.setByY(dest.getY() - selected.getLayoutY());
//        goTo.setCycleCount(1);
//        goTo.play();
//        selected.setX(dest.getX());
//        selected.setY(dest.getY());
        if (selected.getImage().equals(redBead) && setPlay) {
            attack(dest);
            selected.setLayoutX(dest.getX());
            selected.setLayoutY(dest.getY());
//        goTo.setOnFinished(a -> {
//
//            clearSelections();
//        });
//        ScaleTransition goToScale = new ScaleTransition(Duration.seconds(0.25), selected);
//        goToScale.setFromX(1);
//        goToScale.setFromY(1);
//        goToScale.setToX(1.25);
//        goToScale.setToY(1.25);
//        goToScale.setCycleCount(2);
//        goToScale.setAutoReverse(true);
//        goToScale.play();
            clearSelections();
            setPlay = false;
        } else {
            if (!selected.getImage().equals(redBead) && !setPlay) {
                attack(dest);
                selected.setLayoutX(dest.getX());
                selected.setLayoutY(dest.getY());
                clearSelections();
                setPlay = true;

            }
        }
    }

    private static void clearSelections() {

        selectedTileF1.stop();
        selectedTileF1.setNode(ways[0]);
        selectedTileF1.setCycleCount(2);
        selectedTileF1.setDuration(Duration.seconds(0.2));
        selectedTileF1.play();
        selectedTileF2.stop();
        selectedTileF2.setNode(ways[1]);
        selectedTileF2.setCycleCount(2);
        selectedTileF2.setDuration(Duration.seconds(0.2));
        selectedTileF2.play();
        selectedFade.stop();
        selectedFade.setCycleCount(2);
        selectedFade.setDuration(Duration.seconds(0.2));
        selectedFade.play();
        selected = null;
        ways[0] = null;
        ways[1] = null;
    }

    private static boolean checkIsNotOurs(double x, double y) {
        for (int i = 0; i < beads.size(); i++) {
            if (beads.get(i).getBoundsInParent().contains(x + 1, y + 1) && !beads.get(i).getImage().equals(selected.getImage())) {
                return true;
            }
        }
        return false;
    }

    private static void attack(ImageView dest) {

        for (int i = 0; i < beads.size(); i++) {
            boolean eq = !selected.getImage().equals(beads.get(i).getImage());
            double x = ((dest.getX() + selected.getLayoutX()) / 2) + (tileSize / 2);
            double y = ((dest.getY() + selected.getLayoutY()) / 2) + (tileSize / 2);
            boolean eq2 = beads.get(i).getBoundsInParent().contains(x, y);
            System.out.println(eq2);
            System.out.println(eq);
            System.out.println(x + "  " + y + "  " + beads.get(i).getLayoutX() + 10 + "  " + beads.get(i).getLayoutY() + 10);
            if (eq && eq2) {
                System.out.println("something");
                removeBead(i);
            }
        }
    }

    private static void removeBead(int i) {
        beads.get(i).setVisible(false);
        if (beads.get(i).getImage().equals(redBead)) {
            redBeadsNumber--;
        } else {
            blackBeadsNumber--;
        }
        beads.remove(i);
    }

    private static void endGame(Stage ps) {
        
    }

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

        int list = (ps.getScene().getHeight() < ps.getScene().getWidth()) ? (int) ps.getScene().getHeight() : (int) ps.getScene().getWidth();
        tileSize = (list - 8 * 2) / 8;
        tileSpace = 2;
        int initX = 0;
        int initY = 0;
        Image whiteTile = new Image("white_tile.png");
        Image blackTile = new Image("black_tile.png");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ImageView temp = new ImageView((((i + j) % 2 == 0) ? whiteTile : blackTile));
                temp.setX(initX + j * tileSize + tileSpace * j);
                temp.setY(initY + i * tileSize + tileSpace * i);
                temp.setFitHeight(tileSize);
                temp.setFitWidth(tileSize);
                temp.setOnMouseClicked(a -> {
                    if (selected != null && checkMove((ImageView) a.getPickResult().getIntersectedNode())) {
                        moveSelected((ImageView) a.getPickResult().getIntersectedNode());
                        if (redBeadsNumber == 0 || blackBeadsNumber == 0) {
                            endGame(ps);
                        }
                    } else {
                        clearSelections();
                    }
                });
                root.getChildren().add(temp);
                tiles.add(temp);
            }
        }
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
        root.getChildren().add(back);
        back.setLayoutX(20);
        back.setLayoutY(list);
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
            makeTypeOfPlayerWindow(ps);
            clearSelections();

        });
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                ImageView temp1 = new ImageView(blackBead);
                ImageView temp2 = new ImageView(redBead);
                temp1.setLayoutX((((i + 1) % 2) * tileSize) + (((i + 1) % 2) * tileSpace) + (2 * (j) * tileSize) + (2 * (j) * tileSpace));
                temp1.setLayoutY(i * tileSize + ((i) * tileSpace));
                temp2.setLayoutX((((i) % 2) * tileSize) + (((i) % 2) * tileSpace) + (2 * (j) * tileSize) + (2 * (j) * tileSpace));
                temp2.setLayoutY((i + 5) * tileSize + ((i + 5) * tileSpace));
                temp1.setFitHeight(tileSize);
                temp1.setFitWidth(tileSize);
                temp2.setFitHeight(tileSize);
                temp2.setFitWidth(tileSize);
                TranslateTransition tt = new TranslateTransition(Duration.millis(2000), temp2);
                tt.setFromX(-1 * j * 2 * tileSize);
                tt.setToX(temp2.getX());
                TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), temp1);
                tt2.setFromX(-1 * j * 2 * tileSize);
                tt2.setToX(temp1.getX());
                temp1.setOnMouseEntered(a -> {
                    temp1.setEffect(new Shadow(10, Color.YELLOW));
                });
                temp1.setOnMouseExited(a -> {
                    temp1.setEffect(null);
                });
                temp2.setOnMouseEntered(a -> {
                    temp2.setEffect(new Shadow(10, Color.YELLOW));
                });
                temp2.setOnMouseExited(a -> {
                    temp2.setEffect(null);
                });
                temp1.setOnMouseClicked(b -> {
                    if (selected == null) {
                        selected = (ImageView) b.getPickResult().getIntersectedNode();
                        addWays();
                        startSelectedFade();
                    } else {
                        clearSelections();
                    }
                });
                temp2.setOnMouseClicked(b -> {
                    if (selected == null) {
                        selected = (ImageView) b.getPickResult().getIntersectedNode();
                        addWays();
                        startSelectedFade();
                    } else {
                        clearSelections();
                    }
                });
                tt.play();
                tt2.play();
                root.getChildren().addAll(temp1, temp2);
                beads.add(temp2);
                beads.add(temp1);

            }
        }
    }

    public static void startSelectedFade() {
        selectedFade.setNode(selected);
        selectedFade.setFromValue(1.0);
        selectedFade.setToValue(0.5);
        selectedFade.setCycleCount(-1);
        selectedFade.setAutoReverse(true);
        selectedFade.setDuration(Duration.seconds(2.0));
        selectedFade.play();
        if (ways[0] != null) {
            selectedTileF1.setNode(ways[0]);
            selectedTileF1.setFromValue(1.0);
            selectedTileF1.setToValue(0.0);
            selectedTileF1.setCycleCount(-1);
            selectedTileF1.setAutoReverse(true);
            selectedTileF1.setDuration(Duration.seconds(1.0));
            selectedTileF1.play();
        }
        if (ways[1] != null) {
            selectedTileF2.setNode(ways[1]);
            selectedTileF2.setFromValue(1.0);
            selectedTileF2.setToValue(0.0);
            selectedTileF2.setCycleCount(-1);
            selectedTileF2.setAutoReverse(true);
            selectedTileF2.setDuration(Duration.seconds(1.0));
            selectedTileF2.play();
        }
    }

    public static boolean checkMove(ImageView selectedTile) {
        if (selectedTile != ways[0] && selectedTile != ways[1]) {
            return false;
        }
        return true;
    }

    public static void addWays() {
        if (selected.getImage().equals(redBead)) {
            double x = selected.getLayoutX() + (1.5 * tileSize);
            double y = selected.getLayoutY() - (tileSize / 2);

            //bala rast !
            if (!checkTile(x, y)) {
                for (int i = 0; i < tiles.size(); i++) {
                    if (tiles.get(i).contains(x, y)) {
                        ways[0] = tiles.get(i);
                        break;
                    }
                }
            } else {
                double x2 = selected.getLayoutX() + tileSize * 2 + tileSize / 2;
                double y2 = selected.getLayoutY() - (1.5 * tileSize);
                if (!checkTile(x2, y2) && checkIsNotOurs(x, y)) {
                    for (int i = 0; i < tiles.size(); i++) {
                        if (tiles.get(i).contains(selected.getLayoutX() + tileSize * 2 + tileSize / 2, selected.getLayoutY() - (1.5 * tileSize))) {
                            ways[0] = tiles.get(i);
                            break;
                        }
                    }
                }
            }
            //bala chap 
            x = selected.getLayoutX() - (0.5 * tileSize);
            y = selected.getLayoutY() - (tileSize / 2);
            if (!checkTile(x, y)) {
                for (int i = 0; i < tiles.size(); i++) {
                    if (tiles.get(i).contains(x, y)) {
                        ways[1] = tiles.get(i);
                        break;
                    }
                }
            } else {
                double x2 = selected.getLayoutX() - (1.5 * tileSize);
                double y2 = selected.getLayoutY() - (1.5 * tileSize);
                if (!checkTile(x2, y2) && checkIsNotOurs(x, y)) {
                    for (int i = 0; i < tiles.size(); i++) {
                        if (tiles.get(i).contains(x2, y2)) {
                            ways[1] = tiles.get(i);
                            break;
                        }
                    }
                }
            }
        } else {

            //for black 
            //paiin rast ! 
            double x = selected.getLayoutX() + (1.5 * tileSize);
            double y = selected.getLayoutY() + (1.5 * tileSize);
            if (!checkTile(x, y)) {
                for (int i = 0; i < tiles.size(); i++) {

                    if (tiles.get(i).contains(x, y)) {
                        ways[0] = tiles.get(i);
                        break;
                    }
                }
            } else {
                double x2 = selected.getLayoutX() + (2.5 * tileSize);
                double y2 = selected.getLayoutY() + (2.5 * tileSize);
                if (!checkTile(x2, y2)) {
                    for (int i = 0; i < tiles.size(); i++) {
                        if (tiles.get(i).contains(x2, y2) && checkIsNotOurs(x, y)) {
                            ways[0] = tiles.get(i);
                            break;
                        }
                    }
                }
            }
            //pain chap
            x = selected.getLayoutX() - (0.5 * tileSize);
            y = selected.getLayoutY() + (1.5 * tileSize);

            if (!checkTile(x, y)) {
                for (int i = 0; i < tiles.size(); i++) {
                    if (tiles.get(i).contains(x, y)) {
                        ways[1] = tiles.get(i);
                        break;
                    }
                }
            } else {
                double x2 = selected.getLayoutX() - (1.5 * tileSize);
                double y2 = selected.getLayoutY() + (2.5 * tileSize);
                if (!checkTile(x2, y2)) {
                    for (int i = 0; i < tiles.size(); i++) {
                        if (tiles.get(i).contains(x2, y2) && checkIsNotOurs(x, y)) {
                            ways[1] = tiles.get(i);
                            break;
                        }
                    }
                }
            }
        }

    }

    public static boolean checkTile(double x, double y) {
        for (int i = 0; i < beads.size(); i++) {
            if (beads.get(i).getBoundsInParent().contains(x + 1, y + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void makeMenu(Stage primaryStage) {
        StackPane root = new StackPane();
        FadeTransition rootFade = new FadeTransition(Duration.seconds(6), root);
        rootFade.setFromValue(0.0);
        rootFade.setToValue(1.0);
        rootFade.play();
        Scene menuScene = new Scene(root, 500, 550);
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
