
package Controller;

import Model.MonHoc_HocKy;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class MonHoc_HocKyController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;
    
    //Phương thức thêm lớp
    
    public boolean ThemMH_HK(MonHoc_HocKy mh) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT INTO MonHoc_HocKy(Id, MaMonHoc, MaHocKy) VALUES (?,?,?)";
        try {
            stmt = conn.prepareStatement(sql); 
            stmt.setInt(1, mh.getId());
            stmt.setString(2,mh.getMaMonHoc());
            stmt.setString(3, mh.getMaHocKy());         
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
        
    
    //Phương thức xóa lớp     
    public boolean XoaMH_HK(MonHoc_HocKy mh) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "DELETE FROM MonHoc_HocKy WHERE Id=? and MaMonHoc=? and MaHocKy=?";
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, mh.getId()); 
            stmt.setString(2,mh.getMaMonHoc());
            stmt.setString(3, mh.getMaHocKy());

           return stmt.executeUpdate() >0;                        
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    // method đọc dữ liệu từ CSDL    
   public ArrayList<MonHoc_HocKy> getListMH_HK(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<MonHoc_HocKy> listMH_HK= new ArrayList <>();
        String sql = "SELECT * FROM MonHoc_HocKy";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                MonHoc_HocKy mh_hk= new MonHoc_HocKy();
                mh_hk.setId(rs.getInt("Id"));
                mh_hk.setMaHocKy(rs.getString("MaHocKy"));
                mh_hk.setMaMonHoc(rs.getString("MaMonHoc"));
                listMH_HK.add(mh_hk);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listMH_HK ;
    } 
        
     // method lấy dữ liệu trong sql hiển thị 
    public ArrayList<String> getListMaMH(String mahk){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listMaMH= new ArrayList <>();
        String sql = "select MaMonHoc from MonHoc_HocKy where MaHocKy='"+ mahk+"'";
       // System.out.println("input:"+mahk);
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {               
                String maMH = rs.getString("MaMonHoc");
                listMaMH.add(maMH);
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
        return listMaMH;
    }
    
    // method lấy ID khi biết Mã môn học và mã học kỳ
    public  int getId(String mamh, String mahk){
        conn = ConnectToDatabase.ConnectToDatabase();
        int id = -1;
        String sql = "select Id from MonHoc_HocKy where MaMonHoc ='"+mamh+"' and MaHocKy ='"+mahk+"'";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Id");
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
        return  id;
    }
}
