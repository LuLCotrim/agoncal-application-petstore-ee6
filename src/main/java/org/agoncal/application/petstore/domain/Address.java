package org.agoncal.application.petstore.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Embeddable
@EqualsAndHashCode
public class Address {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Column(nullable = false)
    @NotNull
    @Size(min = 5, max = 50)
    @Getter @Setter private String street1;
    @Getter @Setter private String street2;
    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 2, max = 50)
    @Getter @Setter private String city;
    @Getter @Setter private String state;
    @Column(name = "zip_code", nullable = false, length = 10)
    @NotNull
    @Size(min = 1, max = 10)
    @Getter @Setter private String zipcode;
    @Column(nullable = false, length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    @Getter @Setter private String country; // TODO use an enum

    // ======================================
    // =            Constructors            =
    // ======================================

    public Address() {
    }

    public Address(String street1, String city, String zipcode, String country) {
        this.street1 = street1;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    // ======================================
    // =   Methods hash, equals, toString   =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Address");
        sb.append("{street1='").append(street1).append('\'');
        sb.append(", street2='").append(street2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
