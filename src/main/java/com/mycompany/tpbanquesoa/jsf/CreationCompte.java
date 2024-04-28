/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanquesoa.jsf;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import com.mycompany.tpbanquesoa.jsf.util.Util;
import com.mycompany.tpbanquesoa.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;



/**
 *
 * @author Soa
 */
@Named(value = "creationCompte")
@RequestScoped
public class CreationCompte {
    
    @Inject
    private GestionnaireCompte gComptes;
    private String nom;
    @PositiveOrZero
    private int solde;
    
    /**
     * Creates a new instance of CreationCompte
     */
    public CreationCompte() {
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
    
    public String ajouterNouveauCompte(){
        CompteBancaire compte=new CompteBancaire(this.nom,this.solde);
        gComptes.creerCompte(compte);
        Util.addFlashInfoMessage("Compte de ("+this.nom+") ajouté avec succés!");
        return "listeDesComptes?faces-redirect=true";
    }
}
