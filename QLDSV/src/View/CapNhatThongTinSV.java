package View;

import Controller.KhoaVienController;
import Controller.LoginController;
import Controller.LopController;
import Controller.SinhVienController;
import Controller.ValidateInput;
import Model.SinhVien;
import ValidateInput.ValidateEmail;
import ValidateInput.ValidateName;
import ValidateInput.ValidatePhone;
import connectsql.ConnectToDatabase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class CapNhatThongTinSV extends  JPanel{
    private ButtonGroup GroupSex;
    private JButton btnExit;
    private JLabel jLabelTitle;
    private JLabel jLabelMSSV;
    private JLabel jLabelName;
    private JLabel jLabelSex;
    private JLabel jLabelPhone;
    private JLabel jLabelAddress;
    private JLabel jLabelEmail;
    private JLabel jLabelLopSV;
    private JLabel jLabelKhoaHoc;
    private JLabel jLabelNgaySinh;
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
    private JTextField txtNgaySinh;
    private JComboBox cbxLopSV;
    private JTextField txtKhoaHoc;
    private JComboBox cbxKhoaVien;
    
    private Connection conn;
    
    private ArrayList<String> listMaLopSV;
    private ArrayList<String> listMaKV;
    
    public  CapNhatThongTinSV() {
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
    }

    private void createMainPanel() {
        JPanel panel = new JPanel();
          setLayout(null);
          setPreferredSize(new Dimension(700, 800));
       
        jLabelTitle = new JLabel("CẬP NHẬT THÔNG TIN SINH VIÊN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
          add(jLabelTitle);
        
        jLabel = new JLabel("Thông tin cá nhân");
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel.setForeground(new Color(0, 153, 153));
        jLabel.setBounds(150, 100, 200, 30);
          add(jLabel);
        
        jLabelMSSV = new JLabel("MSSV: ");
        jLabelMSSV.setFont(new Font("Times New Roman",Font.PLAIN, 20));

        jLabelMSSV.setBounds(170, 150, 100, 30);
          add(jLabelMSSV);
        
        jLabelName = new JLabel("Họ tên: ");
        jLabelName.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelName.setBounds(170, 200, 100, 30);
          add(jLabelName);
        
        jLabelSex = new JLabel("Giới tính: ");
        jLabelSex.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelSex.setBounds(120, 300, 100, 30);
          add(jLabelSex);
    
        jLabelPhone = new JLabel("SĐT: ");
        jLabelPhone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelPhone.setBounds(170, 350, 100, 30);
          add(jLabelPhone);
        
        
        jLabelEmail = new JLabel("Email: ");
        jLabelEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelEmail.setBounds(170, 450, 100, 30);
          add(jLabelEmail);
        
        jLabelKhoaVien = new JLabel("Khoa Viện: ");
        jLabelKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelKhoaVien.setBounds(170, 500, 100, 30);
          add(jLabelKhoaVien);
        
        jLabelLopSV = new JLabel("Lớp SV: ");
        jLabelLopSV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelLopSV.setBounds(170, 550, 100, 30);
          add(jLabelLopSV);
                
         
        txtMSSV = new JTextField();
        txtMSSV.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtMSSV.setBounds(450, 150, 400, 30);
          add(txtMSSV);
        
        txtTen = new JTextField();
        txtTen.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtTen.setBounds(450, 200, 400, 30);
          add(txtTen);
        
        
        txtSĐT = new JTextField();
        txtSĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtSĐT.setBounds(450, 350, 400, 30);
          add(txtSĐT);
        
        
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtEmail.setBounds(450, 450,400, 30);
          add(txtEmail);
        
        ButtonGroup GroupSex= new ButtonGroup();
       
        
        jRadioButtonBoy = new JRadioButton();
        jRadioButtonBoy.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonBoy.setText("Nam");
        jRadioButtonBoy.setBounds(350, 300, 100,50);
        
        GroupSex.add(jRadioButtonBoy);
          add(jRadioButtonBoy);
        
        jRadioButtonGirl = new JRadioButton();
        jRadioButtonGirl.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonGirl.setText("Nữ");
        jRadioButtonGirl.setBounds(500, 300, 100,50);
        
        GroupSex.add(jRadioButtonGirl);
          add(jRadioButtonGirl);
        
        cbxLopSV = new JComboBox();
        cbxLopSV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cbxLopSV.setBounds(250, 550,400, 30);
          add(cbxLopSV);
        
        cbxKhoaVien = new JComboBox();
        cbxKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cbxKhoaVien.setBounds(250, 500,400, 30);
          add(cbxKhoaVien);

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnCapNhat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnCapNhat.setBounds(200, 700, 150, 40);
        add(btnCapNhat);
        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnCapNhatActionPerformed(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(CapNhatThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            
    private void btnCapNhatActionPerformed(ActionEvent evt) throws ParseException {        
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
           // sv.setDiaChi(txtDiaChi.getText());
            sv.setEmail(txtEmail.getText());
          

            sv.setVienQuanLy(cbxKhoaVien.getSelectedItem().toString());                
            sv.setMaLopSV(cbxLopSV.getSelectedItem().toString());
        //    sv.setKhoaHoc(txtKhoaHoc.getText());

            if( new SinhVienController().CapNhatTT(sv, LoginController.maTK)){
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
            }
        }
        
    }

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
}
