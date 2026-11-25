package com.mycompany.kedaingombeapp.ui.admin;

import com.mycompany.kedaingombeapp.service.CategoryServices;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KategoriAdminFrame extends JFrame {

    private CategoryServices categoryService = new CategoryServices();
    private DefaultTableModel tableModel;
    private String namaTerpilih = null;   // null = belum ada baris dipilih

    private JTable tblKategori;
    private JTextField txtNama;

    public KategoriAdminFrame() {
        siapkanTampilan();
        siapkanTabel();
        muatTabel();
        resetForm();
        setLocationRelativeTo(null);
    }

    private void siapkanTampilan() {
        setTitle("Kelola Kategori");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);

        tblKategori = new JTable();
        txtNama = new JTextField(20);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUbah = new JButton("Ubah");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");
        JButton btnKembali = new JButton("Kembali");

        btnTambah.addActionListener(e -> tambah());
        btnUbah.addActionListener(e -> ubah());
        btnHapus.addActionListener(e -> hapus());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> {
            new StockAdminFrame().setVisible(true);
            dispose();
        });

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Nama kategori"));
        form.add(txtNama);
        form.add(btnTambah);
        form.add(btnUbah);
        form.add(btnHapus);
        form.add(btnReset);

        JPanel bawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bawah.add(btnKembali);

        JPanel selatan = new JPanel(new BorderLayout());
        selatan.add(form, BorderLayout.CENTER);
        selatan.add(bawah, BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(tblKategori), BorderLayout.CENTER);
        getContentPane().add(selatan, BorderLayout.SOUTH);
    }

    private void siapkanTabel() {
        tableModel = new DefaultTableModel(new Object[]{"Kategori", "Jumlah Menu"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   // tabel cuma buat dilihat, edit lewat form
            }
        };
        tblKategori.setModel(tableModel);
        tblKategori.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        // Klik baris -> isi form
        tblKategori.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) isiFormDariBaris();
        });
    }

    private void muatTabel() {
        tableModel.setRowCount(0);
        List<Object[]> daftar = categoryService.getDenganJumlah();
        for (Object[] baris : daftar) {
            tableModel.addRow(baris);
        }
    }

    private void isiFormDariBaris() {
        int row = tblKategori.getSelectedRow();
        if (row < 0) return;

        namaTerpilih = tableModel.getValueAt(row, 0).toString();
        txtNama.setText(namaTerpilih);
    }

    private void resetForm() {
        namaTerpilih = null;
        txtNama.setText("");
        tblKategori.clearSelection();
    }

    // Baca nama dari form, balikin null kalau input salah
    private String bacaNama() {
        String nama = txtNama.getText().trim();
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama kategori wajib diisi");
            return null;
        }
        if (nama.length() > 50) {
            JOptionPane.showMessageDialog(this, "Nama kategori maksimal 50 karakter");
            return null;
        }
        return nama;
    }

    private void tambah() {
        String nama = bacaNama();
        if (nama == null) return;

        if (categoryService.insert(nama)) {
            JOptionPane.showMessageDialog(this, "Kategori berhasil ditambah");
            muatTabel();
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambah kategori, nama mungkin sudah ada");
        }
    }

    private void ubah() {
        if (namaTerpilih == null) {
            JOptionPane.showMessageDialog(this, "Pilih kategori di tabel dulu");
            return;
        }
        String nama = bacaNama();
        if (nama == null) return;

        if (categoryService.rename(namaTerpilih, nama)) {
            JOptionPane.showMessageDialog(this, "Kategori berhasil diubah");
            muatTabel();
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengubah kategori, nama mungkin sudah ada");
        }
    }

    private void hapus() {
        if (namaTerpilih == null) {
            JOptionPane.showMessageDialog(this, "Pilih kategori di tabel dulu");
            return;
        }

        int jumlah = categoryService.jumlahMenu(namaTerpilih);
        if (jumlah > 0) {
            JOptionPane.showMessageDialog(this, "Kategori masih dipakai " + jumlah
                    + " menu. Pindahkan atau hapus menunya dulu.");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this,
            "Yakin hapus kategori ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi != JOptionPane.YES_OPTION) return;

        if (categoryService.delete(namaTerpilih)) {
            muatTabel();
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menghapus kategori");
        }
    }
}