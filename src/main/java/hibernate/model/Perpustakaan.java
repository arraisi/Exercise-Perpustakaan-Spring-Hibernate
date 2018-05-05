/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERPUSTAKAAN")
public class Perpustakaan {
    @Id
    @Column(name = "ID_PERPUSTAKAAN")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerpustakaan;
    
    @Column(name = "NAMA_PERPUSTAKAAN", length = 100)
    private String namaPerpus;
    
    @OneToOne(mappedBy = "perpus", cascade = CascadeType.PERSIST)
    private Buku buku;

    public int getIdPerpustakaan() {
        return idPerpustakaan;
    }

    public void setIdPerpustakaan(int idPerpustakaan) {
        this.idPerpustakaan = idPerpustakaan;
    }

    public String getNamaPerpus() {
        return namaPerpus;
    }

    public void setNamaPerpus(String namaPerpus) {
        this.namaPerpus = namaPerpus;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }


    
    
}
