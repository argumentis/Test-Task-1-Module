import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartAddItemTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void testAddItemTitle() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("", 1, 1, Item.Type.REGULAR),
                "Empty title is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem(null, 1, 1, Item.Type.REGULAR),
                "Title with null is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Title With 33 Characters ... ....", 1, 1, Item.Type.REGULAR),
                "Title with 33 characters is failed");
        assertDoesNotThrow(() -> cart.addItem("1", 1, 1, Item.Type.REGULAR),
                "Title with 1 characters is not failed");
        assertDoesNotThrow(
                () -> cart.addItem("Title With 32 Characters ... ...", 1, 1, Item.Type.REGULAR),
                "Title with 32 characters is not failed");
    }

    @Test
    void testAddItemPrice() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("0 Price", 0, 1, Item.Type.REGULAR),
                "Test 0 price item is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("1000 Price", 1000, 1, Item.Type.REGULAR),
                "Test 1000 price item is failed");
        assertDoesNotThrow(
                () -> cart.addItem("1 Price", 1, 1, Item.Type.REGULAR),
                "Test 1 price item is not failed");
        assertDoesNotThrow(
                () -> cart.addItem("999 Price", 999, 1, Item.Type.REGULAR),
                "Test 999 price item is not failed");
    }

    @Test
    void testAddItemQuantity() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(Exception.class,
                () -> cart.addItem("0 Quantity", 1, 0, Item.Type.REGULAR),
                "Test 0 quantity item is failed");
        assertThrows(Exception.class,
                () -> cart.addItem("1001 Quantity", 1, 1001, Item.Type.REGULAR),
                "Test 1001 quantity item is failed");
        assertDoesNotThrow(
                () -> cart.addItem("1 Quantity", 1, 1, Item.Type.REGULAR),
                "Test 1 quantity item is not failed");
        assertDoesNotThrow(
                () -> cart.addItem("1000 Quantity", 1, 1000, Item.Type.REGULAR),
                "Test 1000 quantity item is not failed");
    }

    @Test
    void testAddHundredItems() {
        ShoppingCart cart = new ShoppingCart();
        for (int i = 1; i < 100; i++) {
            cart.addItem("Item " + i, 1, 1, Item.Type.REGULAR);
        }
        assertThrows(IndexOutOfBoundsException.class,
                () -> cart.addItem("Item 100", 1, 1, Item.Type.REGULAR),
                "Adding of 100th item is failed");
    }
}