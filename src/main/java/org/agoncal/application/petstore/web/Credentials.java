package org.agoncal.application.petstore.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Named
@RequestScoped
public class Credentials {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String password2;
}
