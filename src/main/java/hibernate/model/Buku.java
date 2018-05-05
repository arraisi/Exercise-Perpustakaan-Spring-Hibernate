/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BUKU")
public class Buku {

    @Id
    @Column(name = "ID_BUKU")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "JUDUL", length = 100)
    private String title;
    @Column(name = "PENGARANG", length = 100)
    private String author;

    @Column(name = "DI_PINJAM")
    private boolean isPinjam;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PERPUSTAKAAN_FK")
    private Perpustakaan perpus;

    @OneToOne(mappedBy = "buku", cascade = CascadeType.PERSIST)
    private Peminjam pinjam;

    public Perpustakaan getPerpus() {
        return perpus;
    }

    public void setPerpus(Perpustakaan perpus) {
        this.perpus = perpus;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIsPinjam() {
        return isPinjam;
    }

    public void setIsPinjam(boolean isPinjam) {
        this.isPinjam = isPinjam;
    }

    public Peminjam getPinjam() {
        return pinjam;
    }

    public void setPinjam(Peminjam pinjam) {
        this.pinjam = pinjam;
    }

    
    @Override
    public String toString() {
        return "Buku{" + "id= " + id + ", title= " + title + ", author= " + author + ", Sedang dipinjam ?= " + isPinjam + ", peminjam= " + pinjam.getNama() + '}';
    }

    public String toString1() {
        return "Buku{" + "id= " + id + ", title= " + title + ", author= " + author + ", Sedang dipinjam ?= " + isPinjam + '}';
    }

}
