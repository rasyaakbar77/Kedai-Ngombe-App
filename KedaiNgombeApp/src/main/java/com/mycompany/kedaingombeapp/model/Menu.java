/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kedaingombeapp.model;

/**
 *
 * @author LOQ
 */
public class Menu {
    public static final String[] KATEGORI = {
        "Milk Tea", "Jasmine Tea", "Coffee", "Smoothies", "Freshmilk", "Black Tea"
    };

    private int id;
    private String nama;
    private String kategori;
    private int harga;
    private int stok;
    private String gambar;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public String getGambar() { return gambar; }
    public void setGambar(String gambar) { this.gambar = gambar; }
}