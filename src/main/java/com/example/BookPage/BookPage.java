package com.example.BookPage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet("/hello-servlet")
public class BookPage extends HttpServlet {

    private final String DATABASE_NAME = "BookPage";
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/" + DATABASE_NAME;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(Html.SITE);

            String nameParameter = request.getParameter("name");

            SearchBy selectedSearchBy = getSearchBy(request.getParameter("po"));

            String name = nameParameter;
            if (nameParameter.isEmpty()) {
                name = "wzystkieKsiazki";
            }

            List<String[]> foundedBooks = findBooks(name, selectedSearchBy);

            Html.buildTable(foundedBooks);

            printWriter.println(Html.tableContent);
            printWriter.close();

            Html.resetTableContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SearchBy getSearchBy(String poParameter) {
        SearchBy selectedSearchBy;
        switch (poParameter.toLowerCase(Locale.ROOT)) {
            case "wyszukiwanie po autorze":
                selectedSearchBy = SearchBy.AUTHOR;
                break;
            case "wyszukiwanie po isbn":
                selectedSearchBy = SearchBy.ISBN;
                break;
            default:
                selectedSearchBy = SearchBy.NAME;
                break;
        }
        return selectedSearchBy;
    }

    private List<String[]> findBooks(String name, SearchBy selectedSearchBy) {
        List<String[]> foundedBooks;
        if (name.equals("wzystkieKsiazki")) {
            foundedBooks = retrieveBooks(Sql.SELECT_ALL_BOOKS);
        } else {
            switch (selectedSearchBy) {
                case AUTHOR:
                    foundedBooks = retrieveBooks(Sql.SELECT_BOOKS_WITH_AUTHOR_LIKE(name));
                    break;
                case ISBN:
                    foundedBooks = retrieveBooks(Sql.SELECT_BOOKS_WITH_ISBN_LIKE(name));
                    break;
                default:
                    foundedBooks = retrieveBooks(Sql.SELECT_BOOKS_WITH_TITLE_LIKE(name));
                    break;
            }
        }
        return foundedBooks;
    }

    private List<String[]> retrieveBooks(String sqlQuery) {
        ArrayList<String[]> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection.getMetaData();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sqlQuery);
            rs.afterLast();
            while (rs.previous()) {
                String[] bookDetails = new String[5];
                bookDetails[0] = rs.getString(1); //isbn
                bookDetails[1] = rs.getString(2); //autor
                bookDetails[2] = rs.getString(3); //tytul
                bookDetails[3] = rs.getString(4); //rok
                bookDetails[4] = rs.getString(5); //cena
                books.add(bookDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

}