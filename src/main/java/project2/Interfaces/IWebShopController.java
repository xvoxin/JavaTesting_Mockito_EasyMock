package project2.Interfaces;

import project2.Models.*;

import java.io.IOException;
import java.util.List;

public interface IWebShopController
{
    boolean AddClient(Client client);
    int AddClients(List<Client> clients);
    Client GetClientByName(String name);
    List<Client> GetAllClients();
    boolean DeleteClient(Client client);
    void EditClientEmail(String email, Client client);

    boolean AddProduct(Product product);
    int AddProducts(List<Product> products);
    Product GetProductByName(String name);
    List<Product> GetAllProducts();
    boolean DeleteProduct(Product product);
    boolean EditProduct(Product product);

    List<Order> GetClientOrders(Client client);
    List<Product> GetProductsByOrder(Order order);
    boolean CreateOrder(Product product, Order order);

    boolean AddOrder(Order order);
    Order GetOrderById(int id);
    boolean DeleteOrder(Order order);
}
