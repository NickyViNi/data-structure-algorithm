
public class Main {
    public static void main(String[] args) {
        GenericArray<String> strArr = new GenericArray<>(5);
        strArr.setElement(0, "Hey!");
        strArr.setElement(1, "Hao,");
        strArr.setElement(2, "How");
        strArr.setElement(3, "are");
        strArr.setElement(4, "you?");
        strArr.printArray();
    }
}
