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

    private static ArrayList<Integer> pikkusChordX = new ArrayList<>();
    private static ArrayList<Integer> pikkusChordY = new ArrayList<>();
    private static ArrayList<Integer> laiusChordX = new ArrayList<>();
    private static ArrayList<Integer> laiusChordY = new ArrayList<>();
    private static ArrayList<Integer> uksChordX = new ArrayList<>();
    private static ArrayList<Integer> uksChordY = new ArrayList<>();
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
    static Map<String, sample.Tuba> data = dao.selectAll();
    static sample.Tuba Tuba = data.get("13");
    //static Volume BallVolume = new Volume(Ball);



    public void vajuta(MouseEvent event) {

        GraphicsContext gc = joonis.getGraphicsContext2D();
        gc.setFill(Color.BLACK);


        if (check1.isSelected()) {
            if (!drawn1) {
                drawn1 = true;
                double pindala = (Tuba.laius * Tuba.pikkus) - (Tuba.uks - Tuba.uks);
                gc.fillText(Tuba.name + " pikkus: " + Tuba.pikkus + " laius: " + Tuba.laius + " S: " + pindala, 200, 400);
            }
            DrawRoom(gc);
        }
        int itemCount = pikkusChordX.size();
        for (int i = 0; i < itemCount; i++) {
            newX = pikkusChordX.get(i);
            newY = pikkusChordY.get(i);
            //GraphicsContext gc = joonis.getGraphicsContext2D();
            DrawRoomColor(gc);

        }
//
    }

    private static void DrawRoom(GraphicsContext gc) {
        Random rand = new Random();
/*      double x = s + (400 - s) * rand.nextDouble();
        double y = s + (400 - s) * rand.nextDouble();*/
        double x = 30;
        double y = 30;
        gc.setFill(Color.BLACK);

        //double[] dashes = { 2 };
        //gc.setLineDashes(dashes);
        //Drawing a room.
//        gc.strokeOval(x, y, Cilinder.radius, Cilinder.radius / 4);
//        gc.strokeLine(x - 1, y + Cilinder.radius / 8, x - 1, y + Cilinder.radius / 8 + Cilinder.height);
//        gc.strokeLine(x + Cilinder.radius, y + Cilinder.radius / 8, x + Cilinder.radius, y + Cilinder.radius / 8 + Cilinder.height);
//        gc.strokeOval(x, y + Cilinder.height, Cilinder.radius, Cilinder.radius / 4);
        gc.strokeLine(x, y, x, Tuba.pikkus);
        gc.strokeLine(x, Tuba.pikkus, Tuba.laius, Tuba.pikkus);
        gc.strokeLine(Tuba.laius, Tuba.pikkus, Tuba.laius, Tuba.pikkus-100);
        gc.strokeLine(Tuba.laius, Tuba.pikkus-100, Tuba.laius-Tuba.uks, Tuba.pikkus-100);
        gc.strokeLine(Tuba.laius-Tuba.uks, Tuba.pikkus-100, Tuba.laius-Tuba.uks, Tuba.pikkus-100-Tuba.uks);
        gc.strokeLine(Tuba.laius-Tuba.uks, Tuba.pikkus-100-Tuba.uks, Tuba.laius, Tuba.pikkus-100-Tuba.uks);
        gc.strokeLine(Tuba.laius, Tuba.pikkus-100-Tuba.uks, Tuba.laius, Tuba.pikkus-Tuba.uks*3.5);
        gc.strokeLine(Tuba.laius, Tuba.pikkus-100-Tuba.uks*2.5, Tuba.laius, y);
        gc.strokeLine(Tuba.laius, y,x, y );



        //adding Chrods to array.
        pikkusChordX.add((int) x);
        pikkusChordY.add((int) y);
    }

    private static void DrawRoomColor(GraphicsContext gc) {
        //Coloring cilinder ovals with old chrods.
        //gc.setFill(Color.RED);
//        gc.fillOval(newX, newY, Cilinder.radius, Cilinder.radius / 4);
//        gc.fillOval(newX, newY + Cilinder.height, Cilinder.radius, Cilinder.radius / 4);
        gc.setStroke(Color.RED);
        gc.strokeLine(newX, newY, newX, Tuba.pikkus);
        gc.strokeLine(newX, Tuba.pikkus, Tuba.laius, Tuba.pikkus);
        gc.strokeLine(Tuba.laius, Tuba.pikkus, Tuba.laius, Tuba.pikkus-100);
        gc.strokeLine(Tuba.laius, Tuba.pikkus-100, Tuba.laius-Tuba.uks, Tuba.pikkus-100);
        gc.strokeLine(Tuba.laius-Tuba.uks, Tuba.pikkus-100, Tuba.laius-Tuba.uks, Tuba.pikkus-100-Tuba.uks);
        gc.strokeLine(Tuba.laius-Tuba.uks, Tuba.pikkus-100-Tuba.uks, Tuba.laius, Tuba.pikkus-100-Tuba.uks);
        gc.strokeLine(Tuba.laius, Tuba.pikkus-100-Tuba.uks, Tuba.laius, Tuba.pikkus-Tuba.uks*3.5);
        gc.strokeLine(Tuba.laius, Tuba.pikkus-100-Tuba.uks*2.5, Tuba.laius, newY);
        gc.strokeLine(Tuba.laius, newY,newX, newY );

    }


//    private void DrawCone(GraphicsContext gc) {
//        Random rand = new Random();
//        double x = rand.nextInt(300) + 1;
//        double y = rand.nextInt(200) + 1;
//        gc.setFill(Color.BLACK);
//        gc.setLineDashes(1);
//
//        //Drawing a cone.
//        gc.strokeLine(x, y, x + Cone.radius, y + Cone.height);
//        gc.strokeLine(x, y, x - Cone.radius, y + Cone.height);
//        gc.strokeOval(x - Cone.radius, y + Cone.height - Cone.radius / 4, 2 * Cone.radius, Cone.radius / 2);
//        //adding chrods to array.
//        laiusChordX.add((int) x);
//        laiusChordY.add((int) y);
//    }
//
//    private static void DrawConeColor(GraphicsContext gc) {
//        //Coloring cilinder ovals with old chrods.
//        gc.setFill(Color.RED);
//        gc.fillOval(new1X - Cone.radius, new1Y + Cone.height - Cone.radius / 4, 2 * Cone.radius, Cone.radius / 2);
//    }
//
//
//    private static void DrawBall(GraphicsContext gc) {
//        Random rand = new Random();
//        double x = rand.nextInt(300) + 1;
//        double y = rand.nextInt(300) + 1;
//        //teen kera.
//        gc.setLineDashes(1);
//        gc.strokeOval(x, y, Ball.radius, Ball.radius);
//
//        double[] dashes = { 2, 3, 6, 3 };
//        gc.setLineDashes(dashes);
//        gc.strokeOval(x, y + 3 * Ball.radius / 8, Ball.radius, Ball.radius / 4);
//        //adding chrods to array.
//        uksChordX.add((int) x);
//        uksChordY.add((int) y);
//    }
//
//    private static void DrawBallColor(GraphicsContext gc) {
//        //Coloring cilinder ovals with old chrods.
//        gc.setFill(Color.RED);
//        gc.fillOval(new2X, new2Y + 3 * Ball.radius / 8, Ball.radius, Ball.radius / 4);
//    }

    @FXML
    public void liiguta(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            GraphicsContext gc = joonis.getGraphicsContext2D();
            int itemCount = pikkusChordX.size();
//            for (int i = 0; i < itemCount; i++) {
//                newX = pikkusChordX.get(i);
//                newY = pikkusChordY.get(i);
//
//
//                DrawRoomColor(gc);
//
//            }
            double pindala = (Tuba.laius * Tuba.pikkus) - (Tuba.uks - Tuba.uks);
            gc.fillText(Tuba.name + " pikkus: " + Tuba.pikkus + " laius: " + Tuba.laius + " S: " + pindala, 200, 400);
//            int itemCount1 = laiusChordX.size();
//            for (int i = 0; i < itemCount1; i++) {
//                new1X = laiusChordX.get(i);
//                new1Y = laiusChordY.get(i);
//                GraphicsContext gc = joonis.getGraphicsContext2D();
//                DrawConeColor(gc);
//            }
//            int itemCount2 = uksChordX.size();
//            for (int i = 0; i < itemCount2; i++) {
//                new2X = uksChordX.get(i);
//                new2Y = uksChordY.get(i);
//                GraphicsContext gc = joonis.getGraphicsContext2D();
//                DrawBallColor(gc);
//            }
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
            pikkusChordX = new ArrayList<>();
            pikkusChordY = new ArrayList<>();
            laiusChordX = new ArrayList<>();
            laiusChordY = new ArrayList<>();
            uksChordX = new ArrayList<>();
            uksChordY = new ArrayList<>();
            drawn1 = false;
            drawn2 = false;
            drawn3 = false;
        }

    }

    public void initialize() {
        GraphicsContext gc = joonis.getGraphicsContext2D();
        DrawRoom(gc);
    }
}