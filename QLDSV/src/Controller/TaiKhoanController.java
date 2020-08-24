/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TaiKhoan;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class TaiKhoanController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;

    public boolean ThemTK(TaiKhoan tk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT INTO TaiKhoan(MaTaiKhoan, UserName, Password, Quyen) VALUES('"+tk.getMaTaiKhoan()+"',N'"+tk.getUserName()+"','"+tk.getPassword()+"', N'"+tk.getQuyen()+"')";
        try {
            stmt = conn.prepareStatement(sql);           
           return stmt.executeUpdate() >0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    //Phương thức sửa tài khoản sau khi sửa giảng viên
    
    public boolean SuaTK(TaiKhoan tk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "UPDATE dbo.TaiKhoan SET UserName = '"+tk.getUserName()+"' WHERE MaTaiKhoan='"+tk.getMaTaiKhoan()+"'";
       // System.out.println(sql);
        try {
            stmt = conn.prepareStatement(sql);           
           return stmt.executeUpdate() >0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    //Phương thức thay đổi password
    
    public boolean DoiPass(String matk, String mk_moi){
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql = "UPDATE dbo.TaiKhoan " +
                    "SET Password = '"+mk_moi+"' " +
                    "WHERE MaTaiKhoan='"+matk+"'";
        try {
            stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate()>0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public String getMK(String matk){
        String mk = null;
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT Password " +
                    "FROM dbo.TaiKhoan  " +
                    "WHERE MaTaiKhoan = '"+matk+"'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {               
                 mk = rs.getString("Password");
            }
        } catch (SQLException e) {
        }
        return mk;
    }
    
     public String getQuyen(String matk){
        String q = null;
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT Quyen " +
                    "FROM dbo.TaiKhoan  " +
                    "WHERE MaTaiKhoan = '"+matk+"'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            while (rs.next()) {
              q = rs.getString("Quyen");
            }            
        } catch (SQLException e) {
        }
        return q;
    }
    
  /*  //Phương thức xóa tài khoản     
    public boolean XoaTaiKhoan(TaiKhoan tk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "DELETE FROM TaiKhoan WHERE  MaTaiKhoan=? and UserName=?  and Password= ? and Quyen=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tk.getMaTaiKhoan()); 
            stmt.setString(2,tk.getUserName());
            stmt.setString(3, tk.getPassword());         
            stmt.setString(4,tk.getQuyen());

           return stmt.executeUpdate() >0;                        
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    } */
    
    // method đọc dữ liệu từ CSDL    
    public ArrayList<TaiKhoan> getListTaiKhoan(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<TaiKhoan> listTaiKhoan= new ArrayList <>();
        String sql = "SELECT * FROM TaiKhoan";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TaiKhoan tk= new TaiKhoan();
                tk.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                tk.setUserName(rs.getString("UserName"));
                tk.setPassword(rs.getString("Password"));
                tk.setQuyen(rs.getString("Quyen"));
                listTaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listTaiKhoan ;
    }
    
    //Xóa tài khoản có mã tk ... và có quyền là giảng viên
    public boolean XoaGV(TaiKhoan tk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
           String sql = "DELETE dbo.TaiKhoan WHERE MaTaiKhoan ='"+tk.getMaTaiKhoan()
                   +"' and UserName='"+tk.getUserName()+"'  and Password= '"+tk.getPassword()+"'" ;
           try {
               stmt = conn.prepareStatement(sql); 
               return stmt.executeUpdate() >0;
           } catch (SQLException e) {
               
           }
           stmt.close();
           return false;
    }
}
