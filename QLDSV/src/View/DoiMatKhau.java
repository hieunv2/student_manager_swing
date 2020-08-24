
package View;

import Controller.LoginController;
import Controller.TaiKhoanController;
import Model.TaiKhoan;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DoiMatKhau  extends JPanel{
    private Connection conn;
    
    private JButton btnExit;
    private JButton btnChange;
   // private JButton btnQuenMK;
    private JLabel jLabelMKCu;
    private JLabel jLabelMKMoi;
    
    private JPasswordField MKCu;
    private JPasswordField MKMoi;
    
    public DoiMatKhau(){    
    add(createMainPanel(), BorderLayout.CENTER);
       // setTitle("Đổi mật khẩu");
       // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 100,600,500);
       // setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {            
            }
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(600, 500));
        panel.setBorder(customBorder("ĐỔI MẬT KHẨU"));
        
        jLabelMKMoi = new JLabel("Mật khẩu cũ ");
        jLabelMKMoi.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelMKMoi.setBounds(100, 100, 120, 30);
        panel.add(jLabelMKMoi);
        
        jLabelMKCu = new JLabel("Mật khẩu mới ");
        jLabelMKCu.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelMKCu.setBounds(100, 200, 120, 30);
        panel.add(jLabelMKCu);
        
        MKMoi = new JPasswordField();
        MKMoi.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        MKMoi.setBounds(250, 200, 250, 30);
        panel.add(MKMoi);
        
        MKCu = new JPasswordField();
        MKCu.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        MKCu.setBounds(250, 100, 250, 30);
        panel.add(MKCu);
        
        
         btnChange = new JButton("Đổi");
        btnChange.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnChange.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnChange.setBounds(150, 350, 150, 40);
        add(btnChange);
        btnChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });
        
        
     /*   btnExit = new JButton("Thoát");
        btnExit.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnExit.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnExit.setBounds(350,350, 150, 40);
        panel.add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });   */
          
        
        return panel;
    }
    /*   
    private void btnExitActionPerformed(ActionEvent evt) {
        this.dispose();
    } */
    
    private void btnChangeActionPerformed(ActionEvent evt) {
        String matk = LoginController.maTK;
        String mk_moi = MKMoi.getText();  
        String quyen = new TaiKhoanController().getQuyen(matk);
        if ((MKCu.getText()).equals(new TaiKhoanController().getMK(matk))) {
            if (new TaiKhoanController().DoiPass(matk, mk_moi)){
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công. Vui lòng đăng nhập lại.");
                System.exit(0);
               /* if (quyen.equals("SinhVien")) {
                    System.out.println("téuujf");
                    MainForSinhVien mainSV = new MainForSinhVien();
                    mainSV.dispose();
                }                
                if(quyen.equals("GiangVien")){
                   // MainForGiangVien mainGV = new MainForGiangVien();
                    mainGV.dispose();
                } */
            }            
        } else {
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại");
        }   
        Login lg = new Login();
        lg.dispose();
    }
    
    public void restForm(){
        MKCu.setText("");
        MKMoi.setText("");
    }
    
    public TitledBorder customBorder(String name) {
        TitledBorder titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), name, TitledBorder.CENTER, TitledBorder.TOP);
        titleBorder.setTitleFont(new Font("Time New Roman", Font.BOLD, 24));
        titleBorder.setTitleColor(Color.red);
        return titleBorder;
    }
}
