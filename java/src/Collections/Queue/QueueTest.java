package Collections.Queue;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class QueueTest {
    @Test
    public void testMyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(5);
        queue.enqueue(21);
        queue.enqueue(30);
        queue.enqueue(55);

        assertFalse(queue.isEmpty());

        // peek
        assertEquals(Integer.valueOf(5), queue.peek());
        assertEquals(Integer.valueOf(5), queue.peek());

        // remove
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(21), queue.peek());
        assertFalse(queue.isEmpty());

        assertEquals(Integer.valueOf(21), queue.dequeue());
        assertEquals(Integer.valueOf(30), queue.dequeue());
        assertEquals(Integer.valueOf(55), queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
