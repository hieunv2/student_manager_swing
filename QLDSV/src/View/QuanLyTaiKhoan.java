package View;

import Controller.GiangVienController;
import Controller.SinhVienController;
import Controller.TaiKhoanController;
import Model.TaiKhoan;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class QuanLyTaiKhoan extends JPanel {
    JLabel jLabelMaTK; 
    JLabel jLabelTitle;
    JLabel jLabelDS;
    JTable TableDSTK;
    JButton btnSua;
    JButton btnThem;
    JButton btnThoat;
   // JButton btnXoa;
    JLabel jLabelUserName;
    JLabel jLabelPassword;
    JLabel jLabelQuyen;
    JTextField txtMTK;
    JPasswordField txtPassword;
    JTextField txtUserName;
    JComboBox<String> cbxRole;
    
    private ArrayList<TaiKhoan> listTaiKhoan;
    DefaultTableModel model;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    SinhVienController sinhVien = new SinhVienController();
    GiangVienController giangVien = new GiangVienController();
    
    public QuanLyTaiKhoan() { 
        initComponents();                
      //  setTitle("Quản lý tài khoản");
        setBounds(450, 100,1200,800);
       // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       // this.setLocationRelativeTo(this);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }

        listTaiKhoan = new TaiKhoanController().getListTaiKhoan();
   
        showDSTaiKhoan();      
    }
    
     private void initComponents(){
        this.setLayout(null);
        jLabelTitle = new JLabel("QUẢN LÝ TÀI KHOẢN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(325, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);

       
        jLabelMaTK = new JLabel("Mã tài khoản");
        jLabelMaTK.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelMaTK.setBounds(100, 150, 150, 30);
        add(jLabelMaTK);
        jLabelUserName= new JLabel("UserName");
        jLabelUserName.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelUserName.setBounds(100, 220, 150, 30);
        add(jLabelUserName);
        jLabelPassword = new JLabel("Password");
        jLabelPassword.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelPassword.setBounds(450, 220, 175, 30);
        add(jLabelPassword);
        jLabelQuyen = new JLabel("Quyền");
        jLabelQuyen.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelQuyen.setBounds(450, 150, 150, 30);
        add(jLabelQuyen);
        
        txtMTK = new JTextField();
        txtMTK.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtMTK.setBounds(225, 150, 175, 30);
        add(txtMTK);
        
        txtUserName = new JTextField();
        txtUserName.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtUserName.setBounds(225, 220, 175, 30);
        add(txtUserName);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtPassword.setBounds(550, 220, 175, 30);
        add(txtPassword);
        
        cbxRole = new JComboBox<>();
        cbxRole.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxRole.setBounds(550, 150, 175, 30);
        add(cbxRole);
        cbxRole.setModel(new DefaultComboBoxModel<>(new String[]{
            "SinhVien", "GiangVien", "admin"
        }));
     /*   
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
        
    /*   btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnXoa.setBounds(800, 220, 125, 30);
        add(btnXoa);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnXoaActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); */
        /*
        btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThoat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnThoat.setBounds(875, 220, 125, 30);
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
      //  getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSTK = new JTable(model); 
        TableDSTK.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Mã tài khoản");
        model.addColumn("UserName");
        model.addColumn("Password");
        model.addColumn("Quyền");
        JScrollPane sc = new JScrollPane(TableDSTK, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(150,350,900,300);
      //  getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }
    
    private void showDSTaiKhoan(){
    model.setRowCount(0);
    listTaiKhoan.forEach((tk) -> {
        model. addRow(new Object[]{
            tk.getMaTaiKhoan(), tk.getUserName(), tk.getPassword(), tk.getQuyen()
        });
    });
    }
     public void restForm(){
       txtMTK.setText("");
       txtMTK.setEditable(true);
       txtUserName.setText("");
       txtPassword.setText("");
       cbxRole.setSelectedItem("");
    }
   
  /*  private void btnThemActionPerformed(ActionEvent evt) {
        TaiKhoan tk = new TaiKhoan();     
        tk.setMaTaiKhoan(txtMTK.getText());
        tk.setUserName(txtUserName.getText());
        tk.setPassword(txtPassword.getText());
        tk.setQuyen(cbxRole.getSelectedItem().toString());
        try {
            if( new TaiKhoanController().ThemTaiKhoan(tk)){
                JOptionPane.showMessageDialog(this,"Add success");
                listTaiKhoan.add(tk);
                if (cbxRole.getSelectedItem().equals("GiangVien")) {
                    giangVien.ThemGV(txtMTK.getText());
                } else if(cbxRole.getSelectedItem().equals("SinhVien")) {
                    sinhVien.ThemSV(txtMTK.getText());
                }
            }else{
                JOptionPane.showMessageDialog(this, "Add fail. Ma Tai Khoan cannot be duplicated.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        showDSTaiKhoan();
        restForm();
    } 
    
    private void btnSuaActionPerformed(ActionEvent evt) throws SQLException {
        try {
            int updateRow = TableDSTK.getSelectedRow();
            if(listTaiKhoan.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm Tài khoản rồi sửa!");
            } else if(updateRow == -1){ 
                JOptionPane.showMessageDialog(this, "Hãy chọn Tài khoản cần sửa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn thay đổi ?", "Sửa Tài khoản", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {
                    TaiKhoan tk = listTaiKhoan.get(updateRow);
                    TaiKhoan newTaiKhoan = new TaiKhoan();
                    newTaiKhoan.setMaTaiKhoan(txtMTK.getText()); 
                    newTaiKhoan.setUserName(txtUserName.getText());
                    newTaiKhoan.setPassword(txtPassword.getText());
                    newTaiKhoan.setQuyen(cbxRole.getSelectedItem().toString());
                    if( new TaiKhoanController().SuaTaiKhoan(newTaiKhoan)) {  
                        listTaiKhoan.remove(tk);
                        listTaiKhoan.add(newTaiKhoan);
                        JOptionPane.showMessageDialog(this,"Sửa thành công");
                    }else{
                        JOptionPane.showMessageDialog(this, "Sửa thất bại");
                        }                        
                    }  
                showDSTaiKhoan();
                restForm();
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {

        }    
    }
    

 /*private void btnXoaActionPerformed(ActionEvent evt) throws SQLException {
        try {
            int removeRow = TableDSTK.getSelectedRow();            
            if(listTaiKhoan.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm tài khoản rồi xóa");
            } else if(removeRow == -1){
                JOptionPane.showMessageDialog(this, "Hãy chọn một tài khoản rồi xóa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn xóa ?", "Xóa tài khoản", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {  
                    TaiKhoan tk= listTaiKhoan.get(removeRow);  
                    System.out.println(txtMTK.getText());
                    if (cbxRole.getSelectedItem().equals("GiangVien")) {
                        if (giangVien.XoaGV(txtMTK.getText())) {
                            if( new TaiKhoanController().XoaTaiKhoan(tk)) {
                                System.out.println("xoa1 oke");
                                listTaiKhoan.remove(tk);
                                JOptionPane.showMessageDialog(this,"Xóa thành công.");
                                }
                            }   
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                    }
                    if(cbxRole.getSelectedItem().equals("SinhVien")) {
                        if (sinhVien.XoaSV(txtMTK.getText())) {
                           if(new TaiKhoanController().XoaTaiKhoan(tk)){
                               if (new TaiKhoanController().XoaTaiKhoan(tk)) {
                                   listTaiKhoan.remove(tk);
                                   JOptionPane.showMessageDialog(this, "Xóa thành công");
                               }
                           }
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Xóa thất bại");
                    }
                }
                        
                showDSTaiKhoan();
                restForm();
            }           
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */
        
   /* private void btnThoatActionPerformed(ActionEvent evt) {
       this.dispose();
    } */
    
                    
    private void TableDSTKMouseClicked(MouseEvent evt) {
        int row = TableDSTK.getSelectedRow();
        if(row >=0){
            txtMTK.setText(TableDSTK.getValueAt(row,0).toString());
            txtUserName.setText(TableDSTK.getValueAt(row, 1).toString());
            txtPassword.setText(TableDSTK.getValueAt(row, 2).toString());
            cbxRole.setSelectedItem(TableDSTK.getValueAt(row, 3).toString());
            txtMTK.setEditable(false);
        }
    }

}
