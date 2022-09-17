import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        String str = "3+(11-7+24*6)/7+5";
        System.out.println(calc3((numbersToList(str))));
    }

    public static boolean isSign(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals(")"));
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
                if (!str1[i-1].equals(")")) numbers.add(number);
                numbers.add(str1[i]);
                number = "";
            } else if (!str1[i].equals("(")&&!str1[i].equals(")")) {

                number = number.concat(str1[i]);
            }

            if (str1[i].equals("(")){
                numbers.add(str1[i]);
            }

            if (i == str1.length - 1) numbers.add(number);
        }
        return numbers;
    }

    public static List<String> calc(List<String> numbers, int j) {
        double result = 0;
        for (int i = j; ; i++) {

            if (isSignPlusOrMinus(numbers.get(i))) {
                result = actionSumOrSubtraction(numbers.get(i), Double.parseDouble(numbers.get(i - 1)), Double.parseDouble(numbers.get(i + 1)));
                numbers.set(i - 1, String.valueOf(result));
                numbers.remove(i);
                numbers.remove(i);
                i = i - 1;
            }
            if (i == numbers.size() - 1) break;
            if(numbers.get(i).equals(")")){
                break;
            }
        }
        return numbers;
    }

    public static List<String> calc2(List<String> numbers, int j) {
        double result = 0;
        for (int i = j; ; i++) {

            if (isSignMultiplicationOrDivision(numbers.get(i))) {
                result = actionMultiplicationOrDivision(numbers.get(i), Double.parseDouble(numbers.get(i - 1)), Double.parseDouble(numbers.get(i + 1)));
                numbers.set(i - 1, String.valueOf(result));
                numbers.remove(i);
                numbers.remove(i);
                i = i - 1;
            }
            if (i == numbers.size() - 1) break;
            if(numbers.get(i).equals(")")){
                break;
            }
        }
        return numbers;
    }

    public static double calc3(List<String> numbers) {
        double result = 0;
        for (int i = 0; i<numbers.size()-1 ; i++) {
            if (numbers.get(i).equals("(")) {

                calc(calc2(numbers,i),i);
                //numbers.set(i - 1, String.valueOf(result));
                numbers.remove(i);
                numbers.remove(i + 1);

            }
        }
            //if(!numbers.get(i).equals("("))
            calc(calc2(numbers,0),0);
            result = Double.parseDouble(numbers.get(0));
            //break;


        return result;
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
