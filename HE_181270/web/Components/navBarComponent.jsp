<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Navigation-->
<script>
    function login() {
        window.location.href = "login";
    }
    function logout() {
        window.location.href = "logout";
    }
</script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <h1><a class="navbar-brand" href="home">Nature Tourism</a></h1>
        <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
            >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <c:if test="${sessionScope.acc.isAdmin}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="manageaccount">Manage Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="managecategory">Manage Category</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="managetour">Manage Tour</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="manageorder">Manage Order</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="viewlog">Notification</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="dashboard">Dashboard</a>
                    </li>


                </c:if>
                <c:if test="${sessionScope.acc.isSeller}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="managetour">Manage Tour</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="manageorder">Manage Order</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <c:if test="${!sessionScope.acc.isSeller && !sessionScope.acc.isAdmin}">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="trackorder">Order History</a>
                        </li>
                    </c:if>
                    <a class="nav-link active" aria-current="page" href="changepass">
                        <b>${sessionScope.acc.username}</b>
                    </a>
                </c:if>

            </ul>
            <form action="searchtour" class="d-flex mx-auto">
                <input
                    value="${key}"
                    class="form-control me-2"
                    type="search"
                    placeholder="Search"
                    aria-label="Search"
                    name="keyword"
                    />
                <button class="btn btn-outline-success" type="submit">
                    Search
                </button>
            </form>
            <c:if test="${!sessionScope.acc.isAdmin && !sessionScope.acc.isSeller}">
                <div class="d-flex my-2">
                    <a class="btn btn-outline-dark" href="cartdetail">
                        <i class="bi-cart-fill me-1"></i>
                        Cart
                        <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.carts.size()}</span>
                    </a>

                </div>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.acc == null}">
                    <button class="btn btn-outline-primary ms-lg-2" onclick="login()">Login</button>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-outline-primary ms-lg-2" onclick="logout()">Logout</button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
