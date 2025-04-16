/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Dao.SizeDao;
import Model.Size;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tuanb
 */
public class SizeJDialog extends javax.swing.JDialog {

    private DefaultTableModel model;
    int index = -1;
    SizeDao sizeDao;

    public SizeJDialog(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        setTitle("Bảng Size");
        this.setLocationRelativeTo(null);
        sizeDao = new SizeDao();
        fillTableSize();
    }

    void fillTableSize() {
        model = (DefaultTableModel) tblBangSize.getModel();
        model.setRowCount(0);
        try {
            List<Size> sizes = sizeDao.findAllSize();
            if (sizes.isEmpty()) {
                System.out.println("List Size NUll");
            }
            for (Size size : sizes) {
                Object[] row = {
                    size.getIdSize(),
                    size.getTenSize(),
                    size.getTrangThai() == 1 ? "Tồn Tại" : "Không Tồn Tại"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Size getFormSize() {
        Size size = new Size();
        if (index != -1) {
            size.setIdSize(Integer.parseInt(txtIDSize.getText()));
        }
        size.setTenSize(txtTenSize.getText());
        return size;
    }

    private void setFormSize(int index) {
        if (index != -1) {
            String idSize = tblBangSize.getValueAt(index, 0).toString();
            String tenSize = tblBangSize.getValueAt(index, 1).toString();

            txtIDSize.setText(idSize);
            txtTenSize.setText(tenSize);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangSize = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIDSize = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenSize = new javax.swing.JTextField();
        btnThemSize = new javax.swing.JButton();
        btnSuaSize = new javax.swing.JButton();
        btnXoaSize = new javax.swing.JButton();
        btnKhoiphucSize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblBangSize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên Size", "Trạng Thái"
            }
        ));
        tblBangSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangSizeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBangSize);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setText("Bảng Size");

        jLabel1.setText("ID");

        txtIDSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDSizeActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên Size");

        btnThemSize.setText("Thêm");
        btnThemSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSizeActionPerformed(evt);
            }
        });

        btnSuaSize.setText("Sửa");
        btnSuaSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSizeActionPerformed(evt);
            }
        });

        btnXoaSize.setText("Xóa");
        btnXoaSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSizeActionPerformed(evt);
            }
        });

        btnKhoiphucSize.setText("Khôi phục");
        btnKhoiphucSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiphucSizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSize)
                    .addComponent(txtIDSize, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSuaSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKhoiphucSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSize)
                    .addComponent(btnSuaSize))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSize)
                    .addComponent(btnKhoiphucSize))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSizeActionPerformed
        // TODO add your handling code here:
        Size size = getFormSize();
        int edit = sizeDao.update(size);
        if (edit > 0) {
            System.out.println("edit THanh COng");
            fillTableSize();
        }
    }//GEN-LAST:event_btnSuaSizeActionPerformed

    private void btnThemSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSizeActionPerformed
        // TODO add your handling code here:
        Size size = getFormSize();
        int addsize = sizeDao.create(size);
        if (addsize > 0) {
            System.out.println("ADD THanh COng");
            fillTableSize();
        }
    }//GEN-LAST:event_btnThemSizeActionPerformed

    private void txtIDSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDSizeActionPerformed

    private void tblBangSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangSizeMouseClicked
        // TODO add your handling code here:
        index = tblBangSize.getSelectedRow();
        setFormSize(index);
    }//GEN-LAST:event_tblBangSizeMouseClicked

    private void btnXoaSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSizeActionPerformed
        // TODO add your handling code here:
        Size size = getFormSize();
        int delete = sizeDao.delete(size);
        if (delete > 0) {
            System.out.println("DELETE THanh COng");
            fillTableSize();
        }
    }//GEN-LAST:event_btnXoaSizeActionPerformed

    private void btnKhoiphucSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiphucSizeActionPerformed
        // TODO add your handling code here:
        if (index != -1) {
            Size size = getFormSize();
            int edit = sizeDao.khoiphuc(size);
            if (edit > 0) {
                System.out.println("KHoi Phuc THanh COng");
                fillTableSize();
            }
        }
    }//GEN-LAST:event_btnKhoiphucSizeActionPerformed

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
//            java.util.logging.Logger.getLogger(SizeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SizeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SizeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SizeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                SizeJDialog dialog = new SizeJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnKhoiphucSize;
    private javax.swing.JButton btnSuaSize;
    private javax.swing.JButton btnThemSize;
    private javax.swing.JButton btnXoaSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBangSize;
    private javax.swing.JTextField txtIDSize;
    private javax.swing.JTextField txtTenSize;
    // End of variables declaration//GEN-END:variables
}
