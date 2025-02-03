package homework01;

import java.util.Random;

public class ArrayOfNumbers {

    public static void main(String[] args) {
        int size = 2_000_000;
        int[] numbers = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(10_000_000) + 1;
        }

        int count = 0;
        for (int num : numbers) {
            if (num % 21 == 0 && containsDigit3(num)) {
                count++;
            }
        }

        System.out.println("Количество чисел, делящихся на 21 и содержащих цифру 3: " + count);
    }

    private static boolean containsDigit3(int num) {
        while (num > 0) {
            if (num % 10 == 3) {
                return true;
            }
            num /= 10;
        }
        return false;
    }
}
