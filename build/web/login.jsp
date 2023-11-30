<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In Page</title>
    </head>
    <body>
        <%-- Display success or failure messages --%>
        <% String status = (String) request.getAttribute("status"); %>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>

        <% if ("success".equals(status)) { %>
        <p style="color: green;">Login successful!</p>
        <% } else if ("failed".equals(status)) { %>
        <p style="color: red;"><%= errorMessage %></p>
        <% } %>
        
        <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
        
        <h1>Library Management System</h1>
        <h2>Sign In</h2>
        <form method ="post" action="login">
            Email<input type="email" id="Email" name="email"><br>
            Password<input type="password" id="Password" name="password"><br>
            <a href="registration.jsp">Don't have an account? Sign Up</a>
            <input type="submit" value="Sign In">
        </form>
        
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel='stylesheet' href="alert/dist/sweetalert.css">
        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status == "failed") {
                swal("Wrong username or password!", "error");
            }
        </script>
    </body>
</html>
