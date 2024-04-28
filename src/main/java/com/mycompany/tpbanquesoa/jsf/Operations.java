/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedgCompte.java to edit this template
 */
package com.mycompany.tpbanquesoa.jsf;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import com.mycompany.tpbanquesoa.entities.OperationBancaire;
import com.mycompany.tpbanquesoa.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Soa
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    @Inject
    private GestionnaireCompte gCompte;

    private Long idCompte;
    private CompteBancaire compte;
    private List<OperationBancaire> operations;
    
    public String getDateFormat(LocalDateTime dt) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dt.format(format);
    }
    
    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long id) {
        idCompte = id;
    }

    public void loadCompte() {
        compte = gCompte.findById(idCompte);
    }

    public CompteBancaire getCompteBancaire() {
        return (CompteBancaire) gCompte.findById(idCompte);
    }

    public List<OperationBancaire> getOperations() {
        operations = compte.getOperations();
        return operations;
    }

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }
}
