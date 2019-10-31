package kaehnd;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Functional {


    public static void main(String[] args) {
        String[] words = {"0rugygs", "OtherDrugs", "LotsOFDrugssssssss", "DSF:SDFJDS"};
        List<String> list = new ArrayList<>();
        for (String str: words){
            list.add(str);
        }


       Function<String, String> function = s -> s.toUpperCase();
        function.apply("dsfdf");
        function = s -> s.replace('a', 'e');

        Consumer<String> consume = s -> System.out.println(s);
        list.parallelStream()
                .map(s -> s.replace(s.charAt(0), '0'))
                .forEach(consume);
        for (String word : list) {
            System.out.println(word);
        }
    }

    private static double averageLength(List<String> list, StringStuff ss){
        return list.stream()
                .map(s -> s.toLowerCase())
                .filter(s -> ss.doStuff(s))
                .mapToInt(s -> s.length())
                .average()
                .getAsDouble();
    }


    private static String stuff(String s, StringStuff stringStuff){
        if(stringStuff.doStuff(s)){
            return s + " is big";
        } else{
            return s + " is not big";
        }
    }
}
