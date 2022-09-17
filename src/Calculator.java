import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        String str = "(14+23-(3*(6-3))/3+2)*(5-3)";
        System.out.println(calc3(numbersToList(str)));
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

            if (i == str1.length - 1 && !str1[i].equals(")") ) numbers.add(number);
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

    public static double calc3(List<String> numbers) {

        List<Integer> openBrasket = new ArrayList<>();
        for (int i = 0; ; i++) {

            if(!(i<numbers.size())) {
                break;
            }
            if (numbers.get(i).equals("(")) {
                openBrasket.add(i);
            }
            if (numbers.get(i).equals(")")) {
                if (openBrasket.size() == 1) {
                    int startIndex = openBrasket.get(0);
                    double res = calc3(numbers.subList(startIndex+1, i));
                   // numbers.set(startIndex, String.valueOf(res));
//                    for (int j = startIndex+1; j<=numbers.size(); j++){
//
//                    }
                    numbers.remove(startIndex);
                    numbers.remove(startIndex+1);

                }
                openBrasket.remove(openBrasket.size() - 1);

            }


        }



        return calc(calc2(numbers));
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
