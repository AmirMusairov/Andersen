package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Advanced2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        list = list.stream().collect(Collectors.toList());
        System.out.println(list);

        int[] nums = {4, 5, 6};

        list.addAll(Arrays.stream(nums).boxed()
                .collect(Collectors.toList()));
        System.out.println(list);
    }
}
