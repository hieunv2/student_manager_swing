package Model;

public class DiemSinhVien {
    private String MSSV ;
    private String MaHocKy ;
    private float DiemCPA;
    private float DiemGPA;
    private int SoTCqua;
    private int SoTCno;
    private String MucCC;
    private String TrinhDoSV;
    private String TinhTrangHocTap;
    private String XepLoai;

    public DiemSinhVien(String MSSV, String MaHocKy, float DiemCPA, float DiemGPA, int SoTCqua, int SoTCno, String MucCC, String TrinhDoSV, String TinhTrangHocTap, String XepLoai) {
        this.MSSV = MSSV;
        this.MaHocKy = MaHocKy;
        this.DiemCPA = DiemCPA;
        this.DiemGPA = DiemGPA;
        this.SoTCqua = SoTCqua;
        this.SoTCno = SoTCno;
        this.MucCC = MucCC;
        this.TrinhDoSV = TrinhDoSV;
        this.TinhTrangHocTap = TinhTrangHocTap;
        this.XepLoai = XepLoai;
    }


    public DiemSinhVien(){

    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getMaHocKy() {
        return MaHocKy;
    }

    public void setMaHocKy(String MaHocKy) {
        this.MaHocKy = MaHocKy;
    }

    public float getDiemCPA() {
        return DiemCPA;
    }

    public void setDiemCPA(float DiemCPA) {
        this.DiemCPA = DiemCPA;
    }

    public float getDiemGPA() {
        return DiemGPA;
    }

    public void setDiemGPA(float DiemGPA) {
        this.DiemGPA = DiemGPA;
    }

    public int getSoTCqua() {
        return SoTCqua;
    }

    public void setSoTCqua(int SoTCqua) {
        this.SoTCqua = SoTCqua;
    }

    public int getSoTCno() {
        return SoTCno;
    }

    public void setSoTCno(int SoTCno) {
        this.SoTCno = SoTCno;
    }

    public String getMucCC() {
        return MucCC;
    }

    public void setMucCC(String MucCC) {
        this.MucCC = MucCC;
    }

    public String getTrinhDoSV() {
        return TrinhDoSV;
    }

    public void setTrinhDoSV(String TrinhDoSV) {
        this.TrinhDoSV = TrinhDoSV;
    }

    public String getTinhTrangHocTap() {
        return TinhTrangHocTap;
    }

    public void setTinhTrangHocTap(String TinhTrangHocTap) {
        this.TinhTrangHocTap = TinhTrangHocTap;
    }

    public String getXepLoai() {
        return XepLoai;
    }

    public void setXepLoai(String XepLoai) {
        this.XepLoai = XepLoai;
    }

    @Override
    public String toString() {
        return "DiemSinhVien{" + "MSSV=" + MSSV + ", MaHocKy=" + MaHocKy + ", DiemCPA=" + DiemCPA + ", DiemGPA=" + DiemGPA + ", SoTCqua=" + SoTCqua + ", SoTCno=" + SoTCno + ", MucCC=" + MucCC + ", TrinhDoSV=" + TrinhDoSV + ", TinhTrangHocTap=" + TinhTrangHocTap + ", XepLoai=" + XepLoai + '}';
    }
 
}
