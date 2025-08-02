import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
                .map(i -> i * i)
                .toList();
        System.out.println("resultFromArray = " + resultFromArray);
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
