package Controller;

import Model.TaiKhoan;
import View.MainForAdmin;
import View.MainForGiangVien;
import View.MainForSinhVien;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class LoginController {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    public static String maTK;
    public boolean check_login(TaiKhoan tk){
         if(tk.getUserName().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng điền Username");
        } else if(tk.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng điền Password");
        } else{                    
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql = "Select * from TaiKhoan where UserName=? and Password=?";
        try 
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tk.getUserName());
            pst.setString(2, tk.getPassword());
            ResultSet rs= pst.executeQuery();
            if( rs.next()){
                LoginController.maTK=rs.getString("MaTaiKhoan");
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                String textRole = rs.getString("Quyen");
                if(textRole.equals("Admin")){
                    MainForAdmin mainAD= new MainForAdmin();
                    mainAD.setVisible(true);
                } else if (textRole.equals("GiangVien")) {
                    MainForGiangVien mainGV = new MainForGiangVien();
                    mainGV.setVisible(true);
                } else {
                    MainForSinhVien mainSV = new MainForSinhVien();
                    mainSV.setVisible(true);
                }
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username hoặc Password không đúng.");
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
            }
        }
         return false;
    }
}
