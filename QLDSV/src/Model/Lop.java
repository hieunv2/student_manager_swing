
package Model;

public class Lop {
    private String MaLop ;
    private String TenLop ;
    private String MaKhoa ;

    public Lop(String MaLop, String TenLop, String MaKhoa) {
        this.MaLop = MaLop;
        this.TenLop = TenLop;
        this.MaKhoa = MaKhoa;
    }

    public Lop() {
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

    public String getMaKhoa() {
        return MaKhoa;
    }

    public void setMaKhoa(String MaKhoa) {
        this.MaKhoa = MaKhoa;
    }

    @Override
    public String toString() {
        return "Lop{" + "MaLop=" + MaLop + ", TenLop=" + TenLop + ", MaKhoa=" + MaKhoa + '}';
    }
    
}
