<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%@include file="/Components/navBarComponent.jsp" %>

        <!-- Product section-->
        <section class="py-5">

            <div class="container" style="min-height: 1000px">
                <h1>Checkout</h1>
                <div class="row">
                    <div class="col-md-8" style="border: 1px solid #ccc; border-radius: 5px; padding: 1rem">
                        <h3>List Products</h3>
                        <form action="checkout" method="POST">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Name</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${cart}" var="c">
                                    <input type="text" name="select" value="${c.key.tourID}" hidden="">
                                    <tr>
                                        <td>${c.key.tourName}</td>
                                        <td><img src="${c.key.tourImage}" width="50"/></td>
                                        <td>${c.key.tourPrice}</td>
                                        <td><input type="number" name="quantity" value="${c.value}" hidden/>${c.value}</td>
                                        <td>${c.key.tourPrice*c.value}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <h3>Total Amount: $${totalMoney}</h3>
                            <button type="submit" class="btn btn-primary w-100">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="/Components/footerComponent.jsp" %>
    </body>
</html>

