//Controller
package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Controller {

    @FXML
    Canvas joonis = new Canvas();
    @FXML
    CheckBox check1 = new CheckBox();
    @FXML
    CheckBox check2 = new CheckBox();
    @FXML
    CheckBox check3 = new CheckBox();

    private static ArrayList<Integer> silinderChordX = new ArrayList<>();
    private static ArrayList<Integer> silinderChordY = new ArrayList<>();
    private static ArrayList<Integer> coneChordX = new ArrayList<>();
    private static ArrayList<Integer> coneChordY = new ArrayList<>();
    private static ArrayList<Integer> ballChordX = new ArrayList<>();
    private static ArrayList<Integer> ballChordY = new ArrayList<>();
    private static double s = 40; //to gen random
    private static int newX;
    private static int newY;
    private static int new1X;
    private static int new1Y;
    private static int new2X;
    private static int new2Y;
    private static boolean drawn1 = false;
    private static boolean drawn2 = false;
    private static boolean drawn3 = false;

    //Connect to Database and get info
    static SQLDao dao = new SQLDao();
    static Map<String, Shape> data = dao.selectAll();
    static Shape Cilinder = data.get("Silinder");
    static Shape Cone = data.get("Koonus");
    static Shape Ball = data.get("Pall");
    static Volume BallVolume = new Volume(Ball);



    public void vajuta(MouseEvent event) {

        GraphicsContext gc = joonis.getGraphicsContext2D();
        gc.setFill(Color.BLACK);


        if (check1.isSelected()) {
            if (!drawn1) {
                drawn1 = true;
                gc.fillText(Cilinder.name + " radius: " + Cilinder.radius + " height: " + Cilinder.height, 300, 400);
            }
            DrawCilinder(gc);
        }
        if (check2.isSelected()) {
            if (!drawn2) {
                drawn2 = true;
                gc.fillText(Cone.name + " radius: " + Cone.radius + " height: " + Cone.height, 300, 385);
            }
            DrawCone(gc);
        }
        if (check3.isSelected()) {
            if (!drawn3) {
                drawn3 = true;
                gc.fillText(Ball.name + " radius: " + Ball.radius, 300, 370);
            }
            DrawBall(gc);
        }
//
    }

    private static void DrawCilinder(GraphicsContext gc) {
        Random rand = new Random();
/*      double x = s + (400 - s) * rand.nextDouble();
        double y = s + (400 - s) * rand.nextDouble();*/
        double x = rand.nextInt(300) + 1;
        double y = rand.nextInt(300) + 1;
        gc.setFill(Color.BLACK);

        double[] dashes = { 2 };
        gc.setLineDashes(dashes);
        //Drawing a cilinder.
        gc.strokeOval(x, y, Cilinder.radius, Cilinder.radius / 4);
        gc.strokeLine(x - 1, y + Cilinder.radius / 8, x - 1, y + Cilinder.radius / 8 + Cilinder.height);
        gc.strokeLine(x + Cilinder.radius, y + Cilinder.radius / 8, x + Cilinder.radius, y + Cilinder.radius / 8 + Cilinder.height);
        gc.strokeOval(x, y + Cilinder.height, Cilinder.radius, Cilinder.radius / 4);


        //adding Chrods to array.
        silinderChordX.add((int) x);
        silinderChordY.add((int) y);
    }

    private static void DrawCilinderColor(GraphicsContext gc) {
        //Coloring cilinder ovals with old chrods.
        gc.setFill(Color.RED);
        gc.fillOval(newX, newY, Cilinder.radius, Cilinder.radius / 4);
        gc.fillOval(newX, newY + Cilinder.height, Cilinder.radius, Cilinder.radius / 4);

    }


    private void DrawCone(GraphicsContext gc) {
        Random rand = new Random();
        double x = rand.nextInt(300) + 1;
        double y = rand.nextInt(200) + 1;
        gc.setFill(Color.BLACK);
        gc.setLineDashes(1);

        //Drawing a cone.
        gc.strokeLine(x, y, x + Cone.radius, y + Cone.height);
        gc.strokeLine(x, y, x - Cone.radius, y + Cone.height);
        gc.strokeOval(x - Cone.radius, y + Cone.height - Cone.radius / 4, 2 * Cone.radius, Cone.radius / 2);
        //adding chrods to array.
        coneChordX.add((int) x);
        coneChordY.add((int) y);
    }

    private static void DrawConeColor(GraphicsContext gc) {
        //Coloring cilinder ovals with old chrods.
        gc.setFill(Color.RED);
        gc.fillOval(new1X - Cone.radius, new1Y + Cone.height - Cone.radius / 4, 2 * Cone.radius, Cone.radius / 2);
    }


    private static void DrawBall(GraphicsContext gc) {
        Random rand = new Random();
        double x = rand.nextInt(300) + 1;
        double y = rand.nextInt(300) + 1;
        //teen kera.
        gc.setLineDashes(1);
        gc.strokeOval(x, y, Ball.radius, Ball.radius);

        double[] dashes = { 2, 3, 6, 3 };
        gc.setLineDashes(dashes);
        gc.strokeOval(x, y + 3 * Ball.radius / 8, Ball.radius, Ball.radius / 4);
        //adding chrods to array.
        ballChordX.add((int) x);
        ballChordY.add((int) y);
    }

    private static void DrawBallColor(GraphicsContext gc) {
        //Coloring cilinder ovals with old chrods.
        gc.setFill(Color.RED);
        gc.fillOval(new2X, new2Y + 3 * Ball.radius / 8, Ball.radius, Ball.radius / 4);
    }

    @FXML
    public void liiguta(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            int itemCount = silinderChordX.size();
            for (int i = 0; i < itemCount; i++) {
                newX = silinderChordX.get(i);
                newY = silinderChordY.get(i);
                GraphicsContext gc = joonis.getGraphicsContext2D();
                DrawCilinderColor(gc);

            }
            int itemCount1 = coneChordX.size();
            for (int i = 0; i < itemCount1; i++) {
                new1X = coneChordX.get(i);
                new1Y = coneChordY.get(i);
                GraphicsContext gc = joonis.getGraphicsContext2D();
                DrawConeColor(gc);
            }
            int itemCount2 = ballChordX.size();
            for (int i = 0; i < itemCount2; i++) {
                new2X = ballChordX.get(i);
                new2Y = ballChordY.get(i);
                GraphicsContext gc = joonis.getGraphicsContext2D();
                DrawBallColor(gc);
            }
        }
        if (event.getCode() == KeyCode.F) {
            Random rand = new Random();
            int r1 = rand.nextInt(255) + 1;
            int r2 = rand.nextInt(255) + 1;
            int r3 = rand.nextInt(255) + 1;
            GraphicsContext gc = joonis.getGraphicsContext2D();
            gc.clearRect(0, 0, 550, 550);
            gc.setFill(Color.rgb(r1, r2, r3));
            gc.fillRect(0, 0, 550, 550);



            //tyhjendan arrayd.
            silinderChordX = new ArrayList<>();
            silinderChordY = new ArrayList<>();
            coneChordX = new ArrayList<>();
            coneChordY = new ArrayList<>();
            ballChordX = new ArrayList<>();
            ballChordY = new ArrayList<>();
            drawn1 = false;
            drawn2 = false;
            drawn3 = false;
        }

    }

    public void initialize() {

    }
}