package View;

import Controller.HocKyController;
import Controller.LoginController;
import Controller.SinhVien_MonHocController;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class BangDiemMonHoc extends JPanel{
    private JLabel jLabelTitle;   
    private JLabel jLabel;
    private JLabel jLabelHocKy;
    JTable TableDSMH;
    DefaultTableModel model;    
    private  JComboBox<String> cbxMaHocKy;
    private JButton btnEnter;
    private JButton btnThoat;
    
    private Connection conn;
    private ArrayList<String> listMaHK;
    
    public BangDiemMonHoc(){
        createMainPanel();
      //  setTitle("Bảng điểm môn học");
      //  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 100,1200,900);
       // setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {            
            }
        listMaHK = new HocKyController().DSHocKy();        
        DSMaHK();
        
        ResultSet rs = new SinhVien_MonHocController().listDiem(LoginController.maTK);
        showDiem(rs);
    }

    private void createMainPanel() {
       // JPanel panel = new JPanel();
          setLayout(null);
          //setPreferredSize(new Dimension(1150, 900));
        
        jLabelTitle = new JLabel("BẢNG ĐIỂM MÔN HỌC");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 30, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
          add(jLabelTitle);
        
        jLabel = new JLabel("Lọc theo");
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel.setForeground(new Color(0, 153, 153));
        jLabel.setBounds(100, 100, 200, 30);
          add(jLabel);
        
        jLabelHocKy = new JLabel("Học kỳ: ");
        jLabelHocKy.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelHocKy.setBounds(120, 150, 100, 30);
          add(jLabelHocKy);
       
        cbxMaHocKy = new JComboBox<>();
        cbxMaHocKy.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        cbxMaHocKy.setBounds(225, 150, 450, 30);
          add(cbxMaHocKy);
        
        btnEnter = new JButton("Enter");
        btnEnter.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnEnter.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\reload.png"));
        btnEnter.setBounds(800, 150, 150, 30);
          add(btnEnter);
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        
          add(createTable());
        
        TableDSMH.addMouseListener(new java.awt.event.MouseAdapter() {
          /*  @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSMHMouseClicked(evt);
            }*/
        }); 
        
    }
    
    private void btnEnterActionPerformed(ActionEvent evt) {
        ResultSet rs = new SinhVien_MonHocController().listDiemHK(LoginController.maTK, cbxMaHocKy.getSelectedItem().toString());
        showDiem(rs);
    }
    
    private void DSMaHK() {
        try {
            
            for (int i = 0; i < listMaHK.size(); i++) {
                cbxMaHocKy.addItem(listMaHK.get(i));
            }           
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Lỗi1");
        }  
    }

    private PopupMenu createTable() {
        PopupMenu tableDSMH = new PopupMenu();        
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSMH = new JTable(model); 
        TableDSMH.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Học kỳ");
        model.addColumn("Tên môn học");
        model.addColumn("Số tín chỉ");
        model.addColumn("Điểm quá trình");
        model.addColumn("Điểm cuối kỳ");
        model.addColumn("Điểm chữ");
        JScrollPane sc = new JScrollPane(TableDSMH, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(100,225,1000,550);
       // getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
        return  tableDSMH;
    }
    
    
    private void showDiem(ResultSet result) {
    try {
        model.setRowCount(0);
        while(result.next()){
            String rows[] = new String[6];
            rows[0] =result.getString(1);
            rows[1] = result.getString(2);
            rows[2]= result.getString(3);
            rows[3]= result.getString(4);
            rows[4]= result.getString(5);
            rows[5]= result.getString(6);

            model.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
            //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
            }
        } catch (SQLException e) {
        }
    } 
}
