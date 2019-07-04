package unit.tests.testng;

import com.google.gson.Gson;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import parser.JsonParser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;

import static org.testng.Assert.*;

public class JsonParserTest {

    private JsonParser parser;
    private Cart cart;
    private File file;
    private final Gson gson = new Gson();

    @BeforeTest
    public void setUp() {
        file = new File("src/main/resources/test-cart2.json");
        cart = new Cart("test-cart2");

        RealItem realItem = new RealItem();
        realItem.setName("Volvo");
        realItem.setPrice(1520);
        realItem.setWeight(1200);

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Vista");
        virtualItem.setPrice(1600);
        virtualItem.setSizeOnDisk(200);

        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        parser = new JsonParser();
    }

    @Test
    public void checkFileExists() {
        parser.writeToFile(cart);
        assertTrue(file.exists(), "File doesn't exist.");
    }

    @Test
    public void checkFileContent() {
        parser.writeToFile(cart);
        Cart actual_cart = parser.readFromFile(file);
        String actual = gson.toJson(actual_cart);
        String expected = "{\"cartName\":\"test-cart2\",\"realItems\":[{\"weight\":1200.0,\"name\":\"Volvo\",\"price\":1520.0}],\"virtualItems\":[{\"sizeOnDisk\":200.0,\"name\":\"Vista\",\"price\":1600.0}],\"total\":3744.0}";
        assertEquals(expected, actual, "Actual file content doesn't match expected content.");
    }

    @AfterTest
    void tearDown() {
        file.delete();
    }
}
