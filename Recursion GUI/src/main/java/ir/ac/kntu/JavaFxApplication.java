package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFxApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane, 350, 200);

        getInput(stage, gridPane);

        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Recursion in JavaFX!");

        stage.setScene(scene);
        stage.show();
    }

    //fist window that get width,height,level from user and handle show button
    public void getInput(Stage stage, GridPane gridPane) {

        gridPane.add(new Label("Enter width:"), 0, 0);
        TextField width = new TextField();
        gridPane.add(width, 1, 0);

        gridPane.add(new Label("Enter height:"), 0, 1);
        TextField height = new TextField();
        gridPane.add(height, 1, 1);

        gridPane.add(new Label("Enter a large level:"), 0, 2);
        TextField level = new TextField();
        gridPane.add(level, 1, 2);

        Button showButton = new Button("Show new random Mondrian");
        gridPane.add(showButton, 1, 3);
        GridPane.setHalignment(showButton, HPos.LEFT);

        // specifying what happen if show button pressed
        showButton.setOnAction(e -> {
            int w = Integer.parseInt(width.getText());
            int h = Integer.parseInt(height.getText());
            int num = Integer.parseInt(level.getText());
            showMondrian(stage, w, h, num);
        });
    }

    //generate new window to show recursive Mondrian
    private void showMondrian(Stage primaryStage, int w, int h, int num) {

        Pane root = new Pane();
        //call recursive method
        RecursiveGUI.drawPicture(root, 0, 0, w, h, num);

        Scene secondScene = new Scene(root, w, h);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Random Recursive Mondrian");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() - 100);

        newWindow.show();
    }
}
