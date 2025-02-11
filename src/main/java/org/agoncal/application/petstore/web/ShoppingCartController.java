package org.agoncal.application.petstore.web;

import org.agoncal.application.petstore.domain.*;
import org.agoncal.application.petstore.service.CatalogService;
import org.agoncal.application.petstore.service.OrderService;
import org.agoncal.application.petstore.util.Loggable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Named
@ConversationScoped
@Loggable
@CatchException
public class ShoppingCartController extends Controller implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    @LoggedIn
    private Instance<Customer> loggedInCustomer;
    @Inject
    private CatalogService catalogBean;
    @Inject
    private OrderService orderBean;
    @Inject
    @Getter private Conversation conversation;

    private List<CartItem> cartItems;
    @Getter @Setter private CreditCard creditCard = new CreditCard();
    @Getter @Setter private Order order;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public String addItemToCart() {
        Item item = catalogBean.findItem(getParamId("itemId"));

        // Start conversation
        if (conversation.isTransient()) {
            cartItems = new ArrayList<CartItem>();
            conversation.begin();
        }

        boolean itemFound = false;
        for (CartItem cartItem : cartItems) {
            // If item already exists in the shopping cart we just change the quantity
            if (cartItem.getItem().equals(item)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                itemFound = true;
            }
        }
        if (!itemFound)
            // Otherwise it's added to the shopping cart
            cartItems.add(new CartItem(item, 1));

        return "showcart.faces";
    }

    public String removeItemFromCart() {
        Item item = catalogBean.findItem(getParamId("itemId"));

        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().equals(item)) {
                cartItems.remove(cartItem);
                return null;
            }
        }

        return null;
    }

    public String updateQuantity() {
        return null;
    }

    public String checkout() {
        return "confirmorder.faces";
    }

    public String confirmOrder() {
        order = orderBean.createOrder(getCustomer(), creditCard, getCartItems());
        cartItems.clear();

        // Stop conversation
        if (!conversation.isTransient()) {
            conversation.end();
        }

        return "orderconfirmed.faces";
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public boolean shoppingCartIsEmpty() {
        return getCartItems() == null || getCartItems().size() == 0;
    }


    public Float getTotal() {

        if (cartItems == null || cartItems.isEmpty())
            return 0f;

        Float total = 0f;

        // Sum up the quantities
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getSubTotal());
        }
        return total;
    }

    // ======================================
    // =         Getters & setters          =
    // ======================================

    public Customer getCustomer() {
        return loggedInCustomer.get();
    }

    public CreditCardType[] getCreditCardTypes() {
        return CreditCardType.values();
    }
}