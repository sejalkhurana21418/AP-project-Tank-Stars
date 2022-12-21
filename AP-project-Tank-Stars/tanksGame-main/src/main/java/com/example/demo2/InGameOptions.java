package com.example.demo2;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class InGameOptions {
    public static Scene scene;
    @FXML
    private Label welcomeText;
    public ImageView winnerGIF,explosionFIF;
    public String Winner = "";
    public void left(MouseEvent mouseEvent) {

        if(game.current_turn) {
            game.player1.game_image.setX(game.player1.game_image.getX() - 20);
        }
        else{
            game.player2.game_image.setX(game.player2.game_image.getX() - 20);
        }
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    public void right(MouseEvent mouseEvent) {

        if(game.current_turn) {
            game.player1.game_image.setX(game.player1.game_image.getX() + 20);
        }
        else{
            game.player2.game_image.setX(game.player2.game_image.getX() + 20);
        }
    }

    class Tank{
        public int health=100;
        public String image_name="res/[removal.ai]_39a7666a-ebad-4b6c-ae82-ef19ec518fba.png";
        public int current_cordinate=20;

        public int power=50;
        public int angle=50;
        Text game_health, game_power;
        Slider game_angle, game_health_slider;

        ImageView game_image;

        boolean updateHealth(int decrement){
            int prev_health = Integer.parseInt(game_health.getText());
            int newHealth =  Integer.max(prev_health- decrement, 0);
            game_health.setText(String.valueOf(newHealth));
            health = newHealth;
            game_health_slider.setValue(newHealth);
            return newHealth == 0;
        }

    }
    class Weapon{
        public int damage;
        public String name;
        Weapon(int damage, String name){
            this.damage = damage;
            this.name = name;
        }
        Text game_health, game_power;
    }


    class Game{
        Tank player1, player2;
        ArrayList<Weapon> weapons;

        Text status;
        boolean current_turn = true;

        Game(){
            player1 = new Tank();
            player1.current_cordinate = 20;
            player1.image_name="res/[removal.ai]_39a7666a-ebad-4b6c-ae82-ef19ec518fba.png";

            player2 = new Tank();
            player2.current_cordinate = 80;
            player2.image_name="res/[removal.ai]_tmp-63809d7ca9270.png";

            weapons = new ArrayList<Weapon>();
            weapons.add(new Weapon(20, "nuke"));
            weapons.add(new Weapon(30, "missile"));
            weapons.add(new Weapon(10, "single shot"));
        }
    }
    Game game;


    public void initialize(){
        game = new Game();
    }

    public void fire(MouseEvent mouseEvent) throws URISyntaxException, InterruptedException {

        Button Fire = (Button) scene.lookup("#fire");
        Fire.setText("fire!!!");

        Random rd = new Random();
        int random_number = rd.nextInt(11);
        int dec = 15 + random_number;

        if(!game.current_turn){
            // player 1
            if (game.player1.updateHealth(dec)){
                Winner = "Player 2 wins ... ";
                delay(4000, ()->game.status.setText(Winner));
                winnerGIF.setVisible(true);
                delay(4000 , ()-> winnerGIF.setVisible(false));
                delay(4000 , ()-> System.exit(0));
//                System.exit(0);
            }
            else
                game.status.setText("PLAYER 1's turn");
        }
        else{
            //player 2
            if (game.player2.updateHealth(dec)){
                Winner = "Player 1 wins";
                game.status.setText(Winner);
                winnerGIF.setVisible(true);
                delay(4000 , ()-> winnerGIF.setVisible(false));
                delay(4000 , ()-> System.exit(0));
            }
            else
                game.status.setText("PLAYER 2's turn");
        }
        explosionFIF.setVisible(true);
        delay(500, ()->explosionFIF.setVisible(false));

        game.current_turn = !game.current_turn;

        Fire.setText("FIRE");
    }

    public void startGame(MouseEvent mouseEvent) throws URISyntaxException {
        scene = ((Node)mouseEvent.getSource()).getScene();
        game.player1.game_health = (Text) scene.lookup("#player1Health");
        game.player2.game_health = (Text) scene.lookup("#player2Health");

        game.player1.game_power = (Text) scene.lookup("#player1Power");
        game.player2.game_power = (Text) scene.lookup("#player2Power");

        game.player1.game_angle = (Slider) scene.lookup("#Player1Angle");
        game.player2.game_angle = (Slider) scene.lookup("#Player2Angle");

        game.player1.game_image = (ImageView) scene.lookup("#player1Image");
        game.player2.game_image = (ImageView) scene.lookup("#player2Image");

        game.player1.game_health_slider = (Slider) scene.lookup("#player1HealthSlider");
        game.player2.game_health_slider = (Slider) scene.lookup("#player2HealthSlider");

        game.status = (Text) scene.lookup("#turn");

        /*
        File file1 = new File("res/logo.png");
        URI uri = new URI("res/logo.png");
        Image image1 = new Image( uri.toString());
        player1Image.setImage(image1);


        File file2 = new File(game.player1.image_name);
        Image image2 = new Image(file2.toURI().toString());
        player2Image.setImage(image2);*/

        ComboBox<String> comboBox = (ComboBox) scene.lookup("#Weapons");
        comboBox.setEditable(true);
        comboBox.setValue("select weapon");
        comboBox.getItems().addAll("nuke", "missile", "single shot");

        winnerGIF = (ImageView) scene.lookup("#winnerGIF");
        explosionFIF = (ImageView) scene.lookup("#explosionGIF");
        Button startGame = (Button) scene.lookup("#startGame");
        startGame.setVisible(false);
    }
}
