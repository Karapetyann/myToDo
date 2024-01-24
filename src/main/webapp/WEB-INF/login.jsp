<%--
  Created by IntelliJ IDEA.
  User: Hakob
  Date: 23.01.2024
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/styleLogin.css">
    <script src="js/appLogin.js"></script>
    <%if (request.getSession().getAttribute("msg") != null) {%>
    <span style="color: red"><h2><%=session.getAttribute("msg")%></h2></span>
    <%session.removeAttribute("msg"); %>
    <%}%>
</head>
<body>
<div class="page">
    <div class="container">
        <div class="left">
            <div class="login">Login</div>
            <div class="eula"> Please input your Email and Password</div>
        </div>
        <div class="right">
            <svg viewBox="0 0 400 400">
                <defs>
                    <linearGradient
                            inkscape:collect="always"
                            id="linearGradient"
                            x1="13"
                            y1="193.49992"
                            x2="307"
                            y2="193.49992"
                            gradientUnits="userSpaceOnUse">
                        <stop
                                style="stop-color:#ff00ff;"
                                offset="0"
                                id="stop876" />
                        <stop
                                style="stop-color:#ff0000;"
                                offset="1"
                                id="stop878" />
                    </linearGradient>
                </defs>
                <path d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143" />
            </svg>
            <div class="form">
                <form action="/login" method="post">
                <label for="email">Email</label>
                <input type="text" id="email" name="email">
                <label for="password">Password</label>
                <input type="password" id="password" name="password">
                <input type="submit" id="submit" value="login">
                </form>
                <a style="color: white" href="/register">Register</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
