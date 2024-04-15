package hashTable;

import java.util.ArrayList;

public class HashTable {
    // insert or lookup key: O(1)
	private int size = 7;
	private Node[] dataMap;

	class Node {
	    String key;
	    int value;
	    Node next;

	    public Node(String key, int value) {
	        this.key = key;
	        this.value = value;
	    }
	}

	public HashTable() {
	    this.dataMap = new Node[size];
	}

    public Node[] getDataMap() {
        return dataMap;
    }

    public void printTable() {
        for(int i = 0; i < dataMap.length; i++) {
            System.out.println(i + ":");
            if(dataMap[i] != null) {
                Node temp = dataMap[i];
                while (temp != null) {
                    System.out.println("   {" + temp.key + ", " + temp.value + "}");
                    temp = temp.next;
                }
            }
        }
    }

    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            int asciiValue = keyChars[i];
            hash = (hash + asciiValue * 23) % dataMap.length;
        }
        return hash;
    }

    public void set(String key, int value) {
        int index = hash(key);
        Node newNode = new Node(key, value);
        if (this.dataMap[index] == null) {
            this.dataMap[index] = newNode;
        } else {
            Node temp = this.dataMap[index];
            while (temp.next != null) {
                if (temp.key == key) {
                    temp.value += value;
                    return;
                }
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public int get(String key) {
        int index = hash(key);
        Node temp = this.dataMap[index];
        while(temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return 0;
    }

     public ArrayList<String> keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (int i = 0; i < this.dataMap.length; i++) {
            Node temp = this.dataMap[i];
            while (temp != null) {
                allKeys.add(temp.key);
                temp = temp.next;
            }
        }
        return allKeys;
    }
}