package project2;

import org.easymock.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project2.Controllers.WebShopController;
import project2.Interfaces.IDbContext;
import project2.Models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class EasyMockTest extends EasyMockSupport
{
    private final String PRODUCT_NAME = "Gitara";
    private final String PRODUCT_NAME2 = "Perkusja";
    private final String PRODUCT_NAME3 = "Bass";
    private final String PRODUCT_NAME4 = "Mikrofon";

    private final double PRODUCT_PRICE = 1299.99;
    private final double PRODUCT_PRICE2 = 4679.49;
    private final double PRODUCT_PRICE3 = 1375.50;
    private final double PRODUCT_PRICE4 = 349.79;

    private final double NEW_PRICE = 799.99;

    private Product product;

    @Mock
    private IDbContext dbMock;

    @TestSubject
    private WebShopController webShopController;

    @BeforeEach void SetUp()
    {
        dbMock = createMock(IDbContext.class);
        webShopController = new WebShopController(dbMock);
        product = new Product(PRODUCT_NAME, PRODUCT_PRICE);
    }

    @Test void CheckAddProduct_ShouldAddNewProduct ()
    {
        expect(dbMock.AddProduct(product)).andReturn(true);
        replay(dbMock);

        webShopController.AddProduct(product);
        verify(dbMock);
    }

    @Test void CheckAddProduct_ShouldNotAddNewProduct ()
    {
        expect(dbMock.AddProduct(product)).andReturn(false);
        replay(dbMock);

        webShopController.AddProduct(product);
        verify(dbMock);
    }

    @Test void CheckAddWronglyProduct_ShouldThrownException ()
    {
        expect(dbMock.AddProduct(product)).andThrow(new IllegalArgumentException("Get out!"));
        replay(dbMock);

        assertThrows(IllegalArgumentException.class, () ->
        {
            webShopController.AddProduct(product);
        });
        verify(dbMock);
    }

    @Test void CheckAddMultipleProducts_ShouldReturnProperCount ()
    {
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_PRICE3);
        Product product4 = new Product(PRODUCT_NAME4, PRODUCT_PRICE4);

        List<Product> products = new ArrayList<>(
                Arrays.asList(new Product[]{product, product2, product3, product4}));

        expect(dbMock.AddProduct(product)).andReturn(true);
        expect(dbMock.AddProduct(product2)).andReturn(true);
        expect(dbMock.AddProduct(product3)).andReturn(true);
        expect(dbMock.AddProduct(product4)).andReturn(true);
        replay(dbMock);

        webShopController.AddProducts(products);
        verify(dbMock);
    }

    @Test void CheckAddMultipleProducts_ShouldNotAddEveryProduct ()
    {
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_PRICE3);
        Product product4 = new Product(PRODUCT_NAME4, PRODUCT_PRICE4);

        List<Product> products = new ArrayList<>(
                Arrays.asList(new Product[]{product, product2, product2, product3, product4}));

        expect(dbMock.AddProduct(product)).andReturn(true);
        expect(dbMock.AddProduct(product2)).andReturn(false).times(2);
        expect(dbMock.AddProduct(product3)).andReturn(true);
        expect(dbMock.AddProduct(product4)).andReturn(true);
        replay(dbMock);

        webShopController.AddProducts(products);
        verify(dbMock);
    }

    @Test void CheckGetProductByName_ShouldReturnProperProduct ()
    {
        expect(dbMock.GetProductByName(PRODUCT_NAME)).andReturn(product);
        replay(dbMock);

        webShopController.GetProductByName(PRODUCT_NAME);
        verify(dbMock);
    }

    @Test void CheckGetAllProduct_EmptyDB_ShouldReturnEmptyList ()
    {
        expect(dbMock.GetAllProducts()).andReturn(new ArrayList<>());
        replay(dbMock);

        webShopController.GetAllProducts();
        verify(dbMock);
    }

    @Test void CheckGetAllProducts_ShouldReturnProperValue ()
    {
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_PRICE3);
        Product product4 = new Product(PRODUCT_NAME4, PRODUCT_PRICE4);

        List<Product> products = new ArrayList<>(
                Arrays.asList(new Product[]{product, product2, product3, product4}));

        expect(dbMock.GetAllProducts()).andReturn(products);
        replay(dbMock);

        assertThat(webShopController.GetAllProducts()).
                hasSize(4).containsOnly(product, product2, product3, product4);
        verify(dbMock);
    }

    @Test void CheckDeleteProduct_ShouldDeleteProduct ()
    {
        expect(dbMock.DeleteProduct(product)).andReturn(true);
        replay(dbMock);

        webShopController.DeleteProduct(product);
        verify(dbMock);
    }

    @Test void CheckDeleteProduct_ProductDontExists_ShouldReturnFalse ()
    {
        expect(dbMock.DeleteProduct(product)).andReturn(true);
        replay(dbMock);

        webShopController.DeleteProduct(product);
        verify(dbMock);
    }

    @Test void CheckEditProduct_ShouldEdit ()
    {
        dbMock.EditProductPrice(eq(NEW_PRICE), eq(product));
        EasyMock.expectLastCall().andAnswer(() ->
        {
            Object[] args = getCurrentArguments();
            if (args.length >1)
            {
                double price = (double) args[0];
                Product prod = (Product) args[1];
                prod.Price = price;
            }
            return null;
        });
        replay(dbMock);

        webShopController.EditProductPrice(NEW_PRICE, product);
        assertEquals(NEW_PRICE, product.Price, 0.0001d);
        verify(dbMock);
    }
}
