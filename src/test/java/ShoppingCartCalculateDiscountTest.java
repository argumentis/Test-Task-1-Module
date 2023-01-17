import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class ShoppingCartCalculateDiscountTest {
    private Pair<Item.Type, Integer> itemParams;
    private int expectedDiscount;

    public ShoppingCartCalculateDiscountTest(Pair<Item.Type, Integer> itemParams, int expectedDiscount) {
        this.itemParams = itemParams;
        this.expectedDiscount = expectedDiscount;
    }

    @Parameterized.Parameters
    public static Collection parameters() {
        return Arrays.asList(new Object[][]{
                {new Pair<>(Item.Type.REGULAR, 1), 0},
                {new Pair<>(Item.Type.REGULAR, 99), 0},
                {new Pair<>(Item.Type.REGULAR, 100), 10},
                {new Pair<>(Item.Type.REGULAR, 150), 10},
                {new Pair<>(Item.Type.REGULAR, 250), 20},
                {new Pair<>(Item.Type.REGULAR, 800), 80},
                {new Pair<>(Item.Type.REGULAR, 900), 80},
                {new Pair<>(Item.Type.SECOND, 1), 0},
                {new Pair<>(Item.Type.SECOND, 2), 50},
                {new Pair<>(Item.Type.SECOND, 100), 60},
                {new Pair<>(Item.Type.SECOND, 350), 80},
                {new Pair<>(Item.Type.DISCOUNT, 1), 10},
                {new Pair<>(Item.Type.DISCOUNT, 9), 10},
                {new Pair<>(Item.Type.DISCOUNT, 10), 20},
                {new Pair<>(Item.Type.DISCOUNT, 11), 20},
                {new Pair<>(Item.Type.DISCOUNT, 40), 50},
                {new Pair<>(Item.Type.DISCOUNT, 100), 60},
                {new Pair<>(Item.Type.DISCOUNT, 300), 80},
                {new Pair<>(Item.Type.DISCOUNT, 400), 80},
                {new Pair<>(Item.Type.SALE, 1), 80},
                {new Pair<>(Item.Type.SALE, 10), 80},
                {new Pair<>(Item.Type.SALE, 100), 80}
        });
    }

    @Test
    public void calculateRegularDiscount() {
        System.out.println("Items Parameters : " + itemParams);
        assertEquals(expectedDiscount,
                ShoppingCart.calculateDiscount(new Item("Regular", 10, itemParams.getKey(), itemParams.getValue())));
    }
}