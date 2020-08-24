package Controller;

import Model.GiangVien_Day_MonHoc;
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
public class GiangVien_MonDayController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;
    
    //Thêm môn dạy
    public boolean Them(int id, String matk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT INTO GiangVien_Day_MonHoc(MaGiangVien,Id) VALUES (?,?)";
        try {
            stmt = conn.prepareStatement(sql);  
            
            stmt.setString(1, matk);
            stmt.setInt(2, id);            
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    // Sửa môn dạy
    public boolean Sua(int id, String matk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql = "UPDATE GiangVien_Day_MonHoc SET Id='"+id+"' where MaGiangVien='"+ matk +"'" ;
        try {
            stmt = conn.prepareStatement(sql); 
            return stmt.executeUpdate() >0;
        } catch (Exception e) {
        }
        stmt.close();
        return  false;
    }
    
    //Xóa môn dạy
    public boolean Xoa (int id, String matk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql = "Delete FROM GiangVien_Day_MonHoc where Id='"+id+"' and MaGiangVien='"+ matk +"'" ;
     //   System.out.println(id + " Mã tài khoản " + matk);
        try {
            stmt = conn.prepareStatement(sql); 
            return stmt.executeUpdate() >0;
        } catch (Exception e) {
        }
        stmt.close();
        return false;
    }
    
    // Lấy danh sách các môn học mà giảng viên đăng ký dạy
    public ArrayList<GiangVien_Day_MonHoc> getListGV_MD(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<GiangVien_Day_MonHoc> listGV_MD= new ArrayList <>();
        String sql = "SELECT * FROM GiangVien_Day_MonHoc";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                GiangVien_Day_MonHoc gv_md= new GiangVien_Day_MonHoc();
                gv_md.setId(rs.getInt("Id"));
                gv_md.setMaGiangVien(rs.getString("MaGiangVien"));
                listGV_MD.add(gv_md);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listGV_MD ;
    } 
    
    //Lấy danh sách các môn dạy của Giảng Viên
    public ArrayList<String> getMonDay(String matk, String mahk) throws SQLException{
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listMD= new ArrayList <>();
        String sql = "SELECT TenMonHoc " 
                +"FROM dbo.GiangVien_Day_MonHoc " 
                +"INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = GiangVien_Day_MonHoc.Id " 
                +"INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc " 
                +"WHERE MaGiangVien ='"+matk+"' and MaHocKy='"+mahk+"' ";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
               String tenMH= rs.getString("TenMonHoc");
               listMD.add(tenMH);
            }
        } catch (SQLException e) {
        }
        return listMD;
    }
    
    //Lấy Tên giảng viên dạy từng môn học
    public ArrayList<String> getTenGV (String mahk, String tenmh){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listTenGV = new ArrayList<>();
        String sql = "SELECT HoTen " +
            "FROM dbo.GiangVien_Day_MonHoc "
            +"INNER JOIN dbo.GiangVien ON GiangVien.MaGiangVien = GiangVien_Day_MonHoc.MaGiangVien " 
            +"INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = GiangVien_Day_MonHoc.Id " 
            +"INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc "
            +"WHERE MaHocKy= '"+mahk+"' and TenMonHoc=N'"+tenmh+"' ";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
               String tenGV= rs.getString("HoTen");
               listTenGV.add(tenGV);
            }
        } catch (Exception e) {
        }
        return listTenGV;
    }
    
    
        //Lấy Mã giảng viên dạy từng môn học
    public ArrayList<String> getMaGV (String mahk, String tenmh){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listMaGV = new ArrayList<>();
        String sql = "SELECT GiangVien.MaGiangVien" +
           "FROM dbo.GiangVien_Day_MonHoc "
            +"INNER JOIN dbo.GiangVien ON GiangVien.MaGiangVien = GiangVien_Day_MonHoc.MaGiangVien " 
            +"INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = GiangVien_Day_MonHoc.Id " 
            +"INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc "
            +"WHERE MaHocKy= '"+mahk+"' and TenMonHoc=N'"+tenmh+"' ";
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
               String maGV= rs.getString("MaGiangVien");
               // System.out.println(maGV);
               listMaGV.add(maGV);
            }
        } catch (Exception e) {
        }
        return listMaGV;
    }
    
    //Lấy danh sách sinh viên ứng với từng môn dạy của theo từng giảng viên
    public ResultSet listDSSV( String tengv, String mahk, String tenMH){
        ResultSet result = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT SinhVien.MSSV, SinhVien.HoTen, TenLop, DiemQT, DiemThiCK, DiemTrungBinh, DiemChu\n" +
"FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.SinhVien ON SinhVien.MSSV = SinhVien_Hoc_MonHoc.MSSV\n" +
"INNER JOIN dbo.GiangVien_Day_MonHoc ON GiangVien_Day_MonHoc.MaGiangVien = SinhVien_Hoc_MonHoc.MaGiangVien AND GiangVien_Day_MonHoc.Id = SinhVien_Hoc_MonHoc.Id\n" +
"INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = GiangVien_Day_MonHoc.Id\n" +
"INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc\n" +
"INNER JOIN dbo.Lop ON Lop.MaLop = SinhVien.MaLopSV\n" +
"INNER JOIN dbo.GiangVien ON GiangVien.MaGiangVien = GiangVien_Day_MonHoc.MaGiangVien\n" +
"WHERE MaHocKy = '"+mahk+"' AND TenMonHoc= N'"+tenMH+"' AND GiangVien.HoTen = N'"+tengv+"'";
        try {
            System.out.println(sql);
            stmt= conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
        }
        return result;
    }
    
    
        public ResultSet listSV( String matk, String mahk, String tenMH){
        ResultSet result = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT SinhVien.MSSV, SinhVien.HoTen, TenLop, DiemQT, DiemThiCK, DiemTrungBinh, DiemChu\n" +
"FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.SinhVien ON SinhVien.MSSV = SinhVien_Hoc_MonHoc.MSSV\n" +
"INNER JOIN dbo.GiangVien_Day_MonHoc ON GiangVien_Day_MonHoc.MaGiangVien = SinhVien_Hoc_MonHoc.MaGiangVien AND GiangVien_Day_MonHoc.Id = SinhVien_Hoc_MonHoc.Id\n" +
"INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = GiangVien_Day_MonHoc.Id\n" +
"INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc\n" +
"INNER JOIN dbo.Lop ON Lop.MaLop = SinhVien.MaLopSV\n" +
"WHERE MaHocKy = '"+mahk+"' AND TenMonHoc= N'"+tenMH+"' AND GiangVien_Day_MonHoc.MaGiangVien = N'"+matk+"'";
        try {
            stmt= conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
        }
        return result;
    }
    
    //Xóa GV_MH có Mã giảng viên là ..
    public boolean XoaGV(String magv) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
           String sql = "DELETE dbo.GiangVien_Day_MonHoc WHERE MaGiangVien ='"+magv+"'" ;
           try {
               stmt = conn.prepareStatement(sql);
               System.out.println("Controller.GiangVien_MonDayController.XoaGV()");
               return stmt.executeUpdate() >0;
           } catch (SQLException e) {
               
           }
           stmt.close();
           return false;
    }
    
    
    //Đếm số lượng môn mà giảng viên đăng ký
    public int SoLuong(String magv) throws SQLException{
         int i=0;
         conn= ConnectToDatabase.ConnectToDatabase();
           String sql = "SELECT * FROM GiangVien_Day_MonHoc where MaGiangVien ='"+magv+"'" ;
           try {
               stmt = conn.prepareStatement(sql); 
               rs =stmt.executeQuery();
               while (rs.next()) {
                  i++;
               }
           //    System.out.println(i);
               return i;
           } catch (SQLException e) {
           }
           stmt.close();
           return i;    
     }
}
