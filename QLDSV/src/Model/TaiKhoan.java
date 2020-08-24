package Model;

public class TaiKhoan {
    private String MaTaiKhoan ;
    private String UserName ;
    private String Password ;
    private String Quyen ;

    public TaiKhoan(String MaTaiKhoan, String UserName, String Password, String Quyen) {
        this.MaTaiKhoan = MaTaiKhoan;
        this.UserName = UserName;
        this.Password = Password;
        this.Quyen = Quyen;
    }

    public TaiKhoan() {
    }
    

    public String getMaTaiKhoan() {
        return MaTaiKhoan;
    }

    public void setMaTaiKhoan(String MaTaiKhoan) {
        this.MaTaiKhoan = MaTaiKhoan;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "MaTaiKhoan=" + MaTaiKhoan + ", UserName=" + UserName + ", Password=" + Password + ", Quyen=" + Quyen + '}';
    }
    
}
