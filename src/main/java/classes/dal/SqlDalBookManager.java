package classes.dal;

import interfaces.dal.IDalBookManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class SqlDalBookManager implements IDalBookManager{
    //JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/library";
    private static final String userDB = "root";
    private static final String passwordDB = "13784765";

    //JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private void setDriver(String driver){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBookInDB(String name,String author, String genre, String year) {
        String query = "INSERT books (name, author, genre, year, presence)\n" +
                "VALUES\n" +
                "    ('"+name+"','"+author+"', '"+genre+"',"+year+",true)";
        doQuery(query);
    }

    @Override
    public ArrayList getListOfAllBooks(){
        String query = "SELECT * FROM books;";
        ArrayList booksBy6 = new ArrayList();
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();
            stmt.execute(query);
            //извлекаем данные
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                booksBy6.add(rs.getString(1));
                booksBy6.add(rs.getString(2));
                booksBy6.add(rs.getString(3));
                booksBy6.add(rs.getString(4));
                booksBy6.add(rs.getString(5));
                booksBy6.add(rs.getString(6));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
        return booksBy6;
    }

    @Override
    public void deleteAllBooks() {
        String query = "TRUNCATE TABLE books";
        doQuery(query);
    }

    @Override
    public void deleteAllDecommissionedBooks() {
        String query = "TRUNCATE TABLE decommissioned_books";
        doQuery(query);
    }

    @Override
    public void updateBookPresence(String id, boolean presence){
        String query = "update books\n" +
                "set presence = "+presence+"\n" +
                "where book_id = '"+id+"'";
        doQuery(query);
    }

    public List getBookByName(String name){
        String query = "SELECT * FROM books WHERE books.name = '"+name+"' ;";
        ArrayList book = new ArrayList();
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();
            stmt.execute(query);
            //извлекаем данные
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                book.add(rs.getString(2));
                book.add(rs.getString(3));
                book.add(rs.getString(4));
                book.add(rs.getString(5));

            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
        return book;
    }

    @Override
    public String checkBookById(String id){
        String query = "SELECT * FROM books WHERE books.book_id = '"+id+"' ;";
        String bookB = "0";
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();
            stmt.execute(query);
            //извлекаем данные
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                if(!rs.getString(2).isEmpty()){
                    bookB = "1";
                }

            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
        return bookB;
    }

    @Override
    public String getBookPresence(String id){
        String query = "SELECT * FROM books WHERE books.book_id = '"+id+"' ;";
        String bookPresence = "0";
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();
            stmt.execute(query);
            //извлекаем данные
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                if(Integer.parseInt(rs.getString(6)) == 1)
                    bookPresence = "1";

            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
        return bookPresence;
    }

    public void deleteBookById(String id){
        String query = "DELETE from books\n" +
                "where book_id = '"+id+"'";
        doQuery(query);
    }

    public String getBookNameById(String id){
        String query = "SELECT * FROM books WHERE books.book_id = '"+id+"' ;";
        String bookName = "";
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();
            stmt.execute(query);
            //извлекаем данные
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                bookName = rs.getString(2);
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
        return bookName;
    }

    @Override
    public void addDecommissionedBook(String id, String who, String reason){
        String bookName = getBookNameById(id);
        deleteBookById(id);
        String query = "INSERT decommissioned_books (name, who, reason)\n" +
                "VALUES\n" +
                "    ('"+bookName+"','"+who+"', '"+reason+"')";
        doQuery(query);
    }

    @Override
    public String checkDecommissionedBookById(String id){
        String query = "SELECT * FROM decommissioned_books WHERE decommissioned_books.dbook_id = '"+id+"' ;";
        String dBookB = "0";
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();
            stmt.execute(query);
            //извлекаем данные
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                if(!rs.getString(2).isEmpty()){
                    dBookB = "1";
                }

            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
        return dBookB;
    }

    private void doQuery(String query){
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();
            stmt.execute(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
    }



}
