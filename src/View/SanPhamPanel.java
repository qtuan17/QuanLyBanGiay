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
import Model.Loai;
import Model.Mau;
import Model.NhanVien;
import Model.SanPham;
import Model.Size;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import util.SessionLogin;
import viewModel.ChiTietSanPhamView;
import viewModel.HoaDonView;

/**
 *
 * @author tuanb
 */
public class SanPhamPanel extends javax.swing.JPanel {

    private DefaultTableModel model;
    int index = -1;
    ChiTietSanPhamDao chiTietSanPhamDao;
    ChiTietHoaDonDao chiTietHoaDonDao;
    MauDao mauDao;
    LoaiDao loaiDao;
    SizeDao sizeDao;
    HoaDonDao hoaDonDao;
    SanPhamDao sanPhamDao;
    KhachHangDao khachHangDao;
    List<ChiTietSanPhamView> gioHang = new ArrayList<>();
    List<HoaDon> lstHoaDon;
    List<ChiTietSanPhamView> sanphams;
    int idKH = -1;

    public SanPhamPanel(java.awt.Frame parent, boolean modal) throws Exception {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        initComponents();
        chiTietSanPhamDao = new ChiTietSanPhamDao();
        chiTietHoaDonDao = new ChiTietHoaDonDao();
        mauDao = new MauDao();
        loaiDao = new LoaiDao();
        sizeDao = new SizeDao();
        hoaDonDao = new HoaDonDao();
        sanPhamDao = new SanPhamDao();
        khachHangDao = new KhachHangDao();
        fillTaleChiTietSP();
        fillTaleHoaDon();
        fillCbbColor();
        fillCbbSize();
        fillCbbSanPham();
    }

    public void fillCbbColor() {
        DefaultComboBoxModel cbbColor = (DefaultComboBoxModel) this.cbbColor.getModel();
        cbbColor.removeAllElements();
        List<Mau> colors = mauDao.findAll();
        for (Mau color : colors) {
            if (color.getTrangThai() == 1) {
                cbbColor.addElement(color);
            }
        }
    }

    public void fillCbbSize() {
        DefaultComboBoxModel cbbSize = (DefaultComboBoxModel) this.cbbSize.getModel();
        cbbSize.removeAllElements();
        List<Size> sizes = sizeDao.findAll();
        for (Size size : sizes) {
            if (size.getTrangThai() == 1) {
                cbbSize.addElement(size);
            }
        }
    }

    public void fillCbbSanPham() {
        DefaultComboBoxModel cbbSanPham = (DefaultComboBoxModel) this.cbbSanPham.getModel();
        cbbSanPham.removeAllElements();
        List<SanPham> sanphams = sanPhamDao.findAll();
        for (SanPham sanpham : sanphams) {
            if (sanpham.getTrangThai() == 1) {
                cbbSanPham.addElement(sanpham);
            }
        }
    }

    void fillTaleChiTietSP() {
        DefaultTableModel modelchitietsp_QLBH = new DefaultTableModel();
        DefaultTableModel modelchitietsp_QLSP = new DefaultTableModel();
        modelchitietsp_QLBH = (DefaultTableModel) tblSanPham.getModel();
        modelchitietsp_QLSP = (DefaultTableModel) tblSP.getModel();
        modelchitietsp_QLBH.setRowCount(0);
        modelchitietsp_QLSP.setRowCount(0);
        try {
            List<ChiTietSanPhamView> chiTietsanpham = chiTietSanPhamDao.findAll();
            if (chiTietsanpham.isEmpty()) {
                System.out.println("chi tiet null");
            }
            for (ChiTietSanPhamView chiTietSanPham : chiTietsanpham) {
                Object[] data_QLBH = {
                    chiTietSanPham.getIdCTSP(),
                    chiTietSanPham.getMaGiay(),
                    chiTietSanPham.getTenGiay(),
                    chiTietSanPham.getTenMau(),
                    chiTietSanPham.getTenSize(),
                    chiTietSanPham.getTenLoai(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getGiaTien(),
                    chiTietSanPham.getTrangThai() == 1 ? "Còn Hàng" : "Hết Hàng",};
                modelchitietsp_QLBH.addRow(data_QLBH);
                Object[] data_QLSP = {
                    chiTietSanPham.getIdCTSP(),
                    chiTietSanPham.getMaGiay(),
                    chiTietSanPham.getTenGiay(),
                    chiTietSanPham.getTenMau(),
                    chiTietSanPham.getTenSize(),
                    chiTietSanPham.getTenLoai(),
                    chiTietSanPham.getSoLuong(),
                    chiTietSanPham.getGiaTien(),
                    chiTietSanPham.getTrangThai(),
                    chiTietSanPham.getTrangThai() == 1 ? "Còn Hàng" : "Hết Hàng",};
                modelchitietsp_QLSP.addRow(data_QLSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fillTaleHoaDon() {
        DefaultTableModel modelhoadon = new DefaultTableModel();
        modelhoadon = (DefaultTableModel) tblHoaDon.getModel();
        modelhoadon.setRowCount(0);
        try {
            List<HoaDonView> hoaDons = hoaDonDao.findAll();
            if (hoaDons.isEmpty()) {
                System.out.println("chi tiet null");
            }
            for (HoaDonView hoaDon : hoaDons) {
                if (hoaDon.getTrangThai() == 1) {
                    Object[] row = {
                        hoaDon.getIdHd(),
                        hoaDon.getHoTenNV(),
                        hoaDon.getHoTenKH(),
                        hoaDon.getSdt(),
                        hoaDon.getDiaChi(),
                        hoaDon.getThanhTien(),
                        hoaDon.getNgayTao(),
                        hoaDon.trangThaiToString(hoaDon.getTrangThai())
                    };
                    modelhoadon.addRow(row);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
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

    void fillGioHang() {
        DefaultTableModel modelgiohang = new DefaultTableModel();
        modelgiohang = (DefaultTableModel) tblGioHang.getModel();
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

    double tongTien() {
        double tongTien = 0;
        for (ChiTietSanPhamView chiTietSanPhamView : gioHang) {
            tongTien += (chiTietSanPhamView.getSoLuong() * chiTietSanPhamView.getGiaTien());
        }
        return tongTien;
    }

    ChiTietSanPham getFormCTSP() {
        SanPham sanPham = (SanPham) cbbSanPham.getSelectedItem();
        Mau mau = (Mau) cbbColor.getSelectedItem();
        Size size = (Size) cbbSize.getSelectedItem();
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        double giaTien = Double.parseDouble(txtGiaTien.getText());
        String ghiChu = txtGhiChu.getText();
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham(
                sanPham.getIdSP(),
                mau.getIdMau(),
                size.getIdSize(),
                soLuong,
                giaTien,
                1);
        return chiTietSanPham;
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
        jTextField1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbbColor = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        btnAddColor = new javax.swing.JButton();
        btnAddSize = new javax.swing.JButton();
        btnAddSP = new javax.swing.JButton();
        btnEditSP = new javax.swing.JButton();
        btnDeleteSP = new javax.swing.JButton();
        cbbSanPham = new javax.swing.JComboBox<>();
        btnThemSanPham = new javax.swing.JButton();
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
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel20.setText("ID Sản Phẩm");

        jTextField1.setEnabled(false);

        jLabel21.setText("Tên Sản Phẩm");

        jLabel22.setText("Màu");

        jLabel23.setText("Size");

        cbbColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbColorActionPerformed(evt);
            }
        });

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel25.setText("Số Lượng");

        jLabel26.setText("Giá Tiền");

        jLabel27.setText("Ghi Chú");

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

        btnEditSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sua san pham.png"))); // NOI18N
        btnEditSP.setText("Sửa");

        btnDeleteSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xoa san pham.png"))); // NOI18N
        btnDeleteSP.setText("Xoá");

        cbbSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnThemSanPham.setText("Thêm ");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 916, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1)
                        .addComponent(txtGiaTien)
                        .addComponent(txtSoLuong)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addComponent(cbbColor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnAddSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteSP)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnAddColor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1310, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSP)
                    .addComponent(btnEditSP)
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

        jLabel32.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
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
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(btnTaoHoaDon1)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jLabel36.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel36.setText("Chi Tiết:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hóa Đơn");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel36)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnSuaGioHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaGioHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLamMoiGioHang)
                                .addContainerGap())))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane8)
                            .addComponent(jScrollPane9)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 705, Short.MAX_VALUE))
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
        int row = tblSanPham.getSelectedRow();
        ChiTietSanPhamView chiTietSanPhamView = new ChiTietSanPhamView(
                (int) tblSanPham.getValueAt(row, 0),
                tblSanPham.getValueAt(row, 1).toString(),
                tblSanPham.getValueAt(row, 2).toString(),
                tblSanPham.getValueAt(row, 3).toString(),
                tblSanPham.getValueAt(row, 4).toString(),
                tblSanPham.getValueAt(row, 5).toString(),
                (int) tblSanPham.getValueAt(row, 6),
                (double) tblSanPham.getValueAt(row, 7),
                1
        );
        addGioHang(chiTietSanPhamView);
        txtTongTienHang.setText(String.valueOf(tongTien()));
        fillGioHang();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        int choice = JOptionPane.showConfirmDialog(null, "Xác nhận Thanh Toán", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            double tongTien = 0;
            if (txtIDHD.getText().equals("") || txtIDHD.getText() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn để thanh toán", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gioHang.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui Lòng Thêm Sản Phẩm Vào Giỏ Hàng", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            HoaDon hoaDon = new HoaDon();
            chiTietHoaDon.setIdHD(Integer.parseInt(txtIDHD.getText()));
            for (ChiTietSanPhamView chiTietSanPhamView : gioHang) {
                chiTietSanPham.setIdCTSP(chiTietSanPhamView.getIdCTSP());
                if (chiTietSanPhamDao.getSoLuongById(chiTietSanPhamView.getIdCTSP()) - chiTietSanPhamView.getSoLuong() >= 0) {
                    chiTietSanPham.setSoLuong(chiTietSanPhamDao.getSoLuongById(chiTietSanPhamView.getIdCTSP()) - chiTietSanPhamView.getSoLuong());
                    chiTietHoaDon.setSoLuong(chiTietSanPhamView.getSoLuong());

                } else {
                    chiTietSanPham.setSoLuong(0);
                    chiTietHoaDon.setSoLuong(chiTietSanPhamDao.getSoLuongById(chiTietSanPhamView.getIdCTSP()));
                }
                chiTietHoaDon.setIdCTSP(chiTietSanPhamView.getIdCTSP());
                chiTietSanPhamDao.updateSoLuong(chiTietSanPham);
                chiTietHoaDon.setDonGia(chiTietSanPhamView.getGiaTien());
                chiTietHoaDon.setThanhTien(chiTietHoaDon.getSoLuong() * chiTietHoaDon.getDonGia());
                int row = chiTietHoaDonDao.insertChiTietHoaDon(chiTietHoaDon);
                if (row > 0) {
                    tongTien += chiTietHoaDon.getThanhTien();
                }
            }
            hoaDon.setIdHD(Integer.parseInt(txtIDHD.getText()));
            hoaDon.setThanhTien(tongTien);
            int row = hoaDonDao.thanhToan(hoaDon);
            if (row > 0) {
                JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công", "Thông Báo", JOptionPane.QUESTION_MESSAGE);
                fillTaleChiTietSP();
                gioHang.clear();
                fillGioHang();
                fillTaleHoaDon();
            }
        } else if (choice == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnLamMoiGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiGioHangActionPerformed
        // TODO add your handling code here:
        gioHang.clear();
        txtTongTienHang.setText(String.valueOf(tongTien()));
        fillGioHang();
    }//GEN-LAST:event_btnLamMoiGioHangActionPerformed

    private void btnSuaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGioHangActionPerformed
        // TODO add your handling code here:
        try {
            int row = tblGioHang.getSelectedRow();
            if (row > -1) {
                String input = JOptionPane.showInputDialog(null, "Nhập số lượng:");
                int number = Integer.parseInt(input);
//                ChiTietSanPhamView sanpham =sanphams.get(row);
//                if(number>sanpham.getSoLuong()){
//                    JOptionPane.showMessageDialog(this, "Số Lượng trong kho không đủ", "Thông Báo Lỗi", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
                ChiTietSanPhamView chiTietSanPhamView = gioHang.get(row);
                chiTietSanPhamView.setSoLuong(number);
                gioHang.set(row, chiTietSanPhamView);
                fillGioHang();
                txtTongTienHang.setText(String.valueOf(tongTien()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnSuaGioHangActionPerformed

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed
        // TODO add your handling code here:
        int row = tblGioHang.getSelectedRow();
        gioHang.remove(row);
        fillGioHang();;
        txtTongTienHang.setText(String.valueOf(tongTien()));

    }//GEN-LAST:event_btnXoaGioHangActionPerformed

    private void btnAddColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddColorActionPerformed
        // TODO add your handling code here:
        JDialog color;
        try {
            color = new ColorJDialog(null, true);
            color.setVisible(true);
            if (!color.isVisible()) {
                fillCbbColor();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_btnAddColorActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbColorActionPerformed

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed
        // TODO add your handling code here:
        ChiTietSanPham chiTietSanPham = getFormCTSP();
        boolean isAdd = chiTietSanPhamDao.addChiTietSanPham(chiTietSanPham);
        if (isAdd) {
            fillTaleChiTietSP();
            JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Thất Bại");
        }

    }//GEN-LAST:event_btnAddSPActionPerformed

    private void txtKhachHAngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHAngActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachHAngActionPerformed

    private void txtIDHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        int idHD = (int) tblHoaDon.getValueAt(row, 0);
        String tenNhanVien = tblHoaDon.getValueAt(row, 1).toString();
        String tenKhachHang = tblHoaDon.getValueAt(row, 2).toString();
        String sdtKH = tblHoaDon.getValueAt(row, 3).toString();
        String diaChiKh = tblHoaDon.getValueAt(row, 4).toString();
        String ngayTao = tblHoaDon.getValueAt(row, 6).toString();
        String thanhTien = tblHoaDon.getValueAt(row, 5).toString();
        String trangThaiHoaDon = tblHoaDon.getValueAt(row, 7).toString();
        txtIDHD.setText(String.valueOf(idHD));
        txtTenNhanVien.setText(tenNhanVien);
        txtKhachHAng.setText(tenKhachHang);
        txtSDTKH.setText(sdtKH);
        txtDiaChiKH.setText(diaChiKh);
        txtThanhTIen.setText(thanhTien);
        txtNgayTao.setText(ngayTao);
        txtTrangThai.setText(trangThaiHoaDon);

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:

        try {
            JDialog giay = new SanPhamJDialog(null, true);
            giay.setVisible(true);
            if (!giay.isVisible()) {
                fillCbbSanPham();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnTaoHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDon1ActionPerformed
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
                        reload();
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

    }//GEN-LAST:event_btnTaoHoaDon1ActionPerformed
    public void reload() {
        txtIDHD.setText("");
        txtTenNhanVien.setText("");
        txtKhachHAng.setText("");
        txtSDTKH.setText("");
        txtDiaChiKH.setText("");
        txtDiaChiKH.setEnabled(false);
        txtKhachHAng.setEnabled(false);
        this.idKH = -1;
    }
    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        // TODO add your handling code here:
        reload();
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void btnTimKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKhActionPerformed
        // TODO add your handling code here:
        String sdt = txtSDTKH.getText().trim();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddColor;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnAddSize;
    private javax.swing.JButton btnDeleteSP;
    private javax.swing.JButton btnEditSP;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoiGioHang;
    private javax.swing.JButton btnSuaGioHang;
    private javax.swing.JButton btnTaoHoaDon1;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnTimKh;
    private javax.swing.JButton btnXoaGioHang;
    private javax.swing.JComboBox<String> cbbColor;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtGhiChu;
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
    // End of variables declaration//GEN-END:variables
}
