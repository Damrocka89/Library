<%@ page import="app.Author" %>
<%@ page import="app.Category" %>
<%@ page import="app.FileReaderFromFileToList" %>
<%@ page import="app.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Library</h2>
<center> <font color="gray" size="7"> Hello World! </font> </center>


    // To jest skryptlet.  Zauważ, że zmienna
    // "date" zadeklarowana w pierwszym wbudowanym
    // wyrażeniu jest dostępna również w tym późniejszym.

    <jsp:useBean id="fileReader" class="app.Klasa" scope="session"></jsp:useBean>
    <jsp:setProperty property="*" name="fileReader"/>

<%
//    List<Author> authors=fileReader.readAuthorsFromFile();
//    List<Category> categories=fileReader.readCathegoriesFromFile();
//    List<Book> books=fileReader.readListOfBooksFromFile(categories,authors);
//    java.util.Date date = new java.util.Date();
%>

<%--<h2>Books</h2>--%>

        <%--<table>--%>
            <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th>bookId</th>--%>
                    <%--<th>title</th>--%>
                    <%--<th>isbnNumber</th>--%>
                    <%--<th>year</th>--%>
                    <%--<th>typeOfBinding</th>--%>
                    <%--<th>authors</th>--%>
                    <%--<th>category</th>--%>
                <%--</tr>--%>
            <%--</thead>--%>

            <%--<tbody>--%>
               <%--&lt;%&ndash;<%for(Book book:books){%>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td><%=book.getBookId()%></td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td><%=book.getTitle()%></td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td><%=book.getIsbnNumber()%></td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td><%=book.getYear()%></td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td><%=book.getTypeOfBinding()%></td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td><%=book.getAuthors()%></td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td><%=book.getCategory()%></td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<%};%>&ndash;%&gt;--%>
                <%--</tr>--%>

            <%--</tbody>--%>
        <%--</table>--%>
<%--Obecnie mamy <%= date %>--%>
</body>
</html>
