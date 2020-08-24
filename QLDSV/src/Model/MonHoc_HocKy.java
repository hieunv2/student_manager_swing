package Model;

public class MonHoc_HocKy {
    private int Id;
    private String MaMonHoc;
    private String MaHocKy;

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getMaHocKy() {
        return MaHocKy;
    }

    public void setMaHocKy(String MaHocKy) {
        this.MaHocKy = MaHocKy;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "MonHoc_HocKy{" + "Id=" + Id + ", MaMonHoc=" + MaMonHoc + ", MaHocKy=" + MaHocKy + '}';
    }

}
