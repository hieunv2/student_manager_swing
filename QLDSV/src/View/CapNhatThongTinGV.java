package View;

import Controller.GiangVienController;
import Controller.KhoaVienController;
import Controller.LoginController;
import Controller.ValidateInput;
import Model.GiangVien;
import connectsql.ConnectToDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
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

public class CapNhatThongTinGV  extends JPanel {
     private ButtonGroup GroupSex;
    private JButton btnExit;
    private JLabel jLabelTitle;
    private JLabel jLabelName;
    private JLabel jLabelSex;
    private JLabel jLabelSĐT;
    private JLabel jLabelTrinhDoGV;
    private JLabel jLabelEmail;
    private JLabel jLabelNgaySinh;
    private JLabel jLabelKhoaVien;
    private JLabel jLabel;
    private JRadioButton jRadioButtonBoy;
    private JRadioButton jRadioButtonGirl;
    private JButton btnCapNhat;
    private JTextField txtTrinhDoGV;
    private JTextField txtSĐT;
    private JTextField txtTen;
    private JTextField txtNgaySinh;
    private JComboBox cbxKhoaVien;
    private JTextField txtEmail;
    
    private Connection conn;
    
    private ArrayList<String> listMaKV;
    
    public CapNhatThongTinGV(){
        createMainPanel();
      //  setTitle("Cập nhật thông tin giảng viên");
       // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // setBounds(550, 100,800,770);
       // setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {            
            }
        
        listMaKV= new KhoaVienController().DSMaKhoa();
        DSMaKhoa();
    }

    private void createMainPanel() {
      //  JPanel panel = new JPanel();
        setLayout(null);
    //     setPreferredSize(new Dimension(700, 770));
        
        jLabelTitle = new JLabel("CẬP NHẬT THÔNG TIN GIẢNG VIÊN");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 100, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
         add(jLabelTitle);
        
        jLabel = new JLabel("Thông tin cá nhân");
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel.setForeground(new Color(0, 153, 153));
        jLabel.setBounds(150, 150, 200, 30);
         add(jLabel);
        
        jLabelName = new JLabel("Họ tên ");
        jLabelName.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelName.setBounds(200, 225, 100, 30);
         add(jLabelName);
        
      /*  jLabelNgaySinh = new JLabel("Ngày sinh ");
        jLabelNgaySinh.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelNgaySinh.setBounds(120, 200, 100, 30);
         add(jLabelNgaySinh); */
        
        jLabelSex = new JLabel("Giới tính: ");
        jLabelSex.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelSex.setBounds(200, 300, 100, 30);
         add(jLabelSex);
        
        jLabelKhoaVien = new JLabel("Khoa Viện: ");
        jLabelKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelKhoaVien.setBounds(200, 375, 100, 30);
         add(jLabelKhoaVien);
        
        jLabelTrinhDoGV = new JLabel("Trình độ ");
        jLabelTrinhDoGV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelTrinhDoGV.setBounds(200, 450,100, 30);
         add(jLabelTrinhDoGV);
    
        jLabelSĐT = new JLabel("SĐT: ");
        jLabelSĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelSĐT.setBounds(200, 525, 100, 30);
         add(jLabelSĐT );       
        
        jLabelEmail = new JLabel("Email: ");
        jLabelEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelEmail.setBounds(200, 600, 100, 30);
         add(jLabelEmail);
        
        txtTen = new JTextField();
        txtTen.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtTen.setBounds(350, 225, 600, 30);
         add(txtTen);
        
       /* txtNgaySinh = new JTextField();
        txtNgaySinh.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtNgaySinh.setBounds(250, 200, 400, 30);
         add(txtNgaySinh); */
        
        cbxKhoaVien = new JComboBox();
        cbxKhoaVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cbxKhoaVien.setBounds(350, 375, 600, 30);
         add(cbxKhoaVien);
        
        
        txtTrinhDoGV = new JTextField();
        txtTrinhDoGV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtTrinhDoGV.setBounds(350, 450, 600, 30);
         add(txtTrinhDoGV);
        
        txtSĐT = new JTextField();
        txtSĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtSĐT.setBounds(350, 525, 600, 30);
         add(txtSĐT);
        
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtEmail.setBounds(350, 600,600, 30);
         add(txtEmail);
        
        ButtonGroup GroupSex= new ButtonGroup();
       
        
        jRadioButtonBoy = new JRadioButton();
        jRadioButtonBoy.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonBoy.setText("Nam");
        jRadioButtonBoy.setBounds(400, 300, 100,50);
        
        GroupSex.add(jRadioButtonBoy);
         add(jRadioButtonBoy);
        
        jRadioButtonGirl = new JRadioButton();
        jRadioButtonGirl.setFont(new Font("Times New Roman",Font.PLAIN, 18)); // NOI18N
        jRadioButtonGirl.setText("Nữ");
        jRadioButtonGirl.setBounds(700, 300, 100,50);
        
        GroupSex.add(jRadioButtonGirl);
         add(jRadioButtonGirl);
        
         btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnCapNhat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnCapNhat.setBounds(550, 700, 150, 40);
        add(btnCapNhat);
        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        
        
    /*    btnExit = new JButton("Thoát");
        btnExit.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnExit.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnExit.setBounds(500, 550, 150, 40);
         add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });  */
    }
    
    private void btnCapNhatActionPerformed(ActionEvent evt) {
        if (txtTen.getText().isEmpty() || new ValidateInput().checkName(txtTen.getText())==false) {
            JOptionPane.showMessageDialog(this, "Hãy nhập lại tên giảng viên, lưu ý, Tên giảng viên là viết không dấu, không có số, không có kí tự đặc biệt.");
        } else if (txtTrinhDoGV.getText().isEmpty()|| new ValidateInput().checkName(txtTrinhDoGV.getText())==false) {
            JOptionPane.showMessageDialog(this, "Hãy nhập lại trình độ giảng viên, lưu ý, trình độ giảng viên viết không dấu, không có ký tự đặc biệt.");
        } else if (txtSĐT.getText().isEmpty()|| new ValidateInput().checkNumber(txtSĐT.getText())== false) {
            JOptionPane.showMessageDialog(this, "Hãy nhập lại số điện thoại, lưu ý, số điện thoại chỉ chứa chữ số và dấu chấm");
        } else if (txtEmail.getText().isEmpty()|| new ValidateInput().checkEmail(txtEmail.getText())== false) {
            JOptionPane.showMessageDialog(this, "Hãy nhập lại email, lưu ý eamil chỉ chứ các chữ cái, chữ số, dấu chấm và @");
        } else {
             GiangVien gv = new GiangVien();
            gv.setHoTen(txtTen.getText());
            if(jRadioButtonBoy.isSelected()){
                gv.setGioiTinh("Nam");
            } else {
                gv.setGioiTinh("Nữ");
            }
            gv.setMaKhoa(cbxKhoaVien.getSelectedItem().toString());
            gv.setTrinhDo(txtTrinhDoGV.getText());
            gv.setSoDienThoai(txtSĐT.getText());
            gv.setEmail(txtEmail.getText());


            if( new GiangVienController().CapNhatTTGV(gv, LoginController.maTK)){
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
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
    
}
