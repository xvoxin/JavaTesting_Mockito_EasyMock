package project2.Storage;

import org.mockito.internal.matchers.Or;
import project2.Interfaces.IDbContext;
import project2.Models.Client;
import project2.Models.Order;
import project2.Models.Product;
import project2.Models.ProductOrder;

import java.util.ArrayList;
import java.util.List;

public class DbContextMock implements IDbContext
{
    private List<Client> _clients;
    private List<Product> _products;
    private List<Order> _orders;
    private List<ProductOrder> _productOrders;

    private int _idClient = 0;
    private int _idProduct = 0;
    private int _idOrder = 0;


    public DbContextMock()
    {
        _clients = new ArrayList<>();
        _products = new ArrayList<>();
        _orders = new ArrayList<>();
        _productOrders = new ArrayList<>();
    }

    @Override public boolean AddClient (Client client)
    {
        if(client == null)
            return false;
        else
        {
            client.Id = _idClient++;
            _clients.add(client);
        }
        return true;
    }

    @Override public Client GetClientByName (String name)
    {
        for (Client client : _clients)
            if (client.Name == name)
                return client;

        return null;
    }

    @Override public List<Client> GetAllClients ()
    {
        return _clients;
    }

    @Override public boolean DeleteClient (Client client)
    {
        if(client == null)
            return false;
        else
        {
            for (Client cl : _clients)
                if (cl.equals(client))
                {
                    _clients.remove(cl);
                    return true;
                }
        }
            return false;
    }

    @Override public void EditClientEmail (String email, Client client)
    {
        for (int i = 0; i < _clients.size(); i++)
            if (_clients.get(i).equals(client))
                _clients.get(i).Email = email;
    }

    @Override public boolean AddProduct (Product product)
    {
        if(product == null)
            return false;
        else
        {
            product.Id = _idProduct++;
            _products.add(product);
        }
        return true;
    }

    @Override public Product GetProductByName (String name)
    {
        for (Product product : _products)
            if (product.Name == name)
                return product;

        return null;
    }

    @Override public List<Product> GetAllProducts ()
    {
        return _products;
    }

    @Override public boolean DeleteProduct (Product product)
    {
        if(product == null)
            return false;
        else
        {
            for (Product prod : _products)
                if (prod.equals(product))
                {
                    _products.remove(prod);
                    return true;
                }
        }
        return false;
    }

    @Override public void EditProductPrice (double price, Product product)
    {
        for (int i = 0; i < _products.size(); i++)
            if (_products.get(i).equals(product))
                _products.get(i).Price = price;
    }

    @Override public List<Order> GetClientOrders (Client client)
    {
        List<Order> clientOrders = new ArrayList<>();

        for (Order order : _orders)
            if(order.IdClient == client.Id)
                clientOrders.add(order);

        return clientOrders;
    }

    @Override public List<Product> GetProductsByOrder (Order order)
    {
        List<Product> products = new ArrayList<>();

        for (ProductOrder orders : _productOrders)
            if(orders.IdOrder == order.Id)
                products.add(_products.get(orders.IdProduct));

        return products;
    }

    @Override public boolean CreateOrder (Product product, Order order)
    {
       if(_products.contains(product) && _orders.contains(order))
       {
           _productOrders.add(new ProductOrder(product.Id, order.Id));
           return true;
       }
       else return false;
    }

    @Override public boolean AddOrder (Order order)
    {
        if(order == null)
            return false;
        else
        {
            order.Id = _idOrder++;
            _orders.add(order);
        }
        return true;
    }

    @Override public Order GetOrderById (int id)
    {
        for (Order order : _orders)
            if (order.Id == id)
                return order;

        return null;
    }

    @Override public boolean DeleteOrder (Order order)
    {
        if(order == null)
            return false;
        else
        {
            for (Order ord : _orders)
                if (ord.equals(order))
                {
                    _orders.remove(ord);
                    return true;
                }
        }
        return false;
    }
}
