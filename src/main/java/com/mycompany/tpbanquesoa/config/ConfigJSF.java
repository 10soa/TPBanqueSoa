/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanquesoa.config;

import jakarta.inject.Named;
import jakarta.faces.annotation.FacesConfig;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Soa
 */
@FacesConfig
@Named(value = "configJSF")
@ApplicationScoped
public class ConfigJSF {

    /**
     * Creates a new instance of ConfigJSF
     */
    public ConfigJSF() {
    }
    
}
