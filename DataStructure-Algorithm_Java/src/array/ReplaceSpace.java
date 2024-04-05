package array;

public class ReplaceSpace {
    public static String replaceSpace (String s, int trueLength) {
        if (s.length() > trueLength) {
            s = s.substring(0, trueLength);
        }
        String[] arr = s.split(" ");
        return String.join("%20", arr);
    }
}
