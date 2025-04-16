/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Dao.HoaDonDao;
import Dao.NhanVienDao;
import Model.ChiTietHoaDon;
import Model.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import viewModel.HoaDonView;

/**
 *
 * @author tuanb
 */
public class HoaDonPanel extends javax.swing.JPanel {

    private DefaultTableModel model;
    HoaDonDao hoaDonDao;
    NhanVienDao nhanVienDao;

    public HoaDonPanel(java.awt.Frame parent, boolean modal) throws Exception {
        initComponents();
        hoaDonDao = new HoaDonDao();
        nhanVienDao = new NhanVienDao();
        fillTableHoaDon();
        fillCbbNhanVien();
        fillCbbTrangThai();
    }

    private void fillCbbNhanVien() {
        DefaultComboBoxModel<String> cbbModel = (DefaultComboBoxModel<String>) cbb_fillNhanVien.getModel();
        cbbModel.removeAllElements();
        cbbModel.addElement("Tất cả");
        for (NhanVien nv : nhanVienDao.findAllNhanVien()) {
            if (nv.getTrangThai() == 1) {
                cbbModel.addElement(nv.getHoTenNV());
            }
        }
    }

    private void fillCbbTrangThai() {
        DefaultComboBoxModel<String> cbbTrangThaiModel = (DefaultComboBoxModel<String>) cbb_fillTrangThai.getModel();
        cbbTrangThaiModel.addElement("Tất cả");
        cbbTrangThaiModel.addElement("Chưa Thanh Toán");
        cbbTrangThaiModel.addElement("Đã Thanh Toán");
        cbbTrangThaiModel.addElement("Đang chờ");
        cbbTrangThaiModel.addElement("Đã Hủy");
    }

    private void fillTableHoaDon() {
        DefaultTableModel modelHD = (DefaultTableModel) tblQLHD.getModel();
        modelHD.setRowCount(0);
        try {
            String selectedNhanVien = (String) cbb_fillNhanVien.getSelectedItem();
            String selectedTrangThai = (String) cbb_fillTrangThai.getSelectedItem();
            for (HoaDonView hoaDon : hoaDonDao.findAllHoaDon()) {
                boolean match = true;

                if (selectedNhanVien != null && !selectedNhanVien.equals("Tất cả")
                        && !hoaDon.getHoTenNV().equalsIgnoreCase(selectedNhanVien)) {
                    match = false;
                }

                if (selectedTrangThai != null && !selectedTrangThai.equals("Tất cả")) {
                    int expectedTrangThai = switch (selectedTrangThai) {
                        case "Chưa Thanh Toán" ->
                            0;
                        case "Đã Thanh Toán" ->
                            1;
                        case "Đã Hủy" ->
                            2;
                        case "Đang chờ" ->
                            3;
                        default ->
                            -1;
                    };
                    if (hoaDon.getTrangThai() != expectedTrangThai) {
                        match = false;
                    }
                }

                if (!match) {
                    continue;
                }

                String trangThaiStr = switch (hoaDon.getTrangThai()) {
                    case 0 ->
                        "Chưa Thanh Toán";
                    case 1 ->
                        "Đã Thanh Toán";
                    case 2 ->
                        "Đã Hủy";
                    case 3 ->
                        "Đang chờ";
                    default ->
                        "Không xác định";
                };

                modelHD.addRow(new Object[]{
                    hoaDon.getIdHd(),
                    hoaDon.getHoTenNV(),
                    hoaDon.getHoTenKH(),
                    hoaDon.getSdt(),
                    hoaDon.getDiaChi(),
                    hoaDon.getThanhTien(),
                    hoaDon.getNgayTao(),
                    trangThaiStr
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popup_ChiTietHoaDon() {
        int selectedRow = tblQLHD.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idHD = (int) tblQLHD.getValueAt(selectedRow, 0);
        String trangThaiStr = tblQLHD.getValueAt(selectedRow, 7).toString();
        int trangThai = switch (trangThaiStr) {
            case "Chưa Thanh Toán" ->
                0;
            case "Đã Thanh Toán" ->
                1;
            case "Đã Hủy" ->
                2;
            case "Đang chờ" ->
                3;
            default ->
                -1;
        };

        HoaDonView hoaDon = new HoaDonView(
                idHD,
                tblQLHD.getValueAt(selectedRow, 1).toString(),
                tblQLHD.getValueAt(selectedRow, 2).toString(),
                tblQLHD.getValueAt(selectedRow, 3).toString(),
                tblQLHD.getValueAt(selectedRow, 4).toString(),
                (java.sql.Date) tblQLHD.getValueAt(selectedRow, 6),
                Double.parseDouble(tblQLHD.getValueAt(selectedRow, 5).toString()),
                trangThai
        );

        List<ChiTietHoaDon> listChiTiet = hoaDonDao.findCTHDByIDHD(idHD);
        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Chi tiết hóa đơn", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel pnlHoaDon = new JPanel(new GridBagLayout());
        pnlHoaDon.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        String[][] invoiceData = {
            {"Mã hóa đơn:", String.valueOf(hoaDon.getIdHd())},
            {"Nhân viên:", hoaDon.getHoTenNV()},
            {"Khách hàng:", hoaDon.getHoTenKH()},
            {"SĐT:", hoaDon.getSdt()},
            {"Địa chỉ:", hoaDon.getDiaChi()},
            {"Ngày tạo:", hoaDon.getNgayTao() != null ? hoaDon.getNgayTao().toString() : ""},
            {"Thành tiền:", String.valueOf(hoaDon.getThanhTien())},
            {"Trạng thái:", switch (hoaDon.getTrangThai()) {
                case 0 ->
                    "Chưa Thanh Toán";
                case 1 ->
                    "Đã Thanh Toán";
                case 2 ->
                    "Đã Hủy";
                case 3 ->
                    "Đang chờ";
                default ->
                    "Không xác định";
            }}
        };

        for (int i = 0; i < invoiceData.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            pnlHoaDon.add(new JLabel(invoiceData[i][0]), gbc);
            gbc.gridx = 1;
            JLabel valueLabel = new JLabel(invoiceData[i][1]);
            valueLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            pnlHoaDon.add(valueLabel, gbc);
        }
        mainPanel.add(pnlHoaDon, BorderLayout.NORTH);

        DefaultTableModel modelCTHD = new DefaultTableModel(new String[]{"ID_CTSP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"}, 0);
        for (ChiTietHoaDon ct : listChiTiet) {
            modelCTHD.addRow(new Object[]{
                ct.getIdCTSP(),
                ct.getTenSP(),
                ct.getSoLuong(),
                ct.getDonGia(),
                ct.getThanhTien()
            });
        }

        JTable tblCTHD = new JTable(modelCTHD);
        tblCTHD.setFillsViewportHeight(true);
        tblCTHD.setRowHeight(24);
        tblCTHD.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblCTHD.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(tblCTHD);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        JButton btnHuyDon = new JButton("Hủy Đơn");
        btnHuyDon.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnHuyDon.setBackground(Color.RED);
        btnHuyDon.setForeground(Color.WHITE);
        btnHuyDon.setVisible(hoaDon.getTrangThai() != 2);

        btnHuyDon.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(dialog, "Xác nhận hủy đơn hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean isCanceled = hoaDonDao.huyHoaDon(hoaDon.getIdHd());
                if (isCanceled) {
                    hoaDonDao.rollbackSanPhamTrongHoaDon(hoaDon.getIdHd());
                    JOptionPane.showMessageDialog(dialog, "Hủy đơn thành công và hoàn kho!");
                    dialog.dispose();
                    fillTableHoaDon();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Hủy đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnDoiTra = new JButton("Yêu cầu Đổi / Trả");
        btnDoiTra.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnDoiTra.setBackground(new Color(255, 204, 0));
        btnDoiTra.setForeground(Color.BLACK);
        btnDoiTra.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog,
                    "Yêu cầu đổi / trả đã được ghi nhận.\nVui lòng chờ xử lý từ nhân viên hỗ trợ!",
                    "Đổi / Trả", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnClose = new JButton("Đóng");
        btnClose.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnClose.addActionListener(e -> dialog.dispose());

        pnlButton.add(btnHuyDon);
        pnlButton.add(btnDoiTra);
        pnlButton.add(btnClose);
        mainPanel.add(pnlButton, BorderLayout.SOUTH);

        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.setPreferredSize(new Dimension(800, 600));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLHD = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimHoaDon = new javax.swing.JTextField();
        btnLocHoaDon = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbb_fillTrangThai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cbb_fillNhanVien = new javax.swing.JComboBox<>();

        tblQLHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Hóa Đơn", "ID Nhân Viên", "ID Khách Hàng", "SDT", "Địa Chỉ", "Tổng Tiền ", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblQLHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLHD);

        jLabel1.setText("Tìm Kiếm :");

        txtTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimHoaDonActionPerformed(evt);
            }
        });

        btnLocHoaDon.setText("Seach");

        jLabel2.setText("Nhân viên");

        cbb_fillTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_fillTrangThaiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setText("QUẢN LÍ HÓA ĐƠN");

        jLabel4.setText("Trạng Thái :");

        cbb_fillNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_fillNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnLocHoaDon)
                            .addGap(85, 85, 85)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbb_fillTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbb_fillNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(407, 407, 407))
                        .addComponent(jSeparator1))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addGap(25, 25, 25)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cbb_fillNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLocHoaDon)
                        .addComponent(cbb_fillTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_fillTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_fillTrangThaiActionPerformed
        fillTableHoaDon();
    }//GEN-LAST:event_cbb_fillTrangThaiActionPerformed

    private void tblQLHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLHDMouseClicked
        popup_ChiTietHoaDon();
    }//GEN-LAST:event_tblQLHDMouseClicked

    private void txtTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimHoaDonActionPerformed

    private void cbb_fillNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_fillNhanVienActionPerformed
        fillTableHoaDon();
    }//GEN-LAST:event_cbb_fillNhanVienActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocHoaDon;
    private javax.swing.JComboBox<String> cbb_fillNhanVien;
    private javax.swing.JComboBox<String> cbb_fillTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblQLHD;
    private javax.swing.JTextField txtTimHoaDon;
    // End of variables declaration//GEN-END:variables
}
