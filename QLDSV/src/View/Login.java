
package View;

import Controller.LoginController;
import Model.TaiKhoan;
import connectsql.ConnectToDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame{
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    JLabel jLabelUse ;
    JLabel jLabelPass;
    JLabel jLabelTitle;
    JTextField txtUseName ;
    JPasswordField txtPassword ;
    JButton btnLogin;    
    JButton btnExit ; 
    JButton btnQuenMK;
      
    public Login(){           
        initComponents();
        setLocationRelativeTo(this);
        setTitle("Đăng nhập hệ thống");
        setBounds(650, 250, 600, 500);//Gán kích thước
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }
    }
    
    private void initComponents(){
        this.setLayout(null);

        jLabelTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(30, 28, 524, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);       
        
        jLabelUse = new JLabel("Username");
        jLabelUse.setFont(new Font("Time New Roman", Font.PLAIN, 18));
        jLabelUse.setBounds(75, 120, 100, 19);
        add(jLabelUse);
        
        jLabelPass = new JLabel("Password");
        jLabelPass.setFont(new Font("Time New Roman", Font.PLAIN, 18));
        jLabelPass.setBounds(75, 220, 100, 19);
        add(jLabelPass);
        
        txtUseName = new JTextField();
        txtUseName.setFont(new Font("Time New Roman", Font.PLAIN, 18));
        txtUseName.setBounds(200, 120,300, 35);
        add(txtUseName);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Time New Roman", Font.PLAIN, 18));
        txtPassword.setBounds(200, 220, 300, 35);
        add(txtPassword);
        
        btnLogin= new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Time New Roman", Font.PLAIN, 18));
        btnLogin.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\login.png"));
        btnLogin.setBounds(50, 320, 150, 40);
        add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLoginActionPerformed(e); 
            }
        });
        
        btnQuenMK = new JButton("Quên MK");
        btnQuenMK.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnQuenMK.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnQuenMK.setBounds(230,320, 150, 40);
        add(btnQuenMK);
        btnQuenMK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnQuenMKActionPerformed(evt);
            }
        });

        btnExit= new JButton("Thoát");
        btnExit.setFont(new Font("Time New Roman", Font.PLAIN, 18));
        btnExit.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\power-off.png"));
        btnExit.setBounds(400, 320, 150, 40);
        add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnExitActionPerformed(e);
            }
        });
     }
    private void btnExitActionPerformed(ActionEvent e) {
        this.dispose();
    }
    
    private void btnLoginActionPerformed(ActionEvent e) {
        TaiKhoan tk= new TaiKhoan();
        tk.setUserName(txtUseName.getText());
        tk.setPassword(txtPassword.getText());        
        LoginController lg = new LoginController();
        if(lg.check_login(tk))
            this.dispose();
        else {
            txtUseName.setText("");
            txtPassword.setText("");
        }
       // System.out.println(LoginController.maTK);
    }
    
    private void btnQuenMKActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(rootPane, "Vui lòng liên hệ qua email: duyen.ptm173075@sis.hust.edu.vn để được hỗ trợ đổi mật khẩu nhé!!");
    }
    
    public static void main(String[] args) {       
        Login obj=new Login();
        obj.setVisible(true);//Hiển thị
       // obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
