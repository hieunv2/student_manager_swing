package View;

import Controller.GiangVienController;
import Controller.KhoaVienController;
import Controller.LoginController;
import Controller.LopController;
import Controller.SinhVienController;
import Controller.TaiKhoanController;
import Controller.ValidateInput;
import Model.SinhVien;
import Model.TaiKhoan;
import ValidateInput.ValidateEmail;
import ValidateInput.ValidateName;
import ValidateInput.ValidatePhone;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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


public class QuanLySinhVien extends  JPanel{
    private ButtonGroup GroupSex;
    private JButton btnExit;
    private JLabel jLabelTitle;
    private JLabel jLabelMSSV;
    private JLabel jLabelName;
    private JLabel jLabelSex;
    private JLabel jLabelPhone;
    private JLabel jLabelEmail;
    private JLabel jLabelLopSV;
    private JLabel jLabelKhoaVien;
    private JLabel jLabel;
    private JRadioButton jRadioButtonBoy;
    private JRadioButton jRadioButtonGirl;
    private JButton btnCapNhat;
    private JTextField txtMSSV;
    private JTextField txtDiaChi;
    private JTextField txtEmail;
    private JTextField txtSĐT;
    private JTextField txtTen;
    private JComboBox cbxLopSV;
    private JTextField txtKhoaHoc;
    private JComboBox cbxKhoaVien;
    private JButton btnThem;
    private JButton btnXoa;
    private JLabel jLabelDS;
    private  JTable TableDSSV;
    
    private ArrayList<TaiKhoan> listTaiKhoan;
    DefaultTableModel model;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;    
    
    private ArrayList<String> listMaKV;
        
    private ArrayList<String> listMaLopSV;
    private ArrayList<String> listMSSV;
        
    private ArrayList<SinhVien> listSV;
    public  QuanLySinhVien(){
        // initComponents();
        createMainPanel();
      //  setTitle("Cập nhật thông tin sinh viên");
      //  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 100,800,900);
      //  setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {            
        }
        listMaKV= new KhoaVienController().DSMaKhoa();
        DSMaKhoa();
        cbxKhoaVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listMaLopSV  = new LopController().DSMaLopSV(cbxKhoaVien.getSelectedItem().toString());
                DSMaLopSV();
            }
        });
        
        listSV= new SinhVienController().getListSV();
        showDSSV();
    }

    private void createMainPanel() {
        JPanel panel = new JPanel();
          setLayout(null);
          setPreferredSize(new Dimension(700, 800));
       
        jLabelTitle = new JLabel("QUẢN LÝ SINH VIÊN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
          add(jLabelTitle);
        
        jLabel = new JLabel("Thông tin cá nhân");
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel.setForeground(new Color(0, 153, 153));
        jLabel.setBounds(100, 100, 200, 30);
          add(jLabel);
        
        jLabelMSSV = new JLabel("MSSV: ");
        jLabelMSSV.setFont(new Font("Times New Roman",Font.PLAIN, 20));

        jLabelMSSV.setBounds(120, 150, 100, 30);
          add(jLabelMSSV);
        
        jLabelName = new JLabel("Họ tên: ");
        jLabelName.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelName.setBounds(120, 200, 100, 30);
          add(jLabelName);
        
        
        jLabelSex = new JLabel("Giới tính: ");
        jLabelSex.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelSex.setBounds(120, 300, 100, 30);
          add(jLabelSex);
    
        jLabelPhone = new JLabel("SĐT: ");
        jLabelPhone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelPhone.setBounds(120, 350, 100, 30);
          add(jLabelPhone);
        
       /* jLabelAddress = new JLabel("Địa chỉ: ");
        jLabelAddress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelAddress.setBounds(120, 400,100, 30);
          add(jLabelAddress); */
        
        jLabelEmail = new JLabel("Email: ");
        jLabelEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelEmail.setBounds(120, 450, 100, 30);
          add(jLabelEmail);
        
        jLabelKhoaVien = new JLabel("Khoa Viện: ");
        jLabelKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelKhoaVien.setBounds(120, 500, 100, 30);
          add(jLabelKhoaVien);
        
        jLabelLopSV = new JLabel("Lớp SV: ");
        jLabelLopSV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelLopSV.setBounds(120, 550, 100, 30);
          add(jLabelLopSV);
                
    /*    jLabelKhoaHoc = new JLabel("Khóa: ");
        jLabelKhoaHoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelKhoaHoc.setBounds(120, 600,100, 30);
          add(jLabelKhoaHoc); */
         
        txtMSSV = new JTextField();
        txtMSSV.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtMSSV.setBounds(250, 150, 250, 30);
          add(txtMSSV);
        
        txtTen = new JTextField();
        txtTen.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtTen.setBounds(250, 200, 250, 30);
          add(txtTen);

        
        txtSĐT = new JTextField();
        txtSĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtSĐT.setBounds(250, 350, 250, 30);
          add(txtSĐT);
        
     /*   txtDiaChi = new JTextField();
        txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtDiaChi.setBounds(250, 400, 250, 30);
          add(txtDiaChi); */
        
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtEmail.setBounds(250, 450,250, 30);
          add(txtEmail);
        
        ButtonGroup GroupSex= new ButtonGroup();
       
        
        jRadioButtonBoy = new JRadioButton();
        jRadioButtonBoy.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonBoy.setText("Nam");
        jRadioButtonBoy.setBounds(300, 300, 100,50);
        
        GroupSex.add(jRadioButtonBoy);
          add(jRadioButtonBoy);
        
        jRadioButtonGirl = new JRadioButton();
        jRadioButtonGirl.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonGirl.setText("Nữ");
        jRadioButtonGirl.setBounds(400, 300, 100,50);
        
        GroupSex.add(jRadioButtonGirl);
          add(jRadioButtonGirl);
        
        cbxLopSV = new JComboBox();
        cbxLopSV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cbxLopSV.setBounds(250, 550,250, 30);
          add(cbxLopSV);
        
        cbxKhoaVien = new JComboBox();
        cbxKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cbxKhoaVien.setBounds(250, 500,250, 30);
          add(cbxKhoaVien);
        
    /*    txtKhoaHoc = new JTextField();
        txtKhoaHoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtKhoaHoc.setBounds(250, 600,250, 30);
          add(txtKhoaHoc); */
        
          
        btnCapNhat = new JButton("Sửa");
        btnCapNhat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnCapNhat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnCapNhat.setBounds(250, 700, 100, 40);
        add(btnCapNhat);
        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnCapNhatActionPerformed(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLySinhVien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLySinhVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(80, 700, 125, 40);
        add(btnThem);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnThemActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLySinhVien.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(QuanLySinhVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jLabelDS = new JLabel("Danh sách");
        jLabelDS.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        jLabelDS.setForeground(new Color(0, 204, 204));
        jLabelDS.setBounds(600, 100, 200, 30);
        add(jLabelDS);
        
        createTable();
                    
        TableDSSV.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSSVMouseClicked(evt);
            }
        });
        /*
        btnExit = new JButton("Thoát");
        btnExit.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnExit.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnExit.setBounds(500, 700, 150, 40);
          add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });  */
      
    }

    private void btnThemActionPerformed(ActionEvent evt) throws SQLException {
         if (txtMSSV.getText().isEmpty()) {
             JOptionPane.showMessageDialog(this,"MSSV không được bỏ trống");            
        } else  if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Họ tên sinh viên không được bỏ trống");
        } else if(new ValidateName().validate(txtTen.getText())) {
            JOptionPane.showMessageDialog(this,"Họ tên sinh viên không đúng định dạng, vui lòng kiểm tra lại");
        }else if (txtSĐT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Số đt không được bỏ trống");
        } else if (new ValidatePhone().validate(txtSĐT.getText())) {
            JOptionPane.showMessageDialog(this,"Số điện thoại không hợp lệ, vui lòng xem lại");
        } else if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Email không được bỏ trống");
        } else if (new ValidateEmail().validate(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this,"Email không hợp lệ");
        } else {
            SinhVien sv = new SinhVien();
            sv.setMSSV(txtMSSV.getText());
            sv.setHoTen(txtTen.getText());
            if(jRadioButtonBoy.isSelected()){
                sv.setGioiTinh("Nam");
            } else {
                sv.setGioiTinh("Nữ");
            }
            sv.setSoDienThoai(txtSĐT.getText());
          //  sv.setDiaChi(txtDiaChi.getText());
            sv.setEmail(txtEmail.getText());
          //  String date = getStartDate(txtNgaySinh.getText());

            sv.setVienQuanLy(cbxKhoaVien.getSelectedItem().toString());                
            sv.setMaLopSV(cbxLopSV.getSelectedItem().toString());
        //    sv.setKhoaHoc(txtKhoaHoc.getText());
           
            TaiKhoan tk = new TaiKhoan();
                tk.setMaTaiKhoan(txtMSSV.getText());
                tk.setUserName(txtEmail.getText());
                tk.setPassword(txtMSSV.getText());
                tk.setQuyen("SinhVien");
                int dem =0;
                listMSSV= new SinhVienController().getMSSV();
                for (int i = 0; i < listMSSV.size(); i++) {
                    String get = listMSSV.get(i);
                    if (!(get.equals(txtMSSV.getText()))) {
                        dem ++;
                    }
                }
               // System.out.println(dem);
                if (dem != listMSSV.size()) {
                    JOptionPane.showMessageDialog(this, "Mã số sinh viên đã bị trùng nhau. Hãy nhập lại !!!");
                } else {
                    if( new SinhVienController().ThemSV(sv)){                
                        if(new TaiKhoanController().ThemTK(tk)){
                            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công");
                             listSV= new SinhVienController().getListSV();
                            showDSSV();
                        } else{
                            JOptionPane.showMessageDialog(this, "Không thêm được user vào bảng Tài Khoản");
                        }              
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm sinh viên thất bại");
                    }
                }    
        }
    }
    
    /**
     *
     * @param startDate
     * @return
     */
    private void TableDSSVMouseClicked(MouseEvent evt) {
          int row = TableDSSV.getSelectedRow();
            if(row >=0){
            txtMSSV.setText(TableDSSV.getValueAt(row, 0).toString());
            txtTen.setText(TableDSSV.getValueAt(row,1).toString());
          
            if(TableDSSV.getValueAt(row,2).toString().equals("Nam")){
                
            } else {
                jRadioButtonGirl.isSelected();
            }
            txtSĐT.setText(TableDSSV.getValueAt(row,3).toString());
            txtEmail.setText(TableDSSV.getValueAt(row,4).toString());
            cbxKhoaVien.setSelectedItem(TableDSSV.getValueAt(row, 5).toString());
            cbxLopSV.setSelectedItem(TableDSSV.getValueAt(row,6).toString());
                                   
            txtMSSV.setEditable(false);
        }
    }
            
    private void btnCapNhatActionPerformed(ActionEvent evt) throws ParseException, SQLException {   
        int updateRow = TableDSSV.getSelectedRow();
            if(listSV.isEmpty()){
                 JOptionPane.showMessageDialog(this, "Hãy thêm giảng viên rồi sửa!");
            } else if(updateRow == -1){ 
                JOptionPane.showMessageDialog(this, "Hãy chọn giảng viên cần sửa");
            } else{
                SinhVien sv = new SinhVien();
        
                if (txtTen.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Họ tên sinh viên không được bỏ trống");
                } else if(new ValidateName().validate(txtTen.getText())) {
                    JOptionPane.showMessageDialog(this,"Họ tên sinh viên không đúng định dạng, vui lòng kiểm tra lại");
                }else if (txtSĐT.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Số đt không được bỏ trống");
                } else if (new ValidatePhone().validate(txtSĐT.getText())) {
                    JOptionPane.showMessageDialog(this,"Số điện thoại không hợp lệ, vui lòng xem lại");
                } else if (txtEmail.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Email không được bỏ trống");
                } else if (new ValidateEmail().validate(txtEmail.getText())) {
                    JOptionPane.showMessageDialog(this,"Email không hợp lệ");
                } else {
                    sv.setMSSV(txtMSSV.getText());
                    sv.setHoTen(txtTen.getText());
                    if(jRadioButtonBoy.isSelected()){
                        sv.setGioiTinh("Nam");
                    } else {
                        sv.setGioiTinh("Nữ");
                    }
                    sv.setSoDienThoai(txtSĐT.getText());
                  //  sv.setDiaChi(txtDiaChi.getText());
                    sv.setEmail(txtEmail.getText());
                  //  String date = getStartDate(txtNgaySinh.getText());

                    sv.setVienQuanLy(cbxKhoaVien.getSelectedItem().toString());                
                    sv.setMaLopSV(cbxLopSV.getSelectedItem().toString());
                    
                     TaiKhoan tk = new TaiKhoan();
                tk.setMaTaiKhoan(txtMSSV.getText());
                tk.setUserName(txtEmail.getText());
                    
                if( new SinhVienController().SuaSinhVien(sv)){
                if (new TaiKhoanController().SuaTK(tk)){
                    JOptionPane.showMessageDialog(this, "Sửa sinh viên thành công");
                    showDSSV();
                } else {
                    JOptionPane.showMessageDialog(this,"Lỗi sửa tài khoản sinh viên");
                }                
            } else {
                JOptionPane.showMessageDialog(this,"Sửa sinh viên thất bại");
            }
            }
        
    }
    
    }   
    
    private void btnXoaActionPerformed(ActionEvent evt) throws SQLException {
       
    }
 
 /*   private void btnExitActionPerformed(ActionEvent evt) {
        this.dispose();
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

    private void DSMaLopSV() {
        try {
            cbxLopSV.removeAllItems();
            for (int i = 0; i < listMaLopSV.size(); i++) {               
                cbxLopSV.addItem(listMaLopSV.get(i));  
            }           
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Lỗi2");
        }    
    }

    private void createTable() {
          model = new DefaultTableModel();
        TableDSSV = new JTable(model); 
        TableDSSV.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("MSSV");
        model.addColumn("Họ tên");
        model.addColumn("Giới tính");
        model.addColumn("SĐT");
        model.addColumn("Email");
        model.addColumn("Viện");
        model.addColumn("Lớp SV");

        JScrollPane sc = new JScrollPane(TableDSSV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(550,150,610,550);
      //  getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }
    
    
       private void showDSSV() {
        model.setRowCount(0);
        listSV.forEach((sv) -> {
            model.addRow(new Object[]{
                sv.getMSSV(), sv.getHoTen(), sv.getGioiTinh(),sv.getSoDienThoai(), sv.getEmail(), sv.getVienQuanLy(), sv.getMaLopSV()
            });
        });
    } 
}
