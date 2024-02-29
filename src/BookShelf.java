import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class BookShelf {
    private List<Book> books = new ArrayList<>();

    static class Book {
        String title;
        String author;
        int year;

        Book(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }

        @Override
        public String toString() {
            return title + " by " + author + ", " + year;
        }
    }

    void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }

    void removeBook(String title) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.title.equals(title)) {
                iterator.remove();
                break;
            }
        }
    }

    void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    class ShelfIterator implements Iterator<Book> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < books.size();
        }

        @Override
        public Book next() {
            if (hasNext()) {
                Book book = books.get(currentIndex);
                currentIndex = (currentIndex + 1) % books.size();
                return book;
            } else {
                return null;
            }
        }
    }

    Iterator<Book> iterator() {
        return new ShelfIterator();
    }

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        bookShelf.addBook("To Kill a Mockingbird", "Harper Lee", 1960);
        bookShelf.addBook("1984", "George Orwell", 1949);

        System.out.println("Books on the shelf:");
        bookShelf.displayBooks();

        System.out.println("\nRemoving 'To Kill a Mockingbird' from the shelf.");
        bookShelf.removeBook("To Kill a Mockingbird");

        System.out.println("\nRemaining books on the shelf:");
        bookShelf.displayBooks();

        System.out.println("\nUsing Iterator to display books:");
        Iterator<Book> shelfIterator = bookShelf.iterator();
        while (shelfIterator.hasNext()) {
            System.out.println(shelfIterator.next());
        }
    }
}
