package array;
import java.util.Set;
import java.util.HashSet;

public class UniqueChar {
    public Boolean uniqueChar(String str) {
        // confirm the string is ASCII string or Unicode string;
        // 128 just for standard ASCII table, 256 will include extended ASCII codes;
        if (str.length() > 128) return false;
        Set <String> strSet = new HashSet<>();
        String[] strArr = str.split("");
        for (int i = 0; i < strArr.length; i++) {
            if (strSet.contains(strArr[i])) {
                return false;
            } else {
                strSet.add(strArr[i]);
            }
        }
        return true;
    }

    // public static void main(String[] args) {
    //     UniqueChar uniqueString = new UniqueChar();
    //     System.out.println(uniqueString.uniqueChar("howareyu"));
    // }
}
