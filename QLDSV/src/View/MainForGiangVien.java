package View;

import Controller.LoginController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
 
public class MainForGiangVien extends JFrame implements ActionListener{
        private JPanel childPanel;
        private JPanel mainPanel ;
    public MainForGiangVien() {
            mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBackground(new Color(48, 65, 86));
            this.add(mainPanel, BorderLayout.WEST);
            getContentPane().add(mainPanel);
        initUI();
    }
 
    private void initUI() {
 
        JMenuBar menubar = new JMenuBar();
        JMenu TTGiangVienMenu = new JMenu("Thông tin tài khoản");
        TTGiangVienMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem UpdateInfoItem = new JMenuItem("Cập nhật thông tin");
        UpdateInfoItem.addActionListener((ActionEvent evt) ->
        {
            UpdateInfoItemActionPerformed(evt);
        }        
        );
        UpdateInfoItem.setFont(new Font("Times New Roman", 0, 20));        
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
        TTGiangVienMenu.add(UpdateInfoItem);
        TTGiangVienMenu.add(ChangePassMenuItem);
        TTGiangVienMenu.add(logoutMenuItem);
        TTGiangVienMenu.add(ExitMenuItem);
        
        JMenu DKMDMenu = new JMenu("Đăng ký môn dạy");
        DKMDMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem DKMDMenuItem = new JMenuItem("Đăng ký môn dạy");
        DKMDMenuItem.addActionListener((ActionEvent evt) ->{
            try {
                DKMDMenuItemActionPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(MainForGiangVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        DKMDMenuItem.setFont(new Font("Times New Roman", 0, 20));
        DKMDMenu.add(DKMDMenuItem);

        
        JMenu QLDSVMenu = new JMenu("Quản lý điểm SV");
        QLDSVMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem DSVMenuItem = new JMenuItem("Nhập điểm sinh viên");
        DSVMenuItem.addActionListener((ActionEvent evt) ->{
            DSVMenuItemActionPerformed(evt);
        });
        DSVMenuItem.setFont(new Font("Times New Roman", 0, 20));
       
        QLDSVMenu.add(DSVMenuItem);
         
        menubar.add(TTGiangVienMenu);
        menubar.add(DKMDMenu);
        menubar.add(QLDSVMenu);
        setJMenuBar(menubar);
        
      //  JLabel txtLabel = new JLabel("Xin chào bạn");
      //  txtLabel.setFont(new Font("Times New Roman", 0, 50));
      //  txtLabel.setForeground(new Color(255, 0, 0));
      //  txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
      //  JLabel txtLabel1 = new JLabel("Đây là giao diện làm việc của Admin");
      //  JLabel txtLabel2 = new JLabel("Bạn có quyền thêm, sửa, xóa tài khoản/ khoa viện/ lớp sinh viên/ môn học");
  
        setTitle("Giao diện làm việc của Giảng viên");
        setFont(new Font("Times New Roman", 0, 22));
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);       
    }
     
    public static void main(String[] args) {
         
        EventQueue.invokeLater(() -> {
            MainForGiangVien ex = new MainForGiangVien();
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

    private void UpdateInfoItemActionPerformed(ActionEvent evt) {
        showPanel(new CapNhatThongTinGV());
    }

    private void DKMDMenuItemActionPerformed(ActionEvent evt) throws SQLException {
        showPanel(new DangKyMonDay());
    }

    private void DSVMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new NhapDiemSV());
    }
    
    private void showPanel(JPanel panel){
       mainPanel.removeAll();
       mainPanel.add(panel);
       mainPanel.validate();
    }

    private void logoutMenuItemActionPerformed(ActionEvent evt) {
        LoginController.maTK=null;
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }
}
