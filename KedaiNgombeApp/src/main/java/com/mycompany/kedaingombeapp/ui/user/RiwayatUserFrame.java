package com.mycompany.kedaingombeapp.ui.user;

import com.mycompany.kedaingombeapp.model.User;
import com.mycompany.kedaingombeapp.service.TransaksiService;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RiwayatUserFrame extends JFrame {

    private User user;
    private TransaksiService transaksiService = new TransaksiService();
    private DefaultTableModel tableModel;
    private JTable tblRiwayat;

    public RiwayatUserFrame(User user) {
        this.user = user;
        siapkanTampilan();
        siapkanTabel();
        muatTabel();
        setLocationRelativeTo(null);
    }

    private void siapkanTampilan() {
        setTitle("Riwayat Transaksi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);

        tblRiwayat = new JTable();
        JButton btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(e -> {
            new MenuUserFrame(user).setVisible(true);
            dispose();
        });

        JPanel atas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        atas.add(new JLabel("Klik dua kali pada transaksi untuk melihat struk"));

        JPanel bawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bawah.add(btnKembali);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(atas, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(tblRiwayat), BorderLayout.CENTER);
        getContentPane().add(bawah, BorderLayout.SOUTH);
    }

    private void siapkanTabel() {
        tableModel = new DefaultTableModel(
            new Object[]{"No. Transaksi", "Total", "Bayar", "Kembalian", "Waktu"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   // tabel cuma buat dilihat
            }
        };
        tblRiwayat.setModel(tableModel);
        tblRiwayat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        // Klik dua kali -> buka struk transaksi itu
        tblRiwayat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) bukaStruk();
            }
        });
    }

    private void muatTabel() {
        tableModel.setRowCount(0);
        List<Object[]> daftar = transaksiService.riwayatUser(user.getId());
        for (Object[] baris : daftar) {
            tableModel.addRow(baris);
        }
    }

    private void bukaStruk() {
        int row = tblRiwayat.getSelectedRow();
        if (row < 0) return;

        int idTransaksi = (int) tableModel.getValueAt(row, 0);
        new ReceiptUserFrame(user, idTransaksi).setVisible(true);
        dispose();
    }
}