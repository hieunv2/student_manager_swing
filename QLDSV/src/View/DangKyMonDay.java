package View;

import Controller.GiangVien_MonDayController;
import Controller.HocKyController;
import Controller.LoginController;
import Controller.MonHocController;
import Controller.MonHoc_HocKyController;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
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

public class DangKyMonDay extends JPanel {
    JLabel jLabelHocKy; 
    JLabel jLabelTitle;
    JLabel jLabelDS;
    JTable TableDSMD;
    JButton btnSua;
    JButton btnThem;
    JButton btnThoat;
    JButton btnXoa;
    JLabel jLabelTenMon;
    JComboBox<String> txtHocKy;
    JComboBox<String> txtTenMon;
    private Connection conn;
    
    private ArrayList<String> listDSMD;
   
    private ArrayList<String> listMaHK;
    private ArrayList<String> listMaMH;
    DefaultTableModel model;
    
    public DangKyMonDay() throws SQLException{
        initComponents();
      //  setTitle("Đăng ký môn dạy");
      //  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 100,1200,800);
       // setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }

      //  listDSMD= new GiangVien_MonDayController().getListGV_MD();
        listMaHK = new HocKyController().DSHocKy();
      //listMaMH = new MonHoc_HocKyController().getListMaMH
        DSMaHK();
        txtHocKy.addActionListener((ActionEvent e) -> {
            listMaMH  = new MonHoc_HocKyController().getListMaMH(txtHocKy.getSelectedItem().toString());  
            
            //Hiển thị dữ liệu từ cơ sở dữ liệu lên Jtable: Thông tin các môn mà Giảng viên đăng ký theo kỳ
            try {
                listDSMD = new GiangVien_MonDayController().getMonDay(LoginController.maTK,txtHocKy.getSelectedItem().toString());
            } catch (SQLException ex) {
                Logger.getLogger(DangKyMonDay.class.getName()).log(Level.SEVERE, null, ex);
            }
            DSTenMH();
          //  showDSGV_MD();
        });
        
       

       //showDSMD();
    }    
    
    private void initComponents() {
       // JPanel panel = new JPanel();
        this.setLayout(null);
       // panel.setPreferredSize(new Dimension(1200,800));
               
        jLabelTitle = new JLabel("ĐĂNG KÝ MÔN DẠY");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(325, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);

       
        jLabelHocKy = new JLabel("Học kỳ");
        jLabelHocKy.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelHocKy.setBounds(150, 150, 150, 30);
        add(jLabelHocKy);
        
        jLabelTenMon= new JLabel("Môn học");
        jLabelTenMon.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelTenMon.setBounds(150, 220, 150, 30);
       add(jLabelTenMon);

        
        txtHocKy = new JComboBox<>();
        txtHocKy.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtHocKy.setBounds(225, 150, 250, 30);
        add(txtHocKy);
        
        txtTenMon = new JComboBox<>();
        txtTenMon.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtTenMon.setBounds(225, 220, 250, 30);
        add(txtTenMon);
        
        btnThem = new JButton("Đăng ký");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(600, 150, 125, 30);
        add(btnThem);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnThemActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(DangKyMonDay.class.getName()).log(Level.SEVERE, null, ex);
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
        btnXoa.setBounds(600, 220, 125, 30);
        add(btnXoa);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnXoaActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(DangKyMonDay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    /*    btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThoat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnThoat.setBounds(850, 150, 125, 30);
        add(btnThoat);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        }); */
        
        jLabelDS = new JLabel("Danh sách");
        jLabelDS.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        jLabelDS.setForeground(new Color(0, 204, 204));
        jLabelDS.setBounds(150, 300, 150, 30);
        add(jLabelDS);
        
        createTable();
    }
    
    private void btnThemActionPerformed(ActionEvent evt) throws SQLException {
        try {            
            String maHK= txtHocKy.getSelectedItem().toString();
            if ((new HocKyController().hkHienTai().compareTo(maHK))!=0) {    
                    JOptionPane.showMessageDialog(this, "Học kỳ hiện tại là: "+new HocKyController().hkHienTai()+". Hãy chọn đúng học kỳ");
            } else {
                String maMH= new MonHocController().showMaMonHoc(txtTenMon.getSelectedItem().toString());
                int id= new MonHoc_HocKyController().getId(maMH ,maHK);
                String matk= LoginController.maTK;
                if( new GiangVien_MonDayController().Them(id, matk)) {
                    JOptionPane.showMessageDialog(this,"Đăng ký thành công");
                    listDSMD = new GiangVien_MonDayController().getMonDay(LoginController.maTK, maHK); 
                    showDSGV_MD();
                } else{
                    JOptionPane.showMessageDialog(this, "Đăng ký thất bại");
                }     
            }    
        } catch (HeadlessException | SQLException e) {
        }
    }
    
    private void btnXoaActionPerformed(ActionEvent evt) throws SQLException {
        try {
            int removeRow = TableDSMD.getSelectedRow(); 
            if(removeRow < 0){
                JOptionPane.showMessageDialog(this, "Hãy chọn môn cần hủy");
            }else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn hủy ?", "Hủy đăng ký", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {  
                    String maHK = TableDSMD.getValueAt(removeRow,0).toString();
                    String tenMH = TableDSMD.getValueAt(removeRow,1).toString();
                    String maMH = new MonHocController().showMaMonHoc(tenMH);
                    int id = new MonHoc_HocKyController().getId(maMH ,maHK);
                    if( new GiangVien_MonDayController().Xoa(id,LoginController.maTK)){                        
                        JOptionPane.showMessageDialog(this,"Hủy đăng ký thành công.");
                        listDSMD = new GiangVien_MonDayController().getMonDay(LoginController.maTK, maHK); 
                        showDSGV_MD();
                    } else{
                        JOptionPane.showMessageDialog(this, "Hủy đăng ký thất bại.");
                        } 
                    }       
            }           
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
   /* private void btnThoatActionPerformed(ActionEvent evt) {
        this.dispose();
    }*/

    private void createTable() {
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSMD = new JTable(model); 
        TableDSMD.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Học kỳ");
        model.addColumn("Môn học");
        JScrollPane sc = new JScrollPane(TableDSMD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(150,350,900,300);
      //  getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }    

    private void showDSGV_MD(){        
        model.setRowCount(0);
        for (int i = 0; i < listDSMD.size(); i++) {
                model.addRow(new Object[]{
                    txtHocKy.getSelectedItem().toString(),listDSMD.get(i)
                });            
            }
        } 
  /*  public void restForm(){
       txtHocKy.setSelectedItem("");
       txtTenMon.setSelectedItem("");
    } */
    
  /*   private void TableDSMDMouseClicked(MouseEvent evt) {
        int row = TableDSMD.getSelectedRow();
        System.out.println(row);
        if(row >=0){            
            String maHK = TableDSMD.getValueAt(row,0).toString();
            String tenMH = TableDSMD.getValueAt(row,1).toString();
        }
    } */   

    private void DSMaHK() {
        try {
            for (int i = 0; i < listMaHK.size(); i++) {
                txtHocKy.addItem(listMaHK.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi1");
        }  
    }

    private void DSTenMH() {
         try {
            txtTenMon.removeAllItems();
            for (int i = 0; i < listMaMH.size(); i++) {
                String tenMH = new MonHocController().showTenMonHoc(listMaMH.get(i));
                txtTenMon.addItem(tenMH);
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi2");
        }  
    }
}
