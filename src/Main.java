import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
        Function<String, Integer> countSymbols = new Function<>() {
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

        System.out.println("ints = " + IntStream.of(1, 4, 9, 18).filter(i -> i % 3 == 0).boxed().toList());

        List<String> list = List.of("one", "two", "three")
                .stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("List = " + list);

        //создаём без лишнего List
        list = Stream.of("one", "two", "three")
                .map(String::toUpperCase)
                .toList();
        System.out.println("List from stream = " + list);

        //используют,когда вначале не известен весь набор элементов
        list = Stream.<String>builder()
                .add("one")
                .add("two")
                .add("three")
                .build()
                .map(String::toUpperCase)
                .toList();
        System.out.println("List from builder = " + list);

        List<Integer> powers2 = Stream.iterate(1,i -> i*2)
                .takeWhile(i -> i < 10000) //ограничитель
                .toList();
        System.out.println("power2 = " + powers2);

        //передаём без параметра
        List<Integer> randomInts = Stream.generate(() -> new Random().nextInt())
                .limit(5) //ограничиваемся в 5 чисел
                .toList(); //собираем элементы в лист
        System.out.println("randomInts = " + randomInts);
//испльуем коллектор
        Set<String> stringSet = List.of("one", "two", "three","two","one")
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        System.out.println("stringSet = " + stringSet);

        //в порядке возрастания букв
        Set<String> stringSet1 = List.of("one", "two", "three","two","one")
                .stream()
                .map(String::toUpperCase)
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println("stringSet1 = " + stringSet1);

        //кол-во чётных элементов
        long count = Stream.of(arr)
                .map(Integer::valueOf)
                .filter(i -> i%2 == 0)
                .count();
        System.out.println("count = " + count);

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

