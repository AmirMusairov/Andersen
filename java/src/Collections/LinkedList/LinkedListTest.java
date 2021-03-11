package Collections.LinkedList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LinkedListTest {

    @Test
    public void testMyLinkedList() {
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        assertEquals(0, linkedList.size());

        linkedList.addFirst("first");
        linkedList.addFirst("second");

        assertEquals(2, linkedList.size());
        assertEquals("second", linkedList.getFirst());
        assertEquals("first", linkedList.getLast());

        linkedList.addLast("last");
        assertEquals(3, linkedList.size());
        assertEquals("last", linkedList.getLast());

        assertEquals("second", linkedList.get(0));
        assertEquals("first", linkedList.get(1));
        assertEquals("last", linkedList.get(2));
        assertNull(linkedList.get(3));

        linkedList.remove(1);
        assertEquals(2, linkedList.size());
        assertEquals("last", linkedList.get(1));

        assertTrue(linkedList.insert(1, "new"));
        assertEquals(3, linkedList.size());
        assertEquals("new", linkedList.get(1));

    }
}