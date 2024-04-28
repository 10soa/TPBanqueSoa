/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanquesoa.config;

import com.mycompany.tpbanquesoa.entities.CompteBancaire;
import com.mycompany.tpbanquesoa.service.GestionnaireCompte;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Soa
 */
@Named(value = "initBean")
@ApplicationScoped
public class InitBean implements Serializable{
    
    @Inject
    private GestionnaireCompte gCompte;
    
    /**
     * Creates a new instance of InitBean
     */
    public InitBean() {
    }
    
    
    /**
     *
     * @param init
     */
    @PostConstruct
    @Transactional
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init){
        CompteBancaire[] rep=new CompteBancaire[4];
        rep[0] = new CompteBancaire("John Lennon",150000);
        rep[1] = new CompteBancaire("Paul McCartney",950000);
        rep[2] = new CompteBancaire("Ringo Starr",20000);
        rep[3] = new CompteBancaire("Georges Harrisson",100000);
        if(gCompte.nbComptes()<=0){
            for (CompteBancaire compte : rep) {
                gCompte.creerCompte(compte);
            }
        }
    }
    
}
