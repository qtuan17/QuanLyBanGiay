/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Dao.MauDao;
import Model.Mau;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tuanb
 */
public class ColorJDialog extends javax.swing.JDialog {

    private DefaultTableModel model;
    int index = -1;
    MauDao mauDao;

    public ColorJDialog(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        setTitle("Bảng Màu");
        this.setLocationRelativeTo(null);
        mauDao = new MauDao();
        fillTableMau();
    }

    //Hiển thị bảng màu
    void fillTableMau() {
        model = (DefaultTableModel) tblColor.getModel();
        model.setRowCount(0);
        try {
            List<Mau> maus = mauDao.findAll();
            if (maus.isEmpty()) {
                System.out.println("List Mau NUll");
            }
            for (Mau mau : maus) {
                Object[] row = {
                    mau.getIdMau(),
                    mau.getTenMau(),
                    mau.getTrangThai() == 1 ? "Tồn Tại" : "Không Tồn Tại"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra dữ liệu nhập của form Màu
    private boolean validateFormMau() {
        // Lấy tên màu đã nhập và loại bỏ khoảng trắng thừa
        String tenMau = txtTenMau.getText().trim();
        if (tenMau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên màu!");
            return false;
        }
        // Nếu bạn cần kiểm tra thêm các điều kiện khác (ví dụ: độ dài ký tự, định dạng,...)
        return true;
    }

    // Lấy dữ liệu từ form và tạo đối tượng Mau (sau khi đã kiểm tra validate)
    private Mau getFormMau() {
        // Kiểm tra dữ liệu nhập, nếu không hợp lệ thì trả về null
        if (!validateFormMau()) {
            return null;
        }
        Mau mau = new Mau();
        // Nếu đang chỉnh sửa sản phẩm (index != -1) thì set ID màu, có thể cần kiểm tra định dạng số
        if (index != -1) {
            try {
                int idMau = Integer.parseInt(txtIDMau.getText().trim());
                mau.setIdMau(idMau);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID màu không hợp lệ!");
                return null;
            }
        }
        mau.setTenMau(txtTenMau.getText().trim());
        return mau;
    }

    // Phương thức hiển thị dữ liệu từ bảng vào form (không cần thay đổi để validate)
    private void setFormMau(int index) {
        if (index != -1) {
            String idMau = tblColor.getValueAt(index, 0).toString();
            String tenMau = tblColor.getValueAt(index, 1).toString();
            txtIDMau.setText(idMau);
            txtTenMau.setText(tenMau);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblColor = new javax.swing.JTable();
        txtIDMau = new javax.swing.JTextField();
        txtTenMau = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnThemMau = new javax.swing.JButton();
        btnSuaMau = new javax.swing.JButton();
        btnXoaMau = new javax.swing.JButton();
        btnKhoiphucMau = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        tblColor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên Màu", "Trạng Thái"
            }
        ));
        tblColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblColorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblColor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        txtIDMau.setEditable(false);

        jLabel1.setText("ID");

        jLabel2.setText("Tên Màu");

        btnThemMau.setText("Thêm");
        btnThemMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauActionPerformed(evt);
            }
        });

        btnSuaMau.setText("Sửa");
        btnSuaMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMauActionPerformed(evt);
            }
        });

        btnXoaMau.setText("Xóa");
        btnXoaMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMauActionPerformed(evt);
            }
        });

        btnKhoiphucMau.setText("Khôi phục");
        btnKhoiphucMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiphucMauActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setText("Bảng Màu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenMau, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtIDMau)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemMau, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKhoiphucMau)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMau)
                    .addComponent(btnSuaMau))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaMau)
                    .addComponent(btnKhoiphucMau))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMauActionPerformed
        // TODO add your handling code here:
        Mau mau = getFormMau();
        int edit = mauDao.delete(mau);
        if (edit > 0) {
            System.out.println("DELETE THANH CONG");
            fillTableMau();
        }
    }//GEN-LAST:event_btnXoaMauActionPerformed

    private void btnThemMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauActionPerformed
        // TODO add your handling code here:
        Mau mau = getFormMau();
        int addMau = mauDao.create(mau);
        if (addMau > 0) {
            System.out.println("ADD THANH CONG");
            fillTableMau();
        }
    }//GEN-LAST:event_btnThemMauActionPerformed

    private void tblColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblColorMouseClicked

        index = tblColor.getSelectedRow();
        setFormMau(index);
    }//GEN-LAST:event_tblColorMouseClicked

    private void btnSuaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMauActionPerformed
        // TODO add your handling code here:
        Mau mau = getFormMau();
        int edit = mauDao.update(mau);
        if (edit > 0) {
            System.out.println("EDIT THANH CONG");
            fillTableMau();
        }
    }//GEN-LAST:event_btnSuaMauActionPerformed

    private void btnKhoiphucMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiphucMauActionPerformed
        // TODO add your handling code here:
        if (index != -1) {
            Mau mau = getFormMau();
            int edit = mauDao.khoiphuc(mau);
            if (edit > 0) {
                System.out.println("KHOI PHUC THANH CONG");
                fillTableMau();
            }
        }
    }//GEN-LAST:event_btnKhoiphucMauActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Color.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Color.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Color.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Color.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Color dialog = null;
//                try {
//                    dialog = new Color(new javax.swing.JFrame(), true);
//                } catch (Exception ex) {
//                    Logger.getLogger(Color.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoiphucMau;
    private javax.swing.JButton btnSuaMau;
    private javax.swing.JButton btnThemMau;
    private javax.swing.JButton btnXoaMau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblColor;
    private javax.swing.JTextField txtIDMau;
    private javax.swing.JTextField txtTenMau;
    // End of variables declaration//GEN-END:variables
}
