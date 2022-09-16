import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        String str = "23+43+35+5-32";
        System.out.println(calc(numbersToList(str)));

    }

    public static boolean znaki(String s) {
        return (s.equals("+") || s.equals("-"));
    }

    public static List<String> numbersToList(String str) {
        String[] str1 = str.split("");
        String number = "";
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < str1.length; i++) {
            if (znaki(str1[i])) {
                numbers.add(number);
                numbers.add(str1[i]);
                number = "";
            } else number = number.concat(str1[i]);

            if (i == str1.length - 1) numbers.add(number);

        }
        return numbers;

    }

    public static int calc(List<String> numbers) {
        int result = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (i == 0) {
                result = Integer.parseInt(numbers.get(i));
            }

            if (znaki(numbers.get(i))) {
                result = action(numbers.get(i), result, Integer.parseInt(numbers.get(i + 1)));
            }
        }
        return result;

    }

    public static int action(String sign, int a, int b) {

        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            default -> 0;
        };

    }


}
