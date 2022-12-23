package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile("deleteProduct.in");
        StdOut.setFile("deleteProduct.out");

	// Use this file to test deleteProduct
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
            else if(key.compareTo("delete")==0)
            {
                int id = StdIn.readInt();
                w.deleteProduct(id);
            }
        }

        StdOut.println(w);
    }
}
