package project2.Controllers;

import project2.Interfaces.*;
import project2.Models.*;

import java.util.List;

public class WebShopController implements IWebShopController
{
    IDbContext dbcontext;

    public WebShopController(IDbContext dbcontext)
    {
        this.dbcontext = dbcontext;
    }


    public void AddClient (Client client)
    {
        dbcontext.AddClient(client);
    }

    public void AddClients (List<Client> clients)
    {
        for (Client client : clients)
            dbcontext.AddClient(client);
    }

    public Client GetClientByName (String name)
    {
        return dbcontext.GetClientByName(name);
    }

    public List<Client> GetAllClients ()
    {
        return dbcontext.GetAllClients();
    }

    public void DeleteClient (Client client)
    {
        dbcontext.DeleteClient(client);
    }

    public void EditClient (Client client)
    {
        dbcontext.EditClient(client);
    }

    public void AddProduct (Product product)
    {
        dbcontext.AddProduct(product);
    }

    public void AddProducts (List<Product> products)
    {
        for (Product product : products)
            dbcontext.AddProduct(product);
    }

    public Product GetProductByName (String name)
    {
        return dbcontext.GetProductByName(name);
    }

    public List<Product> GetAllProducts ()
    {
        return dbcontext.GetAllProducts();
    }

    public void DeleteProduct (Product product)
    {
        dbcontext.DeleteProduct(product);
    }

    public void EditProduct (Product product)
    {
        dbcontext.EditProduct(product);
    }

    public List<Order> GetClientOrders (Client client)
    {
        return dbcontext.GetClientOrders(client);
    }

    public List<Product> GetProductsByOrder (Order order)
    {
        return dbcontext.GetProductsByOrder(order);
    }

    public void CreateOrder(Product product, Order order)
    {
        dbcontext.CreateOrder(product, order);
    }

    public void AddOrder (Order order)
    {
        dbcontext.AddOrder(order);
    }

    public Order GetOrderById (int id)
    {
        return dbcontext.GetOrderById(id);
    }

    public void DeleteOrder (Order order)
    {
        dbcontext.DeleteOrder(order);
    }
}
