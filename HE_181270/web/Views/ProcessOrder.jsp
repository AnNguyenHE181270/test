<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Process Order</title>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Product section-->
        <section class="py-5">
            <div class="container" style="min-height: 1000px">
                <h3>List Tours</h3>
                <form action="processorder" method="POST">
                    <table class="table">
                        <input type="hidden" name="id" value="${order.orderID}"/>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total Price</th>
                                    <c:if test="${(sessionScope.acc.isAdmin || sessionScope.acc.isSeller) && (order.status eq 'Processing')}">
                                    <th>Result</th> <!-- Thêm cột Result -->
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Noteeeeeeeeeeeeeeeeeeeeeee -->
                            <c:forEach items="${tours}" var="t" varStatus="status">
                            <input type="hidden" name="tourID" value="${t.tourID}"/>
                            <tr>
                                <td>${t.tourName}</td>
                                <td><img src="${t.tourImage}" width="50"/></td>
                                <td>${t.tourPrice}</td>
                                <td>
                                    <input type="hidden" name="quantity" value="${t.quantity}"/>
                                    ${t.quantity}
                                </td>
                                <td>${t.tourPrice * t.quantity}</td>
                                <c:if test="${(sessionScope.acc.isAdmin || sessionScope.acc.isSeller) && (order.status eq 'Processing')}">
                                    <td>${message[status.index]}</td> <!-- Hiển thị giá trị Result tương ứng -->
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${(sessionScope.acc.isAdmin || sessionScope.acc.isSeller) && (order.status eq 'Processing')}">
                        <button class="btn btn-outline-danger" type="submit" name="check">Check</button>
                        <button class="btn btn-outline-danger" type="submit" name="accept">Accept</button>
                        <button class="btn btn-outline-danger" type="submit" name="denied">Deny</button>
                    </c:if>
                </form>
            </div>
        </section>
    </body>
</html>
