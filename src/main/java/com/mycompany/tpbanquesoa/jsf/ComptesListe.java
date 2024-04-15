/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanquesoa.jsf;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import com.mycompany.tpbanquesoa.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Soa
 */
@Named(value = "comptesListe")
@ViewScoped
public class ComptesListe implements Serializable {

    /**
     * Creates a new instance of ComptesListe
     */
    public ComptesListe() {
    }
    
    @Inject
    private GestionnaireCompte bean;
    private List<CompteBancaire> compte; 
   
    public List<CompteBancaire> getAllComptes() {
        if (compte == null) {
            compte= bean.getAllComptes();
        }
        return compte;
    }
    
}
