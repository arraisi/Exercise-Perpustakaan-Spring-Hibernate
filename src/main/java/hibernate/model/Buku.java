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

    @Column(name = "IS_PINJAM")
    private boolean isPinjam;
    
    @OneToOne(mappedBy="buku")
    private Peminjam pinjam;

    //@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
   // @JoinColumn(name = "ID_PERPUS")
   // private Perpustakaan perpustakaan;

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

    @Override
    public String toString() {
        return "Buku{" + "id= " + id + ", title= " + title + ", author= " + author + ", Sedang dipinjam ?= " + isPinjam + ", peminjam= " + pinjam.getNama() + '}';
    }

    public String toString1() {
        return "Buku{" + "id= " + id + ", title= " + title + ", author= " + author + ", Sedang dipinjam ?= " + isPinjam +  '}';
    }
    
    
}
