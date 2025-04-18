/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Dao.HoaDonDao;
import Dao.NhanVienDao;
import Model.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import viewModel.ThongKe;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author tuanb
 */
public class ThongKePanel extends javax.swing.JPanel {

    /**
     * Creates new form ThongKePanel
     */
    public ThongKePanel(java.awt.Frame parent, boolean modal) throws Exception {
        initComponents();
        buildThongKeUI();
    }
    private javax.swing.JComboBox<String> cbxThang;
    private javax.swing.JLabel lblSoHoaDonValue, lblTongDoanhThuValue;
    private JLabel lblTuNgayValue;
    private JLabel lblDenNgayValue;
    private javax.swing.JComboBox<String> cbxNhanVien;
    private List<NhanVien> danhSachNhanVien;

    private void buildThongKeUI() throws Exception {
        // ComboBox chọn số tháng
        cbxThang = new JComboBox<>(new String[]{
            "3 tháng gần nhất", "6 tháng gần nhất", "9 tháng gần nhất", "12 tháng gần nhất"
        });

        // Nút Thống kê
        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setBackground(new Color(33, 150, 243));
        btnThongKe.setForeground(Color.WHITE);
        btnThongKe.setFocusPainted(false);
        btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Các label kết quả
        JLabel lblSoHoaDon = new JLabel("Số lượng hóa đơn:");
        lblSoHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        lblSoHoaDonValue = new JLabel("0");
        lblSoHoaDonValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblSoHoaDonValue.setForeground(new Color(25, 118, 210));

        JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu:");
        lblTongDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        lblTongDoanhThuValue = new JLabel("0 VNĐ");
        lblTongDoanhThuValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTongDoanhThuValue.setForeground(new Color(46, 125, 50));

        // Các label ngày bắt đầu & kết thúc
        lblTuNgayValue = new JLabel("-");
        lblTuNgayValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblDenNgayValue = new JLabel("-");
        lblDenNgayValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // tạo cbb chứa nhân viên cần lọc
        cbxNhanVien = new JComboBox<>();
        danhSachNhanVien = new ArrayList<>();
        NhanVienDao nvDao = new NhanVienDao();
        danhSachNhanVien = nvDao.findAllNhanVien();

        // Tạo panel chứa nội dung thống kê
        JPanel panelNoiDung = new JPanel();
        panelNoiDung.setBackground(Color.WHITE);
        panelNoiDung.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panelNoiDung.setLayout(new GridLayout(7, 2, 10, 10)); // Tăng lên 7 dòng
        panelNoiDung.setPreferredSize(new Dimension(420, 320));

        // Thêm các thành phần vào panel
        panelNoiDung.add(new JLabel("📅 Chọn khoảng thời gian:"));
        panelNoiDung.add(cbxThang);
        panelNoiDung.add(new JLabel()); // spacer
        panelNoiDung.add(btnThongKe);
        panelNoiDung.add(lblSoHoaDon);
        panelNoiDung.add(lblSoHoaDonValue);
        panelNoiDung.add(lblTongDoanhThu);
        panelNoiDung.add(lblTongDoanhThuValue);
        
        panelNoiDung.add(new JLabel("📅 Từ ngày:"));
        panelNoiDung.add(new JLabel("📅 Đến ngày:"));
        panelNoiDung.add(lblTuNgayValue);
        panelNoiDung.add(lblDenNgayValue);

        panelNoiDung.add(new JLabel("👤 Chọn nhân viên:"));
        panelNoiDung.add(cbxNhanVien);

        cbxNhanVien.addItem("Tất cả nhân viên");
        for (NhanVien nv : danhSachNhanVien) {
            cbxNhanVien.addItem(nv.getHoTenNV());
        }

        // Panel biểu đồ bên phải
        ChartPanel chartPanel = createChartPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Container trái phải
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        container.setBackground(new Color(240, 240, 240));
        container.add(panelNoiDung);
        container.add(chartPanel);
        jPanel3.setLayout(new BorderLayout());
        jPanel3.removeAll();
        jPanel3.add(container, BorderLayout.CENTER);
        jPanel3.revalidate();
        jPanel3.repaint();
        btnThongKe.addActionListener(e -> {
            int soThang = switch (cbxThang.getSelectedIndex()) {
                case 0 ->
                    3;
                case 1 ->
                    6;
                case 2 ->
                    9;
                case 3 ->
                    12;
                default ->
                    3;
            };

            try {
                LocalDate endDate = LocalDate.now();
                LocalDate startDate = endDate.minusMonths(soThang);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                lblTuNgayValue.setText(startDate.format(formatter));
                lblDenNgayValue.setText(endDate.format(formatter));

                String tenNhanVien = cbxNhanVien.getSelectedItem().toString();
                Integer idNhanVien = null;

                if (!tenNhanVien.equals("Tất cả nhân viên")) {
                    for (NhanVien nv : danhSachNhanVien) {
                        if (nv.getHoTenNV().equals(tenNhanVien)) {
                            idNhanVien = nv.getIdNV();
                            break;
                        }
                    }
                }

                HoaDonDao dao = new HoaDonDao();
                ThongKe thongKe;

                if (idNhanVien == null) {
                    thongKe = dao.thongKeTheoKhoangNgay(startDate, endDate); // Hàm có sẵn
                } else {
                    thongKe = dao.thongKeTheoNhanVienVaKhoangNgay(idNhanVien, startDate, endDate);
                }

                lblSoHoaDonValue.setText(String.valueOf(thongKe.getSoHoaDon()));
                lblTongDoanhThuValue.setText(String.format("%,.0f VNĐ", thongKe.getTongDoanhThu()));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi thống kê: " + ex.getMessage());
            }
        });

        // Action Thống kê
        btnThongKe.addActionListener(e -> {
            int soThang = switch (cbxThang.getSelectedIndex()) {
                case 0 ->
                    3;
                case 1 ->
                    6;
                case 2 ->
                    9;
                case 3 ->
                    12;
                default ->
                    3;
            };

            try {
                // Ngày bắt đầu và kết thúc
                LocalDate endDate = LocalDate.now();
                LocalDate startDate = endDate.minusMonths(soThang);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                lblTuNgayValue.setText(startDate.format(formatter));
                lblDenNgayValue.setText(endDate.format(formatter));

                // Gọi DAO (nếu bạn muốn lọc đúng theo khoảng này thì cần truyền startDate và endDate)
                HoaDonDao dao = new HoaDonDao();
                ThongKe thongKe = dao.thongKeTheoKhoangNgay(startDate, endDate);

                lblSoHoaDonValue.setText(String.valueOf(thongKe.getSoHoaDon()));
                lblTongDoanhThuValue.setText(String.format("%,.0f VNĐ", thongKe.getTongDoanhThu()));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi thống kê: " + ex.getMessage());
            }
        });
    }

    private ChartPanel createChartPanel() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        HoaDonDao dao = new HoaDonDao();
        Map<String, Double> thongKeThang = dao.getDoanhThuTheoTungThangTrongNam();

        for (Map.Entry<String, Double> entry : thongKeThang.entrySet()) {
            dataset.addValue(entry.getValue(), "Doanh thu", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "", // bỏ tiêu đề
                "Năm",
                "Doanh thu (VNĐ)",
                dataset
        );

        // Tùy chỉnh giao diện đẹp hơn
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 18));
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(33, 150, 243));
        chart.getCategoryPlot().setOutlineVisible(false);
        chart.getCategoryPlot().setBackgroundPaint(new Color(250, 250, 250));
        chart.getCategoryPlot().setRangeGridlinePaint(new Color(200, 200, 200));

        return new ChartPanel(chart);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1043, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
