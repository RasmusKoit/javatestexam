package sample;

import java.util.Map;
import java.util.Scanner;

//JavaFx part
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import static sample.Controller.dao;

public class testShape extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Eksamiks kordamine");
        primaryStage.setScene(new Scene(root, 550, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        String ruum;
        Float pikkus;
        Float laius;
        Float uks;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta ruumi andmed: ID, Pikkus, Laius, Uks");
        ruum = scanner.nextLine();
        pikkus = scanner.nextFloat();
        laius = scanner.nextFloat();
        uks = scanner.nextFloat();
        scanner.close();
        Tuba tuba = new Tuba(ruum, pikkus, laius, uks);
        tuba.give_info();

        SQLDao dao = new SQLDao();
        Map<String, Tuba> data = dao.selectAll();
        //Tuba Silinder = data.get("Silinder");
        Tuba sqlTuba = data.get("13");
        sqlTuba.give_info();


        launch(args);

    }
}
