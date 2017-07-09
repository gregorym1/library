package classes.dal;

import interfaces.dal.IDalVisitorManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 05.11.2016.
 */
public class SqlDalVisitorManager implements IDalVisitorManager{
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
    public void addVisitorInDB(String name,String lastname, String pasport, String year) {
        String query = "INSERT visitors (name, lastname, pasport, year, taken_book_id)\n" +
                "VALUES\n" +
                "    ('"+name+"','"+lastname+"', '"+pasport+"',"+year+",'0')";
        doQuery(query);
    }

    @Override
    public void deleteAllVisitors() {
        String query = "TRUNCATE TABLE visitors";
        doQuery(query);
    }

    @Override
    public void updateTakenBook(String idV, String idB){
        String query ="update visitors\n" +
                "set taken_book_id = '"+idB+"'\n" +
                "where visitor_id = '"+idV+"'";
        doQuery(query);
    }

    @Override
    public String getVisitorTakenBook(String id){
        String query = "SELECT * FROM visitors WHERE visitors.visitor_id = '"+id+"' ;";
        String bookPresence = "";
        try {
            //извлекаем данные
            rs = doQueryWithRS(query);

            while (rs.next()) {
                bookPresence = rs.getString(6);
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                rs.close();
            } catch (SQLException se) {  }
        }
        return bookPresence;
    }

    public List getVisitorByPasport(String pasport){
        String query = "SELECT * FROM visitors WHERE visitors.pasport = '"+pasport+"' ;";
        ArrayList visitor = new ArrayList();
        try {
            //извлекаем данные
            rs = doQueryWithRS(query);

            while (rs.next()) {
                visitor.add(rs.getString(2));
                visitor.add(rs.getString(3));
                visitor.add(rs.getString(4));
                visitor.add(rs.getString(5));
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                rs.close();
            } catch (SQLException se) {  }
        }
        return visitor;
    }

    @Override
    public ArrayList getListOfAllVisitors(){
        String query = "SELECT * FROM visitors;";
        ArrayList visitorsBy6 = new ArrayList();
        try {
            //извлекаем данные
            rs = doQueryWithRS(query);

            while (rs.next()) {
                visitorsBy6.add(rs.getString(1));
                visitorsBy6.add(rs.getString(2));
                visitorsBy6.add(rs.getString(3));
                visitorsBy6.add(rs.getString(4));
                visitorsBy6.add(rs.getString(5));
                visitorsBy6.add(rs.getString(6));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                rs.close();
            } catch (SQLException se) {  }
        }
        return visitorsBy6;
    }

    @Override
    public String checkVisitorById(String id){
        String query = "SELECT * FROM visitors WHERE visitors.visitor_id = '"+id+"' ;";
        String visitorB = "0";
        try {
            //извлекаем данные
            rs = doQueryWithRS(query);

            while (rs.next()) {
                if (!rs.getString(2).isEmpty())
                    visitorB = "1";
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //Close connection ,stmt and result set here
            try {
                rs.close();
            } catch (SQLException se) {  }
        }
        return visitorB;
    }
    private ResultSet doQueryWithRS(String query) throws SQLException {
        try {
            setDriver("com.mysql.jdbc.Driver");

            //Устанавливаем соединение
            con = DriverManager.getConnection(url, userDB, passwordDB);

            //создаем объект для работы с запросом запросу
            stmt = con.createStatement();

            return stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            throw new SQLException(sqlEx);//have to return something
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
