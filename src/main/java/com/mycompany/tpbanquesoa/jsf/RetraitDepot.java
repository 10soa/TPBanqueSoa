/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanquesoa.jsf;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import com.mycompany.tpbanquesoa.jsf.util.Util;
import com.mycompany.tpbanquesoa.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author Soa
 */
@Named(value = "retraitDepot")
@ViewScoped
public class RetraitDepot implements Serializable {

    @Inject
    private GestionnaireCompte gComptes;
    private Long id;
    private String action;
    private int solde;
    private CompteBancaire compte;

    /**
     * Creates a new instance of RetraitDepot
     */
    public RetraitDepot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String confirmer() {
        boolean erreur = false;
        if (solde <= 0) {
            Util.messageErreur("Entrer un solde valide!");
            erreur = true;
        } else {
            //action1: retrait
            //action2: dépôt
            if ("action1".equals(action)) {
                if (compte.getSolde() < solde) {
                    Util.messageErreur("Impossible de faire cette action : Fonds insuffisant!");
                    erreur = true;
                } else {
                    gComptes.retrait(compte, solde);
                    Util.addFlashInfoMessage("Retrait de " + solde + "Euro effectué! (" + compte.getNom() + ")");
                }
            } else if ("action2".equals(action)) {
                gComptes.depot(compte, solde);
                Util.addFlashInfoMessage("Dépôt de " + solde + "Euro effectué! (" + compte.getNom() + ")");
            } else {
                Util.messageErreur("Erreur du traitement de votre demande!");
                erreur = true;
            }
        }

        if (erreur) {
            return null;
        }
        return "listeDesComptes?faces-redirect=true";
    }
    
    public void loadCompte() {
        this.compte = gComptes.findById(id);
    }

}
