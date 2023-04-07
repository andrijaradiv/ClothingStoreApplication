package clothing4you;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    void testOrderConstructor() {
        Order order = new Order("2023-04-06", "1234", "100.00");
        Assertions.assertEquals("2023-04-06", order.getDate());
        Assertions.assertEquals("1234", order.getOrderNumber());
        Assertions.assertEquals("100.00", order.getAmount());
    }

    @Test
    void testOrderGettersAndSetters() {
        Order order = new Order("2023-04-06", "1234", "100.00");
        order.setDate("2023-04-07");
        order.setOrderNumber("5678");
        order.setAmount("200.00");
        Assertions.assertEquals("2023-04-07", order.getDate());
        Assertions.assertEquals("5678", order.getOrderNumber());
        Assertions.assertEquals("200.00", order.getAmount());
    }

}
