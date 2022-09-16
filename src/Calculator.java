import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        String str = "23+43+35+5-32";
        System.out.println(numbers(str));

    }

    public static boolean znaki(String s){
        return (s.equals("+")||s.equals("-"));
    }

    public static int numbers(String str){
        String [] str1 = str.split("");
        String chislo="";
        List<String> chisla = new ArrayList<>();
        List<Integer> chislaInt = new ArrayList<>();
        for (int i=0; i<str1.length;i++){
            if (znaki(str1[i])) {
                chisla.add(chislo);
                chisla.add(str1[i]);
                chislo="";
            }else chislo = chislo.concat(str1[i]);

            if (i == str1.length-1) chisla.add(chislo);

        }

        for (String s:chisla){
            if (!znaki(s)) chislaInt.add(Integer.parseInt(s));
        }


        for (int i=0; i<chisla.size()-1; i++) {
            switch (chisla.get(i)){
                case "+":{
                    int j=1;
                    chislaInt.add(0,chislaInt.get(j-1)+chislaInt.get(j));
                    chislaInt.remove(j);
                    if (chislaInt.size()!=2) chislaInt.remove(j);
                    break;
                }
                case "-":{
                    int j=1;
                    chislaInt.add(0,chislaInt.get(j-1)-chislaInt.get(j));
                    chislaInt.remove(j);
                    if (chislaInt.size()!=2)chislaInt.remove(j);
                    break;
                }

            }

        }
        return chislaInt.get(0);


    }


}
