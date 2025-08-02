import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Skypr Uni - Java Profession start!");

        String[] arr = {"1", "2", "3", "4", "5", "6", "111", "222", "1024"};
        ArrayList<Integer> result = new ArrayList<>();

        for (String i : arr) {
            Integer value = Integer.valueOf(i);
            if (value % 2 != 0) {
                continue;
            }
            value = value * value;
            result.add(value);
        }
        System.out.println("result = " + result);

        List<Integer> resultFromArray = Arrays.stream(arr)
                .map(Integer::valueOf)
                .filter(new CheckEven().and(new CheckMoreThan9()))
                //будет выводить всё, что в него попало. Чётные больше 9
                .peek(System.out::println)
                .map(i -> i * i)
                .toList();
        System.out.println("resultFromArray = " + resultFromArray);
//проверяем чисо 111 на чётность
        System.out.println("new CheckEven().test(111) = " + new CheckEven().test(111));
        //переводит из строчки в число и считает количество необходимых элементов
        Function<String, Integer> countSymbols = new Function<>(){
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println("countSymbols.apply(\"123456\") = " + countSymbols.apply("123456"));

        //соплаер. Стрим, который будет делать бесконечное число 9-к. в данном случае 9 девяток
        System.out.println(" 9_9 = " + Stream.generate(() -> 9).limit(9).toList());

        //соплаер. Стрим, который будет делать в данном случае 9 рандомных чисел
        System.out.println(" 9_9 = " + Stream.generate(() -> Math.random()).limit(9).toList());

        System.out.println(" 9_9 = " + Stream.generate(Math::random).limit(9).toList());

        System.out.println("ints = " + IntStream.of(1,4,9,18).filter(i -> i % 3 == 0).boxed().toList());
    }

    static class CheckEven implements Predicate<Integer> {

        @Override
        public boolean test(Integer i) {
            return i % 2 == 0;
        }
    }

    //мето для нахождения двухэначных чисел
    static class CheckMoreThan9 implements Predicate<Integer> {
        @Override
        public boolean test(Integer integer) {
            return integer - 9 > 0;
        }
    }
}
