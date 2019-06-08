import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    /*
     *   @param WIDTH is the width of the GUI
     *   @param HEIGHT is the height of the GUI
    */

    private static final float WIDTH = 1400;
    private static final float HEIGHT = 400;


    public boolean freeze = false;

    //Represents how many digits of pi we want to calculate
    public static int digits = 7;

    public double powerOfHundred = Math.pow(100, digits);

    public double timeStep = 1000000;

    Block firstBlock = new Block(100,20, 0, 1, 20);
    Block secondBlock = new Block(300, 100, -5/timeStep, powerOfHundred, 100);


    Label massLabel = new Label("");
    Rectangle firstSquare = new Rectangle(20,20, Color.BLACK);
    Rectangle secondSquare  = new Rectangle(100,100, Color.BLACK);

    public int count = 0;

    Label counter = prepareLabel();



    public void start(Stage primaryStage){


        // Get the graphics context of the canvas
        Group pane = new Group();

        firstBlock.update();
        secondBlock.update();

        firstSquare.setTranslateX(firstBlock.getTranslateX());
        secondSquare.setTranslateX(secondBlock.getTranslateX());
        firstSquare.setTranslateY(firstBlock.getTranslateY());
        secondSquare.setTranslateY(secondBlock.getTranslateY());


        massLabel.setFont(Font.font("Tahoma", 40));
        massLabel.setTranslateX(500);
        massLabel.setTranslateY(100);

        pane.getChildren().add(firstSquare);
        pane.getChildren().add(secondSquare);
        pane.getChildren().add(counter);
        pane.getChildren().add(massLabel);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case SPACE:
                        freeze = true;
                        break;
                    case ENTER:
                        freeze = false;

                }

            }
        });
        primaryStage.setTitle("Pi Collisions");
        primaryStage.setScene(scene);
        primaryStage.show();

        animation();

    }


    /*
     *   @param timer is a javafx object which allows the user to set a repeated operation
     */
    public void animation(){
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(20), e -> {

                    if (freeze == true) {
                        massLabel.setText("Mass of block " + powerOfHundred + " kg ");
                        counter.setText("Collisions : " + count);
                        redraw();
                    }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void redraw() {
        firstSquare.setTranslateX(firstBlock.getTranslateX());
        secondSquare.setTranslateX(secondBlock.getTranslateX());firstSquare.setTranslateX(firstBlock.getTranslateX());
        secondSquare.setTranslateX(secondBlock.getTranslateX());


        for(int i = 0 ; i < timeStep ; i++) {
            if (secondBlock.collide(firstBlock)) {
                double velOne = firstBlock.bounce(secondBlock);
                double velTwo = secondBlock.bounce(firstBlock);
                firstBlock.velX = velOne;
                secondBlock.velX = velTwo;
                count++;
            }

            if (firstBlock.wall()) {
                firstBlock.reverse();
                count++;
            }

            firstBlock.update();
            secondBlock.update();
        }
       // System.out.println(count);

    }

    public Label prepareLabel(){

        Label showCount = new Label();
        showCount.setFont(Font.font("Tahoma", 40));
        showCount.setTextFill(Color.BLACK);
        showCount.setTranslateX(500);
        showCount.setTranslateY(50);
        return showCount;

    }




}
