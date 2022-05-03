package org.agoncal.application.petstore.web;

import org.agoncal.application.petstore.util.Loggable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import lombok.AccessLevel;
import lombok.Getter;

@Named
@SessionScoped
@Loggable
public class LocaleBean implements Serializable {

    @Produces
    @Getter private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    // ======================================
    // =          Business methods          =
    // ======================================

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}