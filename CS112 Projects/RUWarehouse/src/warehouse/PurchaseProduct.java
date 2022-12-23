package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile("purchaseProduct.in");
        StdOut.setFile("purchaseProduct.out");

	// Use this file to test purchaseProduct
        int queries = StdIn.readInt();
        Warehouse w = new Warehouse();
        for(int i = 0; i < queries; i++)
        {
            String key = StdIn.readString();
            if(key.compareTo("add") == 0)
            {
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String name = StdIn.readString();
                int initialStock = StdIn.readInt();
                int initialDemand = StdIn.readInt();
                w.addProduct(id, name, initialStock, day, initialDemand);
            }
            else if(key.compareTo("purchase") == 0)
            {
                int day =StdIn.readInt();
                int id = StdIn.readInt();
                int amtPurchased = StdIn.readInt();
                w.purchaseProduct(id, day, amtPurchased);
            }
        }

        StdOut.println(w);

    }
}
