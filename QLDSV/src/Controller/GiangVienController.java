
package Controller;

import Model.GiangVien;
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
public class GiangVienController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;
    static String MaTK;

//Phương thức thêm giảng viên
    public boolean ThemGiangVien(GiangVien gv) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT INTO dbo.GiangVien (MaGiangVien, HoTen, GioiTinh, MaKhoa, TrinhDo, SoDienThoai, Email ) VALUES ('"+gv.getMaGiangVien()+"', N'"+gv.getHoTen()
                +"', N'"+gv.getGioiTinh()+"', '"+gv.getMaKhoa()+"', N'"+gv.getTrinhDo()+"', '"+gv.getSoDienThoai()+"', '"+gv.getEmail()+"')";
        try {
            stmt = conn.prepareStatement(sql);                     
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        
        return  false;
    }
    
//Phương thức sửa giảng viên
    public boolean SuaGiangVien(GiangVien gv) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "UPDATE dbo.GiangVien SET HoTen = N'"+gv.getHoTen()
                +"', GioiTinh= N'"+gv.getGioiTinh()+"', MaKhoa = '"+gv.getMaKhoa()+"', TrinhDo= N'"+gv.getTrinhDo()
                +"', SoDienThoai= '"+gv.getSoDienThoai()+"', Email=N'"+gv.getEmail()
                +"' where MaGiangVien ='"+gv.getMaGiangVien()+"'";
        try {
            stmt = conn.prepareStatement(sql);                     
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();        
        return  false;
    }
    
  //Phương thức xóa giảng viên
    public boolean XoaGiangVien(String magv) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "DELETE dbo.GiangVien where MaGiangVien ='"+magv+"'";
        System.out.println(sql);
        try {
            stmt = conn.prepareStatement(sql);  
            System.out.println("Controller.GiangVienController.XoaGiangVien()");
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();        
        return  false;
    }


// method đọc dữ liệu từ CSDL    
    public ArrayList<GiangVien> getListGV(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<GiangVien> listGV= new ArrayList <>();
        String sql = "SELECT * FROM GiangVien";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                GiangVien gv= new GiangVien();
                gv.setMaGiangVien(rs.getString("MaGiangVien"));
                gv.setHoTen(rs.getString("HoTen"));
                gv.setGioiTinh(rs.getString("GioiTinh"));
                gv.setMaKhoa(rs.getString("MaKhoa")); 
                gv.setTrinhDo(rs.getString("TrinhDo"));
                gv.setSoDienThoai(rs.getString("SoDienThoai"));
                gv.setEmail(rs.getString("Email"));
                listGV.add(gv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listGV ;
    } 

    
    
    //Lấy tất cả mã giảng viên trong bảng Giảng viên
    
    public ArrayList<String>getMaGV(){
        ArrayList listMaGV = new ArrayList();
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MaGiangVien FROM GiangVien";
        try {
            stmt= conn.prepareStatement(sql);
            rs= stmt.executeQuery();
            while (rs.next()) {                
                 listMaGV.add(rs.getString("MaGiangVien"));
            }
          
        } catch (Exception e) {
        }
        return listMaGV;
    }
    
//Method cập nhật thông tin giảng viên
    public boolean CapNhatTTGV(GiangVien gv, String matk){
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "UPDATE GiangVien SET HoTen =N'"+gv.getHoTen().trim()+"', GioiTinh = N'"+gv.getGioiTinh().trim()+"',MaKhoa='"+gv.getMaKhoa().trim()+"', TrinhDo =N'"+gv.getTrinhDo().trim()+"',SoDienThoai ='"+ gv.getSoDienThoai().trim()+"',Email='"+gv.getEmail().trim()+"'WHERE  MaTaiKhoan ='"+matk+"'and MaGiangVien= '"+ matk+"'";
     //   System.out.println(sql);
        try {
            stmt= conn.prepareStatement(sql);
           //  stmt.setDate(1, (Date) gv.getNgaySinh());
            return stmt.executeUpdate() >0;
        } catch (Exception e) {
        }
        return  false;
    }
    
    //Lấy mã giảng viên từ tên gv
    public String getMaGV(String tenGV, int id){
        String magv= null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql= "SELECT GiangVien.MaGiangVien " 
                    +"FROM dbo.GiangVien_Day_MonHoc "
                    +"INNER JOIN dbo.GiangVien ON GiangVien.MaGiangVien = GiangVien_Day_MonHoc.MaGiangVien " 
                    +"WHERE Id ="+id+" AND HoTen=N'"+tenGV+"'";
        try {
            stmt= conn.prepareStatement(sql);
          //  System.out.println(stmt);
            rs = stmt.executeQuery();
          //  System.out.println(rs);
            while(rs.next()) {
                magv = rs.getString("MaGiangVien");
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
        return magv;
    }
    
    //Lấy tên GV theo mã GV
    public String TenGV (String maGV){
        String tenGV = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql= "SELECT HoTen " 
                    +"FROM dbo.GiangVien "
                    +"WHERE MaGiangVien="+maGV+"";
        try {
            stmt= conn.prepareStatement(sql);
          //  System.out.println(stmt);
            rs = stmt.executeQuery();
          //  System.out.println(rs);
            while(rs.next()) {
                tenGV = rs.getString("HoTen");
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
        return tenGV;
    }
    
    
     //Lấy mã giảng viên khi biết tên gv
       public String getMaGV(String tenGV){
        String maGV = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql ="SELECT MaGiangVien " +
                    "FROM dbo.GiangVien" +
                    "WHERE HoTen='"+tenGV+"'";       
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {               
                 maGV = rs.getString("MaGiangVien");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return maGV;
     }

}
