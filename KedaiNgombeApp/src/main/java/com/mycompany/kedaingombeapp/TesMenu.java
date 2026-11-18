/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kedaingombeapp;

import com.mycompany.kedaingombeapp.model.Menu;
import com.mycompany.kedaingombeapp.service.MenuService;

public class TesMenu {
    public static void main(String[] args) {
        MenuService s = new MenuService();
        for (Menu m : s.getAll()) {
            System.out.println(m.getId() + " | " + m.getNama() + " | " + m.getKategori()
                    + " | " + m.getHarga() + " | " + m.getStok());
        }
        System.out.println("Milk Tea: " + s.getByKategori("Milk Tea").size());
        System.out.println("Cari 'choco': " + s.search("choco").size());
    }
}