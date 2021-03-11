package Collections.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class ArrayTest {

    @Test
    public void addTest() {
        MyArray<String> list = new MyArray<>();
        list.add("Test");
        list.add("Array");
        for (int i = 0; i < 8; i++) {
            Assert.assertTrue(list.add(0, "Test"));
        }
    }

    @Test
    public void testRemove() {
        MyArray<String> list = new MyArray<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.remove(1);
        list.remove(0);
        Assert.assertEquals("c", list.get(0));
    }
}