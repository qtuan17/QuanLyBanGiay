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
        DefaultTableModel modelNV = (DefaultTableModel) tblNV.getModel();
        modelNV.setRowCount(0);
        try {
            List<NhanVien> nhanViens = nhanVienDao.findAllNhanVien();
            for (NhanVien nv : nhanViens) {
                modelNV.addRow(new Object[]{
                    nv.getIdNV(),
                    nv.getHoTenNV(),
                    nv.getNgaySinh(),
                    nv.getDiaChi(),
                    nv.getSdt(),
                    nv.getPassword(),
                    nv.getTrangThai() == 1 ? "Đang làm việc" : "Đã nghỉ"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private NhanVien getFormNhanVien() {
        try {
            NhanVien nv = new NhanVien();
            if (index != -1) {
                nv.setIdNV(Integer.parseInt(txtID.getText().trim()));
            }
            nv.setHoTenNV(txtHoTen.getText().trim());

            String strNgaySinh = txtNgaySinh.getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                Date parsed = sdf.parse(strNgaySinh);
                nv.setNgaySinh(new java.sql.Date(parsed.getTime()));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Ngày sinh sai định dạng (yyyy-MM-dd)");
                return null;
            }

            nv.setDiaChi(txtDiaChi.getText().trim());
            nv.setSdt(txtTaiKhoan.getText().trim());
            nv.setPassword(txtMatKhau.getText());
            return nv;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lấy thông tin nhân viên.");
            e.printStackTrace();
            return null;
        }
    }

    private void setFormNhanVien(int index) {
        if (index != -1) {
            txtID.setText(tblNV.getValueAt(index, 0).toString());
            txtHoTen.setText(tblNV.getValueAt(index, 1).toString());
            txtNgaySinh.setText(tblNV.getValueAt(index, 2).toString());
            txtDiaChi.setText(tblNV.getValueAt(index, 3).toString());
            txtTaiKhoan.setText(tblNV.getValueAt(index, 4).toString());
            txtMatKhau.setText(tblNV.getValueAt(index, 5).toString());
        }
    }

    private void taoMoiNhanVien() {
        if (!validateFormNhanVien()) {
            return;
        }

        NhanVien nv = getFormNhanVien();
        if (nv == null) {
            return;
        }

        if (nhanVienDao.findBySdt(nv.getSdt()) != null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại/Tài khoản đã tồn tại!");
            return;
        }

        int result = nhanVienDao.create(nv);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            fillTableNhanVien();
            clearFormNhanVien();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
    }

    private void suaNhanVien() {
        if (!validateFormNhanVien()) {
            return;
        }

        NhanVien nv = getFormNhanVien();
        if (nv == null) {
            return;
        }

        int result = nhanVienDao.update(nv);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            fillTableNhanVien();
            clearFormNhanVien();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }

    private boolean validateFormNhanVien() {
        if (txtHoTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên nhân viên!");
            txtHoTen.requestFocus();
            return false;
        }

        if (txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh!");
            txtNgaySinh.requestFocus();
            return false;
        }

        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ!");
            txtDiaChi.requestFocus();
            return false;
        }

        if (txtTaiKhoan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại/tài khoản!");
            txtTaiKhoan.requestFocus();
            return false;
        }

        String password = txtMatKhau.getText().trim();
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu!");
            txtMatKhau.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
            txtMatKhau.requestFocus();
            return false;
        }
        String sdt = txtTaiKhoan.getText();
        if (sdt == null || sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        sdt = sdt.trim();
        if (!sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải gồm đúng 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!sdt.startsWith("0")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int numberSDT = Integer.parseInt(sdt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập định dạng số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String strNgaySinh = txtNgaySinh.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(strNgaySinh);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh sai định dạng (yyyy-MM-dd)!");
            txtNgaySinh.requestFocus();
            return false;
        }

        return true;
    }

    private void clearFormNhanVien() {
        txtID.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        index = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnAddNV = new javax.swing.JButton();
        btnEditNV = new javax.swing.JButton();
        btnDeleteNV = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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

        txtID.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Họ Tên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày Sinh");

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
        btnEditNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNVActionPerformed(evt);
            }
        });

        btnDeleteNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDeleteNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDeleteNV.setText("Xóa");
        btnDeleteNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNVActionPerformed(evt);
            }
        });

        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh-arrow.png"))); // NOI18N
        btnKhoiPhuc.setText("Khôi Phục");

        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
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
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        tbl_NhanVien.setViewportView(tblNV);

        jScrollPane1.setViewportView(tbl_NhanVien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addContainerGap(397, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(btnKhoiPhuc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );

        jTabbedPane1.addTab("Nhân Viên", jPanel1);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 48)); // NOI18N
        jLabel2.setText("                     Quản Lí Nhân Viên");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 951, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1))
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTabbedPane1)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked

    }//GEN-LAST:event_tbl_NhanVienMouseClicked

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        index = tblNV.getSelectedRow();
        setFormNhanVien(index);
    }//GEN-LAST:event_tblNVMouseClicked

    private void btnEditNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNVActionPerformed
        suaNhanVien();
    }//GEN-LAST:event_btnEditNVActionPerformed

    private void btnAddNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNVActionPerformed
        taoMoiNhanVien();
    }//GEN-LAST:event_btnAddNVActionPerformed

    private void btnDeleteNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNVActionPerformed
        // TODO add your handling code here :
    }//GEN-LAST:event_btnDeleteNVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNV;
    private javax.swing.JButton btnDeleteNV;
    private javax.swing.JButton btnEditNV;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblNV;
    private javax.swing.JScrollPane tbl_NhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
