package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
        StdIn.setFile(fileName);

	/* Your code goes here */
    
        //ArrayList<CharFreq> charObjects = new ArrayList<CharFreq>();
        //ArrayList<Character> chars = new ArrayList<Character>();
        sortedCharFreqList = new ArrayList<CharFreq>();
        int[] ascii = new int[128];
        double c = 0;

        while(StdIn.hasNextChar())
        {
            //chars.add(StdIn.readChar());
            char p = StdIn.readChar();
            ascii[(int)p] += 1;
            c++;
        }
        //System.out.println(c);
        
        for(int i = 0; i < ascii.length; i++)
        {

            if(ascii[i] >0)
            {
                CharFreq add = new CharFreq((char)i, (ascii[i] / c));
                sortedCharFreqList.add(add);
            }
        }


        // for(int i = 0; i < chars.size(); i++)
        // {
        //     double count = 0;
        //     for(int j = 0; j < chars.size(); j++)
        //     {
        //         if(chars.get(i).compareTo(chars.get(j)) == 0)
        //         {
        //             count++;
        //         }
        //     }
        //     CharFreq temp = new CharFreq(chars.get(i), (count/c));
        //     // System.out.println(count);
        //     if(!charObjects.contains(temp))
        //     {
        //         charObjects.add(temp);
        //         // System.out.println("Added");
        //         ascii[(int)chars.get(i)] = (int)count;
        //     }
        // }

        // ArrayList<CharFreq> charObjects2 = new ArrayList<CharFreq>();

        // for(int i = 0; i < ascii.length; i++)
        // {
        //     if(ascii[i] != null)
        //     {
        //         CharFreq add = new CharFreq((char)i, ((double)ascii[i] / c));
        //         charObjects2.add(add);
        //     }
        // }

        if(sortedCharFreqList.size() == 1)
        {
            int asc = (((int)sortedCharFreqList.get(0).getCharacter()) + 1) % 128;
            // if((int)sortedCharFreqList.get(0).getCharacter() != 127)
            //     asc = (int)sortedCharFreqList.get(0).getCharacter() + 1;
            // else
            //     asc = 0;
            CharFreq extra = new CharFreq((char)asc, 0);
            sortedCharFreqList.add(extra);
        }
       

        Collections.sort(sortedCharFreqList);
        // sortedCharFreqList = charObjects2;

        
    }

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {

	/* Your code goes here */
    Queue<TreeNode> source = new Queue<TreeNode>();
    Queue<TreeNode> target = new Queue<TreeNode>();
    Queue<TreeNode> hold = new Queue<TreeNode>();
    for(int i = 0; i < sortedCharFreqList.size(); i++)
    {
        //System.out.println("SortedCharFreqList: " +sortedCharFreqList.get(i).getCharacter());
        TreeNode n = new TreeNode(sortedCharFreqList.get(i),null, null);
        source.enqueue(n);
    }

    while(source.isEmpty() != true || target.size() != 1)
    {
        TreeNode dequeueFirst = new TreeNode();
        TreeNode dequeueSecond = new TreeNode();
        //for(int i = 0; i < 2; i++)
        while(hold.size() < 2)
        {
            if(target.isEmpty())
            {
                //dequeueFirst = source.dequeue();
                hold.enqueue(source.dequeue());
            }
            else
            {
                if(source.size() <= 0)
                {
                    hold.enqueue(target.dequeue());
                }
                else
                {
                    if(source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc())
                    {
                        hold.enqueue(source.dequeue());
                    }
                    else if(source.peek().getData().getProbOcc() > target.peek().getData().getProbOcc())
                    {
                        hold.enqueue(target.dequeue());
                    }                
                }
                
            }
            // if(target.isEmpty() != true && source.size() > 0 && source.peek().getData().getProbOcc() < target.peek().getData().getProbOcc())
            // {
            //     if(dequeueFirst.getData() == null)
            //         dequeueFirst = source.dequeue();
            //     else
            //         dequeueSecond = source.dequeue();
            //     //source.dequeue();
            // }
            // else if(target.isEmpty() != true && source.size() > 0 && source.peek().getData().getProbOcc() == target.peek().getData().getProbOcc())
            // {
            //     if(dequeueFirst.getData() != null)
            //     {
            //     dequeueSecond = source.dequeue();
            //     break;
            //     }
            //     else if(dequeueFirst.getData() == null)
            //     {
            //         dequeueFirst = source.dequeue();
            //         dequeueSecond = target.dequeue();
            //         break;
            //     }
            //     //source.dequeue();
            //     //target.dequeue();
            // }
            // else if(target.isEmpty() != true  && source.size() > 0 && source.peek().getData().getProbOcc() > target.peek().getData().getProbOcc())
            // {
            //     if(dequeueFirst.getData() == null)
            //         dequeueFirst = target.dequeue();
            //     else
            //         dequeueSecond = target.dequeue();
            //     //target.dequeue();
            // }
            // else if(target.isEmpty() && source.size() > 0)
            // {   
            //     if(dequeueFirst.getData() == null)
            //         dequeueFirst = source.dequeue();
            //     else
            //         dequeueSecond = source.dequeue();
            // }
            // else if(target.isEmpty() != true && source.size() == 0)
            // {
            //     if(dequeueFirst.getData() == null)
            //         dequeueFirst = target.dequeue();
            //     else
            //         dequeueSecond = target.dequeue();
            // }
            
        }
        //System.out.println("Characters : " + dequeueFirst.getData().getCharacter() + " " + dequeueSecond.getData().getCharacter());
        // if(hold.isEmpty())
        // {
        //     dequeueFirst = null;
        // }
        // else
        // {
            dequeueFirst = hold.dequeue();
        //}

        // if(hold.isEmpty())
        // {
        //     dequeueSecond = null;
        // }
        // else
        // {
            dequeueSecond = hold.dequeue();
        //}


        double probSum = dequeueFirst.getData().getProbOcc() + dequeueSecond.getData().getProbOcc();
        CharFreq newChar = new CharFreq(null, probSum);
        TreeNode newNode = new TreeNode(newChar, dequeueFirst, dequeueSecond);
        target.enqueue(newNode);
        //System.out.println(target.size());

        //System.out.println("Added: " + newNode.getLeft().getData().getCharacter() + " " + newNode.getRight().getData().getCharacter());
    }

    //System.out.println(target.size());

    // for(int i = 0; i < target.size(); i++)
    // {
    //     System.out.println("target " + target.peek().getData().getCharacter());
        
    // }

    huffmanRoot = target.peek();
    }

    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {

	/* Your code goes here */
    encodings = new String[128];
    String path = "";

    mE(huffmanRoot, path);
    // TreeNode root = huffmanRoot;
    // String path = "";
    // while(huffmanRoot.getLeft() != null)
    // {
    //     huffmanRoot = huffmanRoot.getLeft();

    //     if(huffmanRoot.getData().getCharacter() != null)
    //     {
    //         encodings[huffmanRoot.getData().getCharacter()] = path; 
    //     }

    // }



    }

    private void mE(TreeNode node, String path)
    {
        
        //String pathR="";
        
        if(node.getData().getCharacter() != null)
        {
            encodings[(int)node.getData().getCharacter()] = path;
        }

        if(node.getLeft()!= null)
        {
            path = path+ "0";
            //lastAdd = "0";
            mE(node.getLeft(), path);  
        }

        if(path.length() == 1)
        {
            String s = "";
            path = s;
        }
        else
        {
            path = path.substring(0, path.length()-1);
        }
        
        if(node.getRight() != null)
        {
            path = path+ "1";
            //lastAdd = "1";
            mE(node.getRight(), path);
        }

        
        return;

    }

    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);

	/* Your code goes here */
    String toEncode = "";

    while(StdIn.hasNextChar())
    {
        char s = StdIn.readChar();

        toEncode = toEncode + encodings[(int)s];
        // for(int i = 0; i < 128; i++)
        // {
        //     if(s == 0)
        //     {
        //         encodedFile = encodedFile + s;
        //     }
        // }
    }
    //System.out.println(toEncode);
    writeBitString(encodedFile, toEncode);
    
    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);

	/* Your code goes here */

    String decoded = readBitString(encodedFile);
    String decodedText = "";
    // String path = "";
    // String text = "";

    //int s = ();

    //System.out.println(decoded);
    // for(int i = 0; i < 128; i++)
    // {
    //     if(encodings[i] == decoded)
    //     {
    //         //decodedFile = decodedFile + ;
    //     }
    // }

    //while(!= null){}


    //decodeTraverse(huffmanRoot, path, decoded, text);
    int count = 0;
    TreeNode root = huffmanRoot;
    while(decoded.isEmpty() != true)
    {
        if(decoded.length() == count)
        {
            decodedText = decodedText + root.getData().getCharacter();
            //System.out.println("Final: " + decodedText);
            break;
        }
        else if((decoded.charAt(count) == '0' && root.getLeft() == null) || (decoded.charAt(count) == '1' && root.getRight() == null))
        {
            decodedText = decodedText + root.getData().getCharacter();
            decoded = decoded.substring(count);
            root = huffmanRoot;
            count = 0;
            // System.out.println("Count: " +count);
            // System.out.println("decoded File: " +decodedFile);
            // System.out.println("Left to Decode: " +decoded);
            // System.out.println("Root Probability: " +root.getData().getProbOcc());
        }
        
        if(decoded.charAt(count) == '0')
        {
            root = root.getLeft();
            //System.out.println("left");
            //decoded = decoded.substring(count);
        }
        else if(decoded.charAt(count) == '1')
        {
            root = root.getRight();
            //System.out.println("Right");
            //decoded = decoded.substring(count);
        }

        // if(decoded.length() == 0)
        // {
        //     decodedFile = decodedFile + root.getData().getCharacter();
        //     break;
        // }
        // else if((decoded.charAt(count) == '0' && root.getLeft() == null) || (decoded.charAt(count) == '1' && root.getRight() == null))
        // {
        //     decodedFile = decodedFile + root.getData().getCharacter();
        //     // decoded = decoded.substring(count);
        //     root = huffmanRoot;
        //     count = 0;
        //     System.out.println("Count: " +count);
        //     System.out.println("decoded File: " +decodedFile);
        //     System.out.println("Left to Decode: " +decoded);
        //     System.out.println("Root Probability: " +root.getData().getProbOcc());
        // }

        count++;
    }

    StdOut.print(decodedText);

    }


    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
