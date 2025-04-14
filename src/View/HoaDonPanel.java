/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Dao.HoaDonDao;
import Model.ChiTietHoaDon;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
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
    int index = -1;

    public HoaDonPanel(java.awt.Frame parent, boolean modal) throws Exception {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        initComponents();
        hoaDonDao = new HoaDonDao();
        fillTableHoaDon();

    }

    void fillTableHoaDon() {
        DefaultTableModel modelHD = new DefaultTableModel();
        modelHD = (DefaultTableModel) tblQLHD.getModel();
        modelHD.setRowCount(0);
        try {
            List<HoaDonView> hoaDons = hoaDonDao.findAll();
            if (hoaDons.isEmpty()) {

            }
            for (HoaDonView hoaDon : hoaDons) {
                Object[] row = {
                    hoaDon.getIdHd(),
                    hoaDon.getHoTenNV(),
                    hoaDon.getHoTenKH(),
                    hoaDon.getSdt(),
                    hoaDon.getDiaChi(),
                    hoaDon.getThanhTien(),
                    hoaDon.getNgayTao(),
                    hoaDon.getTrangThai() == 1 ? "Chưa Thanh Toán" : "Đã Thanh Toán"
                };
                modelHD.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Cửa sổ nổi chi tiết hóa đơn
    void popup_ChiTietHoaDon() {
        int selectedRow = tblQLHD.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idHD = (int) tblQLHD.getValueAt(selectedRow, 0);
        List<ChiTietHoaDon> listChiTiet = hoaDonDao.findCTHDByIDHD(idHD);

        HoaDonView hoaDon = new HoaDonView(
                idHD,
                tblQLHD.getValueAt(selectedRow, 1).toString(),
                tblQLHD.getValueAt(selectedRow, 2).toString(),
                tblQLHD.getValueAt(selectedRow, 3).toString(),
                tblQLHD.getValueAt(selectedRow, 4).toString(),
                (java.sql.Date) tblQLHD.getValueAt(selectedRow, 6),
                Double.valueOf(tblQLHD.getValueAt(selectedRow, 5).toString()),
                tblQLHD.getValueAt(selectedRow, 7).toString().equals("Chưa Thanh Toán") ? 1 : 0
        );

        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Chi tiết hóa đơn", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ==== Thông tin hóa đơn ====
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
            {"Trạng thái:", hoaDon.getTrangThai() == 1 ? "Chưa Thanh Toán" : "Đã Thanh Toán"}
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

        // ==== Bảng chi tiết hóa đơn ====
        DefaultTableModel modelCTHD = new DefaultTableModel();
        modelCTHD.addColumn("ID_CTSP");
        modelCTHD.addColumn("Tên sản phẩm");
        modelCTHD.addColumn("Số lượng");
        modelCTHD.addColumn("Đơn giá");
        modelCTHD.addColumn("Thành tiền");
        modelCTHD.addColumn("Trạng thái");

        for (ChiTietHoaDon ct : listChiTiet) {
            modelCTHD.addRow(new Object[]{
                ct.getIdCTSP(),
                ct.getTenSP(),
                ct.getSoLuong(),
                ct.getDonGia(),
                ct.getThanhTien(),
                ct.getTrangThai() == 0 ? "Chưa Thanh Toán" : "Đã Thanh Toán"
            });
        }

        JTable tblCTHD = new JTable(modelCTHD);
        tblCTHD.setFillsViewportHeight(true);
        tblCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCTHD.setRowHeight(24);
        tblCTHD.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblCTHD.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(tblCTHD);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ==== Nút đóng ====
        JPanel pnlButton = new JPanel();
        JButton btnClose = new JButton("Đóng");
        btnClose.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnClose.addActionListener(e -> dialog.dispose());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLHD = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbbTTHD = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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

        jButton1.setText("Seach");

        jLabel2.setText("Trạng Thái :");

        cbbTTHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã Thanh Toán", "Chưa Thanh Toán", "Chờ" }));
        cbbTTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTTHDActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setText("QUẢN LÍ HÓA ĐƠN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbbTTHD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addGap(25, 25, 25)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(cbbTTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbTTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTTHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTTHDActionPerformed

    private void tblQLHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLHDMouseClicked
        popup_ChiTietHoaDon();
    }//GEN-LAST:event_tblQLHDMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbTTHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblQLHD;
    // End of variables declaration//GEN-END:variables
}
