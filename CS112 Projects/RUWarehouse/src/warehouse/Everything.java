package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile("everything.in");
        StdOut.setFile("everything.out");

	// Use this file to test all methods
    Warehouse w = new Warehouse();
    int queries = StdIn.readInt();
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
            else if(key.compareTo("delete")==0)
            {
                int id = StdIn.readInt();
                w.deleteProduct(id);
            }
            else if(key.compareTo("restock")==0)
            {
                int id = StdIn.readInt();
                int amt = StdIn.readInt();
                w.restockProduct(id, amt);
            }
    }
    StdOut.println(w);
    }
}
