package Immutable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestImmutable {
    @Test
    public void modificationTest() {
        final String name = "immutable";
        final String newName = name.replace("table", "-");

        assertEquals("immutable", name);
        assertEquals("immu-", newName);
    }

    @Test
    public void sizeTest() {
        final List<String> strings = new ArrayList<>();
        assertEquals(0, strings.size());
        strings.add("Immutable");
        assertEquals(1, strings.size());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testImmutableObject() {
        String s = "Immutable";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                count++;
            }
        }
        MyImmutableClass immutable = new MyImmutableClass(count, s, new Date(2021, 3, 23));
        assertEquals(immutable.getCount(), 9);
        assertEquals(immutable.getCreation(), new Date(2021, 3, 23));
        assertEquals(immutable.getName(), "Immutable");
    }
}