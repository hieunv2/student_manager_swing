package Model;

public class GiangVien {
    private String MaGiangVien ;
    private String HoTen ;
    private String GioiTinh ;
    private String MaKhoa ;
    private String TrinhDo ;
    private String SoDienThoai ;
    private String Email ;

    public GiangVien(String MaGiangVien,String HoTen, String GioiTinh, String MaKhoa, String TrinhDo, String SoDienThoai, String Email) {
        this.MaGiangVien = MaGiangVien;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.MaKhoa = MaKhoa;
        this.TrinhDo = TrinhDo;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
    }
    public GiangVien (){
        
    }

    public String getMaGiangVien() {
        return MaGiangVien;
    }

    public void setMaGiangVien(String MaGiangVien) {
        this.MaGiangVien = MaGiangVien;
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

    public String getMaKhoa() {
        return MaKhoa;
    }

    public void setMaKhoa(String MaKhoa) {
        this.MaKhoa = MaKhoa;
    }

    public String getTrinhDo() {
        return TrinhDo;
    }

    public void setTrinhDo(String TrinhDo) {
        this.TrinhDo = TrinhDo;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "GiangVien{" + "MaGiangVien=" + MaGiangVien + ", HoTen=" + HoTen + ", GioiTinh=" + GioiTinh + ", MaKhoa=" + MaKhoa + ", TrinhDo=" + TrinhDo + ", SoDienThoai=" + SoDienThoai + ", Email=" + Email + '}';
    }
    
}
