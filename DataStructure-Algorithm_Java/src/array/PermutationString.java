package array;
import java.util.Arrays;

public class PermutationString {
    //solution1: sort the strings and compare them:
    public char[] sortString(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return content;
    }

    public boolean permutation(String a, String b) {
        if(a.length() != b.length()) return false;
        return Arrays.compare(sortString(a), sortString(b)) == 0;
    }

    //solution2:
    public boolean permutation2(String a, String b) {

        if(a.length() != b.length()) return false;

        int[] charList = new int[128];

        for(int i = 0; i < a.length(); i++) {
            int val = a.charAt(i);
            charList[val]++;
        }

        for(int i = 0; i < b.length(); i++) {
            int val = b.charAt(i);
            charList[val]--;
            if (charList[val] < 0) return false;
        }

        return true;
    }


}
