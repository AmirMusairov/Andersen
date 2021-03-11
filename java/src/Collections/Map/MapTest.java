package Collections.Map;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class MapTest {

    @Test
    public void testMyMap() {
        MyMap<String, String> myMap = new MyMap<>(3);
        myMap.put("Human", "Men");
        myMap.put("Animal", "Cat");
        myMap.put("Fish", "Shark");

        assertNotNull(myMap);
        assertEquals(3, myMap.size());
        assertEquals("Cat", myMap.get("Animal"));
        assertEquals("Shark", myMap.get("Fish"));
        assertEquals("Men", myMap.get("Human"));
    }
}