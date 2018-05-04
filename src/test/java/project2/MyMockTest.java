package project2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project2.Controllers.WebShopController;
import project2.Interfaces.IDbContext;
import project2.Models.*;
import project2.Storage.DbContextMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyMockTest
{
    private final String CLIENT_NAME = "Adrian";
    private final String CLIENT_SURNAME = "Smith";
    private final String CLIENT_EMAIL = "some@email.com";

    private final String PRODUCT_NAME = "Gitara";
    private final String PRODUCT_NAME2 = "Perkusja";
    private final String PRODUCT_NAME3 = "Bass";
    private final String PRODUCT_NAME4 = "Mikrofon";

    private final double PRODUCT_PRICE = 1299.99;
    private final double PRODUCT_PRICE2 = 4679.49;
    private final double PRODUCT_PRICE3 = 1375.50;
    private final double PRODUCT_PRICE4 = 349.79;

    private IDbContext dbMock;
    private WebShopController webShopController;

    private Client client;
    private Order order;


    @BeforeEach void SetUp ()
    {
        dbMock = new DbContextMock();
        webShopController = new WebShopController(dbMock);
        client = new Client ("Janusz", "Tracz", "testowanie.java@ug.pl");
        order = new Order(0);
    }

    @Test void CheckAddOrder_ShouldAddOrder ()
    {
        boolean res = webShopController.AddOrder(order);
        assertTrue(res);
    }

    @Test void CheckAddWronglyOrder_ShouldReturnFalse ()
    {
        boolean res = webShopController.AddOrder(null);
        assertFalse(res);
    }

    @Test void CheckAddGetOrder_ShouldReturnProperOrder ()
    {
        dbMock.AddOrder(order);
        Order res = webShopController.GetOrderById(0);

        assertThat(res).isEqualTo(order);
    }

    @Test void CheckGetOrder_OrderDoesNotExist ()
    {
        dbMock.AddOrder(order);
        Order res = webShopController.GetOrderById(2);

        assertThat(res).isEqualTo(null);
    }

    @Test void CheckDeleteOrder_ShouldDeleteReturnTrue ()
    {
        dbMock.AddOrder(order);
        assertTrue(webShopController.DeleteOrder(order));
    }

    @Test void CheckDeleteOrder_OrderDoesNotExists_ShouldReturnFalse ()
    {
        dbMock.AddOrder(order);
        Order ord = new Order(0);
        assertFalse(webShopController.DeleteOrder(ord));
    }

    @Test void CheckGetClientOrders_OrdersDoesNotExists_ShouldReturnEmptyList ()
    {
        dbMock.AddClient(client);
        assertThat(webShopController.GetClientOrders(client)).isEmpty();
    }

    @Test void CheckGetClientOrders_ShouldReturnProperValue ()
    {
        dbMock.AddClient(client);
        webShopController.AddOrder(order);
        webShopController.AddOrder(order);
        webShopController.AddOrder(order);
        webShopController.AddOrder(order);

        assertThat(webShopController.GetClientOrders(client)).hasSize(4).contains(order);
    }

    @Test void CheckGetClientOrders_TwoClients_ShouldReturnProperValue ()
    {
        Client client2 = new Client(CLIENT_NAME, CLIENT_SURNAME, CLIENT_EMAIL);
        Order order2 = new Order( 1);

        dbMock.AddClient(client);
        dbMock.AddClient(client2);

        webShopController.AddOrder(order);
        webShopController.AddOrder(order);
        webShopController.AddOrder(order2);
        webShopController.AddOrder(order);
        webShopController.AddOrder(order2);
        webShopController.AddOrder(order);
        webShopController.AddOrder(order2);
        webShopController.AddOrder(order);

        assertThat(webShopController.GetClientOrders(client)).hasSize(5).contains(order);
        assertThat(webShopController.GetClientOrders(client2)).hasSize(3).contains(order2);
    }

    @Test void CheckCreateOrder_ShouldCreate ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE);
        dbMock.AddProduct(product);
        dbMock.AddOrder(order);

        assertThat(webShopController.CreateOrder(product, order)).isTrue();
    }

    @Test void CheckCreateOrder_ProductDoesNotExists_ShouldReturnFalse ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE);
        dbMock.AddOrder(order);

        assertThat(webShopController.CreateOrder(product, order)).isFalse();
    }

    @Test void CheckGetProductsByOrder_OrderDoesNotExists_ShouldReturnEmptyList ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE);
        dbMock.AddProduct(product);
        dbMock.AddOrder(order);

        assertThat(webShopController.GetProductsByOrder(order)).isEmpty();
    }

    @Test void CheckGetProductsByOrder_ShouldReturnProperList ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE);
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_PRICE3);
        Product product4 = new Product(PRODUCT_NAME4, PRODUCT_PRICE4);

        dbMock.AddOrder(order);
        dbMock.AddProduct(product);
        dbMock.AddProduct(product2);
        dbMock.AddProduct(product3);
        dbMock.AddProduct(product4);

        webShopController.CreateOrder(product, order);
        webShopController.CreateOrder(product2, order);
        webShopController.CreateOrder(product3, order);
        webShopController.CreateOrder(product4, order);

        assertThat(webShopController.GetProductsByOrder(order))
                .hasSize(4).containsOnly(product, product2, product3, product4);
    }

    @Test void CheckGetProductsByOrder_FewOrders_ShouldReturnProperList ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE);
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_PRICE3);
        Product product4 = new Product(PRODUCT_NAME4, PRODUCT_PRICE4);
        Order order2 = new Order(1);
        Order order3 = new Order(2);

        dbMock.AddOrder(order);
        dbMock.AddOrder(order2);
        dbMock.AddOrder(order3);
        dbMock.AddProduct(product);
        dbMock.AddProduct(product2);
        dbMock.AddProduct(product3);
        dbMock.AddProduct(product4);

        webShopController.CreateOrder(product, order);
        webShopController.CreateOrder(product2, order);
        webShopController.CreateOrder(product3, order);
        webShopController.CreateOrder(product4, order);

        webShopController.CreateOrder(product, order2);
        webShopController.CreateOrder(product, order2);
        webShopController.CreateOrder(product, order2);

        webShopController.CreateOrder(product2, order3);
        webShopController.CreateOrder(product3, order3);
        webShopController.CreateOrder(product4, order3);

        assertThat(webShopController.GetProductsByOrder(order))
                .hasSize(4).containsOnly(product, product2, product3, product4);
        assertThat(webShopController.GetProductsByOrder(order2))
                .hasSize(3).containsOnly(product, product, product);
        assertThat(webShopController.GetProductsByOrder(order3))
                .hasSize(3).containsOnly(product2, product3, product4);
    }
}
