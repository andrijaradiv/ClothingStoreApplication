package clothing4you;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderSummaryTest {

    private OrderSummary orderSummary;
    private Catalog catalog;
    private WishlistPage wishlistPage;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        catalog = new Catalog(null);
        orderSummary = new OrderSummary(null, catalog);
        wishlistPage = new WishlistPage(null, new ArrayList<>(), catalog);
    }

    @Test
    public void testOrderSummaryNotNull() {
        assertNotNull(orderSummary);
    }

    @Test
    public void testCatalogNotNull() {
        assertNotNull(catalog);
    }

    @Test
    public void testWishlistPageNotNull() {
        assertNotNull(wishlistPage);
    }

    @Test
    public void testSubTotal() {
        double subTotal = Cart.getSubTotal();
        double expectedSubTotal = 0.0;
        for (Item item : Cart.getItems()) {
            expectedSubTotal += item.getPrice() * item.getQuantity();
        }
        assertEquals(expectedSubTotal, subTotal, 0.001);
    }

    @Test
    public void testTotal() {
        double total = Cart.getTotal();
        double expectedTotal = Cart.getSubTotal() + (Cart.getSubTotal() * Cart.getTax());
        assertEquals(expectedTotal, total, 0.001);
    }

}
