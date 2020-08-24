
package Model;

import java.util.Date;

public class SinhVien {
    private String MSSV ;
    private String HoTen ;
    private String GioiTinh ;
    private Date NgaySinh ;
    private String VienQuanLy ;
    private String Email ;
    private String DiaChi ;
    private String SoDienThoai ;
    private String KhoaHoc ;
    private String MaLopSV ;


    public SinhVien(String MSSV,String HoTen, String GioiTinh, Date NgaySinh, String VienQuanLy, String Email, String DiaChi, String SoDienThoai, String KhoaHoc, String MaLopSV) {
        this.MSSV = MSSV;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.VienQuanLy = VienQuanLy;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.KhoaHoc = KhoaHoc;
        this.MaLopSV = MaLopSV;

    }
    
    public SinhVien (){
        
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getVienQuanLy() {
        return VienQuanLy;
    }

    public void setVienQuanLy(String VienQuanLy) {
        this.VienQuanLy = VienQuanLy;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getKhoaHoc() {
        return KhoaHoc;
    }

    public void setKhoaHoc(String KhoaHoc) {
        this.KhoaHoc = KhoaHoc;
    }

    public String getMaLopSV() {
        return MaLopSV;
    }

    public void setMaLopSV(String MaLopSV) {
        this.MaLopSV = MaLopSV;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "MSSV=" + MSSV + ", HoTen=" + HoTen + ", GioiTinh=" + GioiTinh + ", NgaySinh=" + NgaySinh + ", VienQuanLy=" + VienQuanLy + ", Email=" + Email + ", DiaChi=" + DiaChi + ", SoDienThoai=" + SoDienThoai + ", KhoaHoc=" + KhoaHoc + ", MaLopSV=" + MaLopSV + '}';
    }

}
