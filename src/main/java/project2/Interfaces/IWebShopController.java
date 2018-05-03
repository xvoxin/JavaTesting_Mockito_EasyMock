package project2.Interfaces;

import project2.Models.*;
import java.util.List;

public interface IWebShopController
{
    void AddClient(Client client);
    void AddClients(List<Client> clients);
    Client GetClientByName(String name);
    List<Client> GetAllClients();
    void DeleteClient(Client client);
    void EditClient(Client client);

    void AddProduct(Product product);
    void AddProducts(List<Product> products);
    Product GetProductByName(String name);
    List<Product> GetAllProducts();
    void DeleteProduct(Product product);
    void EditProduct(Product product);

    List<Order> GetClientOrders(Client client);
    void CreateOrder(Product product);

    void AddOrder(Order order);
    void AddOrders(List<Order> orders);
    Order GetOrderById(int id);
    void DeleteOrder(Order order);
    void EditOrder(Order order);
}
