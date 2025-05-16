<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Cart</title>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%@include file="/Components/navBarComponent.jsp" %>
        <!-- Product section-->
        <section class="py-5">
            <div class="container" style="min-height: 1000px">
                <h3>List Tours</h3>
                <form action="checkout" method="GET">
                    <table class="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th scope="col">Name</th>
                                <th scope="col">Image</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total Price</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cart}" var="c">
                                <tr>
                                    <td><input type="checkbox" name="select" value="${c.key.tourID}"></td>
                                    <td>${c.key.tourName}</td>
                                    <td><img src="${c.key.tourImage}" width="50"/></td>
                                    <td>${c.key.tourPrice}</td>
                                    <td><input type="number" name="quantity" onchange="validate(${c.key.tourID}, this)" value="${c.value}" min="1"/></td>
                                    <td>${c.key.tourPrice*c.value}</td>
                                    <td><a href="deleteitem?tourID=${c.key.tourID}" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Delete</a></td>
                                </tr>
                            </c:forEach>  
                        </tbody>
                    </table>
                    <input type="submit" class="btn btn-primary w-100" value="Check Out"/>
                </form>
                <span style="color: red; font-size: 18px; font-weight: bold">${error}</span>
            </div>
        </section>
        <%@include file="/Components/footerComponent.jsp" %>
        <script>
            function validate(id, input) {
                var value = parseInt(input.value);
                var min = parseInt(input.min);
                if (value < min) {
                    input.value = min;
                }
        </script>
    </body>
</html>
