package unit.tests.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import shop.VirtualItem;

import static org.testng.Assert.assertTrue;

public class VirtualItemTest {

    private VirtualItem virtualItem;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        virtualItem = new VirtualItem();
        virtualItem.setName("Vista");
        virtualItem.setSizeOnDisk(1600);
        virtualItem.setPrice(200);
    }

    @AfterTest(alwaysRun = true)
    void tearDown() {    }

    @Test(groups = {"virtual_item_tests"})
    public void checkVirtualItem() {
        String toString = virtualItem.toString();
        assertTrue(toString.contains("Size on disk: " + virtualItem.getSizeOnDisk()), "toString() method failed.");
    }
}
