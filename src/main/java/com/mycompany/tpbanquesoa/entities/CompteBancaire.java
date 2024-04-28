/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpbanquesoa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soa
 */
@Entity
@Table(name = "comptebancaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT DISTINCT compte FROM CompteBancaire compte LEFT JOIN FETCH compte.operations"),
    @NamedQuery(name = "CompteBancaire.findByCompteBancaireId", query = "SELECT compte FROM CompteBancaire compte WHERE compte.id = :id")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOM")
    @Size(max = 30)
    private String nom;
    @Column(name = "SOLDE")
    private int solde;
    @Version
    private int version;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
    private List<OperationBancaire> operations = new ArrayList<>();

    public CompteBancaire() {
    }
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        operations.add(new OperationBancaire("Création du compte",solde));
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void deposer(int montant) {
        solde += montant;
        operations.add(new OperationBancaire("Crédit",montant));
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
        } else {
            solde = 0;
        }
        operations.add(new OperationBancaire("Débit",-montant));
    }
    
    public List<OperationBancaire> getOperations() {  
      return operations;  
    } 
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tpbancaire.entity.CompteBancaire[ id=" + id + " ]";
    }

}
