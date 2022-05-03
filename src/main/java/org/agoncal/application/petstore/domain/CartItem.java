package org.agoncal.application.petstore.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@EqualsAndHashCode
public class CartItem {

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    @Getter @Setter private Item item;
    @NotNull
    @Min(1)
    @Getter @Setter private Integer quantity;

    // ======================================
    // =            Constructors            =
    // ======================================

    public CartItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // ======================================
    // =              Public Methods        =
    // ======================================

    public Float getSubTotal() {
        return item.getUnitCost() * quantity;
    }

    // ======================================
    // =   Methods hash, equals, toString   =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CartItem");
        sb.append("{item='").append(item).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}