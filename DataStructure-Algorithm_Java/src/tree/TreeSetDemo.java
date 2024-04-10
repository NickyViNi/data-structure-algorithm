package tree;

import java.util.Arrays;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        //TreeSet, an implementation of a red-black tree in Java.
        //TreeSet maintains a sorted order for storing unique elements.
        /* O(log(n))
         * The Data Structure that TreeSet uses is a Red-Black Tree.
         * To add or remove the element from HashSet, the time complexity is O(1).
         * For adding or removing the element from TreeSet, the time complexity is O(log(n)).
         */
        String phrase = "We are made wise not by the recollection of our past, but by the responsibility for our future.";
        String[] phraseList = phrase.split(" ");
        TreeSet<String> words = new TreeSet<>();
        words.addAll(Arrays.asList(phraseList));

        for (String w : words) {
            System.out.print(w + " ");
        }
    }
}
