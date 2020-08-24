package Model;

import java.sql.Time;

public class GiangVien_Day_MonHoc {
    private String MaGiangVien ;
    private int Id ;

    public GiangVien_Day_MonHoc(String MaGiangVien, int Id) {
        this.Id = Id;
        this.MaGiangVien = MaGiangVien;
    }
    public GiangVien_Day_MonHoc(){
    
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

    @Override
    public String toString() {
        return "GiangVien_Day_MonHoc{" + "MaGiangVien=" + MaGiangVien + ", Id=" + Id + '}';
    }

    
}
