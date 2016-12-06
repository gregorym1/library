<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library</title>
    <style type="text/css">
        nav {
            display: block;
            background: white;
        }
        ul {
            list-style: none;
            margin: 0;
            padding: 0;
        }
        a {
            text-decoration: none;
            outline: none;
            -webkit-transition: .4s ease-in-out;
            transition: .4s ease-in-out;
        }
        .myform{
            width:50%;
            text-align: center;
            list-style-type:none;
            list-style-position:outside;
            margin:0px;
            margin-left: 25%;
            margin-right: 25%;
            padding:0px;
        }
        .myform h2 {font-family:Georgia, Times, "Times New Roman", serif;}
        .topmenu {
            display: inline-block;
            width: 100%;
            text-align: center;
            margin: 10px 0;
        }
        .topmenu:after {
            content: "";
            display: table;
            clear: both;
        }
        .topmenu li {
            display: inline-block;
            position: relative;
        }
        .topmenu > li > a{
            display: block;
            padding: 12px 26px;
            color: #767676;
            text-transform: uppercase;
            font-weight: bold;
            letter-spacing: 1px;
            position: relative;
            font-family: 'Exo 2', sans-serif;
        }
        .topmenu > li:after {
            content: "";
            position: absolute;
            right: 0;
            width: 1px;
            height: 12px;
            background: #d2d2d2;
            top: 16px;
            box-shadow: 4px -2px 0 #d2d2d2;
            -webkit-transform: rotate(30deg);
            -moz-transform: rotate(30deg);
            transform: rotate(30deg);
        }
        .topmenu > li:last-child:after {
            background: none;
            box-shadow: none;
        }
        .topmenu > li:hover > a{
            color: #c0a97a;
        }
        .submenu {
            visibility: hidden;
            opacity: 0;
            position: absolute;
            width: 210px;
            background: #fafafa;
            border: 1px solid #ededed;
            left: 50%;
            margin-left: -105px;
            z-index: 10;
            -webkit-transform: scale(.8);
            -moz-transform: scale(.8);
            transform: scale(.8);
            -webkit-transition: .4s ease-in-out;
            transition: .4s ease-in-out;
        }
        .submenu > li {
            display: block;
        }
        li:hover > .submenu {
            visibility: visible;
            opacity: 1;
            -webkit-transform: scale(1);
            -moz-transform: scale(1);
            transform: scale(1);
        }
        .submenu li a {
            padding: 10px 0;
            margin: 0 10px;
            border-bottom: 1px solid #efefef;
            font-size: 12px;
            color: #484848;
            display: block;
            font-family: 'Kurale', serif;
        }
        .submenu li a:hover {
            color: #c0a97a;
        }
    </style>
</head>
<body>
    <nav>
        <ul class="topmenu">
            <li><a href="#">Книги</a>
                <ul class="submenu">
                    <li><a href="addBook.jsp">Добавить</a></li>
                    <li><a href="giveBook.jsp">Выдать</a></li>
                    <li><a href="takeBook.jsp">Принять</a></li>
                    <li><a href="deleteBook.jsp">Списать</a></li>
                    <li><a href="showBooks.jsp">Показать все</a></li>
                </ul>
            </li>
            <li><a href="#">Посетители</a>
                <ul class="submenu">
                    <li><a href="addVisitor.jsp">Добавить</a></li>
                    <li><a href="showVisitors.jsp">Показать всех</a></li>
                </ul>
            </li>
            <li><a href="find.jsp">Поиск</a></li>
        </ul>
    </nav>
    <form class="myform"  >

                <h2>${info}</h2>

    </form>
</body>
</html>


