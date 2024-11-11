import group3.business.Product;
import group3.business.ProductIO;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProductUnitTest {
    
 private static final String TEST_FILE_PATH = "test_products.txt";
    private Product testProduct;

    @BeforeAll
    static void setUpClass() {
        ProductIO.init(TEST_FILE_PATH);
    }

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setCode("TST01");
        testProduct.setDescription("ArtistName - AlbumName");
        testProduct.setPrice(19.99);

        // clears test file before each test
        clearTestFile();
    }

    @AfterEach
    void tearDown() {
        clearTestFile();
    }

    @Test
    void testInsertProduct() {
        ProductIO.insertProduct(testProduct);
        List<Product> products = ProductIO.selectProducts();
        assertEquals(1, products.size(), "Product list should contain one product.");
        assertEquals(testProduct.getCode(), products.get(0).getCode(), "Inserted product code should match.");
    }

    @Test
    void testSelectProduct() {
        ProductIO.insertProduct(testProduct);
        Product retrievedProduct = ProductIO.selectProduct("TST01");
        assertNotNull(retrievedProduct, "Product should be found.");
        assertEquals(testProduct.getDescription(), retrievedProduct.getDescription(), "Descriptions should match.");
    }

    @Test
    void testSelectProducts() {
        ProductIO.insertProduct(testProduct);
        List<Product> products = ProductIO.selectProducts();
        assertFalse(products.isEmpty(), "Product list should not be empty.");
    }

    @Test
    void testExists() {
        ProductIO.insertProduct(testProduct);
        assertTrue(ProductIO.exists("TST01"), "Product should exist.");
        assertFalse(ProductIO.exists("NON_EXISTENT_CODE"), "Product should not exist.");
    }

    @Test
    void testUpdateProduct() {
        ProductIO.insertProduct(testProduct);
        Product updatedProduct = new Product();
        updatedProduct.setCode("TST01");
        updatedProduct.setDescription("Updated Artist - Updated Album");
        updatedProduct.setPrice(29.99);
        ProductIO.updateProduct(updatedProduct);

        Product retrievedProduct = ProductIO.selectProduct("TST01");
        assertNotNull(retrievedProduct, "Updated product should be found.");
        assertEquals("Updated Artist - Updated Album", retrievedProduct.getDescription(), "Description should be updated.");
        assertEquals(29.99, retrievedProduct.getPrice(), "Price should be updated.");
    }

    @Test
    void testDeleteProduct() {
        ProductIO.insertProduct(testProduct);
        ProductIO.deleteProduct(testProduct);

        assertNull(ProductIO.selectProduct("TST01"), "Deleted product should not be found.");
        assertFalse(ProductIO.exists("TST01"), "Deleted product should not exist.");
    }

    // Unit Tests for Product Class Methods

    @Test
    void testGetArtistName() {
        testProduct.setDescription("ArtistName - AlbumName");
        assertEquals("ArtistName", testProduct.getArtistName(), "Artist name should be 'ArtistName'.");
    }

    @Test
    void testGetAlbumName() {
        testProduct.setDescription("ArtistName - AlbumName");
        assertEquals("AlbumName", testProduct.getAlbumName(), "Album name should be 'AlbumName'.");
    }

    @Test
    void testGetPriceCurrencyFormat() {
        testProduct.setPrice(19.99);
        String formattedPrice = NumberFormat.getCurrencyInstance().format(19.99);
        assertEquals(formattedPrice, testProduct.getPriceCurrencyFormat(), "Price should be formatted correctly as currency.");
    }

    @Test
    void testGetImageURL() {
        testProduct.setCode("TST01");
        String expectedURL = "/musicStore/images/TST01_cover.jpg";
        assertEquals(expectedURL, testProduct.getImageURL(), "Image URL should match expected format.");
    }

    @Test
    void testGetProductType() {
        assertEquals("Audio CD", testProduct.getProductType(), "Product type should be 'Audio CD'.");
    }

    private static void clearTestFile() {
        try {
            new PrintWriter(TEST_FILE_PATH).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
