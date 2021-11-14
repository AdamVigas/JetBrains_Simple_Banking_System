package banking;


import java.sql.*;

public class Database{

    public void createNewDatabase() {

        String url = "jdbc:sqlite:card.s3db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a new table in the test database
     *
     */
    public void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:card.s3db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " number TEXT NOT NULL,\n"
                + " pin TEXT NOT NULL,\n"
                + " balance INTEGER DEFAULT 0"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
