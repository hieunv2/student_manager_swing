package View;

import Controller.HocKyController;
import Controller.MonHocController;
import Controller.ValidateInput;
import Model.MonHoc;
import connectsql.ConnectToDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class QuanLyMonHoc  extends JPanel{
    private JTable TableDSMonHoc;
    private JButton btnSua;
    private JButton btnThem;
  //  private JButton btnThoat;
    private JButton btnXoa;
    private JLabel jLabelMaMH;
    private JLabel jLabelTenMon;
    private JLabel jLabelSoTC;
    private JLabel jLabelHeSoQT;
    private JLabel jLabelHeSoCK;
    private JLabel jLabelTitle;
    private JLabel jLabel;
    private JTextField txtHeSoCK;
    private JTextField txtHeSoQT;
    private JTextField txtMaMH;
    private JTextField txtSoTC;
    private JTextField txtTenMH;
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    private ArrayList<MonHoc> listMonHoc;
    private ArrayList<String> listDSHocKy;
    DefaultTableModel model;
    
    public QuanLyMonHoc(){
        initComponents();
      //  setTitle("Quản lý môn học");
      ////  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(550, 100,1300,900);
      //  setLocationRelativeTo(null);
        try {
            conn = ConnectToDatabase.ConnectToDatabase();
        } catch (Exception e) {
            
        }
        listMonHoc = new MonHocController().getListMonHoc();
        listDSHocKy = new HocKyController().DSHocKy();
        model = (DefaultTableModel) TableDSMonHoc.getModel(); 
        showDSMonHoc();
    }

    private void initComponents() {
       // JPanel panel = new JPanel();
        this.setLayout(null);
        //panel.setPreferredSize(new Dimension(1200, 800));
        
        jLabelTitle = new JLabel("QUẢN LÝ MÔN HỌC");
        jLabelTitle.setFont(new Font("Time New Roman", Font.BOLD, 24));
        jLabelTitle.setForeground(new Color(255, 0, 0));
        jLabelTitle.setBounds(350, 70, 520, 44);
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabelTitle);
        
        jLabelMaMH = new JLabel("Mã môn học: ");
        jLabelMaMH.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelMaMH.setBounds(120, 150, 150, 30);
        add(jLabelMaMH);
        
        jLabelTenMon = new JLabel("Tên môn: ");
        jLabelTenMon.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        jLabelTenMon.setBounds(120, 200, 150, 30);
        add(jLabelTenMon);
        
        jLabelSoTC = new JLabel("Số tín chỉ: ");
        jLabelSoTC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelSoTC.setBounds(120, 250, 150, 30);
        add(jLabelSoTC);
        
        jLabelHeSoQT = new JLabel("Hệ số quá trình: ");
        jLabelHeSoQT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelHeSoQT.setBounds(120, 300, 150, 30);
        add(jLabelHeSoQT);
        
        jLabelHeSoCK = new JLabel("Hệ số cuối kỳ: ");
        jLabelHeSoCK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabelHeSoCK.setBounds(120, 350,150, 30);
        add(jLabelHeSoCK);
        
        txtMaMH = new JTextField();
        txtMaMH.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtMaMH.setBounds(250, 150, 400, 30);
        add(txtMaMH);
        
        txtTenMH = new JTextField();
        txtTenMH.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        txtTenMH.setBounds(250, 200, 400, 30);
        add(txtTenMH);
        
        
        txtSoTC = new JTextField();
        txtSoTC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtSoTC.setBounds(250, 250, 400, 30);
        add(txtSoTC);
        
        txtHeSoQT = new JTextField();
        txtHeSoQT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtHeSoQT.setBounds(250, 300, 400, 30);
        add(txtHeSoQT);
        
        
       txtHeSoCK = new JTextField();
        txtHeSoCK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtHeSoCK.setBounds(250, 350,400, 30);
        add(txtHeSoCK); 
        
        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\plus.png"));
        btnThem.setBounds(750, 150, 125, 30);
        add(btnThem);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnThemActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyMonHoc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnSua.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\updated.png"));
        btnSua.setBounds(1000, 150, 125, 30);
        add(btnSua);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        
        btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\delete.png"));
        btnXoa.setBounds(750, 220, 125, 30);
        add(btnXoa);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        
      /*  btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        btnThoat.setIcon(new ImageIcon("D:\\QLDSV\\src\\View\\images\\multiply.png"));
        btnThoat.setBounds(1000, 220, 125, 30);
        add(btnThoat);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        }); */
        
        
        jLabel = new JLabel("Danh sách môn học");
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22)); // NOI18N
        jLabel.setForeground(new Color(0, 153, 153));
        jLabel.setBounds(100, 400, 200, 30);
        add(jLabel);
        
        createTable();                    
        TableDSMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDSMonHocMouseClicked(evt);
            }
        });     
    }
    
    public void restForm(){
        txtTenMH.setText("");
        txtSoTC.setText("");
        txtHeSoCK.setText("");
        txtHeSoQT.setText("");
        txtMaMH.setText(""); 
        txtMaMH.setEditable(true);
    }
    
    private void TableDSMonHocMouseClicked(MouseEvent evt) {
        try {
            int row = TableDSMonHoc.getSelectedRow();
            if(row >=0){
            txtMaMH.setText(TableDSMonHoc.getValueAt(row, 0).toString());
            txtTenMH.setText(TableDSMonHoc.getValueAt(row,1).toString());
            txtSoTC.setText(TableDSMonHoc.getValueAt(row, 2).toString());
            txtHeSoQT.setText(TableDSMonHoc.getValueAt(row,3).toString());
            txtHeSoCK.setText(TableDSMonHoc.getValueAt(row,4).toString());
            
            txtMaMH.setEditable(false);
            }
        } catch (java.lang.NullPointerException e) {
        }
    }
    
    private void btnThemActionPerformed(ActionEvent evt) throws SQLException {
        try {

            if(txtMaMH.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy nhập mã môn học");
            }else if (txtTenMH.getText().isEmpty() || new ValidateInput().checkName(txtTenMH.getText())==false) {
                JOptionPane.showMessageDialog(this, "Hãy nhập lại tên môn học");
            } else if (Integer.parseInt(txtSoTC.getText())<=0 || new ValidateInput().checkNumber(txtSoTC.getText())==false) {
                JOptionPane.showMessageDialog(this, "Hãy nhập lại số tín chỉ");
            } else if (Integer.parseInt(txtHeSoQT.getText())<=0 || Integer.parseInt(txtHeSoQT.getText())>=10 || new ValidateInput().checkNumber(txtHeSoQT.getText())==false) {
                JOptionPane.showMessageDialog(this, "Hãy nhập lại hệ số quá trình");
            }else if (Integer.parseInt(txtHeSoCK.getText())<=0 || Integer.parseInt(txtHeSoCK.getText())>=10 || new ValidateInput().checkNumber(txtHeSoCK.getText())==false) {
                JOptionPane.showMessageDialog(this, "Hãy nhập lại hệ số cuối kỳ");
            } else if( Integer.parseInt(txtHeSoCK.getText())!=((int)10 - Integer.parseInt(txtHeSoQT.getText()))){       
                JOptionPane.showMessageDialog(this, "Bạn đã nhập sai, vì hệ số cuối kì bằng 10 trừ hệ số quá trình. Yêu cầu nhập lại");                    
            } else {               
                MonHoc mh = new MonHoc();
                mh.setMaMonHoc(txtMaMH.getText());
                mh.setTenMonHoc(txtTenMH.getText());
                mh.setSoTinChi(Integer.parseInt(txtSoTC.getText()));
                mh.setHeSoQT(Integer.parseInt(txtHeSoQT.getText()));
                mh.setHeSoCK(Integer.parseInt(txtHeSoCK.getText()));
                if(new MonHocController().ThemMonHoc(mh)){
                    JOptionPane.showMessageDialog(this,"Thêm thành công");
                    listMonHoc.add(mh);
                }else{
                     JOptionPane.showMessageDialog(this, "Thêm môn học thất bại");
                }         
            }        
            showDSMonHoc();
           // restForm();
        } catch (HeadlessException | NumberFormatException e) {
            
        }
    }
        
    private void btnSuaActionPerformed(ActionEvent evt) {
        try {
            int updateRow = TableDSMonHoc.getSelectedRow();
            if(listMonHoc.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm môn học rồi sửa!");
            } else if(updateRow == -1){ 
                JOptionPane.showMessageDialog(this, "Hãy chọn môn học cần sửa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn thay đổi ?", "Sửa môn học", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {
                MonHoc mh = listMonHoc.get(updateRow);
                
                if(txtMaMH.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Hãy nhập mã môn học");
                }else if (txtTenMH.getText().isEmpty()|| new ValidateInput().checkName(txtTenMH.getText())==false) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập lại tên môn học");
                } else if (Integer.parseInt(txtSoTC.getText())<=0 || new ValidateInput().checkNumber(txtSoTC.getText())==false) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập lại số tín chỉ");
                } else if (Integer.parseInt(txtHeSoQT.getText())<=0 || Integer.parseInt(txtHeSoQT.getText())>=10 || new ValidateInput().checkNumber(txtHeSoQT.getText())==false) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập lại hệ số quá trình");
                }else if (Integer.parseInt(txtHeSoCK.getText())<=0 || Integer.parseInt(txtHeSoCK.getText())>=10 || new ValidateInput().checkNumber(txtHeSoCK.getText())==false) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập lại hệ số cuối kỳ");
                } else if( Integer.parseInt(txtHeSoCK.getText())!=((int)10 - Integer.parseInt(txtHeSoQT.getText()))){       
                    JOptionPane.showMessageDialog(this, "Bạn đã nhập sai, vì hệ số cuối kì bằng 10 trừ hệ số quá trình. Yêu cầu nhập lại");                    
                } else { 
                    MonHoc newMonHoc = new MonHoc();
                    newMonHoc.setMaMonHoc(txtMaMH.getText());
                    newMonHoc.setTenMonHoc(txtTenMH.getText());
                    newMonHoc.setSoTinChi(Integer.parseInt(txtSoTC.getText()));
                    newMonHoc.setHeSoQT(Integer.parseInt(txtHeSoQT.getText()));
                    newMonHoc.setHeSoCK(Integer.parseInt(txtHeSoCK.getText()));
                    if( new MonHocController().SuaMonHoc(newMonHoc)) {  
                        listMonHoc.remove(mh);
                        listMonHoc.add(newMonHoc);
                        JOptionPane.showMessageDialog(this,"Sửa thành công");
                    }else{
                        JOptionPane.showMessageDialog(this, "Sửa thất bại");
                        }                        
                    }  
                }
                showDSMonHoc();
                restForm();
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException e) {

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnXoaActionPerformed(ActionEvent evt) {
         try {
            int removeRow = TableDSMonHoc.getSelectedRow();            
            if(listMonHoc.isEmpty()){
                JOptionPane.showMessageDialog(this, "Hãy thêm môn học rồi xóa");
            } else if(removeRow == -1){
                JOptionPane.showMessageDialog(this, "Hãy chọn một môn học rồi xóa");
            } else {
                int select = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn xóa ?", "Xóa môn học", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
                if (select == 0) {  
                    MonHoc mh= listMonHoc.get(removeRow); 
                    if( new MonHocController().XoaMonHoc(mh)) {
                        listMonHoc.remove(mh);
                        JOptionPane.showMessageDialog(this,"Xóa thành công.");
                    } else{
                        JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                        } 
                    }       
                showDSMonHoc();
                restForm();
            }           
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /* 
    private void btnThoatActionPerformed(ActionEvent evt) {
        this.dispose();
    }*/

    private void createTable() {
       // getContentPane().setLayout(null);
        model = new DefaultTableModel();
        TableDSMonHoc = new JTable(model); 
        TableDSMonHoc.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        model.addColumn("Mã môn học");
        model.addColumn("Tên môn học");
        model.addColumn("Số tín chỉ");
        model.addColumn("Hệ số quá trình");
        model.addColumn("Hệ số cuối kỳ");
        JScrollPane sc = new JScrollPane(TableDSMonHoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setBounds(150,450,1000,350);
        //getContentPane().add(sc);
        this.add(sc, BorderLayout.CENTER);
    }

    private void showDSMonHoc() {
        model.setRowCount(0);
        listMonHoc.forEach((mh) -> {
            model.addRow(new Object[]{
                mh.getMaMonHoc(), mh.getTenMonHoc(), mh.getSoTinChi(), mh.getHeSoQT(), mh.getHeSoCK()
            });
        });
    }
}
