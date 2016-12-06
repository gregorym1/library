package servlets;

import classes.svc.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/action-servlet")
public class ActionServlet extends HttpServlet {
    LibraryService libraryService = new LibraryService();
    String bookName, bookAuthor, bookGenre, bookYear,
            visitorName, visitorLastname, visitorPasport, visitorYear;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        action(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        action(request, response);
    }

    private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("addBook") != null) {
            request.setCharacterEncoding("utf-8");
            bookName = decodeGetParameter(request.getParameter("name"));
            bookAuthor = decodeGetParameter(request.getParameter("author"));
            bookGenre = decodeGetParameter(request.getParameter("genre"));
            bookYear = request.getParameter("year");
            if (libraryService.addBook(bookName,bookAuthor,bookGenre,bookYear).isStatus()) {
                request.setAttribute("info", "Книга добавлена!");
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }
            request.setAttribute("info", libraryService.operationStatus.getExeption());
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
        } else if (request.getParameter("addVisitor") != null) {
            request.setCharacterEncoding("utf-8");
            visitorName = decodeGetParameter(request.getParameter("name"));
            visitorLastname = decodeGetParameter(request.getParameter("lastname"));
            visitorPasport = decodeGetParameter(request.getParameter("pasport"));
            visitorYear = request.getParameter("year");
            if (libraryService.addVisitor(visitorName,visitorLastname,visitorPasport,visitorYear).isStatus()) {
                request.setAttribute("info", "Посетитель добавлен!");
                request.getRequestDispatcher("addVisitor.jsp").forward(request, response);
            }
            request.setAttribute("info", libraryService.operationStatus.getExeption());
            request.getRequestDispatcher("addVisitor.jsp").forward(request, response);
        } else if (request.getParameter("giveBook") != null) {
            request.setCharacterEncoding("utf-8");
            bookName = decodeGetParameter(request.getParameter("idBook"));
            visitorName = decodeGetParameter(request.getParameter("idVisitor"));
            if (libraryService.giveBookToVisitor(bookName,visitorName).isStatus()) {
                request.setAttribute("info", "Книга выдана!");
                request.getRequestDispatcher("giveBook.jsp").forward(request, response);
            }
            request.setAttribute("info", libraryService.operationStatus.getExeption());
            request.getRequestDispatcher("giveBook.jsp").forward(request, response);
        } else if (request.getParameter("takeBook") != null) {
            request.setCharacterEncoding("utf-8");
            bookName = decodeGetParameter(request.getParameter("idBook"));
            visitorName = decodeGetParameter(request.getParameter("idVisitor"));
            if (libraryService.takeBookFromVisitor(bookName,visitorName).isStatus()) {
                request.setAttribute("info", "Книга принята!");
                request.getRequestDispatcher("takeBook.jsp").forward(request, response);
            }
            request.setAttribute("info", libraryService.operationStatus.getExeption());
            request.getRequestDispatcher("takeBook.jsp").forward(request, response);
        } else if (request.getParameter("deleteBook") != null) {
            request.setCharacterEncoding("utf-8");
            String bookId = decodeGetParameter(request.getParameter("idBook"));
            String employee = decodeGetParameter(request.getParameter("who"));
            String reason = decodeGetParameter(request.getParameter("reason"));
            if (libraryService.addDecommissionedBook(bookId,employee,reason).isStatus()) {
                request.setAttribute("info", "Книга списана!");
                request.getRequestDispatcher("deleteBook.jsp").forward(request, response);
            }
            request.setAttribute("info", libraryService.operationStatus.getExeption());
            request.getRequestDispatcher("deleteBook.jsp").forward(request, response);
        } else if (request.getParameter("showAllBooks") != null) {
            request.setCharacterEncoding("utf-8");
            request.setAttribute("list", libraryService.getAllBooks());
            request.getRequestDispatcher("showBooks.jsp").forward(request, response);
        } else if (request.getParameter("showVisitorBooks") != null) {
            request.setCharacterEncoding("utf-8");
            request.setAttribute("list", libraryService.getVisitorBooks());
            request.getRequestDispatcher("showBooks.jsp").forward(request, response);
        }  else if (request.getParameter("showLibraryBooks") != null) {
            request.setCharacterEncoding("utf-8");
            request.setAttribute("list", libraryService.getLibararyBooks());
            request.getRequestDispatcher("showBooks.jsp").forward(request, response);
        } else if (request.getParameter("showAllVisitors") != null) {
            request.setCharacterEncoding("utf-8");
            request.setAttribute("list", libraryService.getAllVisitors());
            request.getRequestDispatcher("showVisitors.jsp").forward(request, response);
        } else if (request.getParameter("showActiveVisitors") != null) {
            request.setCharacterEncoding("utf-8");
            request.setAttribute("list", libraryService.getActiveVisitors());
            request.getRequestDispatcher("showVisitors.jsp").forward(request, response);
        } else if (request.getParameter("showPassiveVisitors") != null) {
            request.setCharacterEncoding("utf-8");
            request.setAttribute("list", libraryService.getPassiveVisitors());
            request.getRequestDispatcher("showVisitors.jsp").forward(request, response);
        } else if (request.getParameter("find") != null) {
            request.setCharacterEncoding("utf-8");
            String query = decodeGetParameter(request.getParameter("query"));
            request.setAttribute("listBooks", libraryService.findBooks(query));
            request.setAttribute("listVisitors", libraryService.findVisitors(query));
            request.getRequestDispatcher("find.jsp").forward(request, response);
        }
    }

    public static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
    }


}


