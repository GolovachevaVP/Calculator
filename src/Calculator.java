import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        String str = "3*11-7+24*6/7";
        System.out.println(calc(calc2(numbersToList(str))));
    }

    public static boolean isSign(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }

    public static boolean isSignPlusOrMinus(String s) {
        return (s.equals("+") || s.equals("-"));
    }

    public static boolean isSignMultiplicationOrDivision(String s) {
        return (s.equals("*") || s.equals("/"));
    }

    public static List<String> numbersToList(String str) {
        String[] str1 = str.split("");
        String number = "";
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < str1.length; i++) {
            if (isSign(str1[i])) {
                numbers.add(number);
                numbers.add(str1[i]);
                number = "";
            } else number = number.concat(str1[i]);

            if (i == str1.length - 1) numbers.add(number);
        }
        return numbers;
    }

    public static double calc(List<String> numbers) {
        double result = 0;
        for (int i = 0; ; i++) {
            if (isSignPlusOrMinus(numbers.get(i))) {
                result = actionSumOrSubtraction(numbers.get(i), Double.parseDouble(numbers.get(i - 1)), Double.parseDouble(numbers.get(i + 1)));
                numbers.set(i - 1, String.valueOf(result));
                numbers.remove(i);
                numbers.remove(i);
                i = i - 1;
            }
            if (i == numbers.size() - 1) break;
        }
        return result;
    }

    public static List<String> calc2(List<String> numbers) {
        double result = 0;
        for (int i = 0; ; i++) {
            if (isSignMultiplicationOrDivision(numbers.get(i))) {
                result = actionMultiplicationOrDivision(numbers.get(i), Double.parseDouble(numbers.get(i - 1)), Double.parseDouble(numbers.get(i + 1)));
                numbers.set(i - 1, String.valueOf(result));
                numbers.remove(i);
                numbers.remove(i);
                i = i - 1;
            }
            if (i == numbers.size() - 1) break;
        }
        return numbers;
    }

    public static double actionSumOrSubtraction(String sign, double a, double b) {
        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            default -> 0;
        };
    }

    public static double actionMultiplicationOrDivision(String sign, double a, double b) {
        return switch (sign) {
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }
}
