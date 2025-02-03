package homework01;

import java.util.Random;

public class MultiThreadedProcessing {

    private static final int SIZE = 2_000_000;
    private static final int[] numbers = new int[SIZE];

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            numbers[i] = random.nextInt(10_000_000) + 1;
        }

        long startSingle = System.currentTimeMillis();
        int singleThreadResult = countDivisibleBy21With3(0, SIZE);
        long endSingle = System.currentTimeMillis();
        System.out.println("Однопоточный результат: " + singleThreadResult + " (время: " + (endSingle - startSingle) + " мс)");

        long startMulti = System.currentTimeMillis();
        int multiThreadResult = countUsingTwoThreads();
        long endMulti = System.currentTimeMillis();
        System.out.println("Двухпоточный результат: " + multiThreadResult + " (время: " + (endMulti - startMulti) + " мс)");
    }

    private static int countDivisibleBy21With3(int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (numbers[i] % 21 == 0 && containsDigit3(numbers[i])) {
                count++;
            }
        }
        return count;
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

    private static int countUsingTwoThreads() {
        final int middle = SIZE / 2;
        final int[] results = new int[2];

        Thread thread1 = new Thread(() -> results[0] = countDivisibleBy21With3(0, middle));
        Thread thread2 = new Thread(() -> results[1] = countDivisibleBy21With3(middle, SIZE));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return results[0] + results[1];
    }
}
