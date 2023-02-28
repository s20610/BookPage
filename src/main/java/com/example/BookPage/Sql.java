package com.example.BookPage;

public class Sql {

    public static final String SELECT_ALL_BOOKS = "SELECT pozycje.isbn, autor.name, pozycje.tytul, pozycje.rok, pozycje.cena\n" +
            "FROM autor,\n" +
            "     pozycje\n" +
            "where pozycje.autid = autor.autid\n" +
            "ORDER BY autor.name DESC";

    public static String SELECT_BOOKS_WITH_TITLE_LIKE(String title) {
        return "SELECT POZYCJE.ISBN, AUTOR.NAME, POZYCJE.TYTUL, POZYCJE.ROK, POZYCJE.CENA\n" +
                "FROM autor,\n" +
                "     pozycje\n" +
                "where POZYCJE.AUTID = AUTOR.AUTID\n" +
                "  AND POZYCJE.TYTUL LIKE '%" + title + "%'\n" +
                "ORDER BY AUTOR.NAME DESC";
    }

    public static String SELECT_BOOKS_WITH_AUTHOR_LIKE(String author) {
        return "SELECT POZYCJE.ISBN, AUTOR.NAME, POZYCJE.TYTUL, POZYCJE.ROK, POZYCJE.CENA\n" +
                "FROM autor,\n" +
                "     pozycje\n" +
                "where POZYCJE.AUTID = AUTOR.AUTID\n" +
                "  AND AUTOR.NAME LIKE '%" + author + "%'";
    }

    public static String SELECT_BOOKS_WITH_ISBN_LIKE(String isbn) {
        return "SELECT POZYCJE.ISBN, AUTOR.NAME, POZYCJE.TYTUL, POZYCJE.ROK, POZYCJE.CENA\n" +
                "FROM autor,\n" +
                "     pozycje\n" +
                "where POZYCJE.AUTID = AUTOR.AUTID\n" +
                "  AND POZYCJE.ISBN LIKE '%" + isbn + "%'";
    }
}
