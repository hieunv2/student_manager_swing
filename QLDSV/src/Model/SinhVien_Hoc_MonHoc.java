package Model;

public class SinhVien_Hoc_MonHoc {
    private String MSSV ;
    private String MaGiangVien ;
    private int Id ;
    private float DiemQT ;
    private float DiemThiCK ;
    private float DiemTrungBinh ;
    private String DiemChu ;

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }


    public String getMaGiangVien() {
        return MaGiangVien;
    }

    public void setMaGiangVien(String MaGiangVien) {
        this.MaGiangVien = MaGiangVien;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public float getDiemQT() {
        return DiemQT;
    }

    public void setDiemQT(float DiemQT) {
        this.DiemQT = DiemQT;
    }

    public float getDiemThiCK() {
        return DiemThiCK;
    }

    public void setDiemThiCK(float DiemThiCK) {
        this.DiemThiCK = DiemThiCK;
    }

    public float getDiemTrungBinh() {
        return DiemTrungBinh;
    }

    public void setDiemTrungBinh(float DiemTrungBinh) {
        this.DiemTrungBinh = DiemTrungBinh;
    }

    public String getDiemChu() {
        return DiemChu;
    }

    public void setDiemChu(String DiemChu) {
        this.DiemChu = DiemChu;
    }

    @Override
    public String toString() {
        return "SinhVien_Hoc_MonHoc{" + "MSSV=" + MSSV + ", MaGiangVien=" + MaGiangVien + ", Id=" + Id + ", DiemQT=" + DiemQT + ", DiemThiCK=" + DiemThiCK + ", DiemTrungBinh=" + DiemTrungBinh + ", DiemChu=" + DiemChu + '}';
    }

    
}
