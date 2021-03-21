package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FirstPart {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("=====Using multiples=====");
        list = list.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(list);

        System.out.println("=====Using map to increase=====");
        list.stream()
                .map(n -> n + 1)
                .forEach(System.out::println);

        System.out.println("=====Using foreach=====");
        list.stream().map(n -> n + 2)
                .parallel()
                .forEach(System.out::println);

        System.out.println("=====Using forEachOrdered=====");
        list.stream().map(n -> n + 2)
                .parallel()
                .forEachOrdered(System.out::println);
    }
}
