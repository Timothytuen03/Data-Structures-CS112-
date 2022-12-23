package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile("restock.in");
        StdOut.setFile("restock.out");

	// Use this file to test restock
        int queries = StdIn.readInt();
        Warehouse w = new Warehouse();


        for(int i = 0; i < queries; i++)
        {
            String s = StdIn.readString();
            if(s.compareTo("add") == 0)
            {
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String name = StdIn.readString();
                int initialStock = StdIn.readInt();
                int initialDemand = StdIn.readInt();
                w.addProduct(id, name, initialStock, day, initialDemand);
            }
            else if(s.compareTo("restock")==0)
            {
                int id = StdIn.readInt();
                int amt = StdIn.readInt();
                w.restockProduct(id, amt);
            }
        }

        StdOut.println(w);
    }
}
