package View;

import Controller.KhoaVienController;
import Controller.ValidateInput;
import Model.Khoa;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class QuanLyKhoaVien extends JPanel {
    JLabel jLabelMaKV; 
    JLabel jLabelTitle;
    JLabel jLabelDS;
    JTable TableDSKV;
    JButton btnSua;
    JButton btnThem;
    JButton btnThoat;
    JButton btnXoa;
    JLabel jLabelTenKhoa;
    JTextField txtMaKV;
    JTextField txtTenKhoa;
    
    private ArrayList<Khoa> listKhoa;
    DefaultTableModel model;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public QuanLyKhoaVien() { 
        initComponents();
       // setTitle("Quản lý khoa viện");
        setBounds(450, 100,1200,800);
       // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       // this.setLocationRelativeTo(this);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }
        listKhoa = new KhoaVienController().getListKhoaVien();
   
        showDSKhoaVien();      
    }
    
     private void initComponents(){
        this.setLayout(null);
        jLabelTitle = new JLabel("QUẢN LÝ KHOA VIỆN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(325, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);

       
        jLabelMaKV = new JLabel("Mã khoa");
        jLabelMaKV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelMaKV.setBounds(150, 150, 150, 30);
        add(jLabelMaKV);
        jLabelTenKhoa= new JLabel("Tên khoa");
        jLabelTenKhoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        jLabelTenKhoa.setBounds(150, 220, 150, 30);
        add(jLabelTenKhoa);

        
        txtMaKV = new JTextField();
        txtMaKV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtMaKV.setBounds(225, 150, 250, 30);
        add(txtMaKV);
        
        txtTenKhoa = new JTextField();
        txtTenKhoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        txtTenKhoa.setBounds(225, 220, 250, 30);
        add(txtTenKhoa);
        
        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(600, 150, 125, 30);
        add(btnThem);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        
        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnSua.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\updated.png"));
        btnSua.setBounds(850, 150, 125, 30);
        add(btnSua);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        
        btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnXoa.setBounds(600, 220, 125, 30);
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
        btnThoat.setBounds(850, 220, 125, 30);
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
                    
        TableDSKV.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSTKMouseClicked(evt);
            }
        });
    }

    private void createTable() {
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSKV = new JTable(model); 
        TableDSKV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Mã khoa");
        model.addColumn("Tên khoa");
        JScrollPane sc = new JScrollPane(TableDSKV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(150,350,900,300);
       // getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }
    
    private void showDSKhoaVien(){
    model.setRowCount(0);
    listKhoa.forEach((tk) -> {
        model. addRow(new Object[]{
            tk.getMaKhoa(), tk.getTenKhoa()
        });
    });
    }
    
    public void restForm(){
       txtMaKV.setText("");
       txtMaKV.setEditable(true);
       txtTenKhoa.setText("");
    }
   
    private void btnThemActionPerformed(ActionEvent evt) {
        Khoa k = new Khoa();     
        if(txtMaKV.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy nhập mã khoa viện");
        }else if(txtTenKhoa.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy nhập tên khoa viện");
        }else if (new ValidateInput().checkName(txtMaKV.getText())&&
                new ValidateInput().checkName(txtTenKhoa.getText())) {
                k.setMaKhoa(txtMaKV.getText());
                k.setTenKhoa(txtTenKhoa.getText());
                if( new KhoaVienController().ThemKhoaVien(k)) {
                    JOptionPane.showMessageDialog(this,"Thêm thành công");
                    listKhoa.add(k);
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }       
                showDSKhoaVien();
                restForm();
        }else if(new ValidateInput().checkName(txtMaKV.getText())==false){
            JOptionPane.showMessageDialog(this, "Mã Khoa viện chỉ chứa dấu cách,chữ cái và không dấu. Vui lòng nhập lại");
        }else if(new ValidateInput().checkName(txtTenKhoa.getText())==false){
            JOptionPane.showMessageDialog(this, "Tên khoa viện chỉ chứa dấu cách,chữ cái và không dấu. Vui lòng nhập lại");
        }              
    }
    
    private void btnSuaActionPerformed(ActionEvent evt) {
        try {
            int updateRow = TableDSKV.getSelectedRow();
          //  System.out.println(updateRow);
            if(listKhoa.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm khoa/viện rồi sửa!");
            } else if(updateRow == -1){ 
                JOptionPane.showMessageDialog(this, "Hãy chọn khoa/viện cần sửa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn thay đổi ?", "Sửa khoa/viện", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {
                Khoa k = listKhoa.get(updateRow);
                if(new ValidateInput().checkName(txtTenKhoa.getText())){
                   Khoa newKhoa = new Khoa();
                   newKhoa.setTenKhoa(txtTenKhoa.getText()); 
                   newKhoa.setMaKhoa(txtMaKV.getText());
                   if( new KhoaVienController().SuaKhoaVien(newKhoa)) {  
                       listKhoa.remove(k);
                       listKhoa.add(newKhoa);
                       JOptionPane.showMessageDialog(this,"Sửa thành công");
                   }else{
                       JOptionPane.showMessageDialog(this, "Sửa thất bại");
                   }  
                   showDSKhoaVien();
                   restForm();
                }else{
                    JOptionPane.showMessageDialog(this, "Tên khoa viện không đúng định dạng (Tên khoa viện chỉ chứa chữ cái, dấu cách và không dấu)");
                }                                    
            }               
        }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhoaVien.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private void btnXoaActionPerformed(ActionEvent evt) {
         try {
            int removeRow = TableDSKV.getSelectedRow();
          //  System.out.println(removeRow);            
            if(listKhoa.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm lớp sinh viên rồi xóa");
            } else if(removeRow == -1){
                JOptionPane.showMessageDialog(this, "Hãy chọn một lớp sinh viên rồi xóa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn xóa ?", "Xóa khoa/viện", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {  
                    Khoa k= listKhoa.get(removeRow); 
                    if( new KhoaVienController().XoaKhoaVien(k)) {
                        listKhoa.remove(k);
                        JOptionPane.showMessageDialog(this,"Xóa thành công.");
                    } else{
                        JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                        } 
                    }       
                showDSKhoaVien();
                restForm();
            }           
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /*    
    private void btnThoatActionPerformed(ActionEvent evt) {
       this.dispose();
    } */
                        
    private void TableDSTKMouseClicked(MouseEvent evt) {
        int row = TableDSKV.getSelectedRow();
        if(row >=0){
            txtMaKV.setText(TableDSKV.getValueAt(row,0).toString());
            txtTenKhoa.setText(TableDSKV.getValueAt(row, 1).toString());
            txtMaKV.setEditable(false);
        }
    }

}
