/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Dao.ChiTietHoaDonDao;
import Dao.ChiTietSanPhamDao;
import Dao.HoaDonDao;
import Dao.KhachHangDao;
import Dao.LoaiDao;
import Dao.MauDao;
import Dao.SanPhamDao;
import Dao.SizeDao;
import Model.ChiTietHoaDon;
import Model.ChiTietSanPham;
import Model.HoaDon;
import Model.KhachHang;
import Model.Mau;
import Model.NhanVien;
import Model.SanPham;
import Model.Size;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import util.SessionLogin;
import viewModel.ChiTietSanPhamView;
import java.text.Normalizer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import javax.swing.JComboBox;

/**
 *
 * @author tuanb
 */
public class SanPhamPanel extends javax.swing.JPanel {

    private final ChiTietSanPhamDao chiTietSanPhamDao = new ChiTietSanPhamDao();
    private final ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();
    private final MauDao mauDao = new MauDao();
    private final LoaiDao loaiDao = new LoaiDao();
    private final SizeDao sizeDao = new SizeDao();
    private final HoaDonDao hoaDonDao = new HoaDonDao();
    private final SanPhamDao sanPhamDao = new SanPhamDao();
    private final KhachHangDao khachHangDao = new KhachHangDao();

    private final List<ChiTietSanPhamView> gioHang = new ArrayList<>();
    private int idKH = -1;

    public SanPhamPanel(java.awt.Frame parent, boolean modal) throws Exception {
        initComponents();
        initializeData();
    }

    private void initializeData() {
        fillTaleChiTietSP();
        fillTaleHoaDon();
        fillAllCombo();
    }


    private <E> void fillCombo(JComboBox<E> combo, List<E> items, Predicate<E> filter) {
        DefaultComboBoxModel<E> model = (DefaultComboBoxModel<E>) combo.getModel();
        model.removeAllElements();
        items.stream()
                .filter(filter)
                .forEach(model::addElement);
    }


    public void fillAllCombo() {
        fillCombo(cbbColor, mauDao.findAllMau(), m -> m.getTrangThai() == 1);
        fillCombo(cbbSize, sizeDao.findAllSize(), s -> s.getTrangThai() == 1);
        fillCombo(cbbSanPham, sanPhamDao.findAllSanPham(), p -> p.getTrangThai() == 1);
    }


    void fillTaleChiTietSP() {
        DefaultTableModel bhModel = (DefaultTableModel) tblSanPham.getModel();
        DefaultTableModel spModel = (DefaultTableModel) tblSP.getModel();
        bhModel.setRowCount(0);
        spModel.setRowCount(0);

        try {
            chiTietSanPhamDao.findAll().forEach(p -> {
                Object[] row = new Object[]{
                    p.getIdCTSP(), p.getMaGiay(), p.getTenGiay(),
                    p.getTenMau(), p.getTenSize(), p.getTenLoai(),
                    p.getSoLuong(), p.getGiaTien(),
                    p.getTrangThai() == 1 ? "Còn Hàng" : "Hết Hàng"
                };
                if (p.getTrangThai() == 1) {
                    bhModel.addRow(row);
                }
                spModel.addRow(row);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void fillTaleHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            hoaDonDao.findAllHoaDon().stream()
                    .filter(h -> h.getTrangThai() == 0 || h.getTrangThai() == 3)
                    .forEach(h -> model.addRow(new Object[]{
                h.getIdHd(), h.getHoTenNV(), h.getHoTenKH(),
                h.getSdt(), h.getDiaChi(), h.getThanhTien(),
                h.getNgayTao(), h.trangThaiToString(h.getTrangThai())
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void taoHoaDonTrong() {
        int choice = JOptionPane.showConfirmDialog(null, "Xác nhận tạo hóa đơn", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String idHD = txtIDHD.getText();
            if (!idHD.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui Lòng Làm Mới Trước Khi Tạo Hóa Đơn", "Thông Báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (this.idKH == -1) {
                KhachHang khachHang = Validator.KhachHangValidator.validatorKhachHang(getKhacHang());
                if (khachHang != null) {
                    this.idKH = khachHangDao.create(khachHang);
                    if (this.idKH == -1) {
                        JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra vui lòng thử lại", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
                        resetFormSauThanhToan();
                        return;
                    }
                }
            }
            HoaDon hoaDon = new HoaDon();
            NhanVien nhanVien = SessionLogin.getNhanVienLogin();
            hoaDon.setIdNV(nhanVien.getIdNV());
            hoaDon.setIdKH(this.idKH);
            int isCreateHoaDon = hoaDonDao.insert(hoaDon);
            if (isCreateHoaDon > 0) {
                JOptionPane.showMessageDialog(null, "Thêm Hoá Đơn Thành Công", "Thông Báo", JOptionPane.UNDEFINED_CONDITION);
                fillTaleHoaDon();
            }
        } else if (choice == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
    }

    private void chonHoaDon() {
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn.");
            return;
        }

        DefaultTableModel hdModel = (DefaultTableModel) tblHoaDon.getModel();
        int idHD = (int) hdModel.getValueAt(row, 0);

        // Lấy các cột 1–7 của hóa đơn
        txtIDHD.setText(String.valueOf(idHD));
        txtTenNhanVien.setText(hdModel.getValueAt(row, 1).toString());
        txtKhachHAng.setText(hdModel.getValueAt(row, 2).toString());
        txtSDTKH.setText(hdModel.getValueAt(row, 3).toString());
        txtDiaChiKH.setText(hdModel.getValueAt(row, 4).toString());
        txtThanhTIen.setText(hdModel.getValueAt(row, 5).toString());
        txtTongTienHang.setText(hdModel.getValueAt(row, 5).toString());
        txtNgayTao.setText(hdModel.getValueAt(row, 6).toString());
        txtTrangThai.setText(hdModel.getValueAt(row, 7).toString());

        // Load luôn danh sách chi tiết từ DB, gán vào gioHang và update table
        try {
            List<ChiTietSanPhamView> details = chiTietHoaDonDao.getChiTietSanPhamTheoHoaDon(idHD);
            gioHang.clear();
            gioHang.addAll(details);
            updateCartTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải chi tiết giỏ hàng: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCartTable() {
        DefaultTableModel cartModel = (DefaultTableModel) tblGioHang.getModel();
        cartModel.setRowCount(0);
        gioHang.forEach(ct -> cartModel.addRow(new Object[]{
            ct.getIdCTSP(),
            ct.getMaGiay(),
            ct.getTenGiay(),
            ct.getTenMau(),
            ct.getTenSize(),
            ct.getTenLoai(),
            ct.getSoLuong(),
            ct.getGiaTien(),
            ct.getSoLuong() * ct.getGiaTien()
        }));
    }

    void fillGioHang() {
        DefaultTableModel modelgiohang = (DefaultTableModel) tblGioHang.getModel();
        modelgiohang.setRowCount(0);
        for (ChiTietSanPhamView chiTietSanPham : gioHang) {
            Object[] row = {
                chiTietSanPham.getIdCTSP(),
                chiTietSanPham.getMaGiay(),
                chiTietSanPham.getTenGiay(),
                chiTietSanPham.getTenMau(),
                chiTietSanPham.getTenSize(),
                chiTietSanPham.getTenLoai(),
                chiTietSanPham.getSoLuong(),
                chiTietSanPham.getGiaTien(),
                chiTietSanPham.getSoLuong() * chiTietSanPham.getGiaTien()
            };
            modelgiohang.addRow(row);
        }
    }

    void addGioHang(ChiTietSanPhamView chiTietSanPhamView) {
        boolean found = false;
        for (int i = 0; i < gioHang.size(); i++) {
            ChiTietSanPhamView x = gioHang.get(i);
            if (x.getIdCTSP() == chiTietSanPhamView.getIdCTSP()) {
                x.setSoLuong(x.getSoLuong() + 1);
                x.setGiaTien(x.getGiaTien() + chiTietSanPhamView.getGiaTien());
                gioHang.set(i, x);
                found = true;
                break;
            }
        }
        if (!found) {
            chiTietSanPhamView.setSoLuong(1);
            gioHang.add(chiTietSanPhamView);
        }
    }

    public void chonSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm.");
            return;
        }
        try {
            int idCTSP = Integer.parseInt(tblSanPham.getValueAt(row, 0).toString());
            String maGiay = tblSanPham.getValueAt(row, 1).toString();
            String tenGiay = tblSanPham.getValueAt(row, 2).toString();
            String tenMau = tblSanPham.getValueAt(row, 3).toString();
            String tenSize = tblSanPham.getValueAt(row, 4).toString();
            String tenLoai = tblSanPham.getValueAt(row, 5).toString();
            int soLuong = Integer.parseInt(tblSanPham.getValueAt(row, 6).toString());
            double giaTien = Double.parseDouble(tblSanPham.getValueAt(row, 7).toString());
            ChiTietSanPhamView chiTietSanPhamView = new ChiTietSanPhamView(
                    idCTSP, maGiay, tenGiay, tenMau, tenSize, tenLoai, soLuong, giaTien, 1
            );
            addGioHang(chiTietSanPhamView);
            txtTongTienHang.setText(String.valueOf(tongTien()));
            fillGioHang();
            tblGioHang.revalidate();
            tblGioHang.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu số lượng hoặc giá tiền không đúng định dạng.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu sản phẩm bị thiếu.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xảy ra: " + e.getMessage());
        }
    }

    private void tienHanhThanhToan() {
        int choice = JOptionPane.showConfirmDialog(null, "Xác nhận Thanh Toán", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            double tongTien = 0;
            if (txtIDHD.getText() == null || txtIDHD.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn để thanh toán", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gioHang.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui Lòng Thêm Sản Phẩm Vào Giỏ Hàng", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idHD = Integer.parseInt(txtIDHD.getText());
            HoaDon hoaDon = new HoaDon();
            hoaDon.setIdHD(idHD);

            // Duyệt qua từng sản phẩm trong giỏ hàng
            for (ChiTietSanPhamView ctspView : gioHang) {
                int newQuantity = ctspView.getSoLuong(); // Số lượng tổng mới (đã cộng dồn trên giỏ hàng)
                // Lấy số lượng đã có trong hóa đơn từ CSDL (nếu có)
                int oldQuantity = chiTietHoaDonDao.getSoLuongExisting(idHD, ctspView.getIdCTSP());
                // Tính số lượng tăng thêm cần trừ vào tồn kho
                int additionalQuantity = newQuantity - oldQuantity;

                // Lấy số lượng tồn kho hiện tại của sản phẩm
                int available = chiTietSanPhamDao.getSoLuongById(ctspView.getIdCTSP());
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                chiTietSanPham.setIdCTSP(ctspView.getIdCTSP());

                if (available - additionalQuantity >= 0) {
                    // Chỉ trừ thêm phần tăng thêm từ giỏ hàng
                    chiTietSanPham.setSoLuong(available - additionalQuantity);
                } else {
                    // Nếu tồn không đủ, trừ hết tồn và điều chỉnh lại số lượng tăng thêm
                    additionalQuantity = available;
                    newQuantity = oldQuantity + additionalQuantity;
                    chiTietSanPham.setSoLuong(0);
                }
                // Cập nhật tồn kho sản phẩm
                chiTietSanPhamDao.updateSoLuong(chiTietSanPham);

                // Tạo đối tượng ChiTietHoaDon với số lượng mới (tích lũy)
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setIdHD(idHD);
                chiTietHoaDon.setIdCTSP(ctspView.getIdCTSP());
                chiTietHoaDon.setSoLuong(newQuantity);
                chiTietHoaDon.setDonGia(ctspView.getGiaTien());
                chiTietHoaDon.setThanhTien(newQuantity * ctspView.getGiaTien());

                // Gọi hàm INSERT/UPDATE (hàm insertOrUpdateChiTietHoaDon xử lý ghi đè nếu đã tồn tại)
                int row = chiTietHoaDonDao.insertOrUpdateChiTietHoaDon(chiTietHoaDon);
                if (row > 0) {
                    tongTien += chiTietHoaDon.getThanhTien();
                }
            }
            hoaDon.setThanhTien(tongTien);
            int row = hoaDonDao.thanhToan(hoaDon);
            if (row > 0) {
                JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công", "Thông Báo", JOptionPane.QUESTION_MESSAGE);
                resetFormSauThanhToan();
            }
        }
    }

    private void setHoaDonCho() {
        int choice = JOptionPane.showConfirmDialog(null, "Xác nhận Lưu Hóa Đơn", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            double tongTien = 0;
            if (txtIDHD.getText() == null || txtIDHD.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn để lưu", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idHD = Integer.parseInt(txtIDHD.getText());
            HoaDon hoaDon = new HoaDon();
            hoaDon.setIdHD(idHD);

            // Duyệt qua từng sản phẩm trong giỏ hàng
            for (ChiTietSanPhamView ctspView : gioHang) {
                int newQuantity = ctspView.getSoLuong(); // Số lượng tổng mới
                int oldQuantity = chiTietHoaDonDao.getSoLuongExisting(idHD, ctspView.getIdCTSP());
                int additionalQuantity = newQuantity - oldQuantity;

                int available = chiTietSanPhamDao.getSoLuongById(ctspView.getIdCTSP());
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                chiTietSanPham.setIdCTSP(ctspView.getIdCTSP());

                if (available - additionalQuantity >= 0) {
                    chiTietSanPham.setSoLuong(available - additionalQuantity);
                } else {
                    additionalQuantity = available;
                    newQuantity = oldQuantity + additionalQuantity;
                    chiTietSanPham.setSoLuong(0);
                }
                chiTietSanPhamDao.updateSoLuong(chiTietSanPham);

                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setIdHD(idHD);
                chiTietHoaDon.setIdCTSP(ctspView.getIdCTSP());
                chiTietHoaDon.setSoLuong(newQuantity);
                chiTietHoaDon.setDonGia(ctspView.getGiaTien());
                chiTietHoaDon.setThanhTien(newQuantity * ctspView.getGiaTien());

                int row = chiTietHoaDonDao.insertOrUpdateChiTietHoaDon(chiTietHoaDon);
                if (row > 0) {
                    tongTien += chiTietHoaDon.getThanhTien();
                }
            }
            hoaDon.setThanhTien(tongTien);
            int row = hoaDonDao.setHoaDonCho(hoaDon);
            if (row > 0) {
                JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công", "Thông Báo", JOptionPane.QUESTION_MESSAGE);
                resetFormSauThanhToan();
            }
        }
    }

    double tongTien() {
        double tongTien = 0;
        for (ChiTietSanPhamView chiTietSanPhamView : gioHang) {
            tongTien += (chiTietSanPhamView.getSoLuong() * chiTietSanPhamView.getGiaTien());
        }
        return tongTien;
    }

    private ChiTietSanPham getFormCTSP() {
        int idCTSP = -1;
        SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
        Mau mau = (Mau) cbbColor.getSelectedItem();
        Size size = (Size) cbbSize.getSelectedItem();
        int soLuong;
        double giaTien;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText());
        } catch (Exception e) {
            soLuong  = -1;
        }
        try {
            giaTien = Double.parseDouble(txtGiaTien.getText());
        } catch (Exception e) {
            giaTien = -1;
        }
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham(
                sanPham.getIdSP(),
                mau.getIdMau(),
                size.getIdSize(),
                soLuong,
                giaTien,
                1);
        if(!txt_idsp.equals("")){
            chiTietSanPham.setIdCTSP(Integer.parseInt(txt_idsp.getText()));
        }
        return chiTietSanPham;
    }

    private void themMoiSanPham() {
        ChiTietSanPham chiTietSanPham = Validator.ChitetSanPhamValidator.validateChiTietSanPham(getFormCTSP());
        if (chiTietSanPham == null) {
            return;
        }
        
        ChiTietSanPham existed = chiTietSanPhamDao.findBySP_Mau_Size(
                chiTietSanPham.getIdSP(),
                chiTietSanPham.getIdMau(),
                chiTietSanPham.getIdSize()
        );

        if (existed != null) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Sản phẩm đã tồn tại. Bạn có muốn cập nhật số lượng mới không?",
                    "Cập nhật sản phẩm",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int soLuongHienTai = existed.getSoLuong();
                int soLuongMoi = chiTietSanPham.getSoLuong();
                //nếu sửa sl bé hơn sl trong kho thì ẩn đi
                if (soLuongMoi < soLuongHienTai) {
                    JOptionPane.showMessageDialog(this,
                            "Số lượng nhập mới phải lớn hơn số lượng hiện tại (" + soLuongHienTai + ")!",
                            "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                chiTietSanPham.setIdCTSP(existed.getIdCTSP());

                int result = chiTietSanPhamDao.updateSoLuongVaGiaTien(chiTietSanPham);
                if (result > 0) {
                    fillTaleChiTietSP();
                    JOptionPane.showMessageDialog(this, "Cập nhật số lượng thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        } else {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Sản phẩm chưa tồn tại. Bạn có muốn thêm mới không?",
                    "Thêm sản phẩm mới",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean isAdd = chiTietSanPhamDao.addChiTietSanPham(chiTietSanPham);
                if (isAdd) {
                    fillTaleChiTietSP();
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!");
                }
            }
        }
    }

    private void getFormSanPham() {
        int selectedRow = tblSP.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        // Lấy dữ liệu từ bảng tblSP
        int idCTSP = (int) tblSP.getValueAt(selectedRow, 0);
        String tenGiay = tblSP.getValueAt(selectedRow, 2).toString();
        String tenMau = tblSP.getValueAt(selectedRow, 3).toString();
        String tenSize = tblSP.getValueAt(selectedRow, 4).toString();
        int soLuong = (int) tblSP.getValueAt(selectedRow, 6);
        double giaTien = (double) tblSP.getValueAt(selectedRow, 7);
        // Đổ lên form
        txt_idsp.setText(String.valueOf(idCTSP));
        txtSoLuong.setText(String.valueOf(soLuong));
        txtGiaTien.setText(String.valueOf(giaTien));
        // Set comboBox Sản phẩm theo tên giày
        for (int i = 0; i < cbbSanPham.getItemCount(); i++) {
            SanPham sp = (SanPham) cbbSanPham.getItemAt(i);
            if (sp.getTenGiay().equalsIgnoreCase(tenGiay)) {
                cbbSanPham.setSelectedIndex(i);
                break;
            }
        }
        // Set comboBox Màu
        for (int i = 0; i < cbbColor.getItemCount(); i++) {
            Mau mau = (Mau) cbbColor.getItemAt(i);
            if (mau.getTenMau().equalsIgnoreCase(tenMau)) {
                cbbColor.setSelectedIndex(i);
                break;
            }
        }
        // Set comboBox Size
        for (int i = 0; i < cbbSize.getItemCount(); i++) {
            Size size = (Size) cbbSize.getItemAt(i);
            if (size.getTenSize().equalsIgnoreCase(tenSize)) {
                cbbSize.setSelectedIndex(i);
                break;
            }
        }
    }

    public KhachHang getKhacHang() {
        KhachHang khachHang = new KhachHang(
                txtKhachHAng.getText().trim(),
                txtSDTKH.getText().trim(),
                txtDiaChiKH.getText().trim(),
                1
        );
        return khachHang;
    }

    private void suaGioHang() {
        try {
            int row = tblGioHang.getSelectedRow();
            if (row > -1) {
                String input = JOptionPane.showInputDialog(null, "Nhập số lượng:");
                int number = Integer.parseInt(input);

                // Check if the entered quantity is zero.
                if (number == 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng không được bằng 0", "Thông Báo Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Retrieve the product in the shopping cart
                ChiTietSanPhamView chiTietSanPhamView = gioHang.get(row);

                // Get available stock using chiTietSanPhamDao.getSoLuongById
                int availableQuantity = chiTietSanPhamDao.getSoLuongById(chiTietSanPhamView.getIdCTSP());

                // Check if the desired quantity exceeds the available stock
                if (number > availableQuantity) {
                    JOptionPane.showMessageDialog(null, "Số lượng trong kho không đủ", "Thông Báo Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Update the product quantity in the shopping cart (gioHang)
                chiTietSanPhamView.setSoLuong(number);
                gioHang.set(row, chiTietSanPhamView);

                // Refresh the shopping cart display and update the total price
                fillGioHang();
                txtTongTienHang.setText(String.valueOf(tongTien()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void timKiemSanPham() {

        final String keyword = txt_locsanpham.getText().trim().toLowerCase();
        final String keywordNoAccent = removeDiacritics(keyword);

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblSanPham.setRowSorter(sorter);
        if (keyword.length() == 0) {
            sorter.setRowFilter(null); // Show all rows if keyword is empty
        } else {
            // Create a custom RowFilter that compares the text after removing accents
            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                @Override
                public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                    int columnCount = entry.getValueCount();

                    // Iterate through each column for the current row
                    for (int i = 0; i < columnCount; i++) {
                        Object value = entry.getValue(i);
                        if (value != null) {
                            // Convert cell text to lowercase and remove diacritics
                            String cellText = removeDiacritics(value.toString().toLowerCase());
                            if (cellText.contains(keywordNoAccent)) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    private void resetFormSauThanhToan() {
        // Xóa thông tin form hóa đơn
        txtIDHD.setText("");
        txtTenNhanVien.setText("");
        txtKhachHAng.setText("");
        txtSDTKH.setText("");
        txtDiaChiKH.setText("");
        txtThanhTIen.setText("");
        txtNgayTao.setText("");
        txtTrangThai.setText("");
        txtTongTienHang.setText("");

        // Xóa giỏ hàng
        gioHang.clear();
        fillGioHang();

        // Làm mới danh sách sản phẩm, hóa đơn
        fillTaleChiTietSP();
        fillTaleHoaDon();

        // Làm mới combo box
        fillAllCombo();

        // Reset ID khách hàng
        idKH = -1;

        // Xóa bộ lọc tìm kiếm nếu có
        txt_locsanpham.setText("");
    }

    // Xóa dấu phụ để tìm kiếm sản phẩm
    private String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txt_idsp = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbbColor = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        btnAddColor = new javax.swing.JButton();
        btnAddSize = new javax.swing.JButton();
        btnAddSP = new javax.swing.JButton();
        btnDeleteSP = new javax.swing.JButton();
        cbbSanPham = new javax.swing.JComboBox<>();
        btnThemSanPham = new javax.swing.JButton();
        btnSuaGiaTien = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtIDHD = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtKhachHAng = new javax.swing.JTextField();
        txtDiaChiKH = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        txtTrangThai = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtThanhTIen = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        btnTimKh = new javax.swing.JButton();
        txtSDTKH = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtTongTienHang = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        btnTaoHoaDon1 = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnSuaGioHang = new javax.swing.JButton();
        btnXoaGioHang = new javax.swing.JButton();
        btnLamMoiGioHang = new javax.swing.JButton();
        txt_locsanpham = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel8.setBackground(new java.awt.Color(51, 204, 255));

        jLabel20.setText("ID Sản Phẩm");

        txt_idsp.setEnabled(false);

        jLabel21.setText("Tên Sản Phẩm");

        jLabel22.setText("Màu");

        jLabel23.setText("Size");

        cbbColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbColorActionPerformed(evt);
            }
        });

        jLabel25.setText("Số Lượng");

        jLabel26.setText("Giá Tiền");

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID CTSP", "Mã Giày", "Tên Giày", "Màu", "Size", "Loại", "Số Lượng", "Giá Tiền", "Trạng Thái"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblSP);

        btnAddColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAddColor.setText("Thêm ");
        btnAddColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddColorActionPerformed(evt);
            }
        });

        btnAddSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAddSize.setText("Thêm ");
        btnAddSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSizeActionPerformed(evt);
            }
        });

        btnAddSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/them san pham.png"))); // NOI18N
        btnAddSP.setText("Thêm");
        btnAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPActionPerformed(evt);
            }
        });

        btnDeleteSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xoa san pham.png"))); // NOI18N
        btnDeleteSP.setText("Xoá");

        btnThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnThemSanPham.setText("Thêm ");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnSuaGiaTien.setText("Cập nhật giá tiền");
        btnSuaGiaTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaGiaTienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 918, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_idsp)
                        .addComponent(txtGiaTien)
                        .addComponent(txtSoLuong)
                        .addComponent(cbbColor, 0, 330, Short.MAX_VALUE)
                        .addComponent(cbbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnAddSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteSP)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddSize, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaGiaTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1310, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_idsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSanPham))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddColor))
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddSize))
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(btnSuaGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSP)
                    .addComponent(btnDeleteSP))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Danh Sách", jPanel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Quản Lí Sản Phẩm", jPanel2);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel28.setText("Mã HD:");

        txtIDHD.setEditable(false);
        txtIDHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDHDActionPerformed(evt);
            }
        });

        jLabel29.setText("Nhân Viên:");

        txtTenNhanVien.setEditable(false);

        jLabel30.setText("Khách Hàng:");

        txtKhachHAng.setEnabled(false);
        txtKhachHAng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachHAngActionPerformed(evt);
            }
        });

        txtDiaChiKH.setEnabled(false);

        jLabel31.setText("SDT:");

        jLabel37.setText("Địa chỉ:");

        jLabel38.setText("Ngày tạo");

        txtNgayTao.setEnabled(false);

        txtTrangThai.setEnabled(false);

        jLabel39.setText("Trạng Thái");

        txtThanhTIen.setEnabled(false);

        jLabel40.setText("Thành TIền");

        btnTimKh.setText("Tìm");
        btnTimKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThanhTIen)
                    .addComponent(txtTrangThai)
                    .addComponent(txtKhachHAng)
                    .addComponent(txtIDHD)
                    .addComponent(txtTenNhanVien)
                    .addComponent(txtDiaChiKH)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtSDTKH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKh)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtIDHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtKhachHAng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKh)
                        .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThanhTIen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel32.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel32.setText("Thông tin chung:");

        jLabel33.setText("Dư Tính Phải Trả");

        btnTaoHoaDon1.setBackground(new java.awt.Color(153, 153, 153));
        btnTaoHoaDon1.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon1.setText("Tạo Hóa Đơn ");
        btnTaoHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDon1ActionPerformed(evt);
            }
        });

        btnLamMoi1.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoi1.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi1.setText("Làm Mới");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thanh Toán");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 153, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Chờ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnTaoHoaDon1)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel36.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel36.setText("Chi Tiết:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hóa Đơn Bán Hàng");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel36)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giỏ Hàng");

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID SP", " Mã SP", "Tên SP", "Màu", "Size", "Loại", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        jScrollPane8.setViewportView(tblGioHang);
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Danh sách sản phẩm");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID SP", "Mã SP", "Tên SP", "Màu", "Size", "Loại", "Số Lượng", "Giá Tiền ", "Trạng Thái"
            }
        ));
        tblSanPham.setEditingColumn(0);
        tblSanPham.setEditingRow(0);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblSanPham);

        jPanel7.setBackground(new java.awt.Color(51, 204, 255));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HD", "Nhân Viên", "Khách Hàng", "SDT", "Dia Chi", "Thành Tiền", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoaDon);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Danh sách hóa đơn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSuaGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sua san pham.png"))); // NOI18N
        btnSuaGioHang.setText("Sửa");
        btnSuaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaGioHangActionPerformed(evt);
            }
        });

        btnXoaGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xoa san pham.png"))); // NOI18N
        btnXoaGioHang.setText("Xóa");
        btnXoaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioHangActionPerformed(evt);
            }
        });

        btnLamMoiGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh-arrow.png"))); // NOI18N
        btnLamMoiGioHang.setText("Làm Mới");
        btnLamMoiGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiGioHangActionPerformed(evt);
            }
        });

        jButton1.setText("Tìm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_locsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnSuaGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLamMoiGioHang))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane9)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaGioHang)
                    .addComponent(btnXoaGioHang)
                    .addComponent(btnLamMoiGioHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_locsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel6))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 664, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản Lí Bán Hàng", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        chonSanPham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tienHanhThanhToan();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnLamMoiGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiGioHangActionPerformed
        resetFormSauThanhToan();
    }//GEN-LAST:event_btnLamMoiGioHangActionPerformed

    private void btnSuaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGioHangActionPerformed
        suaGioHang();
    }//GEN-LAST:event_btnSuaGioHangActionPerformed

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed
        resetFormSauThanhToan();
    }//GEN-LAST:event_btnXoaGioHangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timKiemSanPham();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtKhachHAngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHAngActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachHAngActionPerformed

    private void txtIDHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        chonHoaDon();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTaoHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDon1ActionPerformed
        taoHoaDonTrong();
    }//GEN-LAST:event_btnTaoHoaDon1ActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        resetFormSauThanhToan();
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void btnTimKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKhActionPerformed
        String sdt = Validator.KhachHangValidator.validatorSDT(txtSDTKH.getText().trim());
        if (sdt == null) {
            return;
        }
        KhachHang khachHang = khachHangDao.findKhachHangByPhone(sdt);
        if (khachHang == null) {
            JOptionPane.showMessageDialog(null, "Thông tin chưa được đăng ký, Vui Lòng Đăng Ký Thông Tin", "Thông Báo", JOptionPane.ERROR_MESSAGE);
            txtKhachHAng.setEnabled(true);
            txtDiaChiKH.setEnabled(true);
        } else {
            txtKhachHAng.setText(khachHang.getHoTenKH());
            txtDiaChiKH.setText(khachHang.getDiaChi());
            this.idKH = khachHang.getIdKH();
        }
    }//GEN-LAST:event_btnTimKhActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:

        try {
            JDialog giay = new SanPhamJDialog(null, true);
            giay.setVisible(true);
            if (!giay.isVisible()) {
                fillAllCombo();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed
        themMoiSanPham();
    }//GEN-LAST:event_btnAddSPActionPerformed

    private void btnAddSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSizeActionPerformed
        // TODO add your handling code here:
        JDialog size;
        try {
            size = new SizeJDialog(null, true);
            size.setVisible(true);
        } catch (Exception ex) {
            System.out.println("ex");
        }
    }//GEN-LAST:event_btnAddSizeActionPerformed

    private void btnAddColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddColorActionPerformed
        // TODO add your handling code here:
        JDialog color;
        try {
            color = new ColorJDialog(null, true);
            color.setVisible(true);
            if (!color.isVisible()) {
                fillAllCombo();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnAddColorActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        getFormSanPham();
    }//GEN-LAST:event_tblSPMouseClicked

    private void cbbColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbColorActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setHoaDonCho();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSuaGiaTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGiaTienActionPerformed
        // TODO add your handling code here:
        if(txt_idsp.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui Lòng chọn sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ChiTietSanPham chiTietSanPham = Validator.ChitetSanPhamValidator.validateChiTietSanPham(getFormCTSP());
        System.out.println(chiTietSanPham.toString());
        int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn cập nhật giá?",
                    "Cập nhật giá tiền",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int isUpdatePrice = chiTietSanPhamDao.updateGiaTien(chiTietSanPham);
                System.out.println(isUpdatePrice);
                if (isUpdatePrice > 0) {
                    fillTaleChiTietSP();
                    JOptionPane.showMessageDialog(this, "Cập nhật giá tiền thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật giá tiền thất bại!");
                }
            }
    }//GEN-LAST:event_btnSuaGiaTienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddColor;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnAddSize;
    private javax.swing.JButton btnDeleteSP;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoiGioHang;
    private javax.swing.JButton btnSuaGiaTien;
    private javax.swing.JButton btnSuaGioHang;
    private javax.swing.JButton btnTaoHoaDon1;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnTimKh;
    private javax.swing.JButton btnXoaGioHang;
    private javax.swing.JComboBox<Mau> cbbColor;
    private javax.swing.JComboBox<SanPham> cbbSanPham;
    private javax.swing.JComboBox<Size> cbbSize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtIDHD;
    private javax.swing.JTextField txtKhachHAng;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtThanhTIen;
    private javax.swing.JTextField txtTongTienHang;
    private javax.swing.JTextField txtTrangThai;
    private javax.swing.JTextField txt_idsp;
    private javax.swing.JTextField txt_locsanpham;
    // End of variables declaration//GEN-END:variables
}
