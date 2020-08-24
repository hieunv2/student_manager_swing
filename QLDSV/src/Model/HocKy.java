package Model;

public class HocKy {
    private String MaHocKy ;
    private String HocKy ;
    private String NamHoc ;

    public HocKy(String MaHocKy, String HocKy, String NamHoc) {
        this.MaHocKy = MaHocKy;
        this.HocKy = HocKy;
        this.NamHoc = NamHoc;
    }
    public HocKy(){
    
    }

    public String getMaHocKy() {
        return MaHocKy;
    }

    public void setMaHocKy(String MaHocKy) {
        this.MaHocKy = MaHocKy;
    }

    public String getHocKy() {
        return HocKy;
    }

    public void setHocKy(String HocKy) {
        this.HocKy = HocKy;
    }

    public String getNamHoc() {
        return NamHoc;
    }

    public void setNamHoc(String NamHoc) {
        this.NamHoc = NamHoc;
    }

    @Override
    public String toString() {
        return "HocKy{" + "MaHocKy=" + MaHocKy + ", HocKy=" + HocKy + ", NamHoc=" + NamHoc + '}';
    }
    
}
