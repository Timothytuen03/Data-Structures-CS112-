package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
    public static void main(String[] args) {
        //StdIn.setFile(args[0]);
        //StdOut.setFile(args[1]);
        StdIn.setFile("addProduct.in");
        StdIn.setFile("addProduct.out");

	// Use this file to test addProduct
    Warehouse w = new Warehouse();
    int numProducts = StdIn.readInt();
    for(int i = 0; i < numProducts; i++)
    {
        int lastPurchaseDay = (StdIn.readInt());
        int id = (StdIn.readInt());
        String name = (StdIn.readString());
        int stock = StdIn.readInt();
        int demand = (StdIn.readInt());
        //Product p = new Product(id, name, stock, lastPurchaseDay, demand);
        w.addProduct(id, name, stock, lastPurchaseDay, demand);
    }
    StdOut.println(w);
    }
}
