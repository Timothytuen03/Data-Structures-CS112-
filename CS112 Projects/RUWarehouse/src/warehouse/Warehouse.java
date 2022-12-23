package warehouse;

/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD
        Product newProduct = new Product(id, name, stock, day, demand);
        //sectors[newProduct.getId().]
        int idmod = id;
        idmod = idmod % 10;
        //System.out.println(sectors.length + "idmod " + idmod);
        sectors[idmod].add(newProduct);

    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
        // IMPLEMENT THIS METHOD
        int sector = id % 10;
        int index = sectors[sector].getSize();
        //System.out.println(index);
        //System.out.println(sectors[sector].get(index).getName() + sectors[sector].get(index).getPopularity());


        while(index >= 0)
        {
        sectors[sector].swim(index);
        index--;
        }
        //System.out.println(sectors[sector].get(index).getId());
        // if(index > 0){
        //     System.out.println("Parent: " + ((index-1)/2));
        //     System.out.println("Index Popularity: " + sectors[sector].get(index).getPopularity());
        //     System.out.println("Parent Popularity: "+ sectors[sector].get((index-1)/2).getPopularity());
        //     while(sectors[sector].get(index).getPopularity() > sectors[sector].get((index-1)/2).getPopularity())
        //     {
        //         sectors[sector].swap(index, ((index-1)/2));
        //     }
        // }

    

    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
       // IMPLEMENT THIS METHOD

       int sector = id % 10;
       int index = sectors[sector].getSize();

       if(index == 5)
       {
            sectors[sector].swap(1, index);
            sectors[sector].deleteLast();
            sectors[sector].sink(1);
            
       }
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        // IMPLEMENT THIS METHOD
        int sector = id %10;
        int index = sectors[sector].getSize();

        for(int i = 0; i < index; i++)
        {
            if(id == sectors[sector].get(i+1).getId())
            {
                sectors[sector].get(i+1).updateStock(amount);
            }
        }
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        // IMPLEMENT THIS METHOD
        int sector = id%10;
        int index = sectors[sector].getSize();

        for(int i = 0; i < index; i++)
        {
            if(id == sectors[sector].get(i+1).getId())
            {
                sectors[sector].swap(i+1, index);
                sectors[sector].deleteLast();
                sectors[sector].sink(i+1);
                break;
            }
        }
    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        // IMPLEMENT THIS METHOD
        int sector = id % 10;
        int index = sectors[sector].getSize();

        for(int i = 0; i < index; i++)
        {
            if(id == sectors[sector].get(i+1).getId())
            {
                int stock = sectors[sector].get(i+1).getStock();
                if(stock > amount)
                {
                    sectors[sector].get(i+1).setLastPurchaseDay(day);
                    sectors[sector].get(i+1).updateStock(amount * -1);
                    sectors[sector].get(i+1).updateDemand(amount);
                    sectors[sector].sink(i+1);
                    break;
                }
            }
        }

        // while(index>= 0)
        // {
        //     sectors[sector].swim(index);
        //     index--;
        // }
    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD
        int sector = id % 10;
        Product newProduct = new Product(id, name, stock, day, demand);
        int newSector = sector+1;
        if(newSector == 10)
        {
            newSector = 0;
        }
        boolean added = false;

        if(sectors[sector].getSize() < 5)
        {
            sectors[sector].add(newProduct);
            sectors[sector].swim(sectors[sector].getSize());
        }
        else
        {
            while(newSector != sector)
            {
                if(sectors[newSector].getSize() < 5)
                {
                    sectors[newSector].add(newProduct);
                    sectors[newSector].swim(sectors[newSector].getSize());
                    added = true;
                    break;
                }

                newSector++;
                if(newSector == 10)
                {
                    newSector = 0;
                }
            }
            if(added == false && sectors[sector].getSize() == 5)
            {
                int index = sectors[sector].getSize();
                sectors[sector].swap(1, index);
                sectors[sector].deleteLast();
                sectors[sector].sink(1);

                sectors[sector].add(newProduct);
                sectors[sector].swim(index);
            }
        }
    }

    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}
