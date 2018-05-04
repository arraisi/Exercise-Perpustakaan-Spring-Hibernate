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
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author arrai
 */
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
//SIMPAN BUKU DAN PEMINJAM
        System.out.print(" Tambah data buku baru kembali ?(jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            Buku buku = new Buku();
            System.out.println("Masukan Judul Buku : ");
            buku.setTitle(input.next());
            System.out.println("Masukan Pengarang : ");
            buku.setAuthor(input.next());
            buku.setIsPinjam(false);
            perpustakaanDao.simpanBuku(buku);
            System.out.print(" Tambah data baru kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }
//UPDATE BUKU DAN PEMINJAM
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
            perpustakaanDao.simpanBuku(buku);
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
            System.out.print(" Tambah data baru kembali ?(jawab dengan true/false : ");
            isTambah = input.nextBoolean();
        }

//UPDATE PEMINJAM
        System.out.print(" Ingin Update Buku ? (jawab dengan true/false : ");
        isTambah = input.nextBoolean();
        while (isTambah) {
            System.out.println("==================== UPDATE BUKU ====================");
            System.out.println("Masukan ID Peminjam : ");
            Integer idPeminjam = input.nextInt();
            //buku = perpustakaanDao.findByIdBuku(idPeminjam);
            System.out.println("Masukan Judul Baru : ");
            buku.setTitle(input.next());
            System.out.println("Masukan Pengarang Baru : ");
            buku.setAuthor(input.next());
            perpustakaanDao.simpanBuku(buku);
        }

    }

}
