package unit.tests.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.RealItem;

import static org.testng.Assert.assertEquals;

public class RealItemTest {

    private RealItem realitem;
    private SoftAssert softAssertion;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        softAssertion = new SoftAssert();
        realitem = new RealItem();
        realitem.setName("Volvo");
        realitem.setPrice(1520);
        realitem.setWeight(1200);
    }

    @AfterTest(alwaysRun = true)
    void tearDown() {    }

    @Test(groups = {"real_item_tests"})
    public void checkRealItem() {
        assertEquals("Volvo", realitem.getName(), "Real item name is incorrect.");
        assertEquals(1520.0, realitem.getPrice(), "Real item price is incorrect.");
        assertEquals(1200.0, realitem.getWeight(), "Real item weight is incorrect.");

        softAssertion.assertAll();
    }
}
