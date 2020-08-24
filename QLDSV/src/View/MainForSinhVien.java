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
 
 
public class MainForSinhVien extends JFrame implements ActionListener{
        private JPanel childPanel;
        private JPanel mainPanel ;
    public MainForSinhVien() {
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
        JMenu TTSinhVienMenu = new JMenu("Thông tin tài khoản");
        TTSinhVienMenu.setFont(new Font("Times New Roman", 0, 20));
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
     /*   JMenuItem ExitMenuItem = new JMenuItem("Thoát");
        ExitMenuItem.setFont(new Font("Times New Roman", 0, 20));        
        ExitMenuItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        }); */
        TTSinhVienMenu.add(UpdateInfoItem);
        TTSinhVienMenu.add(ChangePassMenuItem);
        TTSinhVienMenu.add(logoutMenuItem);
      //  TTSinhVienMenu.add(ExitMenuItem);
        
        JMenu QLTKMenu = new JMenu("Đăng ký môn học");
        QLTKMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem DKMHMenuItem = new JMenuItem("Đăng ký môn học");
        DKMHMenuItem.addActionListener((ActionEvent evt) ->{
            try {
                DKMHMenuItemActionPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(MainForSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        DKMHMenuItem.setFont(new Font("Times New Roman", 0, 20));
        QLTKMenu.add(DKMHMenuItem);

        
        JMenu KQHTMenu = new JMenu("Kết quả học tập");
        KQHTMenu.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem BangDiemMonHocMenuItem = new JMenuItem("Bảng điểm môn học");
        BangDiemMonHocMenuItem.addActionListener((ActionEvent evt) ->{
            BangDiemMonHocMenuItemActionPerformed(evt);
        });
        BangDiemMonHocMenuItem.setFont(new Font("Times New Roman", 0, 20));
        JMenuItem BangDiemTongKetMenuItem = new JMenuItem("Bảng điểm tổng kết");
        BangDiemTongKetMenuItem.addActionListener((ActionEvent evt) ->{
            BangDiemTongKetMenuItemActionPerformed(evt);
        });
        BangDiemTongKetMenuItem.setFont(new Font("Times New Roman", 0, 20));     
        KQHTMenu.add(BangDiemMonHocMenuItem);
        KQHTMenu.add(BangDiemTongKetMenuItem);
         
        menubar.add(TTSinhVienMenu);
        menubar.add(QLTKMenu);
        menubar.add(KQHTMenu);
        setJMenuBar(menubar);
        
      //  JLabel txtLabel = new JLabel("Xin chào bạn");
      //  txtLabel.setFont(new Font("Times New Roman", 0, 50));
      //  txtLabel.setForeground(new Color(255, 0, 0));
      //  txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
      //  JLabel txtLabel1 = new JLabel("Đây là giao diện làm việc của Admin");
      //  JLabel txtLabel2 = new JLabel("Bạn có quyền thêm, sửa, xóa tài khoản/ khoa viện/ lớp sinh viên/ môn học");
  
        setTitle("Giao diện làm việc của sinh viên");
        setFont(new Font("Times New Roman", 0, 22));
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);       
    }
     
    public static void main(String[] args) {
         
        EventQueue.invokeLater(() -> {
            MainForSinhVien ex = new MainForSinhVien();
            ex.setVisible(true);
        });
    }

    private void UpdateInfoItemActionPerformed(ActionEvent evt) {
        showPanel(new CapNhatThongTinSV());
    }

    private void ChangePassMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new DoiMatKhau());
    }

    private void DKMHMenuItemActionPerformed(ActionEvent evt) throws SQLException {
        showPanel(new DangKyMonHoc());
    }
    
    private void BangDiemMonHocMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new BangDiemMonHoc());
    }

    private void BangDiemTongKetMenuItemActionPerformed(ActionEvent evt) {
        showPanel(new BangDiemTongKet());
    }

    private void showPanel(JPanel panel){
       mainPanel.removeAll();
       mainPanel.add(panel);
       mainPanel.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    private void logoutMenuItemActionPerformed(ActionEvent evt) {
        LoginController.maTK=null;
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }
}
