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

    @BeforeTest(groups = {"json_parser_tests"})
    @Parameters({"realItemName", "realItemPrice", "realItemWeight", "virtualItemName", "virtualItemPrice", "virtualItemSize"})
    public void setUp(String realItemName, String realItemPrice, String realItemWeight, String virtualItemName, String virtualItemPrice, String virtualItemSize) {

        file = new File("src/main/resources/test-cart2.json");
        cart = new Cart("test-cart2");

        RealItem realItem = new RealItem();
        realItem.setName(realItemName);
        realItem.setPrice(Double.parseDouble(realItemPrice));
        realItem.setWeight(Double.parseDouble(realItemWeight));

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(virtualItemName);
        virtualItem.setPrice(Double.parseDouble(virtualItemPrice));
        virtualItem.setSizeOnDisk(Double.parseDouble(virtualItemSize));

        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        parser = new JsonParser();
    }

    @Test(enabled=false, groups = {"json_parser_tests", "disabled_tests"})
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

    @Test(expectedExceptions = NoSuchFileException.class, dataProvider = "file-list", groups = {"json_parser_tests"})
    public void checkNoSuchFileExceptionWithDataSet(String file) throws NoSuchFileException {
        parser.readFromFile(new File("src/main/resources/" + file));
    }

    @AfterTest(groups = {"json_parser_tests"})
    void tearDown() {
        file.delete();
    }
}
