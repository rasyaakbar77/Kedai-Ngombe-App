/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kedaingombeapp.ui.user;

import com.mycompany.kedaingombeapp.model.Menu;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.function.Consumer;
import javax.swing.*;

/**
 *
 * @author LOQ
 */
public class MenuItemPanel extends JPanel {
    private Menu menu;
    private JCheckBox chkPilih = new JCheckBox("Pilih");
    private JSpinner spnQty = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));

    public MenuItemPanel(Menu menu, int qtyAwal, boolean dipilih, Consumer<MenuItemPanel> onChange) {
        this.menu = menu;
        setLayout(new FlowLayout(FlowLayout.LEFT, 16, 8));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.LIGHT_GRAY));

        JLabel lblGambar = new JLabel();
        lblGambar.setPreferredSize(new Dimension(160, 160));
        if (menu.getGambar() != null) {
            java.net.URL url = getClass().getResource("/" + menu.getGambar());
            if (url != null) {
                Image img = new ImageIcon(url).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
                lblGambar.setIcon(new ImageIcon(img));
            }
        }

        JLabel lblInfo = new JLabel("<html><b>" + menu.getNama() + "</b><br>Rp " + menu.getHarga()
                + "<br>Stok: " + menu.getStok() + "</html>");
        lblInfo.setPreferredSize(new Dimension(220, 80));

        spnQty.setValue(qtyAwal);
        chkPilih.setSelected(dipilih);
        chkPilih.setEnabled(menu.getStok() > 0);   // stok habis = nggak bisa dipilih

        // qty nggak boleh lebih dari stok
        ((SpinnerNumberModel) spnQty.getModel()).setMaximum(Math.max(1, menu.getStok()));

        chkPilih.addActionListener(e -> onChange.accept(this));
        spnQty.addChangeListener(e -> onChange.accept(this));

        add(lblGambar);
        add(lblInfo);
        add(chkPilih);
        add(spnQty);
    }

    public Menu getMenu() { return menu; }
    public boolean isDipilih() { return chkPilih.isSelected(); }
    public int getQty() { return (int) spnQty.getValue(); }
}
