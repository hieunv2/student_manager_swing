package Controller;

import Model.MonHoc;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MonHocController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;
    
    //Phương thức thêm lớp   
    public boolean ThemMonHoc(MonHoc mh) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT INTO MonHoc(MaMonHoc, TenMonHoc,SoTinChi, HeSoQT, HeSoCK) "
                +"VALUES ('"+mh.getMaMonHoc()+"',N'"+mh.getTenMonHoc()+"',"+mh.getSoTinChi()
                +","+mh.getHeSoQT()+", "+mh.getHeSoCK()+")";
        try {
            stmt = conn.prepareStatement(sql);  
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
      
    //Phương thức sửa lớp sinh viên
    
    public boolean SuaMonHoc(MonHoc mh) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "UPDATE MonHoc SET TenMonHoc=N'"+mh.getTenMonHoc()+"',SoTinChi="+mh.getSoTinChi()
                +",HeSoQT="+mh.getHeSoQT()+",HeSoCK="+mh.getHeSoCK()+" WHERE MaMonHoc='"+mh.getMaMonHoc()+"'";
        try {
            stmt = conn.prepareStatement(sql); 
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return false;
    }
    
    //Phương thức xóa lớp     
    public boolean XoaMonHoc(MonHoc mh) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "DELETE FROM MonHoc WHERE MaMonHoc=? and TenMonHoc=? and SoTinChi=? and HeSoQT=? and HeSoCK=? ";
        try {
            stmt = conn.prepareStatement(sql);
           stmt.setString(1, mh.getMaMonHoc());
            stmt.setString(2, mh.getTenMonHoc());
            stmt.setInt(3, mh.getSoTinChi()); 
            stmt.setInt(4, mh.getHeSoQT());
            stmt.setInt(5, mh.getHeSoCK());
           
           return stmt.executeUpdate() >0;                        
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    // method đọc dữ liệu từ CSDL    
    public ArrayList<MonHoc> getListMonHoc(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<MonHoc> listMonHoc= new ArrayList <>();
        String sql = "SELECT * FROM MonHoc";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                MonHoc mh= new MonHoc();            
                mh.setMaMonHoc(rs.getString("MaMonHoc"));
                mh.setTenMonHoc(rs.getString("TenMonHoc"));
                mh.setSoTinChi(rs.getInt("SoTinChi"));
                mh.setHeSoQT(rs.getInt("HeSoQT"));
                mh.setHeSoCK(rs.getInt("HeSoCK"));

                listMonHoc.add(mh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listMonHoc ;
    }
    
    // lấy tên môn học khi biết mã môn học
    public String showTenMonHoc(String MaMH){
        String tenMH = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "select TenMonHoc from MonHoc where MaMonHoc ='"+MaMH+"'";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tenMH = rs.getString("TenMonHoc");
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
        return tenMH;
    }
    
 // lấy mã môn học khi biết tên môn học   
    public String showMaMonHoc(String TenMH){
        String maMH = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "select MaMonHoc from MonHoc where TenMonHoc =N'"+TenMH+"'";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                maMH = rs.getString("MaMonHoc");
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
        return maMH;
    }
    
    //Method lấy Mã tất cả môn học
      public ArrayList<String> DSMaMH(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listDSMaMH= new ArrayList <>();
        String sql = "select MaMonHoc from MonHoc";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String hk = rs.getString("MaMonHoc");
                listDSMaMH.add(hk);
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
        return listDSMaMH;
    }
      
    // Lấy hệ số của môn học
      
      public int hesoQT(String tenMH){
          int hesoqt=0;
          conn = ConnectToDatabase.ConnectToDatabase();          
          String sql = "SELECT HeSoQT\n" +
                        "FROM dbo.MonHoc\n" +
                        "WHERE TenMonHoc=N'"+tenMH+"'";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                hesoqt = rs.getInt("HeSoQT");
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
          return hesoqt;
      }
    
}
