package View;

import Controller.LoginController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
 
public class MainForAdmin extends JFrame implements ActionListener{
    
    private JPanel childPanel;
   private JPanel mainPanel ;
      private JLabel label;
 
    public MainForAdmin() {    

            mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBackground(new Color(48, 65, 86));
            this.add(mainPanel, BorderLayout.WEST);
            getContentPane().add(mainPanel);
            
           /* label = new JLabel("");
            label.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\test.jpg"));
            
            label.setBounds(0, 0, 1100, 900);
            getContentPane().add(label); */
        
        initUI();
   
        this.setLocationRelativeTo(this);
    }
 
    private void initUI() {
 
        JMenuBar menubar = new JMenuBar();
        JMenu TTAdminMenu = new JMenu("Thông tin tài khoản");
        TTAdminMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem ChangePassMenuItem = new JMenuItem("Đổi mật khẩu");
        ChangePassMenuItem.addActionListener((ActionEvent evt) ->
        {
            ChangePassMenuItemActionPerformed(evt);
        }        
        );
        ChangePassMenuItem.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem logoutMenuItem = new JMenuItem("Đăng xuất");
        logoutMenuItem.addActionListener((ActionEvent evt) ->{
            logoutMenuItemActionPerformed(evt);
        });
        logoutMenuItem.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem ExitMenuItem = new JMenuItem("Thoát");
        ExitMenuItem.setFont(new Font("Times New Roman", 0, 20));        
        ExitMenuItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        TTAdminMenu.add(ChangePassMenuItem);
        TTAdminMenu.add(logoutMenuItem);
        TTAdminMenu.add(ExitMenuItem);
        
        JMenu QLTKMenu = new JMenu("Quản lý tài khoản");
        QLTKMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem DSTKMenuItem = new JMenuItem("Danh sách tài khoản");
        DSTKMenuItem.addActionListener((ActionEvent evt) ->{
            DSTKMenuItemActionPerformed(evt);
        });
        DSTKMenuItem.setFont(new Font("Times New Roman", 0, 20));
        
        JMenuItem QLGVMenuItem = new JMenuItem("Quản lý giảng viên");
        QLGVMenuItem.addActionListener((ActionEvent evt) ->{
            QLGVMenuItemActionPerformed(evt);
        });
        QLGVMenuItem.setFont(new Font("Times New Roman", 0, 20));
        
        JMenuItem QLSVMenuItem = new JMenuItem("Quản lý sinh viên");
        QLSVMenuItem.addActionListener((ActionEvent evt) ->{
            try {
                QLSVMenuItemActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(MainForAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        QLSVMenuItem.setFont(new Font("Times New Roman", 0, 20));
        
        QLTKMenu.add(DSTKMenuItem);
        QLTKMenu.add(QLGVMenuItem);
        QLTKMenu.add(QLSVMenuItem);

        

        
        JMenu QLCTHMenu = new JMenu("Quản lý chương trình học");
        QLCTHMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem QLKVMenuItem = new JMenuItem("Quản lý Khoa/Viên");
        QLKVMenuItem.addActionListener((ActionEvent evt) ->{
            QLKVMenuItemActionPerformed(evt);
        });
        QLKVMenuItem.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem QLLSVMenuItem = new JMenuItem("Quản lý lớp sinh viên");
        QLLSVMenuItem.addActionListener((ActionEvent evt) ->{
            QLLSVMenuItemActionPerformed(evt);
        });
        QLLSVMenuItem.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem QLMHMenuItem = new JMenuItem("Quản lý môn học");
        QLMHMenuItem.addActionListener((ActionEvent evt) ->{
            QLMHMenuItemActionPerformed(evt);
        });
        QLMHMenuItem.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem QLMHTHKMenuItem = new JMenuItem("Quản lý môn học theo học kỳ");
        QLMHTHKMenuItem.addActionListener((ActionEvent evt) ->{
            QLMHTHKMenuItemActionPerformed(evt);
        });
        QLMHTHKMenuItem.setFont(new Font("Times New Roman", 0, 20));        
        QLCTHMenu.add(QLKVMenuItem);
        QLCTHMenu.add(QLLSVMenuItem);
        QLCTHMenu.add(QLMHMenuItem);
        QLCTHMenu.add(QLMHTHKMenuItem);

         
        JMenu QLDMenu = new JMenu("Quản lý điểm");
        QLDMenu.setFont(new Font("Times New Roman", 0, 20));
        
        JMenuItem NhapDiemMenuItem = new JMenuItem("Nhập điểm");
        NhapDiemMenuItem.addActionListener((ActionEvent evt) ->{
            NhapDiemMenuItemActionPerformed(evt);
        });
        NhapDiemMenuItem.setFont(new Font("Times New Roman", 0, 20));
        QLDMenu.add(NhapDiemMenuItem);
         
        menubar.add(TTAdminMenu);
        menubar.add(QLTKMenu);
        menubar.add(QLCTHMenu);
        menubar.add(QLDMenu);
        setJMenuBar(menubar);
        
      //  JLabel txtLabel = new JLabel("Xin chào bạn");
      //  txtLabel.setFont(new Font("Times New Roman", 0, 50));
      //  txtLabel.setForeground(new Color(255, 0, 0));
      //  txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
      //  JLabel txtLabel1 = new JLabel("Đây là giao diện làm việc của Admin");
      //  JLabel txtLabel2 = new JLabel("Bạn có quyền thêm, sửa, xóa tài khoản/ khoa viện/ lớp sinh viên/ môn học");
  
        setTitle("Giao diện làm việc của Admin");
        setFont(new Font("Times New Roman", 0, 22));
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);      
       
    }
     
    public static void main(String[] args) {
         
        EventQueue.invokeLater(() -> {
            MainForAdmin ex = new MainForAdmin();
            ex.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ChangePassMenuItemActionPerformed(ActionEvent evt) {
       showPanel(new DoiMatKhau());
    }
    
    private void showPanel(JPanel panel){
       mainPanel.removeAll();
       mainPanel.add(panel);
       mainPanel.validate();
    }

    private void QLLSVMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new QuanLyLopSV());
    }

    private void DSTKMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new QuanLyTaiKhoan());
    }

    private void QLKVMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new QuanLyKhoaVien());
    }

    private void QLMHMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new QuanLyMonHoc());
    }

    private void QLMHTHKMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new QuanLyMonHocTheoHK());
    }

    private void logoutMenuItemActionPerformed(ActionEvent evt) {
        LoginController.maTK=null;
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }

    private void QLGVMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new QuanLyGiangVien());
    }

    private void QLSVMenuItemActionPerformed(ActionEvent evt) throws ParseException {
        showPanel(new QuanLySinhVien());
    }

    private void NhapDiemMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new NhapDiemSVForAdmin());
    }
}
