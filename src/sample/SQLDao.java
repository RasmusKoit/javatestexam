package sample;

import java.sql.*;
import java.util.*;

public class SQLDao {
    private Connection connect() {
        // SQLite connection string
        String username = "";
        String password = "";

        /* Make sure to add sqlite driver (ctrl + alt + shift + s)
            Project Settings -> Modules -> + and add the jar file.
         */
        String url = "jdbc:sqlite:/home/rasmus/IdeaProjects/javatestexam2/src/sample/identifier.sqlite";
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the warehouses table
     */
    public Map<String, Tuba> selectAll() {
        String sql = "select tuba.id, tuba.pikkus, tuba.laius, tuba.uks from tuba";

        Map<String, Tuba> shapes = new HashMap<String, Tuba>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                Tuba shape = new Tuba();
                //Use ID instead of shape.name for key in dictionary if multiple same named shapes
                shape.name = rs.getString("id");
                shape.pikkus = rs.getFloat("pikkus");
                shape.laius = rs.getFloat("laius");
                shape.uks = rs.getFloat("uks");
                shapes.put(shape.name, shape);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return shapes;
    }
}
