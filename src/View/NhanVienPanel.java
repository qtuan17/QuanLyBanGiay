/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Dao.NhanVienDao;
import Model.NhanVien;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tuanb
 */
public class NhanVienPanel extends javax.swing.JPanel {

    private DefaultTableModel model;
    NhanVienDao nhanVienDao;
    int index = -1;

    public NhanVienPanel(java.awt.Frame parent, boolean modal) throws Exception {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        initComponents();
        nhanVienDao = new NhanVienDao();
        fillTableNhanVien();
    }

    void fillTableNhanVien() {
        DefaultTableModel modelNV = new DefaultTableModel();
        modelNV = (DefaultTableModel) tblNV.getModel();
        modelNV.setRowCount(0);
        try {
            List<NhanVien> nhanViens = nhanVienDao.findAll();
            if (nhanViens.isEmpty()) {
                System.out.println("Không có nhân viên nào.");
            }
            for (NhanVien nhanVien : nhanViens) {
                Object[] row = {
                    nhanVien.getIdNV(),
                    nhanVien.getHoTenNV(),
                    nhanVien.getNgaySinh(),
                    nhanVien.getDiaChi(),
                    nhanVien.getSdt(),
                    nhanVien.getPassword(),
                    // Toán tử 3 ngôi để hiển thị trạng thái
                    nhanVien.getTrangThai() == 1 ? "Đang làm việc" : "Đã nghỉ"
                };
                modelNV.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    void fillTableChucVu() {
//        DefaultTableModel modelCV = new DefaultTableModel();
//        modelCV = (DefaultTableModel) tblChucVu.getModel();
//        modelCV.setRowCount(0);
//        try {
//            List<ChucVu> chucVu = chucVuDao.findAll();
//            if (chucVu.isEmpty()) {
//
//            }
//            for (ChucVu cv : chucVu) {
//                Object[] row = {
//                    cv.getID_CV(),
//                    cv.getTenChucVu(),
//                    cv.getMoTa(),
//                    cv.getTrangThai()
//                };
//                modelCV.addRow(row);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private NhanVien getFormNhanVien() {
        try {
            // Tạo đối tượng NhanVien mới
            NhanVien nhanVien = new NhanVien();

            // Nếu index không phải -1, gán ID từ txtIDNV
            if (index != -1) {
                nhanVien.setIdNV(Integer.parseInt(txtIDNV.getText()));
            }

            // Gán họ tên
            nhanVien.setHoTenNV(txtHoTen.getText());

            // Xử lý ngày sinh (giả sử txtNgaySinh là JTextField và nhập theo định dạng yyyy-MM-dd)
            String stringNgaySinh = txtNgaySinh.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false); // Đảm bảo ngày nhập đúng định dạng
            try {
                Date ngaysinh = dateFormat.parse(stringNgaySinh);
                java.sql.Date sqlDate = new java.sql.Date(ngaysinh.getTime());
                nhanVien.setNgaySinh(sqlDate);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Ngày sinh sai định dạng (yyyy-MM-dd)");
                return null;
            }
            // Gán các giá trị khác từ form
            nhanVien.setDiaChi(txtDiaChi.getText());
            nhanVien.setSdt(txtTaiKhoan.getText());
            nhanVien.setPassword(txtMatKhau.getText());

            return nhanVien;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lấy thông tin nhân viên.");
            e.printStackTrace();
            return null;
        }
    }

    // fill combobox Chức Vụ
//    s

//    private void setFormChucVu(int index) {
//        if (index != -1) {
//            String idCV = tblChucVu.getValueAt(index, 0).toString();
//            String tenCV = tblChucVu.getValueAt(index, 1).toString();
//            txtIDCV.setText(idCV);
//            txtTenCV.setText(tenCV);
//        }
//    }

//    private ChucVu getFormChucVu() {
//    ChucVu chucVu = new ChucVu();
//    if (index != -1) {
//        chucVu.setID_CV(Integer.parseInt(txtIDCV.getText())); // ID nếu có
//        System.out.println("Mã chức vụ: " + txtIDCV.getText());
//        chucVu.setMoTa(txtMoTa.getText());
//        System.out.println("Mô tả: " + txtMoTa.getText());  // Kiểm tra giá trị mô tả
//    }
//    chucVu.setTenChucVu(txtTenCV.getText());
//    System.out.println("Tên chức vụ: " + txtTenCV.getText());  // Kiểm tra tên chức vụ
//    return chucVu;
//}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtIDNV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        BangNV = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnAddNV = new javax.swing.JButton();
        btnEditNV = new javax.swing.JButton();
        btnDeleteNV = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIDCV = new javax.swing.JTextField();
        txtTenCV = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChucVu = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        btnAddCV = new javax.swing.JButton();
        btnEditCV = new javax.swing.JButton();
        btnDeleteCV = new javax.swing.JButton();
        btnKhoiPhucCV = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        txtIDNV.setEnabled(false);

        txtCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCCCDActionPerformed(evt);
            }
        });

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Tài Khoản", "Mật Khẩu", "Trạng Thái"
            }
        ));
        BangNV.setViewportView(tblNV);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Họ Tên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày Sinh");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("CCCD");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Địa Chỉ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tài Khoản");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Mật Khẩu");

        btnAddNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAddNV.setText("Thêm");
        btnAddNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNVActionPerformed(evt);
            }
        });

        btnEditNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        btnEditNV.setText("Sửa");

        btnDeleteNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDeleteNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDeleteNV.setText("Xóa");

        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh-arrow.png"))); // NOI18N
        btnKhoiPhuc.setText("Khôi Phục");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BangNV)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIDNV, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditNV, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteNV, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKhoiPhuc))
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddNV)
                        .addComponent(btnEditNV)
                        .addComponent(btnDeleteNV))
                    .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BangNV, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Nhân Viên", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("ID");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tên Chức Vụ");

        txtIDCV.setEditable(false);

        tblChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên Chức Vụ", "Mô Tả ", "Trạng Thái"
            }
        ));
        jScrollPane2.setViewportView(tblChucVu);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Mô Tả");

        btnAddCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAddCV.setText("Thêm");
        btnAddCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCVActionPerformed(evt);
            }
        });

        btnEditCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        btnEditCV.setText("Sửa ");

        btnDeleteCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDeleteCV.setText("Xóa");

        btnKhoiPhucCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh-arrow.png"))); // NOI18N
        btnKhoiPhucCV.setText("Khôi phục");
        btnKhoiPhucCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucCVActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Trạng Thái");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMoTa)
                                    .addComponent(txtIDCV)
                                    .addComponent(txtTenCV)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAddCV)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEditCV)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDeleteCV)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnKhoiPhucCV))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(154, 154, 154)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIDCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCV)
                    .addComponent(btnEditCV)
                    .addComponent(btnDeleteCV)
                    .addComponent(btnKhoiPhucCV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(540, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chức Vụ", jPanel2);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 48)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/profile.png"))); // NOI18N
        jLabel2.setText(" Quản Lí Nhân Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoiPhucCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucCVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhoiPhucCVActionPerformed

    private void btnAddCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCVActionPerformed
        //        ChucVu chucVu = getFormChucVu();
        //
        //        // Kiểm tra dữ liệu đầu vào (đảm bảo không có giá trị rỗng hoặc null)
        //        if (chucVu.getTenChucVu() == null || chucVu.getTenChucVu().isEmpty() || chucVu.getMoTa() == null || chucVu.getMoTa().isEmpty()) {
            //            JOptionPane.showMessageDialog(this, "Tên chức vụ và mô tả không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            //            return;
            //        }
        //
        //        // Thực hiện thêm mới chức vụ vào cơ sở dữ liệu
        //        try {
            //            int addCV = chucVuDao.create(chucVu);
            //
            //            if (addCV > 0) {
                //                System.out.println("Thêm chức vụ thành công");
                //                // Cập nhật lại bảng sau khi thêm thành công
                //                fillTableChucVu();
                //            } else {
                //                JOptionPane.showMessageDialog(this, "Không thể thêm chức vụ. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                //            }
            //        } catch (Exception e) {
            //            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm chức vụ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            //            e.printStackTrace();
            //        }
    }//GEN-LAST:event_btnAddCVActionPerformed

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void btnAddNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNVActionPerformed
        // TODO add your handling code here:
        NhanVien nhanVien = getFormNhanVien();
        if (nhanVien != null) {
            int addNhanVien = nhanVienDao.create(nhanVien);
            if (addNhanVien > 0) {
                System.out.println("ADD THanh COng");
                fillTableNhanVien();
            }
            JOptionPane.showMessageDialog(btnAddNV, "Thêm thành công");
        }
    }//GEN-LAST:event_btnAddNVActionPerformed

    private void txtCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCCCDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane BangNV;
    private javax.swing.JButton btnAddCV;
    private javax.swing.JButton btnAddNV;
    private javax.swing.JButton btnDeleteCV;
    private javax.swing.JButton btnDeleteNV;
    private javax.swing.JButton btnEditCV;
    private javax.swing.JButton btnEditNV;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnKhoiPhucCV;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblChucVu;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtIDCV;
    private javax.swing.JTextField txtIDNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTenCV;
    // End of variables declaration//GEN-END:variables
}
