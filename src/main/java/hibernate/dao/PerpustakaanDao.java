/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao;

import hibernate.model.Buku;
import hibernate.model.Peminjam;
import java.util.List;
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

    @Transactional(readOnly = false)
    public void simpanPeminjam(Peminjam peminjam) {
        sessionFactory.getCurrentSession().saveOrUpdate(peminjam);
    }
//BUKU   

    @Transactional(readOnly = true)
    public List<Buku> findAllBuku() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Buku");
        return query.list();
    }

    @Transactional(readOnly = false)
    public void simpanBuku(Buku buku) {
        sessionFactory.getCurrentSession().saveOrUpdate(buku);
    }

    @Transactional(readOnly = true)
    public Buku findByIdBuku(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("FROM Buku b WHERE b.id = :id ")
                .setParameter("id", id);
        return (Buku) query.list().get(0);
    }

}
