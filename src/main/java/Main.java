import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    private static final AtomicInteger l3 = new AtomicInteger();
    private static final AtomicInteger l4 = new AtomicInteger();
    private static final AtomicInteger l5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];

        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }


        Thread th1 = new Thread(()-> {
            for (String text : texts) {
                if (text.length() == 3) {
                    if (istPalindrome(text))
                        l3.addAndGet(1);
                    if (repeatingСharacters(text))
                        l3.addAndGet(1);
                    if (ascendingCharacters(text))
                        l3.addAndGet(1);
                }
            }
        });
        Thread th2 = new Thread(()-> {
            for (String text : texts) {
                if (text.length() == 4) {
                    if (istPalindrome(text))
                        l4.addAndGet(1);
                    if (repeatingСharacters(text))
                        l4.addAndGet(1);
                    if (ascendingCharacters(text))
                        l4.addAndGet(1);
                }
            }
        });
        Thread th3 = new Thread(()-> {
            for (String text : texts) {
                if (text.length() == 5) {
                    if (istPalindrome(text))
                        l5.addAndGet(1);
                    if (repeatingСharacters(text))
                        l5.addAndGet(1);
                    if (ascendingCharacters(text))
                        l5.addAndGet(1);
                }
            }
        });

        th1.start();
        th2.start();
        th3.start();
        th1.join();
        th2.join();
        th3.join();
        System.out.println("Красивых слов с длиной 3: " + l3 + " шт.");
        System.out.println("Красивых слов с длиной 4: " + l4 + " шт.");
        System.out.println("Красивых слов с длиной 5: " + l5 + " шт.");

    }

    private static boolean istPalindrome(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        return s.equals(reverse);
    }

    private static boolean repeatingСharacters(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(0) != s.charAt(i)) return false;
        }
        return true;
    }

    private static boolean ascendingCharacters(String s) {
        byte[] byteArr = s.getBytes();

        for (int i = 0; i < byteArr.length - 1; i++) {
            int l = (byteArr[i + 1] - byteArr[i]);
            if (l > 1  || l < 0 ) {
                return false;
            }
        }
        return true;
    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
