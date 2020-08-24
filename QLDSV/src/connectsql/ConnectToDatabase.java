package connectsql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDatabase {
    private static Connection conn;
    public static Connection ConnectToDatabase(){
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLyDiemSV;user=sa;password=123456");

        } catch (Exception e) {
                System.out.println("Connect Error");
        }
         return conn;
    }
}
