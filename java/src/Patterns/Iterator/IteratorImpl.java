package Patterns.Iterator;

public class IteratorImpl {
    public static void main(String[] args) {
        LanguageRepository languageRepository = new LanguageRepository();

        for (Iterator itr = languageRepository.getIterator(); itr.hasNext(); ) {
            String language = (String) itr.next();
            System.out.println("Language: " + language);
        }
    }
}
