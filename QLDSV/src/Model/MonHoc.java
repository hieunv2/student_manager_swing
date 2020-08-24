
package Model;

public class MonHoc {
    private String MaMonHoc ;
    private String TenMonHoc ;
    private int SoTinChi ;
    private int HeSoQT ;
    private int HeSoCK ;

    public MonHoc(String MaMonHoc, String TenMonHoc, int SoTinChi, int HeSoQT, int HeSoCK) {
        this.MaMonHoc = MaMonHoc;
        this.TenMonHoc = TenMonHoc;
        this.SoTinChi = SoTinChi;
        this.HeSoQT = HeSoQT;
        this.HeSoCK = HeSoCK;
    }

    public MonHoc() {
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String TenMonHoc) {
        this.TenMonHoc = TenMonHoc;
    }

    public int getSoTinChi() {
        return SoTinChi;
    }

    public void setSoTinChi(int SoTinChi) {
        this.SoTinChi = SoTinChi;
    }

    public int getHeSoQT() {
        return HeSoQT;
    }

    public void setHeSoQT(int HeSoQT) {
        this.HeSoQT = HeSoQT;
    }

    public int getHeSoCK() {
        return HeSoCK;
    }

    public void setHeSoCK(int HeSoCK) {
        this.HeSoCK = HeSoCK;
    }

    @Override
    public String toString() {
        return "MonHoc{" + "MaMonHoc=" + MaMonHoc + ", TenMonHoc=" + TenMonHoc + ", SoTinChi=" + SoTinChi + ", HeSoQT=" + HeSoQT + ", HeSoCK=" + HeSoCK + '}';
    }
    
}
