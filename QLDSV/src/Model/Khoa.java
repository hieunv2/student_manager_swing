package Model;

public class Khoa {
    private String MaKhoa ;
    private String TenKhoa ;

    public Khoa(String MaKhoa, String TenKhoa) {
        this.MaKhoa = MaKhoa;
        this.TenKhoa = TenKhoa;
    }

    public Khoa() {
    }

    public String getMaKhoa() {
        return MaKhoa;
    }

    public void setMaKhoa(String MaKhoa) {
        this.MaKhoa = MaKhoa;
    }

    public String getTenKhoa() {
        return TenKhoa;
    }

    public void setTenKhoa(String TenKhoa) {
        this.TenKhoa = TenKhoa;
    }

    @Override
    public String toString() {
        return "Khoa{" + "MaKhoa=" + MaKhoa + ", TenKhoa=" + TenKhoa + '}';
    }
    
}
