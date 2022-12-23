package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile("betteraddproduct.in");
        StdOut.setFile("betterAddProduct.out");
        
        // Use this file to test betterAddProduct
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
            w.betterAddProduct(id, name, stock, lastPurchaseDay, demand);
            System.out.println(i);
        }
        StdOut.println(w);
    }
}
