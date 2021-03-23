package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Advanced {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        list = list.stream().collect(Collectors.toList());
        System.out.println(list);

        List<Integer> list2 = Arrays.asList(4, 5, 6);

        list = Stream.concat(list.stream(), list2.stream())
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
