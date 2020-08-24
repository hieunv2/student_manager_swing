package View;

import Controller.KhoaVienController;
import Controller.LopController;
import Controller.ValidateInput;
import Model.Lop;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class QuanLyLopSV extends JPanel {
    JLabel jLabelMaKhoa; 
    JLabel jLabelTitle;
    JLabel jLabelDS;
    JTable TableDSTK;
    JButton btnSua;
    JButton btnThem;
    JButton btnThoat;
    JButton btnXoa;
    JLabel jLabelTenLop;
    JLabel jLabelMaLop;

    JTextField txtTenLop;
    JTextField txtMaLop;
    JComboBox<String> cbxMaKhoa;
    
    
    private ArrayList<Lop> listLopSV;
    private ArrayList<String> listMaKhoa;
    DefaultTableModel model;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public QuanLyLopSV() { 
        initComponents();
       // setTitle("Quản lý lớp sinh viên");
        setBounds(450, 100,1200,800);
       // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       // this.setLocationRelativeTo(this);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }
        listLopSV = new LopController().getListLopSV();
        listMaKhoa = new KhoaVienController().DSMaKhoa();
        DSMaKhoa();
        showDSLopSV();      
    }
    
     private void initComponents(){
        this.setLayout(null);
        jLabelTitle = new JLabel("QUẢN LÝ LỚP SINH VIÊN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(325, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);

       
        jLabelMaKhoa = new JLabel("Mã Khoa");
        jLabelMaKhoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelMaKhoa.setBounds(100, 150, 150, 30);
        add(jLabelMaKhoa);
        jLabelTenLop= new JLabel("Tên lớp sv");
        jLabelTenLop.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelTenLop.setBounds(100, 220, 150, 30);
        add(jLabelTenLop);
        jLabelMaLop = new JLabel("Mã lớp sv");
        jLabelMaLop.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelMaLop.setBounds(450, 150, 150, 30);
        add(jLabelMaLop);
        
        cbxMaKhoa = new JComboBox<>();
        cbxMaKhoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxMaKhoa.setBounds(225, 150, 175, 30);
        add(cbxMaKhoa);
        
        txtTenLop = new JTextField();
        txtTenLop.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtTenLop.setBounds(225, 220, 175, 30);
        add(txtTenLop);
       
        
        txtMaLop = new JTextField();
        txtMaLop.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtMaLop.setBounds(550, 150, 175, 30);
        add(txtMaLop);
        
        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(800, 150, 125, 30);
        add(btnThem);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        
        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnSua.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\updated.png"));
        btnSua.setBounds(950, 150, 125, 30);
        add(btnSua);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnSuaActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnXoa.setBounds(800, 220, 125, 30);
        add(btnXoa);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
      /*  
        btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThoat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnThoat.setBounds(950, 220, 125, 30);
        add(btnThoat);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        }); */
        
        jLabelDS = new JLabel("Danh sách");
        jLabelDS.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        jLabelDS.setForeground(new Color(0, 204, 204));
        jLabelDS.setBounds(100, 300, 150, 30);
        add(jLabelDS);
        
        createTable();
                    
        TableDSTK.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSTKMouseClicked(evt);
            }
        });
    }

    private void createTable() {
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSTK = new JTable(model); 
        TableDSTK.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Mã khoa");
        model.addColumn("Tên lớp sv");
        model.addColumn("Mã lớp sv");
        JScrollPane sc = new JScrollPane(TableDSTK, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(150,350,900,300);
      //  getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }
    
    private void showDSLopSV(){
    model.setRowCount(0);
    listLopSV.forEach((l) -> {
        model. addRow(new Object[]{
            l.getMaKhoa(), l.getTenLop(), l.getMaLop()
        });
    });
    }
    public void restForm(){
       cbxMaKhoa.setSelectedItem("");
       txtTenLop.setText("");
       txtMaLop.setText("");
       txtMaLop.setEditable(true);
    }
   
    private void btnThemActionPerformed(ActionEvent evt) {
        Lop l = new Lop();
        if (txtMaLop.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập mã lớp sinh viên");
        } else if(txtTenLop.getText().isEmpty()|| new ValidateInput().checkName(txtTenLop.getText())==false) {
            JOptionPane.showMessageDialog(this, "Hãy nhập tên lớp sinh viên");
        } else{
            l.setMaKhoa(cbxMaKhoa.getSelectedItem().toString());        
            l.setTenLop(txtTenLop.getText());
            l.setMaLop(txtMaLop.getText());
            try {
                if( new LopController().ThemLop(l)){
                    JOptionPane.showMessageDialog(this,"Thêm thành công");
                    listLopSV.add(l);
                }else{
                    JOptionPane.showMessageDialog(this, "Thêm thất bại. Mã lớp là duy nhất");
                }
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyLopSV.class.getName()).log(Level.SEVERE, null, ex);
            }
            showDSLopSV();
            restForm();
        }
       
    }
    
    private void btnSuaActionPerformed(ActionEvent evt) throws SQLException {
         try {
            int updateRow = TableDSTK.getSelectedRow();
            if(listLopSV.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm lớp SV rồi sửa!");
            } else if(updateRow == -1){ 
                JOptionPane.showMessageDialog(this, "Hãy chọn lớp SV cần sửa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn thay đổi ?", "Sửa lớp SV", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {
                Lop l = listLopSV.get(updateRow);
                Lop newLop = new Lop();
                newLop.setTenLop(txtTenLop.getText()); 
                newLop.setMaLop(txtMaLop.getText());
                newLop.setMaKhoa(cbxMaKhoa.getSelectedItem().toString());
                if( new LopController().SuaLop(newLop)) {  
                    listLopSV.remove(l);
                    listLopSV.add(newLop);
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }else{
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                    }                        
                }  
                showDSLopSV();
                restForm();
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyLopSV.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    

    private void btnXoaActionPerformed(ActionEvent evt) {
        try {
            int removeRow = TableDSTK.getSelectedRow();            
            if(listLopSV.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm lớp sinh viên rồi xóa");
            } else if(removeRow == -1){
                JOptionPane.showMessageDialog(this, "Hãy chọn một lớp sinh viên rồi xóa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn xóa ?", "Xóa lớp SV", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {  
                    Lop l= listLopSV.get(removeRow); 
                    if( new LopController().XoaLop(l)) {
                        listLopSV.remove(l);
                        JOptionPane.showMessageDialog(this,"Xóa thành công.");
                    } else{
                        JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                        } 
                    }       
                showDSLopSV();
                restForm();
            }           
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyLopSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   /* 
    private void btnThoatActionPerformed(ActionEvent evt) {
       this.dispose();
    } */
    
    
       // method lấy dữ liệu trong sql hiển thị trên thuộc tính Mã Khoa
    private void DSMaKhoa(){
        try {
            for (int i = 0; i < listMaKhoa.size(); i++) {
                cbxMaKhoa.addItem(listMaKhoa.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }                  
    }
                    
    private void TableDSTKMouseClicked(MouseEvent evt) {
        int row = TableDSTK.getSelectedRow();
        if(row >=0){
            cbxMaKhoa.setSelectedItem(TableDSTK.getValueAt(row,0).toString());        
            txtTenLop.setText(TableDSTK.getValueAt(row,1).toString());
            txtMaLop.setText(TableDSTK.getValueAt(row,2).toString());
            txtMaLop.setEditable(false);
        }
    }
}
