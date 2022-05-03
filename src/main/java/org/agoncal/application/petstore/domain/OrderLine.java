package org.agoncal.application.petstore.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Entity
@EqualsAndHashCode
public class OrderLine {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Column(nullable = false)
    @Getter @Setter private Integer quantity;
    @OneToOne
    @JoinColumn(name = "item_fk", nullable = false)
    @Getter @Setter private Item item;

    // ======================================
    // =            Constructors            =
    // ======================================

    public OrderLine() {
    }

    public OrderLine(Integer quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
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
        sb.append("OrderLine");
        sb.append("{id=").append(id);
        sb.append(", quantity=").append(quantity);
        sb.append(", item=").append(item);
        sb.append('}');
        return sb.toString();
    }
}
