package Controller;

import Model.SinhVien_Hoc_MonHoc;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SinhVien_MonHocController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;
    
    //Thêm môn dạy
    public boolean Them(int id, String magv, String matk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT INTO SinhVien_Hoc_MonHoc(MSSV,MaGiangVien,Id) VALUES ('"+matk+"','"+magv+"','"+id+"')";
        try {
            stmt = conn.prepareStatement(sql);             
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    //Xóa môn học đăng ký
    public boolean Xoa (int id, String magv, String matk) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql = "Delete FROM SinhVien_Hoc_MonHoc where Id='"+id+"' and MaGiangVien='"+ magv +"' and MSSV ='"+matk+"'" ;
        try {
            stmt = conn.prepareStatement(sql); 
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
        }
        stmt.close();
        return false;
    }    
    //Danh sách môn học mà sinh viên đăng ký theo kỳ    
    public ResultSet listDSMH( String masv){
        ResultSet result = null;
        conn = ConnectToDatabase.ConnectToDatabase();
      //  ArrayList<Object> listMH = new ArrayList<>();
        String sql = "SELECT MaHocKy, TenMonHoc,HoTen,GiangVien_Day_MonHoc.MaGiangVien \n" +
                    "FROM dbo.SinhVien_Hoc_MonHoc \n" +
                    "INNER JOIN dbo.GiangVien_Day_MonHoc ON GiangVien_Day_MonHoc.MaGiangVien = SinhVien_Hoc_MonHoc.MaGiangVien AND GiangVien_Day_MonHoc.Id = SinhVien_Hoc_MonHoc.Id\n" +
                    "INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = GiangVien_Day_MonHoc.Id\n" +
                    "INNER JOIN dbo.GiangVien ON GiangVien.MaGiangVien = GiangVien_Day_MonHoc.MaGiangVien\n" +
                    "INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc\n" +
                    "WHERE MSSV= '"+masv+"'";
        try {
            stmt= conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
        }
        return result;
    }  
    
    //Cập nhật điểm cho sinh viên
    public boolean CapNhatDiem(SinhVien_Hoc_MonHoc sv_d) throws SQLException{
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql ="UPDATE SinhVien_Hoc_MonHoc SET DiemQT= "+sv_d.getDiemQT()
                +", DiemThiCK= "+sv_d.getDiemThiCK()+", DiemTrungBinh="+sv_d.getDiemTrungBinh()
                +", DiemChu='"+sv_d.getDiemChu()+"' WHERE MSSV='"+sv_d.getMSSV()
                +"' and MaGiangVien='"+sv_d.getMaGiangVien()+"' and Id = '"+sv_d.getId()+"'";
        try {
            stmt= conn.prepareStatement(sql);  
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
        }finally{
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return  false;
    }
     
     //Lấy id
     public int getID(String magv, String mahk, String tenmh){
        int id=-1;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql ="SELECT MonHoc_HocKy.Id " +
                    "FROM dbo.MonHoc_HocKy INNER JOIN dbo.SinhVien_Hoc_MonHoc ON SinhVien_Hoc_MonHoc.Id = MonHoc_HocKy.Id " +
                    "INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc " +
                    "WHERE MaGiangVien ='"+magv+"' and MaHocKy='"+mahk+"' and TenMonHoc=N'"+tenmh+"'";       
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {               
                 id = rs.getInt("Id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id;
     }
     
    
     
     
     //Hiển thị điểm của sinh viên theo học kỳ    
     public ResultSet listDiemHK(String masv, String mahk){
        ResultSet result = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql ="SELECT MaHocKy, TenMonHoc, SoTinChi, DiemQT, DiemThiCK, DiemChu " +
                    "FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id " +
                    "INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc " +
                    "WHERE MSSV ='"+masv+"' AND MaHocKy='"+mahk+"'" ;
        try {
            stmt= conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
        }

        return result;
        }
     
    //Hiển thị điểm của sinh viên theo học kỳ    
     public ResultSet listDiem(String masv){
        ResultSet result = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql ="SELECT MaHocKy, TenMonHoc, SoTinChi, DiemQT, DiemThiCK, DiemChu " +
                    "FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id " +
                    "INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc " +
                    "WHERE MSSV ='"+masv+"'" ;
        try {
            stmt= conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
        }

        return result;
        }
     
     //Lấy danh sách mã học kỳ của từng sinh viên
     public ArrayList<String> getMaHK_SV (String masv){
         ArrayList listMaHK_SV = new ArrayList();
         conn = ConnectToDatabase.ConnectToDatabase();
         String sql = "SELECT DISTINCT MaHocKy\n" +
                    "FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id\n" +
                    "WHERE MSSV= '"+masv+"'";
         try {
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
            while (rs.next()) {               
                String mahk_sv = rs.getString("MaHocKy");
                listMaHK_SV.add(mahk_sv);
            }
         } catch (SQLException e) {
         }
         return listMaHK_SV;
     }
     
     
    //Lấy mã học kỳ ứng với Môn học mà SV mới đăng ký
     public String getMaHK_Id (int id){
         String mahk_sv =null;
         conn = ConnectToDatabase.ConnectToDatabase();
         String sql = "SELECT DISTINCT MaHocKy\n" +
                    "FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id\n" +
                    "WHERE Id= "+id+"";
         try {
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
            while (rs.next()) {               
                 mahk_sv = rs.getString("MaHocKy");
                 System.out.println(mahk_sv);
            }
         } catch (Exception e) {
         }
         return mahk_sv;
     }
     
     //Xóa row trong bảng SinhVien_Hoc_MonHoc có mã giảng viên
  /*   public  boolean XoaMaGV(String magv) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
           String sql = "Delete FROM SinhVien_Hoc_MonHoc where MaGiangVien ='"+magv+"'" ;
           try {
               stmt = conn.prepareStatement(sql); 
               return stmt.executeUpdate() >0;
           } catch (SQLException e) {
           }
           stmt.close();
           return false;
     } */
     
     //Dém số lượng sv mà giảng viên có mã .. dạy
     
     public int SoLuong(String magv) throws SQLException{
         int i=0;
         conn= ConnectToDatabase.ConnectToDatabase();
           String sql = "SELECT * from SinhVien_Hoc_MonHoc where MaGiangVien ='"+magv+"'" ;
           try {
               stmt = conn.prepareStatement(sql); 
               rs =stmt.executeQuery();
               while (rs.next()) {
                  i++;
               }
               return i;
           } catch (SQLException e) {
           }
           stmt.close();
           return i;    
     }
     
}
