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


}
