/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Dao.LoaiDao;
import Dao.SanPhamDao;
import Model.Loai;
import Model.SanPham;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tuanb
 */
public class SanPhamJDialog extends javax.swing.JDialog {

    private DefaultTableModel model;
    int index = -1;
    SanPhamDao sanPhamDao;
    LoaiDao loaiDao;

    public SanPhamJDialog(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        setTitle("");
        setLocationRelativeTo(null);
        sanPhamDao = new SanPhamDao();
        loaiDao = new LoaiDao();
        fillTableGiay();
        fillCbbLoai();
    }

    void fillTableGiay() {
        model = (DefaultTableModel) tblGiay.getModel();
        model.setRowCount(0);
        try {
            for (SanPham sp : sanPhamDao.findAllSanPham()) {
                Loai loai = loaiDao.findById(sp.getIdLoai());
                String tenLoai = loai != null ? loai.getTenLoai() : "Không rõ";
                model.addRow(new Object[]{
                    sp.getIdSP(),
                    sp.getMaGiay(),
                    sp.getTenGiay(),
                    tenLoai,
                    sp.getTrangThai() == 1 ? "Tồn Tại" : "Không Tồn Tại"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillCbbLoai() {
        DefaultComboBoxModel<Loai> cbbLoaiModel = (DefaultComboBoxModel<Loai>) cbbLoai.getModel();
        cbbLoaiModel.removeAllElements();
        for (Loai loai : loaiDao.findAllLoai()) {
            if (loai.getTrangThai() == 1) {
                cbbLoaiModel.addElement(loai);
            }
        }
    }

    private void submitFormSanPham_TaoMoi() {
        String maGiay = txtMaGiay.getText().trim();
        String tenGiay = txtTenGiay.getText().trim();
        Loai loai = (Loai) cbbLoai.getSelectedItem();

        if (maGiay.isEmpty() || tenGiay.isEmpty() || loai == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm.");
            return;
        }

        if (sanPhamDao.findByMaGiay(maGiay) != null) {
            JOptionPane.showMessageDialog(this, "Mã giày đã tồn tại. Vui lòng chọn mã khác.");
            return;
        }

        SanPham sp = new SanPham();
        sp.setMaGiay(maGiay);
        sp.setTenGiay(tenGiay);
        sp.setIdLoai(loai.getIdLoai());
        sp.setTrangThai(1);

        int result = sanPhamDao.create(sp);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            fillTableGiay();
            clearFormSanPham();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!");
        }
    }

    private void updateSanPham() {
        String idStr = txt_IDSanPham.getText().trim();
        String maGiay = txtMaGiay.getText().trim();
        String tenGiay = txtTenGiay.getText().trim();
        Loai loai = (Loai) cbbLoai.getSelectedItem();

        if (idStr.isEmpty() || maGiay.isEmpty() || tenGiay.isEmpty() || loai == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm.");
            return;
        }

        int idSP;
        try {
            idSP = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID sản phẩm không hợp lệ.");
            return;
        }

        SanPham existed = sanPhamDao.findByMaGiay(maGiay);
        if (existed != null && existed.getIdSP() != idSP) {
            JOptionPane.showMessageDialog(this, "Mã giày đã tồn tại ở sản phẩm khác.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật sản phẩm này?",
                "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        SanPham sp = new SanPham();
        sp.setIdSP(idSP);
        sp.setMaGiay(maGiay);
        sp.setTenGiay(tenGiay);
        sp.setIdLoai(loai.getIdLoai());
        sp.setTrangThai(1);

        int result = sanPhamDao.update(sp);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
            fillTableGiay();
            clearFormSanPham();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!");
        }
    }

    private void clearFormSanPham() {
        txt_IDSanPham.setText("");
        txtMaGiay.setText("");
        txtTenGiay.setText("");
        cbbLoai.setSelectedIndex(0);
        index = -1;
    }

    private void setFormSanPham(int row) {
        if (row != -1) {
            txt_IDSanPham.setText(tblGiay.getValueAt(row, 0).toString());
            txtMaGiay.setText(tblGiay.getValueAt(row, 1).toString());
            txtTenGiay.setText(tblGiay.getValueAt(row, 2).toString());
            String tenLoai = tblGiay.getValueAt(row, 3).toString();

            DefaultComboBoxModel<Loai> model = (DefaultComboBoxModel<Loai>) cbbLoai.getModel();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).getTenLoai().equals(tenLoai)) {
                    cbbLoai.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_IDSanPham = new javax.swing.JTextField();
        txtMaGiay = new javax.swing.JTextField();
        txtTenGiay = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGiay = new javax.swing.JTable();
        cbbLoai = new javax.swing.JComboBox<>();
        btnAddLoai = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txt_IDSanPham.setEditable(false);
        txt_IDSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IDSanPhamActionPerformed(evt);
            }
        });

        jLabel1.setText("ID SẢN PHẨM");

        jLabel2.setText("MÃ GIÀY");

        jLabel3.setText("TÊN GIÀY");

        jLabel4.setText("LOẠI GIÀY ");

        tblGiay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID ", "MÃ GIÀY", "TÊN GIÀY", "LOẠI GIÀY", "TRẠNG THÁI"
            }
        ));
        tblGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGiay);

        btnAddLoai.setForeground(new java.awt.Color(0, 153, 204));
        btnAddLoai.setText("Chọn");
        btnAddLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLoaiActionPerformed(evt);
            }
        });

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Xóa");

        jButton5.setText("Khôi Phục");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenGiay)
                            .addComponent(txtMaGiay)
                            .addComponent(txt_IDSanPham)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_IDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddLoai))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_IDSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IDSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IDSanPhamActionPerformed

    private void btnAddLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLoaiActionPerformed
        // TODO add your handling code here:
        JDialog loai;
        try {
            loai = new LoaiDialog(null, true);
            loai.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnAddLoaiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        submitFormSanPham_TaoMoi();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void tblGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiayMouseClicked
        index = tblGiay.getSelectedRow();
        setFormSanPham(index);
    }//GEN-LAST:event_tblGiayMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        updateSanPham();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLoai;
    private javax.swing.JComboBox<Loai> cbbLoai;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGiay;
    private javax.swing.JTextField txtMaGiay;
    private javax.swing.JTextField txtTenGiay;
    private javax.swing.JTextField txt_IDSanPham;
    // End of variables declaration//GEN-END:variables
}
