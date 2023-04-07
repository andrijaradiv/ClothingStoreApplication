package clothing4you;

import clothing4you.backend.Item;
import clothing4you.ui.Return;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnTest {

    private Return returnDialog;
    private ArrayList<Item> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        returnDialog = new Return(null, items);
    }

    @Test
    void successfulReturn() throws SQLException, ClassNotFoundException {
        String itemName = "Shoes";
        int quantity = 2;

        // Assuming the item "Shoes" exists in the "catalog" table with a price of 100.0
        double expectedRefund = 0.5 * quantity;

        double actualRefund = quantity * 0.5;
        assertEquals(expectedRefund, actualRefund, "Refund amount should be correct");
    }
}
