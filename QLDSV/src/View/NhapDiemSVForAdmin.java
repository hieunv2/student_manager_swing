package View;

import Controller.GiangVienController;
import Controller.GiangVien_MonDayController;
import Controller.HocKyController;
import Controller.LoginController;
import Controller.MonHocController;
import Controller.MonHoc_HocKyController;
import Controller.SinhVienController;
import Controller.SinhVien_MonHocController;
import Controller.ValidateInput;
import Model.SinhVien_Hoc_MonHoc;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class NhapDiemSVForAdmin extends  JPanel{
    private JLabel jLabelTitle;   
    private JLabel jLabel1;
    private JLabel jLabelHocKy;
    private JLabel jLabelMonHoc;
    private JLabel jLabelGV;
    private JLabel jLabel2;

    private JLabel jLabelMSSV;
    private JLabel jLabelHoTen;
    private JLabel jLabelDiemQT;
    private JLabel jLabelDiemCK;

    private JButton btnEnter;
    private JButton btnLuu;
    private JButton btnThoat;
    
    private Connection conn;
    
    private JTextField txtMSSV;
    private JTextField txtHoTen;
    private JTextField txtDiemQT;
    private JTextField txtDiemCK;

    JTable TableDSSV;
    DefaultTableModel model;
    
    private  JComboBox<String> cbxMaHocKy;
    private JComboBox<String> cbxTenMH;
    private JComboBox<String> cbxGV;
    
    private ArrayList<String> listMaHK;
    private ArrayList<String> listTenMH;
    
    private ArrayList<String> listMaMH;
    private ArrayList<String> listDSGV;
    private ArrayList<String> listMaHK_SV;

    
    public NhapDiemSVForAdmin(){
        createMainPanel();
      //  setTitle("Nhập điểm sinh viên");
      //  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 100,1200,900);
      //  setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }
        listMaHK = new HocKyController().DSHocKy();

        DSMaHK();
        cbxMaHocKy.addActionListener((ActionEvent e) -> {
            
            if (cbxTenMH.getItemCount()!= 0) {
                cbxTenMH.removeAllItems();
            } 
            if (cbxGV.getItemCount()!= 0) {
                cbxGV.removeAllItems();
            }

            listMaMH  = new MonHoc_HocKyController().getListMaMH(cbxMaHocKy.getSelectedItem().toString()); 
            DSTenMH();
        });
        
        cbxTenMH.addActionListener((ActionEvent e)->{
            if (cbxTenMH.getItemCount()!= 0) {
                cbxGV.removeAllItems();
              }
            listDSGV = new GiangVien_MonDayController().getTenGV(cbxMaHocKy.getSelectedItem().toString(), cbxTenMH.getSelectedItem().toString());
            DSGV();

        });       
    }
    
    private void createMainPanel() {
       // JPanel panel = new JPanel();
        setLayout(null);
       // panel.setPreferredSize(new Dimension(1150, 900));
        
        jLabelTitle = new JLabel("NHẬP ĐIỂM SINH VIÊN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);
        
        jLabel1 = new JLabel("Lọc theo");
        jLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel1.setForeground(new Color(0, 153, 153));
        jLabel1.setBounds(100, 80, 200, 30);
        add(jLabel1);
        
        jLabelHocKy = new JLabel("Học kỳ: ");
        jLabelHocKy.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelHocKy.setBounds(120, 120, 100, 30);
        add(jLabelHocKy);
        
        jLabelMonHoc = new JLabel("Môn học: ");
        jLabelMonHoc.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelMonHoc.setBounds(120, 160, 100, 30);
        add(jLabelMonHoc);
        
        jLabelGV = new JLabel("Giảng viên: ");
        jLabelGV.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelGV.setBounds(120, 200, 100, 30);
        add(jLabelGV);
        
        cbxMaHocKy = new JComboBox<>();
        cbxMaHocKy.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxMaHocKy.setBounds(225, 120, 450, 30);
        add(cbxMaHocKy);
        
        cbxTenMH = new JComboBox<>();
        cbxTenMH.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxTenMH.setBounds(225, 160, 450, 30);
        add(cbxTenMH);
        
        cbxGV = new JComboBox<>();
        cbxGV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxGV.setBounds(225, 200, 450, 30);
        add(cbxGV);
        
        btnEnter = new JButton("Cập nhật");
        btnEnter.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnEnter.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnEnter.setBounds(800, 175, 150, 40);
        add(btnEnter);
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        
        jLabel2 = new JLabel("Danh sách sinh viên");
        jLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel2.setForeground(new Color(0, 153, 153));
        jLabel2.setBounds(100, 275, 200, 30);
        add(jLabel2);
        
        jLabelMSSV = new JLabel("MSSV: ");
        jLabelMSSV.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelMSSV.setBounds(120, 325, 150, 30);
        add(jLabelMSSV);
        
        jLabelHoTen = new JLabel("Họ Tên: ");
        jLabelHoTen.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelHoTen.setBounds(120, 375, 150, 30);
        add(jLabelHoTen);
        
        jLabelDiemQT = new JLabel("Điểm quá trình: ");
        jLabelDiemQT.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelDiemQT.setBounds(500, 325, 150, 30);
        add(jLabelDiemQT);
        
        jLabelDiemCK = new JLabel("Điểm cuối kỳ: ");
        jLabelDiemCK.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelDiemCK.setBounds(500, 375, 150, 30);
        add(jLabelDiemCK);
        
        txtMSSV = new JTextField();
        txtMSSV.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtMSSV.setBounds(220,325, 200, 30);
        add(txtMSSV);
        
        txtHoTen = new JTextField();
        txtHoTen.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtHoTen.setBounds(220,375, 200, 30);
        add(txtHoTen);
        
        
        txtDiemQT = new JTextField();
        txtDiemQT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtDiemQT.setBounds(650, 325, 200, 30);
        add(txtDiemQT);
        
        txtDiemCK = new JTextField();
        txtDiemCK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtDiemCK.setBounds(650, 375, 200, 30);
        add(txtDiemCK);
        
        btnLuu = new JButton("Lưu");
        btnLuu.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnLuu.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\updated.png"));
        btnLuu.setBounds(950, 325, 125, 30);
        add(btnLuu);
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnLuuActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(NhapDiemSV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       /* 
        btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThoat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnThoat.setBounds(950, 375, 125, 30);
        add(btnThoat);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        }); */
        
        add(createTable());
        
        TableDSSV.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSSVMouseClicked(evt);
            }
        });
    }
        
    private void btnEnterActionPerformed(ActionEvent evt) {  
           
        ResultSet rs = new GiangVien_MonDayController().listDSSV(cbxGV.getSelectedItem().toString(), cbxMaHocKy.getSelectedItem().toString(), cbxTenMH.getSelectedItem().toString());
        if (rs != null) {            
            showDSSV_MH(rs);
        }
    }
    
    private void btnLuuActionPerformed(ActionEvent evt) throws SQLException {
        int updateRow = TableDSSV.getSelectedRow();
        if(updateRow < 0){
            JOptionPane.showMessageDialog(this, "Bạn cho chọn sinh viên nào để thêm điểm");
        }else { 
            System.out.println(new ValidateInput().checkNumber(txtDiemQT.getText()));
         //   System.out.println(txtDiemQT.getText());
            if (new ValidateInput().checkNumber(txtDiemQT.getText())==false) {
                JOptionPane.showMessageDialog(this,"Nhập điểm sai định dạng");
            } else if (Float.parseFloat(txtDiemQT.getText()) <0 || Float.parseFloat(txtDiemQT.getText())>10) {
                JOptionPane.showMessageDialog(this, "Nhập lại điểm quá trình");
            } else if (new ValidateInput().checkNumber(txtDiemCK.getText())==false) {
                JOptionPane.showMessageDialog(this,"Nhập điểm sai định dạng");
            } else if (Float.parseFloat(txtDiemCK.getText()) <0 || Float.parseFloat(txtDiemCK.getText())>10) {
                JOptionPane.showMessageDialog(this, "Nhập lại điểm cuối kỳ");
            } else{
                SinhVien_Hoc_MonHoc sv_mh = new SinhVien_Hoc_MonHoc();
               //Lấy mã sinh viên
            //   String masv = new SinhVienController().getMaSV(txtHoTen.getText(),txtMSSV.getText());
               // Lấy id
               int id = new SinhVien_MonHocController().getID(LoginController.maTK, cbxMaHocKy.getSelectedItem().toString(),cbxTenMH.getSelectedItem().toString());    
               //Lấy hệ số của môn học
               int heSoQT = new MonHocController().hesoQT(cbxTenMH.getSelectedItem().toString());
               
               //Lấy mã giảng viên từ tên vào id trong bảng GiangrVien_Day_MonHoc
             String maGV = new GiangVienController().getMaGV(cbxGV.getSelectedItem().toString(), id);

               sv_mh.setMSSV(txtMSSV.getText());
               sv_mh.setMaGiangVien(maGV);
               sv_mh.setId(id);                                    
               sv_mh.setDiemQT(Float.parseFloat(txtDiemQT.getText()));
               sv_mh.setDiemThiCK(Float.parseFloat(txtDiemCK.getText()));

               float diemtongket = Float.parseFloat(txtDiemQT.getText())* heSoQT*0.1f + Float.parseFloat(txtDiemCK.getText())*(10-heSoQT)*0.1f;

               sv_mh.setDiemTrungBinh((float)Math.round(diemtongket * 10) / 10);
               if (Float.parseFloat(txtDiemCK.getText()) <=3 |Float.parseFloat(txtDiemQT.getText()) <=3 | diemtongket <4 ) {
                   sv_mh.setDiemChu("F"); 
               } 
               if(diemtongket>=4.0 && diemtongket <= 4.9) {
                   sv_mh.setDiemChu("D"); 
               }
               if (diemtongket > 4.91 && diemtongket <= 5.4) {
                   sv_mh.setDiemChu("D+");
               }
               if (diemtongket > 5.41 && diemtongket <= 6.4) {
                   sv_mh.setDiemChu("C");
               }
               if (diemtongket > 6.41 && diemtongket <= 6.9) {
                   sv_mh.setDiemChu("C+");
               }
               if (diemtongket > 6.91 && diemtongket <= 7.9) {
                   sv_mh.setDiemChu("B");
               }
               if (diemtongket > 7.91 && diemtongket <= 8.4) {
                   sv_mh.setDiemChu("B+");
               }
               if (diemtongket > 8.41 && diemtongket <= 9.4) {
                   sv_mh.setDiemChu("A");
               }
               if (diemtongket > 9.41 && diemtongket <= 10.0) {
                   sv_mh.setDiemChu("A+");
               }

               if (new SinhVien_MonHocController().CapNhatDiem(sv_mh)) {   
                   String maGV1 = new GiangVienController().getMaGV(cbxGV.getSelectedItem().toString());
                   //Hiển thị dữ liệu vào JTable
                   ResultSet result = new  GiangVien_MonDayController().listDSSV(maGV1, cbxMaHocKy.getSelectedItem().toString(),cbxTenMH.getSelectedItem().toString() );         
                    showDSSV_MH(result);
                    JOptionPane.showMessageDialog(this,"Cập nhật điểm thành công");                                    
               }else{
                   JOptionPane.showMessageDialog(this, "Cập nhật điểm thất bại");
               }     
            }
           
        }
    }
   /* 
    private void btnThoatActionPerformed(ActionEvent evt) {
        this.dispose();
    } */

    private PopupMenu createTable() {
        PopupMenu tableDSSV = new PopupMenu();        
      //  getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSSV = new JTable(model); 
        TableDSSV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("STT");
        model.addColumn("MSSV");
        model.addColumn("Họ tên");
        model.addColumn("Lớp SV");
        model.addColumn("Điểm quá trình");
        model.addColumn("Điểm cuối kỳ");
        model.addColumn("Điểm trung bình");
        model.addColumn("Điểm chữ");
        JScrollPane sc = new JScrollPane(TableDSSV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(100,450,1000,300);
      //  getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
        return  tableDSSV;
    }
    
     private void DSTenMH() {
         try {        
           // cbxGiangVien.removeAllItems();
            for (int i = 0; i < listMaMH.size(); i++) {
                String tenMH = new MonHocController().showTenMonHoc(listMaMH.get(i));
                cbxTenMH.addItem(tenMH);                 
            }  
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi2: " + e);
        }  
    }

    private void DSMaHK() {
        try {
            if (cbxMaHocKy.getItemCount()!= 0) {
                cbxMaHocKy.removeAllItems();
            }
            for (int i = 0; i < listMaHK.size(); i++) {
                cbxMaHocKy.addItem(listMaHK.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi1");
        }  
    }
    
    private void DSGV() {
        try {
            if (cbxGV.getItemCount()!= 0) {
                cbxGV.removeAllItems();
            }
            for (int i = 0; i < listDSGV.size(); i++) {
                cbxGV.addItem(listDSGV.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi3");
        }  
    } 

    
    private void showDSSV_MH(ResultSet result) {
    try {
        model.setRowCount(0);
        int i=0;
        while(result.next()){
            String rows[] = new String[8];
            rows[0] =Integer.toString(++i);
            rows[1] = result.getString(1);
            rows[2]= result.getString(2);
            rows[3]= result.getString(3);
            rows[4]= result.getString(4);
            rows[5]= result.getString(5);
            rows[6]= result.getString(6);
            rows[7]= result.getString(7);

            model.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
            //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
            }
        } catch (SQLException e) {
        }
    } 
    
    private void TableDSSVMouseClicked(MouseEvent evt) {
        int row = TableDSSV.getSelectedRow();
        if(row >=0){
          //  cbxMaKhoa.setSelectedItem(TableDSTK.getValueAt(row,0).toString());        
            txtMSSV.setText(TableDSSV.getValueAt(row,1).toString());
            txtHoTen.setText(TableDSSV.getValueAt(row,2).toString());
            try {
                txtDiemQT.setText(TableDSSV.getValueAt(row,4).toString());
                txtDiemCK.setText(TableDSSV.getValueAt(row,5).toString());
            } catch (NullPointerException e) {
            }
        }
    }
}
