package unit.tests.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import shop.VirtualItem;

class VirtualItemTest {

    private VirtualItem virtualItem;

    @BeforeTest
    public void setUp() {
        virtualItem = new VirtualItem();
        virtualItem.setName("Vista");
        virtualItem.setSizeOnDisk(1600);
        virtualItem.setPrice(200);
    }

    @Test(groups = {"virtual_item_tests"})
    public void checkToStringMethod() {
        String toString = virtualItem.toString();
        assertTrue(toString.contains("Size on disk: " + virtualItem.getSizeOnDisk()), "toString() method failed.");
    }

    @AfterTest
    void tearDown() {    }
}
