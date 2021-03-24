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
    public void testConstructor() {
        MyImmutableClass immutableClass = new MyImmutableClass(4, "Test", new Date());
        assertEquals(4, immutableClass.getCount());
        assertEquals("Test", immutableClass.getName());
        assertEquals(new Date(), immutableClass.getCreation());

    }

}