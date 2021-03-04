package Patterns.Iterator;

public class LanguageRepository implements Container {
    public String languages[] = {"Java", "Python", "PHP", "JavaScript"};

    @Override
    public Iterator getIterator() {
        return new LanguageIterator();
    }

    private class LanguageIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < languages.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return languages[index++];
            }
            return null;
        }
    }
}
