/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao;

import hibernate.model.Buku;
import hibernate.model.Peminjam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PerpustakaanDao {

    @Autowired
    private SessionFactory sessionFactory;

//PEMINJAM
    @Transactional(readOnly = true)
    public List<Peminjam> findAllPeminjam() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Peminjam");
        return query.list();
    }
//SAVE OR UPDATE PEMINJAM

    @Transactional(readOnly = false)
    public void simpanPeminjam(Peminjam peminjam) {
        sessionFactory.getCurrentSession().saveOrUpdate(peminjam);
    }
//FIND BY ID PEMINJAM    

    @Transactional(readOnly = true)
    public Peminjam findByIdPeminjam(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("FROM Peminjam p WHERE p.id = :id ")
                .setParameter("id", id);
        return (Peminjam) query.list().get(0);
    }
//DELETE PEMINJAM    

    @Transactional(readOnly = false)
    public void deletePeminjam(Peminjam peminjam) {
        sessionFactory.getCurrentSession().delete(peminjam);
    }

//BUKU   
//FIND ALL BUKU
    @Transactional(readOnly = true)
    public List<Buku> findAllBuku() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Buku");
        return query.list();
    }
//SAVE OR UPDATE BUKU

    @Transactional(readOnly = false)
    public void simpanBuku(Buku buku) {
        sessionFactory.getCurrentSession().saveOrUpdate(buku);
    }
//DELETE BUKU    

    @Transactional(readOnly = false)
    public void deleteBuku(Buku buku) {
        sessionFactory.getCurrentSession().delete(buku);
    }
//FIND BY ID BUKU

    @Transactional(readOnly = true)
    public Buku findByIdBuku(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("FROM Buku b WHERE b.id = :id ")
                .setParameter("id", id);
        return (Buku) query.list().get(0);
    }
/*
    @Transactional(readOnly = false)
    public void hapusPeminjamHibernate(Perpustakaan perpus) {
        sessionFactory.getCurrentSession().createQuery("delete from Book a where a.perpustakaan.idPerpustakaan = :id")
                .setParameter("id", perpus.IdPerpustakaan()).executeUpdate();
        sessionFactory.getCurrentSession().delete(perpus);
*/
    @Transactional(readOnly = false)
    public void hapusPeminjamHibernate(Peminjam peminjam) {
        sessionFactory.getCurrentSession().createQuery("delete from Peminjam p where p.buku.id = :id")
                .setParameter("id", peminjam.getId()).executeUpdate();
        sessionFactory.getCurrentSession().delete(peminjam);
        
       
    }

}
