package Immutable;

import java.util.Date;

public final class MyImmutableClass {

    private final int count;

    private final String name;

    private final Date creation;

    public MyImmutableClass(int count, String name, Date creation) {
        this.count = count;
        this.name = name;
        this.creation = new Date(creation.getTime());
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public Date getCreation() {
        return new Date(creation.getTime());
    }

    public static void main(String[] args) {
        String s = "Immutable";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                count++;
            }
        }
        MyImmutableClass immutableClass = new MyImmutableClass(count, s, new Date());
        System.out.println("Name of String: " + immutableClass.getName());
        System.out.println("Count of symbols: " + immutableClass.getCount());
        System.out.println("Date of creation: " + immutableClass.getCreation());
    }
}
