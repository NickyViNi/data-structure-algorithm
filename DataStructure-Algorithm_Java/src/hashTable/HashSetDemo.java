package hashTable;
import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        /*
         * In Java, HashSet is a collection class that implements the Set interface,
         * which represents a collection of unique elements.
         * It does not guarantee the order of its elements and permits the null element.
         */
        // Create a HashSet
        HashSet<String> hashSet = new HashSet<>();

        // Add elements to the HashSet
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Orange");
        hashSet.add("Apple"); // Adding duplicate, it will be ignored

        // Display the elements of the HashSet
        System.out.println("HashSet: " + hashSet);

        // Check if a specific element exists in the HashSet
        System.out.println("Contains 'Banana': " + hashSet.contains("Banana"));

        // Remove an element from the HashSet
        hashSet.remove("Orange");

        // Display the elements after removal
        System.out.println("HashSet after removal: " + hashSet);

        // Size of the HashSet
        System.out.println("Size of HashSet: " + hashSet.size());

        // Iterate through the HashSet
        System.out.println("Iterating through HashSet:");
        for (String element : hashSet) {
            System.out.println(element);
        }

        // Clear the HashSet
        hashSet.clear();
        System.out.println("HashSet after clearing: " + hashSet);
    }
}
