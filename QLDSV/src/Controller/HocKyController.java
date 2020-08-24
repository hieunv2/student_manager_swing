/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class HocKyController {
    
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;
    private String hkHienTai;
        
     // method lấy dữ liệu trong sql hiển thị trên thuộc tính HocKy
    public ArrayList<String> DSHocKy(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listDSHocKy= new ArrayList <>();
        String sql = "select MaHocKy from HocKy ORDER BY MaHocKy DESC";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String hk = rs.getString("MaHocKy");
                listDSHocKy.add(hk);
               // cbxMaHocKy.addItem(rs.getString("MaHocKy"));
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
        return listDSHocKy;
    }
    
    public String hkHienTai(){
        Calendar cal = Calendar.getInstance();
        if(cal.get(Calendar.MONTH)<=12 && cal.get(Calendar.MONTH)>=8){
            hkHienTai = String.valueOf(cal.get(Calendar.YEAR))+"1";
        } else if(cal.get(Calendar.MONTH)>=1 && cal.get(Calendar.MONTH)<=5){
            hkHienTai = String.valueOf(cal.get(Calendar.YEAR)-1)+"2";
        }else{
            hkHienTai = String.valueOf(cal.get(Calendar.YEAR)-1)+"3";
        }
        return hkHienTai;
    }
}
