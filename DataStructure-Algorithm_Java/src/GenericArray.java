public class GenericArray<T> {
    private T[] array;

    public GenericArray (int size) {
        // Create an array of type T with the specified size
        array = (T[]) new Object[size];
    }

    public T[] getArray() {
        return array;
    }

    public void printArray() {
        for (T i : array) System.out.print(i + " ");
    }

    public void setElement(int index, T value) {
        array[index] = value;
    }

    public T getElement(int index) {
        return array[index];
    }

//    public static void main (String[] args) {
//        GenericArray<Integer> intArr = new GenericArray<>(3);
//        intArr.setElement(0, 11);
//        intArr.setElement(1, 12);
//        intArr.printArray();

//        GenericArray<String> strArr = new GenericArray<>(5);
//        strArr.setElement(0, "Hey!");
//        strArr.setElement(1, "Hao,");
//        strArr.setElement(2, "How");
//        strArr.setElement(3, "are");
//        strArr.setElement(4, "you?");
//        strArr.printArray();
//    }
}
