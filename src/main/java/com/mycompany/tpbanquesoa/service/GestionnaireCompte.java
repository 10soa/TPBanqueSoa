/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanquesoa.service;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

/**
 *
 * @author Soa
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "root",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)

@Named(value = "gestionnaireCompte")
@ApplicationScoped
public class GestionnaireCompte implements Serializable {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     * Creates a new instance of GestionnaireCompte
     */
    public GestionnaireCompte() {
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    @Transactional
    public void creerCompte(CompteBancaire compte) {
        em.persist(compte);
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    public CompteBancaire findById(Long idCompte) {
        return em.find(CompteBancaire.class, idCompte);
    }

    @Transactional
    public void retrait(CompteBancaire source, int montant) {
        source.retirer(montant);
        update(source);
    }

    @Transactional
    public void depot(CompteBancaire source, int montant) {
        source.deposer(montant);
        update(source);
    }

    @Transactional
    public void modificationCompte(CompteBancaire compte, String nom) {
        compte.setNom(nom);
        update(compte);
    }
}
