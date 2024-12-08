/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3.business;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author albertosoto
 */
public class ProductIOTest {

    private static final String TEST_FILE_PATH = "test_products.txt";
    private static final String PRODUCT_CODE = "P001";
    private static final String PRODUCT_DESCRIPTION = "Test Product";
    private static final double PRODUCT_PRICE = 19.99;
    
    @BeforeAll
    public static void setup() {
        // Initialize ProductIO with a test file path
        ProductIO.init(TEST_FILE_PATH);
    }

    @BeforeEach
    public void createTestFile() throws IOException {
        // Create a temporary file for each test method
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.createNewFile();
        
        // Adding a sample product to the file for testing purposes
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.println(PRODUCT_CODE + "|" + PRODUCT_DESCRIPTION + "|" + PRODUCT_PRICE);
        }
    }

    @AfterEach
    public void cleanUp() {
        // Clean up the test file after each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testSelectProducts() {
        List<Product> products = ProductIO.selectProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(PRODUCT_CODE, products.get(0).getCode());
    }

    @Test
    public void testSelectProduct() {
        Product product = ProductIO.selectProduct(PRODUCT_CODE);
        assertNotNull(product);
        assertEquals(PRODUCT_CODE, product.getCode());
        assertEquals(PRODUCT_DESCRIPTION, product.getDescription());
        assertEquals(PRODUCT_PRICE, product.getPrice());
    }

    @Test
    public void testInsertProduct() {
        Product newProduct = new Product();
        newProduct.setCode("P002");
        newProduct.setDescription("New Product");
        newProduct.setPrice(29.99);

        ProductIO.insertProduct(newProduct);

        // Verify the new product is inserted
        Product product = ProductIO.selectProduct("P002");
        assertNotNull(product);
        assertEquals("P002", product.getCode());
        assertEquals("New Product", product.getDescription());
        assertEquals(29.99, product.getPrice());
    }

    @Test
    public void testUpdateProduct() {
        Product product = ProductIO.selectProduct(PRODUCT_CODE);
        product.setDescription("Updated Product");
        product.setPrice(25.99);

        ProductIO.updateProduct(product);

        // Verify that the product was updated
        Product updatedProduct = ProductIO.selectProduct(PRODUCT_CODE);
        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getDescription());
        assertEquals(25.99, updatedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = ProductIO.selectProduct(PRODUCT_CODE);
        ProductIO.deleteProduct(product);

        // Verify that the product was deleted
        Product deletedProduct = ProductIO.selectProduct(PRODUCT_CODE);
        assertNull(deletedProduct);
    }
}
