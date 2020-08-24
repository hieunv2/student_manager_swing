package View;

import Controller.DiemSinhVienController;
import Controller.HocKyController;
import Controller.LoginController;
import Model.DiemSinhVien;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class BangDiemTongKet extends JPanel{
    JTable TableDiemSV;
    DefaultTableModel model; 
    private Connection conn;
    private JButton btnEnter;
    private JButton btnCapNhatDiemTK;
    private JButton btnThoat;

    public BangDiemTongKet(){
        createMainPanel();
       // setTitle("Bảng điểm môn học");
       // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 100,1200,800);
       // setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {            
            }
        
        ResultSet result = new  DiemSinhVienController().listDiem(LoginController.maTK);
                showDiem(result);
    }

    private void createMainPanel() {
       // JPanel panel = new JPanel();
          setLayout(null);
          setPreferredSize(new Dimension(1000, 800));
          setBorder(customBorder("BẢNG ĐIỂM TỔNG KẾT"));
        btnEnter = new JButton("Xem điểm");
        btnEnter.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnEnter.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnEnter.setBounds(300, 50, 150, 30);
          add(btnEnter);
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnEnterActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(BangDiemTongKet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
 
      /*  btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThoat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnThoat.setBounds(700, 50, 150, 30);
        add(btnThoat);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });*/

        
          add(createTable());
    }
    
    
    private void btnEnterActionPerformed(ActionEvent evt) throws SQLException {
        DiemSinhVien diemsv = new DiemSinhVien();
        String masv = LoginController.maTK;
        String mahk = new HocKyController().hkHienTai();
        int soTCqua = new DiemSinhVienController().SoTCqua(masv);

        int soTCno = new DiemSinhVienController().SoTCno(masv);
        float diemCPA = new DiemSinhVienController().DiemCPA(masv);

        diemsv.setMSSV(masv);
        
        diemsv.setMaHocKy(mahk);
        
        diemsv.setDiemGPA(new DiemSinhVienController().DiemGPA(mahk,masv));
        
        diemsv.setDiemCPA(diemCPA);
        
        diemsv.setSoTCqua(soTCqua);
        diemsv.setSoTCno(soTCno);
        if (soTCqua <32) {
            diemsv.setTrinhDoSV("Sinh viên năm nhất");
        }
        if(soTCqua >=32 && soTCqua <64){
            diemsv.setTrinhDoSV("Sinh viên năm hai");
        }
        if(soTCqua >=64 && soTCqua <96){
            diemsv.setTrinhDoSV("Sinh viên năm ba");
        }
        if(soTCqua >=96 && soTCqua <128){
            diemsv.setTrinhDoSV("Sinh viên năm tư");
        }
        if(soTCqua >=128){
            diemsv.setTrinhDoSV("Sinh viên năm năm");
        }
        
        if (soTCno <8) {
            diemsv.setMucCC("Cảnh cáo mức 0");
            diemsv.setTinhTrangHocTap("Học");
        }
        if (soTCno >=8 && soTCno <=16) {
            diemsv.setMucCC("Cảnh cáo mức 1");
            diemsv.setTinhTrangHocTap("Học");
        }
        if(soTCno >16 && soTCno <=27){
            diemsv.setMucCC("Cảnh cáo mức 2");
            diemsv.setTinhTrangHocTap("Học");
        }        
        if(soTCno >27){
            diemsv.setMucCC("Cảnh cáo mức 3");
            diemsv.setTinhTrangHocTap("Thôi học");
        }
        
        
        if (diemCPA <1) {
            diemsv.setXepLoai("Kém");            
        }
        if(diemCPA >=1 && diemCPA <= 1.49){
            diemsv.setXepLoai("Yếu");
        }
        if(diemCPA >=1.5 && diemCPA <= 1.99){
            diemsv.setXepLoai("TB Yếu");
        }
        if(diemCPA >=2 && diemCPA <= 2.49){
            diemsv.setXepLoai("Trung bình");
        }
        if(diemCPA >=2.5 && diemCPA <= 3.19){
            diemsv.setXepLoai("Khá");
        }
        if(diemCPA >=3.2 && diemCPA <= 3.59){
            diemsv.setXepLoai("Giỏi");
        }
        if(diemCPA >=3.6 && diemCPA <= 4){
            diemsv.setXepLoai("Xuất sắc");
        }

        if( new DiemSinhVienController().CapNhatDiemTK(diemsv)){
            ResultSet rs = new DiemSinhVienController().listDiem(LoginController.maTK);
            showDiem(rs); 
            JOptionPane.showMessageDialog(this, "Thành công");
        }
        
       
    }
    
  /*  private void btnThoatActionPerformed(ActionEvent evt) {
        this.dispose();
    }*/

    public TitledBorder customBorder(String name) {
        TitledBorder titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), name, TitledBorder.CENTER, TitledBorder.TOP);
        titleBorder.setTitleFont(new Font("Time New Roman", Font.BOLD, 24));
        titleBorder.setTitleColor(Color.red);
        return titleBorder;
    }

    private PopupMenu createTable() {
        PopupMenu tableDiemSV = new PopupMenu();        
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDiemSV = new JTable(model); 
        TableDiemSV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Học kỳ");
        model.addColumn("GPA");
        model.addColumn("CPA");
        model.addColumn("TC qua");
        model.addColumn("TC nợ");
        model.addColumn("Trình độ SV");
        model.addColumn("Mức CC");
        model.addColumn("Tình trạng học tập");
        model.addColumn("Xếp loại ");
        JScrollPane sc = new JScrollPane(TableDiemSV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(100,100,1000,550);
       // getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
        return  tableDiemSV;
    }
    
       private void showDiem(ResultSet result) {
    try {
        model.setRowCount(0);
        int i=0;
        while(result.next()){
            String rows[] = new String[9];
            rows[0] =result.getString(1);
            rows[1] = result.getString(2);
            rows[2]= result.getString(3);
            rows[3]= result.getString(4);
            rows[4]= result.getString(5);
            rows[5]= result.getString(6);
            rows[6]= result.getString(7);
            rows[7]= result.getString(8);
            rows[8]= result.getString(9);

            model.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
            //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
            }
        } catch (SQLException e) {
        }
    } 
}
