
package Controller;

import Model.Lop;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class LopController {
    private Connection conn ;
    private PreparedStatement stmt ;
    private ResultSet rs ;
    
    //Phương thức thêm lớp
    
    public boolean ThemLop(Lop l) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "INSERT INTO Lop(MaKhoa, TenLop, MaLop) VALUES (?, ?,?)";
        try {
            stmt = conn.prepareStatement(sql);            
            stmt.setString(1, l.getMaKhoa()); 
            stmt.setString(2,l.getTenLop());
            stmt.setString(3, l.getMaLop());         
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
        
    //Phương thức sửa lớp sinh viên
    
    public boolean SuaLop(Lop l) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "UPDATE Lop SET MaKhoa=?,TenLop=? WHERE MaLop= ?";
        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, l.getMaKhoa()); 
            stmt.setString(2,l.getTenLop());
            stmt.setString(3, l.getMaLop());
            
           return stmt.executeUpdate() >0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    //Phương thức xóa lớp     
    public boolean XoaLop(Lop l) throws SQLException{
        conn= ConnectToDatabase.ConnectToDatabase();
        String sql= "DELETE FROM Lop WHERE  MaKhoa=? and TenLop=?  and MaLop= ?";
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, l.getMaKhoa()); 
            stmt.setString(2, l.getTenLop());
            stmt.setString(3, l.getMaLop());

           return stmt.executeUpdate() >0;                        
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }
    
    // method đọc dữ liệu từ CSDL    
    public ArrayList<Lop> getListLopSV(){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<Lop> listLopSV= new ArrayList <>();
        String sql = "SELECT * FROM Lop";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Lop l= new Lop();
                l.setMaKhoa(rs.getString("MaKhoa"));
                l.setMaLop(rs.getString("MaLop"));
                l.setTenLop(rs.getString("TenLop"));
                listLopSV.add(l);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listLopSV ;
    }
    
    
     // method lấy dữ liệu trong sql hiển thị trên thuộc tính Mã Lớp
    public ArrayList<String> DSMaLopSV(String MaKV){
        conn = ConnectToDatabase.ConnectToDatabase();
        ArrayList<String> listMaLopSV= new ArrayList <>();
        String sql = "select * from Lop where MaKhoa ='"+MaKV+"'";
//        System.out.println("input:"+MaKV);
        try {
            stmt= conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if(rs.getString("MaKhoa").equals(MaKV)){
                    String maLopSV = rs.getString("MaLop");
//                    System.out.println(maLopSV);
                    listMaLopSV.add(maLopSV); 
//                    JOptionPane.showMessageDialog(null,maLopSV );
                }
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
        return listMaLopSV;
    }
}
