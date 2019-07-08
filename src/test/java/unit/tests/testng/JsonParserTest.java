package unit.tests.testng;

import com.google.gson.Gson;
import org.testng.annotations.*;
import parser.*;
import shop.*;

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

    @Test(groups = {"json_parser_tests"})
    public void checkFileExists() {
        parser.writeToFile(cart);
        assertTrue(file.exists(), "File doesn't exist.");
    }

    @Test(groups = {"json_parser_tests"})
    public void checkFileContent() {
        parser.writeToFile(cart);
        Cart actual_cart = parser.readFromFile(file);
        String actual = gson.toJson(actual_cart);
        String expected = "{\"cartName\":\"test-cart2\",\"realItems\":[{\"weight\":1200.0,\"name\":\"Volvo\",\"price\":1520.0}],\"virtualItems\":[{\"sizeOnDisk\":200.0,\"name\":\"Vista\",\"price\":1600.0}],\"total\":3744.0}";
        assertEquals(expected, actual, "Actual file content doesn't match expected content.");
    }

    @DataProvider(name = "file-list")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "testFile1.json"}, {"testFile2.json"}, {"testFile3.json"}, {"testFile4.json"}, {"testFile5.json"} };
    }

    @Test(dataProvider = "file-list", groups = {"json_parser_tests"})
    public void checkNoSuchFileException(String filename) {
        assertThrows(NoSuchFileException.class, () -> parser.readFromFile(new File("src/main/resources/" + filename)));
    }

    @Test(enabled=false, groups = {"json_parser_tests", "disabled_tests"})
    public void disabled_test() {    }

    @AfterTest
    void tearDown() {
        file.delete();
    }
}
