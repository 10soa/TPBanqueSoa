/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedgComptes.java to edit this template
 */
package com.mycompany.tpbanquesoa.jsf;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import com.mycompany.tpbanquesoa.jsf.util.Util;
import com.mycompany.tpbanquesoa.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 *
 * @author Soa
 */
@Named(value = "transfertArgent")
@RequestScoped
public class TransfertArgent {

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }
    
    @Inject
    private GestionnaireCompte gComptes;
    private Long source;
    private Long destinataire;
    private int solde;

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Long destinataire) {
        this.destinataire = destinataire;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    public String transferer() {
        CompteBancaire compteSource=gComptes.findById(source);
        CompteBancaire compteDestinataire=gComptes.findById(destinataire);
         boolean erreur = false;
        if(compteSource==null){
            Util.messageErreur("(Source) Aucun compte trouvé!");
            erreur=true;
        }else if(compteDestinataire==null){
            Util.messageErreur("(Destinataire) Aucun compte trouvé!");
            erreur=true;
        }else if(solde<0){
            Util.messageErreur("Veuillez saisir un autre solde!");
            erreur=true;
        }else if(compteSource.getSolde()<solde){
            Util.messageErreur("Transaction non réussie : Solde insuffisant!");
            erreur=true;
        }
        
        if(erreur){
            return null;
        }
        gComptes.transferer(compteSource, compteDestinataire, solde);
        Util.addFlashInfoMessage(solde+" Euro : transaction de ("+compteSource.getNom()+" vers "+compteDestinataire.getNom()+")!");
        return "listeDesComptes";
    }
    
}
