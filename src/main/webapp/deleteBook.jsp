
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Delete book</title>
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

        .myform h2, .myform label {font-family:Georgia, Times, "Times New Roman", serif;}
        .myform ul {
            width:50%;
            text-align: center;
            list-style-type:none;
            list-style-position:outside;
            margin:0px;
            margin-left: 25%;
            margin-right: 25%;
            padding:0px;
        }
        .myform li{
            padding:12px;
            border-bottom:1px solid #eee;
            position:relative;
        }
        .myform li:first-child, .myform li:last-child {
            border-bottom:1px solid #777;
        }
        .myform label {
            width:150px;
            margin-top: 3px;
            display:inline-block;
            float:left;
            padding:3px;
        }
        .myform input {
            height:20px;
            width:220px;
            padding:5px 8px;
        }
        .myform textarea {padding:8px; width:300px;}
        .myform button {margin-left:156px;}
        .myform {
            width: 100%;
            text-align: center;
            margin: 10px 0;
        }
        /* Button Style */
        button.submit {
            background-color: #68b12f;
            background: -webkit-gradient(linear, left top, left bottom, from(#68b12f), to(#50911e));
            background: -webkit-linear-gradient(top, #68b12f, #50911e);
            background: -moz-linear-gradient(top, #68b12f, #50911e);
            background: -ms-linear-gradient(top, #68b12f, #50911e);
            background: -o-linear-gradient(top, #68b12f, #50911e);
            background: linear-gradient(top, #68b12f, #50911e);
            border: 1px solid #509111;
            border-bottom: 1px solid #5b992b;
            border-radius: 3px;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            -ms-border-radius: 3px;
            -o-border-radius: 3px;
            box-shadow: inset 0 1px 0 0 #9fd574;
            -webkit-box-shadow: 0 1px 0 0 #9fd574 inset ;
            -moz-box-shadow: 0 1px 0 0 #9fd574 inset;
            -ms-box-shadow: 0 1px 0 0 #9fd574 inset;
            -o-box-shadow: 0 1px 0 0 #9fd574 inset;
            color: white;
            font-weight: bold;
            padding: 6px 20px;
            text-align: center;
            text-shadow: 0 -1px 0 #396715;
        }
        button.submit:hover {
            opacity:.85;
            cursor: pointer;
        }
        button.submit:active {
            border: 1px solid #20911e;
            box-shadow: 0 0 10px 5px #356b0b inset;
            -webkit-box-shadow:0 0 10px 5px #356b0b inset ;
            -moz-box-shadow: 0 0 10px 5px #356b0b inset;
            -ms-box-shadow: 0 0 10px 5px #356b0b inset;
            -o-box-shadow: 0 0 10px 5px #356b0b inset;
        }
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

<form class="myform" action="/action-servlet" method="post" name="contact_form" >
    <ul>
        <li>
            <h2>Списать книгу</h2>
        </li>
        <li>
            <label>Книга №:</label>
            <input type="text" name="idBook" />
        </li>
        <li>
            <label>Имя сотрудника:</label>
            <input type="text" name="who" />
        </li>
        <li>
            <label>Причина:</label>
            <input type="text" name="reason" />
        </li>
        <li>
            <button name="deleteBook" class="submit" type="submit">Списать</button>
        </li>
        <li>
            <h2>${info}</h2>
        </li>
    </ul>
</form>
</body>
</html>


