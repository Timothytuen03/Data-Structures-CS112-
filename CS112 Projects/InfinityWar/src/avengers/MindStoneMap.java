package avengers;
import java.util.*;

public class MindStoneMap {
    private HashMap<String, ArrayList<String>> map;
    private int neurons;
    private int synapses;

    public MindStoneMap(int vertices) {
        map = new HashMap<String, ArrayList<String>>(vertices);
        neurons = vertices;
    }

    public MindStoneMap(int vertices, int edges) {
        map = new HashMap<String, ArrayList<String>>(vertices);
        neurons = vertices;
        synapses = edges;
    }

    public void setEdges(int edges)
    {
        synapses = edges;
    }

    public void addEdgeList(String key)
    {
        map.put(key, new ArrayList<String>());
    }

    public void addEdge(String key, String value)
    {
        map.get(key).add(value);
    }

    // public boolean hasSynapse(String key)
    // {
    //     boolean has = false;
    //     if(map.containsKey(key))
    //         if(map.containsV)
        
    //     return has;
    // }

    public ArrayList<String> get(String key)
    {
        return map.get(key);
    }

    public String containsSynapse(String key, String value)
    {
        if(map.get(key).indexOf(value) == -1)
        {
            return "";
        }
        else
        {
            return key;
        }
    }
}