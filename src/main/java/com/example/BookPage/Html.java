package com.example.BookPage;

import java.util.List;

public class Html {
    public static String SITE = " <title>BookDatabase - s20516</title>" +
            "<body style=\"background-color: #a3cde3;\"> " +
            "<div style=\"text-align: center;\"> " +
            "<h1 style=\"font-size: 30px;font-family: Arial,sans-serif\">Baza ksiazek derby</h1><p>Kliknij 'wyszukaj' aby wyswietlic wszystkie dostepne ksiazki</p> " +
            "<form  text-align: center;"
            + " action = hello-servlet>\r\n " +
            "<input type=\"text\" name =\"name\" style=\"background-color: white;background-image: url('http://assets.stickpng.com/images/585e4ad1cb11b227491c3391.png');background-position: 10px 10px;background-repeat: no-repeat;padding-left: 30px;transition: width 0.4s ease-in-out\">\r\n " +
            "<select name = \"po\">\r\n" +
            "<option>Wyszukiwanie po autorze</option>\r\n " +
            "<option>Wyszukiwanie po nazwie</option>\r\n " +
            "<option>Wyszukiwanie po isbn</option>\r\n " +
            "</select>\r\n " +
            "<input type=\"submit\" value=\"Wyszukaj\">\r\n \r\n </form> " +
            "</body> </div>";

    public static String tableContent = "<html> <head> <style> table { font-family: arial, sans-serif; border-collapse: collapse;"
            + "width: 80%; margin-left:auto;margin-right:auto;} td, th { border: 1px solid #000000; padding: 4px; height: 10px } {"
            + "background-color: #6c7c93; } </style> </head> <body> <table> <tr> <th>ISBN</th> <th>AUTOR</th>"
            + "<th>TYTUL</th> <th>ROK</th> <th>CENA</th> </tr>";

    public static void resetTableContent() {
        tableContent = "<html> <head> <style> table { font-family: arial, sans-serif; border-collapse: collapse;"
                + "width: 80%; margin-left:auto;margin-right:auto;} td, th { border: 1px solid #000000; padding: 4px; height: 10px } {"
                + "background-color: #6c7c93; } </style> </head> <body> <table> <tr> <th>ISBN</th> <th>AUTOR</th>"
                + "<th>TYTUL</th> <th>ROK</th> <th>CENA</th> </tr>";
    }

    public static void buildTable(List<String[]> booksToPrint) {
        for (String[] book : booksToPrint) {
            Html.tableContent += "<tr>";
            for (String informationAboutBook : book) {
                    Html.tableContent += "<td>" + informationAboutBook + "</td>";
            }
            Html.tableContent += "</tr>";
        }
        Html.tableContent += "</table> </body>";
    }
}
