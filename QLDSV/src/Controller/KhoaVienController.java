
package Controller;

import Model.Khoa;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class KhoaVienController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;

    //Method thêm Khoa/Viện 
    public boolean ThemKhoaVien(Khoa k){
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "INSERT INTO Khoa(MaKhoa, TenKhoa) VALUES ('"+k.getMaKhoa()+"', N'"+k.getTenKhoa()+"')";
        try {
            stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate()> 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false ;
    }
    
    //Method Sửa Khoa/Viện
    public  boolean SuaKhoaVien(Khoa k) throws SQLException{
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "UPDATE Khoa SET TenKhoa=N'"+k.getTenKhoa().trim()+"' WHERE MaKhoa= '"+ k.getMaKhoa().trim()+"'";
        try {
            stmt = conn.prepareStatement(sql);           
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
        stmt.close();
        conn.close();
        return false;
    }
    
    //method Xóa Khoa/Viện
        public  boolean XoaKhoaVien(Khoa k){
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "DELETE FROM Khoa WHERE MaKhoa= ? AND TenKhoa=?";
        try {
            stmt = conn.prepareStatement(sql);            
            stmt.setString(1, k.getMaKhoa());
            stmt.setString(2, k.getTenKhoa());  
            return stmt.executeUpdate()>0 ;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }
               
    // method đọc dữ liệu từ CSDL    
    public ArrayList<Khoa> getListKhoaVien(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<Khoa> listKhoaVien = new ArrayList <>();
        String sql = "SELECT * FROM Khoa";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Khoa k = new Khoa();
                k.setMaKhoa(rs.getString("MaKhoa"));
                k.setTenKhoa(rs.getString("TenKhoa"));
                listKhoaVien.add(k);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listKhoaVien ;
    }
    
    
     // method lấy dữ liệu trong sql hiển thị trên thuộc tính Mã Khoa
    public ArrayList<String> DSMaKhoa(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listMaKhoa= new ArrayList <>();
        String sql = "select MaKhoa from Khoa";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String mk = rs.getString("MaKhoa");
                listMaKhoa.add(mk);
               // cbxMaKhoa.addItem(rs.getString("MaKhoa"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return listMaKhoa;
    }
}
