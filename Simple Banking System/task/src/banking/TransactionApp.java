package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TransactionApp{


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

    //sends money from one card to another
    public void performTransaction(String sender, String receiver, int balance) {
        // SQL for creating a new material
        String sqlSender = "UPDATE card SET balance = balance - ? "
                + "WHERE number = ?";

        // SQL for posting inventory
        String sqlReceiver = "UPDATE card SET balance = balance + ? "
                + "WHERE number = ?";

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt1 = null, pstmt2 = null;

        try {
            // connect to the database
            conn = this.connect();
            if(conn == null)
                return;

            // set auto-commit mode to false
            conn.setAutoCommit(false);

            // 1. send money
            pstmt1 = conn.prepareStatement(sqlSender,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt1.setInt(1, balance);
            pstmt1.setString(2, sender);
            int rowAffected = pstmt1.executeUpdate();


            if (rowAffected != 1) {
                conn.rollback();
            }
            // 2. receive
            pstmt2 = conn.prepareStatement(sqlReceiver);
            pstmt2.setInt(1, balance);
            pstmt2.setString(2, receiver);

            //
            pstmt2.executeUpdate();
            // commit work
            conn.commit();
        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }
    }



}