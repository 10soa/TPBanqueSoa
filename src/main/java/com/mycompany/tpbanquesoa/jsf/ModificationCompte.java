/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanquesoa.jsf;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import com.mycompany.tpbanquesoa.jsf.util.Util;
import com.mycompany.tpbanquesoa.service.GestionnaireCompte;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author Soa
 */
@Named(value = "modificationCompte")
@ViewScoped
public class ModificationCompte implements Serializable {

    @Inject
    private GestionnaireCompte gComptes;
    private Long id;
    private String nom;
    private CompteBancaire compte;

    /**
     * Creates a new instance of ModificationCompte
     */
    public ModificationCompte() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String modifier() {  
        gComptes.modificationCompte(compte, nom);
        Util.addFlashInfoMessage("Modification du compte terminé!");
        return "listeDesComptes?faces-redirect=true";
    }

    public void loadCompte() {
        this.compte = gComptes.findById(id);
    }
}
