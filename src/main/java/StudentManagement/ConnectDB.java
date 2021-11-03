/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author khang
 */
public class ConnectDB {
    private static final String DB_URL="jdbc:sqlserver://localhost:1433;databaseName=QLSV;username=sa;password=12345678";
    protected Connection conn = null;
    
    public void openDB() throws ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(DB_URL);
            System.out.println("Successfull Connection!");
        }catch(SQLException ex) {
            System.out.println("Failed Connection!");
            ex.printStackTrace();
        }
    }
    
    public void closeDB(){
        try {
            this.conn.close();
            System.out.println("Successfull Disconnection!");
        }catch(SQLException ex) {
            System.out.println("Failed Disconnection!");
        }
    }
}
