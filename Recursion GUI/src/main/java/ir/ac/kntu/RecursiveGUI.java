package sample;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class RecursiveGUI{

    private static Color[] colors = new Color[]{Color.RED, Color.WHITE, Color.BLUE, Color.YELLOW};

    /**
     *
     * @param root
     * @param x the starting point of rectangle on the axis x
     * @param y the starting point of rectangle on the axis y
     * @param w rectangle width
     * @param h rectangle height
     * @param n levels
     */
    public static void drawPicture(Pane root, float x, float y, float w, float h, int n) {
        Random rand = new Random();
        //use Platform.runLater() to show recursive better
        Platform.runLater(() -> {
            int randomOffset;
            if (h<20 || w<20 || n==0) {
                return;
            }
            if (w > h) {
                //get random number [0,width)
                randomOffset = rand.nextInt((int) w);
                drawRectangle(root, x, y, randomOffset, h, randomColor());
                drawRectangle(root, x + randomOffset, y, w - randomOffset, h, randomColor());

                drawPicture(root, x, y, randomOffset, h, n-1);
                drawPicture(root, randomOffset + x, y, w - randomOffset, h, n-1);//add x +rand
            } else {
                //get random number [0,height)
                randomOffset = rand.nextInt((int) h);
                drawRectangle(root, x, y, w, randomOffset, randomColor());
                drawRectangle(root, x, y + randomOffset, w, h - randomOffset, randomColor());

                drawPicture(root, x, y, w, randomOffset, n-1);
                drawPicture(root, x, y + randomOffset, w, h - randomOffset, n-1);
            }
        });

    }

    //then this method creat new rectangle with random color and add it to root
    private static void drawRectangle(Pane root, float x, float y, float width, float height,
                               Color color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(color);
        rectangle.setStroke(Color.BLACK);
        root.getChildren().add(rectangle);
    }

    //get one random color among four colors
    private static Color randomColor() {
        return colors[(int) (Math.random() * 4)];
    }
}
