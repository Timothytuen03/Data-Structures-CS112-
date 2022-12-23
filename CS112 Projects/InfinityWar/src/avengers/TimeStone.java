package avengers;
import java.util.*;

public class TimeStone {
    private HashMap<Integer, ArrayList<Integer>> map;
    private int neurons;
    private int synapses;

    public TimeStone(int vertices) {
        map = new HashMap<Integer, ArrayList<Integer>>(vertices);
        neurons = vertices;
    }

    public TimeStone(int vertices, int edges) {
        map = new HashMap<Integer, ArrayList<Integer>>(vertices);
        neurons = vertices;
        synapses = edges;
    }

    public void setEdges(int edges)
    {
        synapses = edges;
    }

    public void addEdgeList(int key)
    {
        map.put(key, new ArrayList<Integer>());
    }

    public void addEdge(int key, int value)
    {
        map.get(key).add(value);
    }

    public int size()
    {
        return map.size();
    }

    // public boolean hasSynapse(String key)
    // {
    //     boolean has = false;
    //     if(map.containsKey(key))
    //         if(map.containsV)
        
    //     return has;
    // }

    public ArrayList<Integer> get(int key)
    {
        return map.get(key);
    }

    // public int containsSynapse(int key, int value)
    // {
    //     if(map.get(key).indexOf(value) == -1)
    //     {
    //         return;
    //     }
    //     else
    //     {
    //         return key;
    //     }
    // }
}
