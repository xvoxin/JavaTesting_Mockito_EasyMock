package project2.Models;

public class ProductOrder
{
    private int idProduct;
    private int idOrder;

    public ProductOrder (int idProduct, int idOrder)
    {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
    }

    public int getIdProduct ()
    {
        return idProduct;
    }

    public void setIdProduct (int idProduct)
    {
        this.idProduct = idProduct;
    }

    public int getIdOrder ()
    {
        return idOrder;
    }

    public void setIdOrder (int idOrder)
    {
        this.idOrder = idOrder;
    }
}
