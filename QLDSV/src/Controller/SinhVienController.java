
package Controller;

import Model.SinhVien;
import Model.SinhVien;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SinhVienController {
    private Connection conn;
    private PreparedStatement stmt ;
    private ResultSet rs ;

    //Method them Sinh Vien
    public boolean ThemSV(SinhVien sv) throws  SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT dbo.SinhVien ( MSSV, HoTen, GioiTinh, SoDienThoai ,Email, VienQuanLy, MaLopSV) " +
                "VALUES ( '"+sv.getMSSV()+"', N'"+sv.getHoTen()+"', N'"+sv.getGioiTinh()
                +"', '"+sv.getSoDienThoai()+"', '"+sv.getEmail()+"', '"+sv.getVienQuanLy()+"', '"+sv.getMaLopSV()+"')";
        System.out.print(sql);
        try {
            stmt = conn.prepareStatement(sql);  
           return stmt.executeUpdate()>0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    
    //Phương thức sửa giảng viên
    public boolean SuaSinhVien(SinhVien sv) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "UPDATE dbo.SinhVien SET HoTen = N'"+sv.getHoTen()
                +"', GioiTinh= N'"+sv.getGioiTinh()+"', SoDienThoai = '"+sv.getSoDienThoai()+"', Email= N'"+sv.getEmail()
                +"', VienQuanLy = '"+sv.getVienQuanLy()+"', MaLopSV=N'"+sv.getMaLopSV()
                +"' where MSSV ='"+sv.getMSSV()+"'";
        try {
            stmt = conn.prepareStatement(sql);                     
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();        
        return  false;
    }
    
    public boolean XoaSV(String maTKSV) throws  SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "DELETE dbo.SinhVien WHERE MSSV = '"+maTKSV+"' AND MaTaiKhoan= '"+maTKSV+"'";
        try {
            stmt = conn.prepareStatement(sql);  
           return stmt.executeUpdate()>0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    
    public boolean CapNhatTT(SinhVien sv, String matk){
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "UPDATE SinhVien SET  MSSV ='"+sv.getMSSV()+"', HoTen =N'"+sv.getHoTen().trim()
                +"',GioiTinh = N'"+sv.getGioiTinh().trim()+"',SoDienThoai ='"
                + sv.getSoDienThoai().trim()+"', Email='"+sv.getEmail().trim()
                +"', VienQuanLy=N'"+sv.getVienQuanLy().trim()+"', MaLopSV='"+sv.getMaLopSV().trim()+"' WHERE MSSV= '"+ matk+"'";
        try {
          //  System.out.println(sql);
            stmt= conn.prepareStatement(sql);
           // stmt.setInt(3, sv.getMSSV());
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
        }
        return  false;
    }
    
    //Lấy mã sinh viên khi biết mssv và họ tên
    public String getMaSV(String tenSV, String mssv){
        String maSV = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql ="SELECT MSSV\n" +
                    "FROM dbo.SinhVien\n" +
                    "WHERE HoTen =N'"+tenSV+"' AND MSSV="+mssv+"";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {               
                 maSV = rs.getString("MSSV");
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
        return maSV;
    }

    public ArrayList<SinhVien> getListSV() {
         conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<SinhVien> listSV= new ArrayList <>();
        String sql = "SELECT MSSV, HoTen, GioiTinh, SoDienThoai, Email, VienQuanLy, MaLopSV FROM SinhVien";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SinhVien sv= new SinhVien();
                sv.setMSSV(rs.getString("MSSV"));
                sv.setHoTen(rs.getString("HoTen"));            
                sv.setGioiTinh(rs.getString("GioiTinh"));
                sv.setSoDienThoai(rs.getString("SoDienThoai"));
                sv.setEmail(rs.getString("Email"));
                sv.setVienQuanLy(rs.getString("VienQuanLy"));
                sv.setMaLopSV(rs.getString("MaLopSV"));
                listSV.add(sv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listSV;
    }

    public ArrayList<String> getMSSV() {
        ArrayList listMSSV = new ArrayList();
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MSSV FROM SinhVien";
        try {
            stmt= conn.prepareStatement(sql);
            rs= stmt.executeQuery();
            while (rs.next()) {                
                 listMSSV.add(rs.getString("MSSV"));
            }
          
        } catch (Exception e) {
        }
        return listMSSV;
    }
}
