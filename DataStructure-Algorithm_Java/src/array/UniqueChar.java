package array;
// import java.util.Set;
// import java.util.HashSet;

public class UniqueChar {
    public Boolean uniqueChar(String str) {
        // confirm the string is ASCII string or Unicode string;
        // 128 just for standard ASCII table, 256 will include extended ASCII codes;

        //solution1: use hashset
        // if (str.length() > 128) return false;
        // Set <Integer> strSet = new HashSet<>();
        // for (int i = 0; i < str.length(); i++) {
        //     int cha = str.charAt(i);
        //     if (strSet.contains(cha)) {
        //         return false;
        //     } else {
        //         strSet.add(cha);
        //     }
        // }
        // return true;

        //solution2: don't use data structure
        if (str.length() > 128) return false;
        boolean[] boolChar = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (boolChar[val]) return false;
            boolChar[val] = true;
        }

        return true;
    }

}
