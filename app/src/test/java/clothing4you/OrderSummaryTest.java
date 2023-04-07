package clothing4you;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertNull;

public class OrderSummaryTest {

    private OrderSummary orderSummary;
    private Catalog catalog;
    private WishlistPage wishlistPage;



    @Test
    public void testCatalogNotNull() {
        assertNull(catalog);
    }

    @Test
    public void testWishlistPageNotNull() {
        assertNull(wishlistPage);
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
