package array;

public class Binary {
    //67 easy -> add binary
    /*Input: a = "11", b = "1"
        Output: "100" */
    public String addBinary(String a, String b) {
        int idxa = a.length() - 1;
        int idxb = b.length() - 1;
        int carry = 0;
        String result = "";
        while (idxa >= 0 || idxb >= 0 || carry == 1) {
            if (idxa >= 0) carry += a.charAt(idxa--) - '0';
            if (idxb >= 0) carry += b.charAt(idxb--) - '0';
            result = String.valueOf(carry % 2) + result;
            carry /= 2;
        }
        return result;
    }

    //191 easy -> number of 1 bits
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1); //The quickest way to eliminate the end 1
            res++;
        }
        return res;
    }
    /*
    n = n & (n - 1)
    for example: n = 10010;
    10010 - 1 = 10001, 10010 & 10001 = 10000, res = 1;
    10000 - 1 = 01111, 10000 & 01111 = 00000, res = 2; return 2;
     */

    // public int hammingWeight(int n) {
    //     int res = 0;
    //     for (int i = 0; i < 32; i++) {
    //         if (((n >> i) & 1) == 1) {
    //             res += 1;
    //         }
    //     }
    //     return res;
    // }

    // public int hammingWeight(int n) {
    //     int num = n;
    //     int res = 0;
    //     while (num > 0) {
    //         int carry = 0;
    //         carry = num % 2;
    //         num /= 2;
    //         if (carry == 1) res++;
    //     }
    //     return res;
    // }

    //137 medium -> single number
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum += num >> i & 1;
            }
            sum %= 3;
            res |= sum << i;
        }
        return res;
    }

    public int singleNumber2(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            a ^= (num & ~b);
            b ^= (num & ~a);
        }
        return a;
    }
}
