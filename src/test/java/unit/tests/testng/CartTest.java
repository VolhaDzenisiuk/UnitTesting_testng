package unit.tests.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;

import static org.testng.Assert.assertEquals;

public class CartTest {

    private Cart cart;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        cart = new Cart("test-cart");
        RealItem realItem1 = new RealItem();
        RealItem realItem2 = new RealItem();

        realItem1.setPrice(12);
        realItem2.setPrice(15);

        cart.addRealItem(realItem1);
        cart.addRealItem(realItem2);
    }

    @AfterTest(alwaysRun = true)
    void tearDown() {    }

    @Test(groups = {"cart_tests"})
    public void checkTotalPrice() {
        assertEquals(32.4, cart.getTotalPrice(), "Cart total price is incorrect.");
    }
}
