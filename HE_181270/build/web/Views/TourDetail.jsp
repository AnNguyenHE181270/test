<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tour Detail</title>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="/Components/navBarComponent.jsp"%>

        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${tour.tourImage}" alt="..." /></div>
                    <div class="col-md-6">
                        <h1 class="display-5 fw-bolder">${tour.tourName}</h1>
                        <div class="fs-5 mb-5">
                            <span>$${tour.tourPrice}</span>
                        </div>
                        <p class="lead">${tour.tourDescription}</p>
                        <c:if test="${!sessionScope.acc.isAdmin && !sessionScope.acc.isSeller}">
                            <div class="d-flex">
                                <form action="addtocart" method="POST">
                                    <input name="tourID" value="${tour.tourID}" hidden=""/>
                                    Quantity: <input type="number" name="quantity" min="1" max="${tour.quantity}" value="1"/>
                                    <input type="submit" value="Add to cart"/>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related Tours</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

                    <c:forEach items="${listLast}" var="L">
                        <div class="col mb-5">
                            <div class="card h-100">
                                <!-- Product image-->
                                <a href="detail?tourID=${L.tourID}">
                                    <img class="card-img-top" src="${L.tourImage}" alt="Not found" width ="200px" height="180px"/>
                                </a>
                                <!-- Product details-->
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <c:if test="${sessionScope.acc.isSeller || sessionScope.acc.isAdmin}">
                                            <b>Quantity: ${L.quantity}</b>
                                        </c:if>
                                        <hr>
                                        <h5 class="fw-bolder">${L.tourName}</h5>
                                        <!-- Product reviews-->
                                        <div class="d-flex justify-content-center small text-warning mb-2">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <!-- Product price-->
                                        $${L.tourPrice}
                                    </div>
                                </div>
                                <!-- Product actions-->
                                <c:if test="${!sessionScope.acc.isAdmin && !sessionScope.acc.isSeller}">

                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent" >
                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="addtocart?tourID=${L.tourID}&quantity=1">Add to cart</a></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
        </section>
        <%@include file="/Components/footerComponent.jsp" %>
    </body>
</html>

