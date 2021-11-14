package banking;

import java.sql.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class SelectApp {


    /**
     * Connect to the data.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:card.s3db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the warehouses table
     */
    public boolean selectNumPin(String inputNum, String inputPin){
        String sql = "SELECT number, pin "
                + "FROM card WHERE number LIKE ? AND pin LIKE ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,inputNum);
            pstmt.setString(2,inputPin);

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int getBalance(String inputNumber){
        String sql = "SELECT balance "
                + "FROM card WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,inputNumber);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                return rs.getInt("balance");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public boolean findCard(String inputNumber){
        String sql = "SELECT number "
                + "FROM card WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,inputNumber);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                if (inputNumber.equals(rs.getString("number"))){
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}