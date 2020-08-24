package Controller;

import Model.DiemSinhVien;
import connectsql.ConnectToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DiemSinhVienController {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    //Phương thức chèn dữ liệu vào bảng DiemSV;
    public void ThemMSV_MHK(String masv, String mahk) throws SQLException {
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "INSERT INTO DiemSinhVien(MSSV, MaHocKy) VALUES (?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, masv);
            stmt.setString(2, mahk);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
    }

    //Phương thức xóa dữ liệu trong bảng DiemSV
    public boolean XoaMSV_MHK(String masv, String mahk) throws SQLException {
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "DELETE FROM DiemSinhVien WHERE  MSSV='" + masv + "' and MaHocKy='" + mahk + "'";
        try {
            stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        stmt.close();
        return false;
    }

    //List mã môn học ứng với sinh viên đã có trong bảng Diểm sinh viên
    public ArrayList<String> listHK(String masv) {
        ArrayList list = new ArrayList();
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MaHocKy\n"
                + "FROM dbo.DiemSinhVien \n"
                + "WHERE MSSV='" + masv + "'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String mahk_sv = rs.getString("MaHocKy");
                list.add(mahk_sv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }

    //Phương thức tính điểm GPA
    public float DiemGPA(String mahk , String masv ) {
        float diemgpa = 0;
        int tinchi = 0;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MaHocKy, MSSV, SoTinChi, DiemChu "
                + "FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id "
                + "INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc "
                + "WHERE MaHocKy = '"+mahk+"' AND MSSV='" + masv + "'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("DiemChu").equals("F")) {
                        diemgpa += 0f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("D")) {
                        diemgpa += 1f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("D+")) {
                        diemgpa += 1.5f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }

                    if (rs.getString("DiemChu").equals("C")) {
                        diemgpa += 2f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("C+")) {
                        diemgpa += 2.5f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("B")) {
                        diemgpa += 3f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("B+")) {
                        diemgpa += 3.5f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("A")) {
                        diemgpa += 4f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("A+")) {
                        diemgpa += 4f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                }
           return (float) Math.round((diemgpa / (float) tinchi) * 100 / 100);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

    //Phương thức tính CPA
    public float DiemCPA(String masv) {
        float diemcpa = 0;
        int tinchi = 0;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MSSV, SoTinChi, DiemChu FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc "
                + "WHERE MSSV= '" + masv + "'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                try {
                    if (rs.getString("DiemChu").equals("F")) {
                        diemcpa += 0f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("D")) {
                        diemcpa += 1f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("D+")) {
                        diemcpa += 1.5f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi"); 
                    }

                    if (rs.getString("DiemChu").equals("C")) {
                        diemcpa += 2f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("C+")) {
                        diemcpa += 2.5f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("B")) {
                        diemcpa += 3f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("B+")) {
                        diemcpa += 3.5f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("A")) {
                        diemcpa += 4f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    if (rs.getString("DiemChu").equals("A+")) {
                        diemcpa += 4f * rs.getInt("SoTinChi");
                        tinchi += rs.getInt("SoTinChi");
                    }
                    } catch (NullPointerException e) {
                }
            }
            return (float) Math.round((diemcpa / (float) tinchi) * 100 / 100);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

    //Phương thức tính số tín chỉ qua
    public int SoTCqua(String masv) {
        int tinchiqua = 0;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MaHocKy, MSSV, SoTinChi, DiemChu\n"
                + "FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id\n"
                + "INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc\n"
                + "WHERE MSSV = '" + masv + "'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                try {
                    if (rs.getString("DiemChu").compareTo("F") != 0) {
                        tinchiqua += rs.getInt("SoTinChi");
                    }
                } catch (NullPointerException e) {
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return tinchiqua;
    }

    //Phương thức tính số tín chỉ qua
    public int SoTCno(String masv) {
        int tinchino = 0;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MaHocKy, MSSV, SoTinChi, DiemChu\n"
                + "FROM dbo.SinhVien_Hoc_MonHoc INNER JOIN dbo.MonHoc_HocKy ON MonHoc_HocKy.Id = SinhVien_Hoc_MonHoc.Id\n"
                + "INNER JOIN dbo.MonHoc ON MonHoc.MaMonHoc = MonHoc_HocKy.MaMonHoc\n"
                + "WHERE MSSV = '" + masv + "'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                try {
                    if (rs.getString("DiemChu").equals("F")) {
                        tinchino += rs.getInt("SoTinChi");
                    }
                } catch (NullPointerException e) {
                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return tinchino;
    }

    //Lấy dữ liệu hiển thị lên JTable
    public ResultSet listDiem(String masv) {
        ResultSet result = null;
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "SELECT MaHocKy,DiemGPA, DiemCPA, SoTCqua, SoTCno, MucCC, TrinhDoSV, TinhTrangHocTap, XepLoai "
                + "FROM dbo.DiemSinhVien "
                + "WHERE MSSV ='" + masv + "'";
        try {
            stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
        }
        return result;
    }

    //Cập nhật điểm tổng kết cho sinh viên
    public boolean CapNhatDiemTK(DiemSinhVien dsv) throws SQLException {
        conn = ConnectToDatabase.ConnectToDatabase();
        String sql = "UPDATE dbo.DiemSinhVien SET DiemGPA= " + dsv.getDiemGPA()
                + ",DiemCPA=" + dsv.getDiemCPA() + ", SoTCqua=" + dsv.getSoTCqua() + ", SoTCno=" + dsv.getSoTCno()
                + ",MucCC=N'" + dsv.getMucCC() + "',TrinhDoSV=N'" + dsv.getTrinhDoSV() + "', TinhTrangHocTap=N'"
                + dsv.getTinhTrangHocTap() + "', XepLoai =N'"+dsv.getXepLoai()+"'\n"
                + "WHERE MSSV='" + dsv.getMSSV() + "' AND MaHocKy='" + dsv.getMaHocKy() + "'";
        try {
            stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return false;
    }
}
