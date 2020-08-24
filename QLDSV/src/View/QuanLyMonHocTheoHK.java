package View;

import Controller.HocKyController;
import Controller.MonHocController;
import Controller.MonHoc_HocKyController;
import Model.MonHoc_HocKy;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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

public class QuanLyMonHocTheoHK extends JPanel{
    private JTable TableDSMonHoc;
    private JButton btnThem;
    private JButton btnThoat;
    private JButton btnXoa;
    private JLabel jLabelMaMH;
    private JLabel jLabelId;
    private JLabel jLabelMaHK;
    private JLabel jLabelTitle;
    private JLabel jLabel;
    private JComboBox<String> cbxMaMH;
    private JComboBox<String> cbxMaHK;
    private JTextField txtId;
    
    private Connection conn;
    DefaultTableModel model;
    private ArrayList<MonHoc_HocKy> listMH_HK;
    private ArrayList<String> listMaHK;
    private ArrayList<String> listMaMH;
    public QuanLyMonHocTheoHK(){
        initComponents();
       // setTitle("Môn học theo học kỳ");
        setBounds(550, 100,1300,900);
       // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       // setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }
        listMaHK = new HocKyController().DSHocKy();
        listMaMH = new MonHocController().DSMaMH();
        listMH_HK = new MonHoc_HocKyController().getListMH_HK();
        DSMaHK();
        DSMaMon();
        showDSMH();
    }

    private void initComponents() {
       // JPanel panel = new JPanel();
        this.setLayout(null);
        //panel.setPreferredSize(new Dimension(1200, 800));
        
        jLabelTitle = new JLabel("MÔN HỌC THEO HỌC KỲ");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 70, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);
        
        jLabelId = new JLabel("Mã: ");
        jLabelId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelId.setBounds(120, 150, 150, 30);
        add(jLabelId);
        
        jLabelMaHK = new JLabel("Mã học kỳ: ");
        jLabelMaHK.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelMaHK.setBounds(120, 200, 150, 30);
        add(jLabelMaHK);
        
        jLabelMaMH = new JLabel("Mã môn học: ");
        jLabelMaMH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelMaMH.setBounds(120, 250, 150, 30);
        add(jLabelMaMH);
        
        
        txtId = new JTextField();
        txtId.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtId.setBounds(250, 150, 400, 30);
        add(txtId);
        
        cbxMaHK = new JComboBox<>();
        cbxMaHK.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        cbxMaHK.setBounds(250, 200, 400, 30);
        add(cbxMaHK);
                
        cbxMaMH = new JComboBox<>();
        cbxMaMH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cbxMaMH.setBounds(250, 250, 400, 30);
        add(cbxMaMH);
        
        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(750, 150, 125, 30);
        add(btnThem);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
       
        btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnXoa.setBounds(750, 220, 125, 30);
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
        btnThoat.setBounds(1000, 150, 125, 30);
        add(btnThoat);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });*/
        
        
        jLabel = new JLabel("Danh sách môn học");
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel.setForeground(new Color(0, 153, 153));
        jLabel.setBounds(100, 400, 200, 30);
        add(jLabel);
        
        createTable();                    
        TableDSMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSMonHocMouseClicked(evt);
            }
        });     
    }
    
    private void TableDSMonHocMouseClicked(MouseEvent evt) {
        int row = TableDSMonHoc.getSelectedRow();
        if(row >=0){
         //   cbxMaKhoa.setSelectedItem(TableDSTK.getValueAt(row,0).toString()); 
            txtId.setText(TableDSMonHoc.getValueAt(row,0).toString());
            cbxMaHK.setSelectedItem(TableDSMonHoc.getValueAt(row,1).toString());
            cbxMaMH.setSelectedItem(TableDSMonHoc.getValueAt(row,2).toString());
            txtId.setEditable(false);
        }
    }
    
    private void btnThemActionPerformed(ActionEvent evt) {
        
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập id");
        } else {
            MonHoc_HocKy mh_hk = new MonHoc_HocKy();
            mh_hk.setId(Integer.parseInt(txtId.getText()));
            mh_hk.setMaHocKy(cbxMaHK.getSelectedItem().toString());
            mh_hk.setMaMonHoc(cbxMaMH.getSelectedItem().toString());
            try {
                if (new HocKyController().hkHienTai().compareTo(cbxMaHK.getSelectedItem().toString())!=0) {    
                    JOptionPane.showMessageDialog(this, "Học kỳ hiện tại là: "+new HocKyController().hkHienTai()+". Hãy chọn đúng học kỳ");
                } else {
                    if(new MonHoc_HocKyController().ThemMH_HK(mh_hk)) {                    
                    JOptionPane.showMessageDialog(this,"Thêm thành công");
                            listMH_HK.add(mh_hk);
                } else{
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }    
                }                         
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyMonHocTheoHK.class.getName()).log(Level.SEVERE, null, ex);
            }
            restForm();
        }
        
        showDSMH();

    }
    
    private void btnXoaActionPerformed(ActionEvent evt) {
        try {
            int removeRow = TableDSMonHoc.getSelectedRow();            
            if(listMH_HK.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm lớp sinh viên rồi xóa");
            } else if(removeRow == -1){
                JOptionPane.showMessageDialog(this, "Hãy chọn một lớp sinh viên rồi xóa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn xóa ?", "Xóa lớp SV", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {  
                    MonHoc_HocKy mh_hk= listMH_HK.get(removeRow); 
                    String mamh = mh_hk.getMaMonHoc();
                    String mahk = mh_hk.getMaHocKy();
                    if( new MonHoc_HocKyController().XoaMH_HK(mh_hk)) {
                        listMH_HK.remove(mh_hk);
                        JOptionPane.showMessageDialog(this,"Xóa thành công.");
                    } else{
                        JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                        } 
                    }       
               showDSMH();
               restForm();
            }           
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyMonHocTheoHK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /* 
    private void btnThoatActionPerformed(ActionEvent evt) {
        this.dispose();
    }*/

    private void createTable() {
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSMonHoc = new JTable(model); 
        TableDSMonHoc.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Mã");
        model.addColumn("Mã học kỳ");
        model.addColumn("Mã môn học");
        JScrollPane sc = new JScrollPane(TableDSMonHoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(150,450,1000,350);
        //getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }

    private void showDSMH() {
    model.setRowCount(0);
    listMH_HK.forEach((mhhk) -> {
        model. addRow(new Object[]{
           mhhk.getId(), mhhk.getMaMonHoc(), mhhk.getMaHocKy()
        });
    });
    }
    public void restForm(){
      // cbxMaKhoa.setSelectedItem("");
       txtId.setText("");
       cbxMaMH.setSelectedItem("");
       cbxMaMH.setSelectedItem("");
       txtId.setEditable(true);
    }

    private void DSMaHK() {
        try {
            for (int i = 0; i < listMaHK.size(); i++) {
                cbxMaHK.addItem(listMaHK.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }   
    }

    private void DSMaMon() {
        try {
            for (int i = 0; i < listMaMH.size(); i++) {
                cbxMaMH.addItem(listMaMH.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }  
    }
}
