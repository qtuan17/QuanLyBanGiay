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
import java.util.Optional;
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
        setLocationRelativeTo(null);  // Căn giữa màn hình
        sanPhamDao = new SanPhamDao();
        loaiDao = new LoaiDao();
        fillTableGiay();
        fillCbbLoai();
    }

    void fillTableGiay() {
        model = (DefaultTableModel) tblGiay.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> sanPhams = sanPhamDao.findAll();
            if (sanPhams.isEmpty()) {
                System.out.println("List Giay trống.");
            }
            for (SanPham sanPham : sanPhams) {
                // Lấy tên loại tương ứng từ LoaiDao
                Loai loai = loaiDao.findById(sanPham.getIdLoai());
                String tenLoai = (loai != null) ? loai.getTenLoai() : "Không rõ";

                Object[] row = {
                    sanPham.getIdSP(),
                    sanPham.getMaGiay(),
                    sanPham.getTenGiay(),
                    //                    sanPham.getIdLoai(),
                    tenLoai, // ✅ Hiển thị tên loại thay vì ID
                    sanPham.getTrangThai() == 1 ? "Tồn Tại" : "Không Tồn Tại"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillCbbLoai() {
        DefaultComboBoxModel cbbLoai = (DefaultComboBoxModel) this.cbbLoai.getModel();
        cbbLoai.removeAllElements();
        List<Loai> loais = loaiDao.findAll();
        for (Loai loai : loais) {
            if (loai.getTrangThai() == 1) {
                cbbLoai.addElement(loai);
            }
        }
    }

    private void taoMoiSanPham() {
        String maGiay = txtMaGiay.getText().trim();
        String tenGiay = txtTenGiay.getText().trim();
        Loai selectedLoai = (Loai) cbbLoai.getSelectedItem();

        // Kiểm tra dữ liệu rỗng
        if (maGiay.isEmpty() || tenGiay.isEmpty() || selectedLoai == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm.");
            return;
        }

        // Kiểm tra trùng mã giày nếu cần
        if (sanPhamDao.findByMaGiay(maGiay) != null) {
            JOptionPane.showMessageDialog(this, "Mã giày đã tồn tại.");
            return;
        }

        // Tạo đối tượng sản phẩm
        SanPham sanPham = new SanPham();
        sanPham.setMaGiay(maGiay);
        sanPham.setTenGiay(tenGiay);
        sanPham.setIdLoai(selectedLoai.getIdLoai());
        sanPham.setTrangThai(1); // mặc định Tồn tại

        // Thêm vào DB
        int result = sanPhamDao.create(sanPham);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            fillTableGiay();
            clearFormSanPham();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!");
        }
    }

    private void clearFormSanPham() {
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
            // Lấy tên loại được hiển thị trên bảng (cột 3 chứa tên loại)
            String tenLoaiTable = tblGiay.getValueAt(row, 3).toString();

            // Lấy model của combobox chứa các đối tượng Loai
            DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbLoai.getModel();
            // Duyệt qua các phần tử trong combobox
            for (int i = 0; i < cbbModel.getSize(); i++) {
                Loai loai = (Loai) cbbModel.getElementAt(i);
                // So sánh tên loại của đối tượng với tên lấy từ bảng
                if (loai.getTenLoai().equals(tenLoaiTable)) {
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
        taoMoiSanPham();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void tblGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiayMouseClicked
        index = tblGiay.getSelectedRow();
        setFormSanPham(index);
    }//GEN-LAST:event_tblGiayMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLoai;
    private javax.swing.JComboBox<String> cbbLoai;
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
