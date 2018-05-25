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
        String url = "jdbc:sqlite://C:/Users/raks4/IdeaProjects/EksamiksKordamine/src/sample/identifier.sqlite";
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
    public Map<String, Shape> selectAll() {
        String sql = "select shape.id, shape.name, shape.radius, shape.height from shape";

        Map<String, Shape> shapes = new HashMap<String, Shape>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                Shape shape = new Shape();
                //Use ID instead of shape.name for key in dictionary if multiple same named shapes
                //String id = rs.getString("id");
                shape.name = rs.getString("name");
                shape.radius = rs.getFloat("radius");
                shape.height = rs.getFloat("height");
                shapes.put(shape.name, shape);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return shapes;
    }
}
