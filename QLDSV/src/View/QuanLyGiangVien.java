package View;

import Controller.GiangVienController;
import Controller.GiangVien_MonDayController;
import Controller.KhoaVienController;
import Controller.SinhVien_MonHocController;
import Controller.TaiKhoanController;
import Model.GiangVien;
import Model.TaiKhoan;
import ValidateInput.ValidateEmail;
import ValidateInput.ValidateName;
import ValidateInput.ValidatePhone;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class QuanLyGiangVien  extends JPanel {
     private ButtonGroup GroupSex;
   // private JButton btnExit;
    private JLabel jLabelTitle;
    private JLabel jLabelDS;
    private JTable TableDSGV;
    private JLabel jLabelName;
    private JLabel jLabelSex;
    private JLabel jLabelSĐT;
    private JLabel jLabelTrinhDoGV;
    private JLabel jLabelEmail;
    private JLabel jLabelMaGiangVien;
    private JLabel jLabelKhoaVien;
    private JLabel jLabel;
    private JRadioButton jRadioButtonBoy;
    private JRadioButton jRadioButtonGirl;
    private JButton btnCapNhat;
    private JTextField txtTrinhDoGV;
    private JTextField txtSĐT;
    private JTextField txtTen;
    private JTextField txtMaGiangVien;
    private JComboBox cbxKhoaVien;
    private JTextField txtEmail;
     private JButton btnThem;
    private JButton btnXoa;
    
  //  private ArrayList<TaiKhoan> listTaiKhoan;
    DefaultTableModel model;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    
    private ArrayList<String> listMaKV;
    private ArrayList<String> listMaGV;
    private ArrayList<GiangVien> listGV;
    
    public QuanLyGiangVien(){
        createMainPanel();
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {            
        }       
        listMaKV= new KhoaVienController().DSMaKhoa();
        listMaGV= new GiangVienController().getMaGV();
        listGV= new GiangVienController().getListGV();
        DSMaKhoa();
        showDSGV();
    }

    private void createMainPanel() {
      //  JPanel panel = new JPanel();
        setLayout(null);
    //     setPreferredSize(new Dimension(700, 770));
        
        jLabelTitle = new JLabel("QUẢN LÝ GIẢNG VIÊN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 70, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
         add(jLabelTitle);
        
        jLabel = new JLabel("Thông tin cá nhân");
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel.setForeground(new Color(0, 153, 153));
        jLabel.setBounds(80, 150, 200, 30);
         add(jLabel);
         
        jLabelMaGiangVien = new JLabel("Mã GV ");
        jLabelMaGiangVien.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelMaGiangVien.setBounds(100, 200, 100, 30);
         add(jLabelMaGiangVien);
        
        jLabelName = new JLabel("Họ tên ");
        jLabelName.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelName.setBounds(100, 250, 100, 30);
         add(jLabelName);
        
      /*  jLabelNgaySinh = new JLabel("Ngày sinh ");
        jLabelNgaySinh.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelNgaySinh.setBounds(120, 200, 100, 30);
         add(jLabelNgaySinh); */
        
        jLabelSex = new JLabel("Giới tính: ");
        jLabelSex.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelSex.setBounds(100, 300, 100, 30);
         add(jLabelSex);
        
        jLabelKhoaVien = new JLabel("Khoa Viện: ");
        jLabelKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelKhoaVien.setBounds(100, 375, 100, 30);
         add(jLabelKhoaVien);
        
        jLabelTrinhDoGV = new JLabel("Trình độ ");
        jLabelTrinhDoGV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelTrinhDoGV.setBounds(100, 450,100, 30);
         add(jLabelTrinhDoGV);
    
        jLabelSĐT = new JLabel("SĐT: ");
        jLabelSĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelSĐT.setBounds(100, 525, 100, 30);
         add(jLabelSĐT );       
        
        jLabelEmail = new JLabel("Email: ");
        jLabelEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelEmail.setBounds(100, 600, 100, 30);
         add(jLabelEmail);
         
        txtMaGiangVien = new JTextField();
        txtMaGiangVien.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtMaGiangVien.setBounds(250, 200, 200, 30);
         add(txtMaGiangVien);
        
        txtTen = new JTextField();
        txtTen.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtTen.setBounds(250, 250, 200, 30);
         add(txtTen);
        
       /* txtNgaySinh = new JTextField();
        txtNgaySinh.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtNgaySinh.setBounds(250, 200, 400, 30);
         add(txtNgaySinh); */
        
        cbxKhoaVien = new JComboBox();
        cbxKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cbxKhoaVien.setBounds(250, 375, 200, 30);
         add(cbxKhoaVien);
        
        
        txtTrinhDoGV = new JTextField();
        txtTrinhDoGV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtTrinhDoGV.setBounds(250, 450, 200, 30);
         add(txtTrinhDoGV);
        
        txtSĐT = new JTextField();
        txtSĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtSĐT.setBounds(250, 525, 200, 30);
         add(txtSĐT);
        
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtEmail.setBounds(250, 600,200, 30);
         add(txtEmail);
        
        ButtonGroup GroupSex= new ButtonGroup();
       
        
        jRadioButtonBoy = new JRadioButton();
        jRadioButtonBoy.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonBoy.setText("Nam");
        jRadioButtonBoy.setBounds(250, 300, 100,50);
        
        GroupSex.add(jRadioButtonBoy);
         add(jRadioButtonBoy);
        
        jRadioButtonGirl = new JRadioButton();
        jRadioButtonGirl.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonGirl.setText("Nữ");
        jRadioButtonGirl.setBounds(350, 300, 100,50);
        
        GroupSex.add(jRadioButtonGirl);
         add(jRadioButtonGirl);
        
        btnCapNhat = new JButton("Sửa");
        btnCapNhat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnCapNhat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnCapNhat.setBounds(250, 700, 100, 40);
        add(btnCapNhat);
        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnCapNhatActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyGiangVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(75, 700, 125, 40);
        add(btnThem);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnThemActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyGiangVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
         btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\updated.png"));
        btnXoa.setBounds(400, 700, 100, 40);
        add(btnXoa);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnXoaActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyGiangVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    
        jLabelDS = new JLabel("Danh sách");
        jLabelDS.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        jLabelDS.setForeground(new Color(0, 204, 204));
        jLabelDS.setBounds(550, 150, 200, 30);
        add(jLabelDS);
        
        createTable();
                    
        TableDSGV.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSGVMouseClicked(evt);
            }
        });
    
    }
    
    
    private void TableDSGVMouseClicked(MouseEvent evt) {
        int row = TableDSGV.getSelectedRow();
            if(row >=0){
            txtMaGiangVien.setText(TableDSGV.getValueAt(row, 0).toString());
            txtTen.setText(TableDSGV.getValueAt(row,1).toString());
          
            if(TableDSGV.getValueAt(row,2).toString().equals("Nam")){
                
            } else {
                jRadioButtonGirl.isSelected();
            }
            cbxKhoaVien.setSelectedItem(TableDSGV.getValueAt(row, 3).toString());
            txtTrinhDoGV.setText(TableDSGV.getValueAt(row,4).toString());
            txtSĐT.setText(TableDSGV.getValueAt(row,5).toString());
            txtEmail.setText(TableDSGV.getValueAt(row,6).toString());            
            txtMaGiangVien.setEditable(false);
        }
    }
    
    private void btnXoaActionPerformed(ActionEvent evt) throws SQLException {
        int deleteRow = TableDSGV.getSelectedRow();
            if(listGV.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm giảng viên rồi sửa!");
            } else if(deleteRow == -1){ 
                JOptionPane.showMessageDialog(this, "Hãy chọn giảng viên cần sửa");
            } else{
                if(new SinhVien_MonHocController().SoLuong(txtMaGiangVien.getText())>0){
                    JOptionPane.showMessageDialog(this, "Bạn không thể thực hiện xóa được, do giảng viên này có liên quan đến điểm của các sinh viên kỳ trước đó.");
                } else {
                    
                    GiangVien gv = new GiangVien();
                    gv.setMaGiangVien(txtMaGiangVien.getText());
                    gv.setHoTen(txtTen.getText());
                    if(jRadioButtonBoy.isSelected())
                        gv.setGioiTinh("Nam");
                    else
                        gv.setGioiTinh("Nữ");
                    gv.setMaKhoa(cbxKhoaVien.getSelectedItem().toString());
                    gv.setTrinhDo(txtTrinhDoGV.getText());
                    gv.setSoDienThoai(txtSĐT.getText());
                    gv.setEmail(txtEmail.getText());
                
                    TaiKhoan tk = new TaiKhoan();
                    tk.setMaTaiKhoan(txtMaGiangVien.getText());
                    tk.setUserName(txtEmail.getText());
                    tk.setPassword(txtMaGiangVien.getText());
                   
                    if (new GiangVien_MonDayController().SoLuong(txtMaGiangVien.getText())>0) {
                        if (new TaiKhoanController().XoaGV(tk) 
                                && new GiangVien_MonDayController().XoaGV(txtMaGiangVien.getText())) {
                            if(new GiangVienController().XoaGiangVien(txtMaGiangVien.getText())){
                                JOptionPane.showMessageDialog(this, "Xóa thành công");
                            } else{
                                JOptionPane.showMessageDialog(this, "Xóa thất bại");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Thực hiện thất bại");
                        }
                    } else {
                       // System.out.println(txtMaGiangVien.getText());
                        if (new TaiKhoanController().XoaGV(tk)&&new GiangVienController().XoaGiangVien(txtMaGiangVien.getText())) {
                            System.out.println(txtMaGiangVien.getText());
                           // if(new GiangVienController().XoaGiangVien(txtMaGiangVien.getText())){
                                JOptionPane.showMessageDialog(this, "Xóa thành công");
                           } else{
                                JOptionPane.showMessageDialog(this, "Xóa thất bại");
                            }
                        // else {
                        //    JOptionPane.showMessageDialog(this, "Thực hiện thất bại");
                       // }
                    }
                  
                }
            }     
    }
      
    private void btnThemActionPerformed(ActionEvent evt) throws SQLException {
        if(txtMaGiangVien.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Mã giảng viên không được bỏ trống");
        } else if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Tên giảng viên không được bỏ trống");
        } else if ((new ValidateName().validate(txtTen.getText()))) {
            JOptionPane.showMessageDialog(this,"Bạn nhập sai định dạng Tên. Lưu ý, tên chỉ chứa chữ cái và dấu cách");
        } else if (txtTrinhDoGV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Trình độ giảng viên không được bỏ trống");
        } else if ((new ValidateName().validate(txtTrinhDoGV.getText()))) {
            JOptionPane.showMessageDialog(this,"Trình độ giảng viên bị nhập sai định dạng. Lưu ý, trường này chỉ chứa chữ cái và dấu cách");
        } else if (txtSĐT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Số điện thoại không được bỏ trống");
        } else if ((new ValidatePhone().validate(txtSĐT.getText()))) {
            JOptionPane.showMessageDialog(this,"Bạn nhập sai định dạng SĐT");
        } else if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Email không được bỏ trống");
        } else if ((new ValidateEmail().validate(txtEmail.getText()))) {
            JOptionPane.showMessageDialog(this,"Email không hợp lệ");
        } else {
            GiangVien gv = new GiangVien();
            gv.setMaGiangVien(txtMaGiangVien.getText());
            gv.setHoTen(txtTen.getText());
            if(jRadioButtonBoy.isSelected())
                gv.setGioiTinh("Nam");
            else
                gv.setGioiTinh("Nữ");
                gv.setMaKhoa(cbxKhoaVien.getSelectedItem().toString());
                gv.setTrinhDo(txtTrinhDoGV.getText());
                gv.setSoDienThoai(txtSĐT.getText());
                gv.setEmail(txtEmail.getText());
                
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTaiKhoan(txtMaGiangVien.getText());
                tk.setUserName(txtEmail.getText());
                tk.setPassword(txtMaGiangVien.getText());
                tk.setQuyen("GiangVien");
                int dem =0;
                for (int i = 0; i < listMaGV.size(); i++) {
                    String get = listMaGV.get(i);
                    if (!(get.equals(txtMaGiangVien.getText()))) {
                        dem ++;
                    }
                }
                
                if (dem != listMaGV.size()) {
                    JOptionPane.showMessageDialog(this, "Mã giảng viên đã bị trùng nhau. Hãy nhập lại !!!");
                } else {
                    if( new GiangVienController().ThemGiangVien(gv)){                
                        if(new TaiKhoanController().ThemTK(tk)){
                            JOptionPane.showMessageDialog(this, "Thêm giảng viên thành công");
                            showDSGV();
                        } else{
                            JOptionPane.showMessageDialog(this, "Không thêm được user vào bảng Tài Khoản");
                        }              
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm giảng viên thất bại");
                    }
                }    
        }
    }
    
    private void btnCapNhatActionPerformed(ActionEvent evt) throws SQLException {
        int updateRow = TableDSGV.getSelectedRow();
            if(listGV.isEmpty()){
                 JOptionPane.showMessageDialog(this, "Hãy thêm giảng viên rồi sửa!");
            } else if(updateRow == -1){ 
                JOptionPane.showMessageDialog(this, "Hãy chọn giảng viên cần sửa");
            } else{
            if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Tên giảng viên không được bỏ trống");
        } else if ((new ValidateName().validate(txtTen.getText()))) {
            JOptionPane.showMessageDialog(this,"Bạn nhập sai định dạng Tên. Lưu ý, tên chỉ chứa chữ cái và dấu cách");
        } else if (txtTrinhDoGV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Trình độ giảng viên không được bỏ trống");
        } else if ((new ValidateName().validate(txtTrinhDoGV.getText()))) {
            JOptionPane.showMessageDialog(this,"Trình độ giảng viên bị nhập sai định dạng. Lưu ý, trường này chỉ chứa chữ cái và dấu cách");
        } else if (txtSĐT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Số điện thoại không được bỏ trống");
        } else if ((new ValidatePhone().validate(txtSĐT.getText()))) {
            JOptionPane.showMessageDialog(this,"Bạn nhập sai định dạng SĐT");
        } else if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Email không được bỏ trống");
        } else if ((new ValidateEmail().validate(txtEmail.getText()))) {
            JOptionPane.showMessageDialog(this,"Email không hợp lệ");
        } else {
            GiangVien gv = new GiangVien();
            gv.setMaGiangVien(txtMaGiangVien.getText());
            gv.setHoTen(txtTen.getText());
            if(jRadioButtonBoy.isSelected())
                gv.setGioiTinh("Nam");
            else
                gv.setGioiTinh("Nữ");
                gv.setMaKhoa(cbxKhoaVien.getSelectedItem().toString());
                gv.setTrinhDo(txtTrinhDoGV.getText());
                gv.setSoDienThoai(txtSĐT.getText());
                gv.setEmail(txtEmail.getText());
                
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTaiKhoan(txtMaGiangVien.getText());
                tk.setUserName(txtEmail.getText());
              //  tk.setPassword(txtMaGiangVien.getText());
              //  tk.setQuyen("GiangVien");

            if( new GiangVienController().SuaGiangVien(gv)){
                if (new TaiKhoanController().SuaTK(tk)){
                    JOptionPane.showMessageDialog(this, "Sửa giảng viên thành công");
                    showDSGV();
                } else {
                    JOptionPane.showMessageDialog(this,"Lỗi sửa tài khoản giảng viên");
                }                
            } else {
                JOptionPane.showMessageDialog(this,"Sửa giảng viên thất bại");
            }
        }        
            }
        
        
    }
    
   /* private void btnExitActionPerformed(ActionEvent evt) {
        System.exit(0);
    } */

    private void DSMaKhoa() {
        try {
            for (int i = 0; i < listMaKV.size(); i++) {
                 cbxKhoaVien.addItem(listMaKV.get(i));
            }           
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi1");
        }
    }

    private void createTable() {
         model = new DefaultTableModel();
        TableDSGV = new JTable(model); 
        TableDSGV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Mã GV");
        model.addColumn("Họ tên");
        model.addColumn("Giới tính");
        model.addColumn("Viện");
        model.addColumn("Trình độ");
        model.addColumn("SĐT");
        model.addColumn("Email");

        JScrollPane sc = new JScrollPane(TableDSGV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(500,200,630,450);
      //  getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }
    
    private void showDSGV() {
        model.setRowCount(0);
        listGV.forEach((gv) -> {
            model.addRow(new Object[]{
                gv.getMaGiangVien(), gv.getHoTen(), gv.getGioiTinh(), gv.getMaKhoa(),
                gv.getTrinhDo(), gv.getSoDienThoai(), gv.getEmail()
            });
        });
    }
    
}
