package View;

import Controller.DiemSinhVienController;
import Controller.GiangVienController;
import Controller.GiangVien_MonDayController;
import Controller.HocKyController;
import Controller.LoginController;
import Controller.MonHocController;
import Controller.MonHoc_HocKyController;
import Controller.SinhVien_MonHocController;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class DangKyMonHoc extends JPanel {
    JLabel jLabelHocKy; 
    JLabel jLabelTitle;
    JLabel jLabelDS;
    JTable TableDSMH;
    JButton btnSua;
    JButton btnThem;
    JButton btnThoat;
    JButton btnXoa;
    JLabel jLabelTenMon;
    JLabel jLabelMaHocKy;
    JLabel jLabelTenGV;
    JComboBox<String> cbxHocKy;
    JComboBox<String> cbxTenMon;
    JComboBox<String> cbxGiangVien;
    private Connection conn;
    
    private ArrayList<String> listMaHK;
    private ArrayList<String> listMaMH;
    private ArrayList<String> listDSGV;
    private ArrayList<String> listMaHK_SV;
    private int dem = 0;
    
    DefaultTableModel model;
    public DangKyMonHoc() throws SQLException{
        initComponents();
      //  setTitle("Đăng ký môn dạy");
      //  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 100,1200,900);
      //  setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }
        listMaHK = new HocKyController().DSHocKy();

        DSMaHK();
        cbxHocKy.addActionListener((ActionEvent e) -> {
            if (cbxGiangVien.getItemCount()!= 0) {
                cbxGiangVien.removeAllItems();
            }
            if (cbxTenMon.getItemCount()!= 0) {
                cbxTenMon.removeAllItems();
            } 
            listMaMH  = new MonHoc_HocKyController().getListMaMH(cbxHocKy.getSelectedItem().toString()); 
            DSTenMH();
        });
        
        cbxTenMon.addActionListener((ActionEvent e)->{
            if (cbxTenMon.getItemCount()!= 0) {
                cbxGiangVien.removeAllItems();
                listDSGV = new GiangVien_MonDayController().getTenGV(cbxHocKy.getSelectedItem().toString(), cbxTenMon.getSelectedItem().toString());
                DSGV();
            }

        });       
        ResultSet rs = new SinhVien_MonHocController().listDSMH(LoginController.maTK);
        showDSSV_MH(rs);
    }

    private void initComponents() {
       // JPanel panel = new JPanel();
        this.setLayout(null);
       // panel.setPreferredSize(new Dimension(1200,800));
               
        jLabelTitle = new JLabel("ĐĂNG KÝ MÔN HỌC");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(325, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);

       
        jLabelHocKy = new JLabel("Học kỳ");
        jLabelHocKy.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelHocKy.setBounds(200, 120, 150, 30);
        add(jLabelHocKy);
        
        jLabelTenMon= new JLabel("Môn học");
        jLabelTenMon.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelTenMon.setBounds(200, 190, 150, 30);
        add(jLabelTenMon);
        
        jLabelTenGV= new JLabel("Giảng Viên");
        jLabelTenGV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelTenGV.setBounds(200, 260, 150, 30);
        add(jLabelTenGV);

        
        cbxHocKy = new JComboBox<>();
        cbxHocKy.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxHocKy.setBounds(300, 120, 350, 30);
        add(cbxHocKy);
        
        cbxTenMon = new JComboBox<>();
        cbxTenMon.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxTenMon.setBounds(300, 190, 350, 30);
        add(cbxTenMon);
        
        cbxGiangVien = new JComboBox<>();
        cbxGiangVien.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxGiangVien.setBounds(300, 260, 350, 30);
        add(cbxGiangVien);
        
        btnThem = new JButton("Đăng ký");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(800, 120, 125, 30);
        add(btnThem);
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnThemActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(DangKyMonHoc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
      /*  btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnSua.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\updated.png"));
        btnSua.setBounds(850, 150, 125, 30);
        add(btnSua);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        }); */
        
        btnXoa = new JButton("Hủy ĐK");
        btnXoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnXoa.setBounds(800, 190, 125, 30);
        add(btnXoa);
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnXoaActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(DangKyMonHoc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /*
        btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThoat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnThoat.setBounds(800, 260, 125, 30);
        add(btnThoat);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        }); */
        
        jLabelDS = new JLabel("Danh sách");
        jLabelDS.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        jLabelDS.setForeground(new Color(0, 204, 204));
        jLabelDS.setBounds(150, 350, 150, 30);
        add(jLabelDS);
        
        createTable();
    }
    
    private void btnThemActionPerformed(ActionEvent evt) throws SQLException {
        String maHK= cbxHocKy.getSelectedItem().toString();         
        //Lấy mã tài khoản
        String matk= LoginController.maTK;
       
        if ((new HocKyController().hkHienTai().compareTo(maHK))!=0) {    
                JOptionPane.showMessageDialog(this, "Học kỳ hiện tại là: "+new HocKyController().hkHienTai()+". Hãy chọn đúng học kỳ");
        } else {
            //Lấy id
            String maMH= new MonHocController().showMaMonHoc(cbxTenMon.getSelectedItem().toString());

            int id= new MonHoc_HocKyController().getId(maMH ,maHK);
             //Lấy mã giảng viên
            String magv = new GiangVienController().getMaGV(cbxGiangVien.getSelectedItem().toString(),id);
            if( new SinhVien_MonHocController().Them(id,magv,matk)) {
                JOptionPane.showMessageDialog(this,"Đăng ký thành công");
                ResultSet rs = new SinhVien_MonHocController().listDSMH(LoginController.maTK);
                showDSSV_MH(rs);
                listMaHK_SV = new DiemSinhVienController().listHK(LoginController.maTK);

                if (listMaHK_SV.isEmpty()==false) {
                    for (int i = 0; i < listMaHK_SV.size(); i++) {
                        if(listMaHK_SV.get(i).equals(new HocKyController().hkHienTai())){
                            dem++;
                        }
                    }    
                }  
                if (listMaHK_SV.isEmpty() | dem==0) {
                    new DiemSinhVienController().ThemMSV_MHK(LoginController.maTK,new HocKyController().hkHienTai());                           
                }                          
            } else{
                JOptionPane.showMessageDialog(this, "Đăng ký thất bại");
            }    
        } 
    }
    
    private void btnXoaActionPerformed(ActionEvent evt) throws SQLException {
        try {
        int removeRow = TableDSMH.getSelectedRow(); 
        if(removeRow < 0){
            JOptionPane.showMessageDialog(this, "Hãy chọn môn cần hủy");
        }else {
            int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn hủy ?", "Hủy đăng ký", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
            if (select == 0) {  
                String maHK = TableDSMH.getValueAt(removeRow,0).toString();
                String tenMH = TableDSMH.getValueAt(removeRow,1).toString();
                String tenGV = TableDSMH.getValueAt(removeRow, 2).toString();
                //Lấy id
                String maMH = new MonHocController().showMaMonHoc(tenMH);
                int id = new MonHoc_HocKyController().getId(maMH ,maHK);
                //Lấy mã gv
                String magv = new GiangVienController().getMaGV(tenGV,id);
                if( new SinhVien_MonHocController().Xoa(id,magv,LoginController.maTK)){                        
                    JOptionPane.showMessageDialog(this,"Bạn đã hủy đăng ký thành công.");
                    ResultSet rs = new SinhVien_MonHocController().listDSMH(LoginController.maTK);
                    showDSSV_MH(rs);
                     listMaHK_SV = new DiemSinhVienController().listHK(LoginController.maTK);

                    if (listMaHK_SV.isEmpty()==false) {
                        for (int i = 0; i < listMaHK_SV.size(); i++) {
                            if(listMaHK_SV.get(i).equals(new HocKyController().hkHienTai())){
                                dem++;
                            }
                        }    
                    }  
                    if(dem==1) {
                        new DiemSinhVienController().XoaMSV_MHK(LoginController.maTK,new HocKyController().hkHienTai());  
                    }                                                                        
                } else{
                    JOptionPane.showMessageDialog(this, "Bạn đã hủy đăng ký thất bại.");
                    } 
                }       
            }           
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
   /* private void btnThoatActionPerformed(ActionEvent evt) {
        this.dispose();
    } */

    private void createTable() {
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSMH = new JTable(model); 
        TableDSMH.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Học kỳ");
        model.addColumn("Môn học");
        model.addColumn("Tên GV");
         model.addColumn("Mã GV");
        JScrollPane sc = new JScrollPane(TableDSMH, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(150,400,900,350);
      //  getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }

    private void DSTenMH() {
         try {        
           // cbxGiangVien.removeAllItems();
            for (int i = 0; i < listMaMH.size(); i++) {
                String tenMH = new MonHocController().showTenMonHoc(listMaMH.get(i));
                cbxTenMon.addItem(tenMH);                 
            }  
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi2: " + e);
        }  
    }

    private void DSMaHK() {
        try {
            if (cbxHocKy.getItemCount()!= 0) {
                cbxHocKy.removeAllItems();
            }
            for (int i = 0; i < listMaHK.size(); i++) {
                cbxHocKy.addItem(listMaHK.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi1");
        }  
    }
    
    private void DSGV() {
        try {
            if (cbxGiangVien.getItemCount()!= 0) {
                cbxGiangVien.removeAllItems();
            }
            for (int i = 0; i < listDSGV.size(); i++) {
                cbxGiangVien.addItem(listDSGV.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi3");
        }  
    } 

    private void showDSSV_MH(ResultSet result) {
        try {
            model.setRowCount(0);
            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[4];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã học kỳ)
                rows[1] = result.getString(2);// lấy dữ liệu tai cột số 2 ứng với tên môn học
                rows[2]= result.getString(3);// lấy dữ liệu tai cột số 2 ứng với tên giảng viên
                rows[3]= result.getString(4);
                model.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
}
