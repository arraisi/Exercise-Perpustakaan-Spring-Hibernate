/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import hibernate.config.KonfigurasiApps;
import hibernate.dao.PerpustakaanDao;
import hibernate.model.Buku;
import hibernate.model.Peminjam;
import hibernate.model.Perpustakaan;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApps {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(KonfigurasiApps.class);
        ctx.refresh();
        boolean isTambah = true;
        boolean isKurang = true;
        Scanner input = new Scanner(System.in);
        PerpustakaanDao perpustakaanDao = ctx.getBean(PerpustakaanDao.class);
        Integer idBuku;

//SIMPAN BUKU 
        System.out.print(" Tambah data perpustakaan baru kembali ?(jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            Perpustakaan perpus = new Perpustakaan();
            System.out.println("Masukan Nama Perpustakaan : ");
            perpus.setNamaPerpus(input.next());
            perpustakaanDao.simpanPerpustakaan(perpus);
            System.out.print(" Tambah data perpustakaan baru kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }
//SIMPAN BUKU 
        System.out.print(" Tambah data buku baru kembali ?(jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            Buku buku = new Buku();
            System.out.println("Masukan Judul Buku : ");
            buku.setTitle(input.next());
            System.out.println("Masukan Pengarang : ");
            buku.setAuthor(input.next());
            buku.setIsPinjam(false);;
            System.out.println("Masukan ID Perpustakaan : ");
            Integer idPerpus = input.nextInt();
            Perpustakaan perpus = perpustakaanDao.findByIdPerpus(idPerpus);
            buku.setPerpus(perpus);
            perpustakaanDao.simpanPerpustakaan(perpus);
            perpustakaanDao.simpanBuku(buku);

            System.out.print(" Tambah data buku baru kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }

// SIMPAN PERPUSTAKAAN      
//UPDATE BUKU
        System.out.print(" Ingin Update Buku ? (jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            System.out.println("==================== UPDATE BUKU ====================");
            System.out.println("Masukan ID buku yang akan diupdate : ");
            idBuku = input.nextInt();
            Buku buku = perpustakaanDao.findByIdBuku(idBuku);
            System.out.println("Masukan Judul Baru : ");
            buku.setTitle(input.next());
            System.out.println("Masukan Pengarang Baru : ");
            buku.setAuthor(input.next());
            System.out.println("Masukan ID Perpustakaan : ");
            Integer idPerpus = input.nextInt();
            Perpustakaan perpus = perpustakaanDao.findByIdPerpus(idPerpus);
            buku.setPerpus(perpus);
            //perpustakaanDao.simpanPerpustakaan(perpus);
            perpustakaanDao.simpanBuku(buku);
            System.out.print(" Update data buku kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }
//DELETE BUKU

        System.out.print(" Ingin Delete Buku ? (jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            System.out.println("==================== DELETE BUKU ====================");
            System.out.println("Masukan ID buku yang akan didelete : ");
            idBuku = input.nextInt();
            Buku buku = perpustakaanDao.findByIdBuku(idBuku);
            if (buku.isIsPinjam()) {
                System.out.println("Buku Sedang Dipinjam");
            } else {
                perpustakaanDao.deleteBuku(buku);
            }
            System.out.print(" Delete data buku kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }

//TAMPIL BUKU
        Buku buku = new Buku();
        List<Buku> listMap = perpustakaanDao.findAllBuku();
        for (Buku map : listMap) {
            if (map.isIsPinjam()) {
                System.out.println(map.toString());
            } else {
                System.out.println(map.toString1());
            }
        }

//PINJAM BUKU
        System.out.print(" Ingin Pinjam Buku ? (jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            Peminjam peminjam = new Peminjam();
            System.out.println("Masukan Peminjam : ");
            peminjam.setNama(input.next());
            System.out.println("Masukan ID Buku Yang Ingin di Pinjam : ");
            idBuku = input.nextInt();
            buku = perpustakaanDao.findByIdBuku(idBuku);
            peminjam.setBuku(buku);

            if (buku.isIsPinjam() == false) {
                perpustakaanDao.simpanPeminjam(peminjam);
                buku.setIsPinjam(true);
                perpustakaanDao.simpanBuku(buku);
            } else {
                System.out.println("Buku Tidak Tersedia");
            }
            System.out.print(" Tambah data peminjam baru kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }

//UPDATE PEMINJAM
        System.out.print(" Ingin Update Data Peminjam ? (jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            System.out.println("==================== UPDATE BUKU ====================");
            System.out.println("Masukan ID Peminjam : ");
            Integer idPeminjam = input.nextInt();
            Peminjam peminjam = perpustakaanDao.findByIdPeminjam(idPeminjam);
            System.out.println("Masukan Nama Peminjam : ");
            peminjam.setNama(input.next());
            System.out.println("Masukan ID Buku Yang Ingin di Pinjam : ");
            idBuku = input.nextInt();
            buku = perpustakaanDao.findByIdBuku(idBuku);
            peminjam.setBuku(buku);

            if (buku.isIsPinjam() == false) {
                perpustakaanDao.simpanPeminjam(peminjam);
                buku.setIsPinjam(true);
                perpustakaanDao.simpanBuku(buku);
            } else {
                System.out.println("Buku Tidak Tersedia");
            }

            System.out.print(" Ingin Mengembalikan Buku ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
            while (isTambah) {
                System.out.println("Masukan ID Buku Yang Ingin di Kembalikan : ");
                idBuku = input.nextInt();
                buku = perpustakaanDao.findByIdBuku(idBuku);
                buku.setIsPinjam(false);
                perpustakaanDao.simpanBuku(buku);
                System.out.print(" Ingin Update data peminjam kembali ?(jawab dengan true/false : ");
                isTambah = input.nextBoolean();
            }

        }

//DELETE PEMINJAM
        System.out.print(" Ingin Delete Data Peminjam ? (jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            System.out.println("==================== DELETE PEMINJAM ====================");
            System.out.println("Masukan ID peminjam yang akan didelete : ");
            Integer idPeminjam = input.nextInt();
            //ambil id buku yg sedang peminjam pinjam 
            Peminjam peminjam = perpustakaanDao.findByIdPeminjam(idPeminjam);
            peminjam.getBuku().getId();
            //set id buku yang sudah didapat menjadi false status pinjamnya
            peminjam.getBuku().setIsPinjam(false);
            perpustakaanDao.simpanPeminjam(peminjam);
            perpustakaanDao.hapusPeminjamHibernate(peminjam);

            //perpustakaanDao.deletePeminjam(peminjam);
            System.out.print(" Delete data peminjam kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }

    }

}
