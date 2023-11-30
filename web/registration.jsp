<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Management System</title>
    </head>
    <body>

        <%-- Display success or failure messages --%>
        <% String status = (String) request.getAttribute("status"); %>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>

        <% if ("success".equals(status)) { %>
        <p style="color: green;">Registration successful! Please proceed to login.</p>
        <% } else if ("failed".equals(status)) { %>
        <p style="color: red;"><%= errorMessage %></p>
        <% } %>
        
<!--        <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">-->
        
        <h1>Library Management System</h1>
        <h2>Sign Up</h2>
        <form method ="post" action="RegistrationServlet">
            Name<input type="text" id="Name" name="name"><br>
            Email<input type="email" id="Email" name="email"><br>
            Password<input type="password" id="Password" name="password"><br>
            <a href="login.jsp">Already have an account? Sign In</a>
            <input type="submit" value="Sign Up">
        </form>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel='stylesheet' href="alert/dist/sweetalert.css">
        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status == "success") {
                swal("Account successfully created!");
            }
        </script>
    </body>
</html>
