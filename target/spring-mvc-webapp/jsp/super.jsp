<%-- 
    Document   : supers
    Created on : Nov 7, 2018, 8:24:19 AM
    Author     : Roger Brock
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Super Hero Sightings</title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superHeroSightings.css" rel="stylesheet">

    </head>

    <body>

        <header>
            <h1 style='border-top:thick black solid;' >Super Hero Sightings</h1>
            <div id = "head"> 
                <nav class="navbar navbar-expand-md navbar-dark bg-dark box-shadow">
                    <button class="navbar-toggler mr-2" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" 
                            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/">Home</a>
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/supers">Supers</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/orgs">Organizations</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/locs">Locations</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/sightings">Sightings</a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="nav-item nav-link text-danger" href="${pageContext.request.contextPath}/displayUserList">
                                    User Admin
                                </a>                       
                            </sec:authorize>

                        </div>
                    </div>	
                    <sec:authorize access="hasRole('ROLE_SIDEKICK')">
                        <div class="d-inline-flex p-2 mr-auto" style="width: 200px;">
                            <button type='button' class='btn btn-primary btn-lg' data-toggle="modal" data-target="#newSighting">New Sighting</button>
                        </div>
                    </sec:authorize>

                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <h5><a href="<c:url value="/login" />">
                                <button type='button' class='btn btn-warning btn-sm'>Login</button></a>
                        </h5>
                    </c:if>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <h5>${pageContext.request.userPrincipal.name} <a 
                                href="<c:url value="/j_spring_security_logout" />">
                                <button type='button' class='btn btn-warning btn-sm'>Logout</button></a>
                        </h5>
                    </c:if>
                </nav>
            </div>
        </header>

        <div class="container">

            <ul class="list-group text-center" >               
                <li class="list-group-item ">
                    <h4>Name: <c:out value="${superHuman.name}"/></h4>
                </li>
                <li class="list-group-item">
                    Type: <c:out value="${superHuman.type}"/>
                </li>
                <li class="list-group-item">
                    Super Power: <c:out value="${superHuman.superpower}"/>
                </li>
                <li class="list-group-item ">
                    Description: <c:out value="${superHuman.description}"/>
                </li>
                <li class="list-group-item">
                    <c:forEach var="i" items="${orgList}">
                        <a href="org?orgId=${i.orgId}"><c:out value="${i.name}"/></br>
                        </c:forEach>   
                </li>
            </ul>
        </div>

        <!--INCLUDED AND HIDDEN TO FIX UNKNOWN ERROR WITH EDIT SUPER MODAL WINDOW LINK-->
        <div class="container" hidden>
            <a>
                <button type='button' class='btn btn-standard btn-lg' id='backToSupers'>Back</button></a>
        </div>

        <!--Top button is to open form modal-->

        <div class="container">
                  <sec:authorize access="hasRole('ROLE_SIDEKICK')"> <button type="button" class="btn btn-warning  btn-lg" data-toggle="modal" data-target="#editModal">Edit Super</button></sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><button type='button' class='btn btn-danger btn-lg' data-toggle="modal" data-target="#deleteModal">Delete</button></sec:authorize>
            <a href="${pageContext.request.contextPath}/supers">
                <button type='button' class='btn btn-standard btn-lg' id='backToSupers'>Back</button></a>         
        </div>
        

        <!--Edit Window-->     
        <div class="modal" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Edit Super</h5>

                    </div>
                    <div class="modal-body">

                        <sf:form class="form-horizontal" role="form" modelAttribute="superHuman"
                                 action="editSuper" method="POST">
                            <div class="form-group">
                                <label for="name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="name"
                                              path="name" placeholder="First Name"/>
                                    <sf:errors id="nameError" path="name" cssclass="error"></sf:errors>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label for="type" class="col-md-4 control-label">Type:</label>
                                    <div class="col-md-8">
                                    <sf:select class="form-control" id="type"
                                               path="type" placeholder="Type">
                                        <sf:option value="hero">Hero</sf:option>
                                        <sf:option value="villain">Villain</sf:option>
                                    </sf:select>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="superpower" class="col-md-4 control-label">Super Power:</label>                          
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="superpower"
                                              path="superpower" placeholder="Super Power"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <sf:textarea class="form-control" id="description"
                                                 path="description" placeholder="Description"/>
                                    <sf:hidden path="superId"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-success" value="Submit"/>
                                    <a href="super?superId=${superHuman.superId}">
                                        <button type="button" class="btn btn-secondary">Close</button></a>
                                </div>
                            </div>
                        </sf:form> 
                    </div>
                </div>
            </div>
        </div>


        <!--Delete Window-->     
        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete Super</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body text-center">
                        <h3>Are you sure?</h3>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <a href="deleteSuper?superId=${superHuman.superId}">
                                <button type='button' class='btn btn-danger' id='edit'>Delete</button></a>
                            <a href="${pageContext.request.contextPath}/supers">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--New Sighting Window-->     
        <div class="modal" id="newSighting" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New Sighting</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form" 
                              action="newSighting" method="POST">

                            <div class="form-group">
                                <label for="super" class="col-md-4 control-label">Super:</label>
                                <div class="col-md-8">
                                    <select class="form-control" id="super"
                                            name="super" >
                                        <c:forEach var="l" items="${superList}">
                                            <option value="${l.superId}"><c:out value="${l.name}"/>
                                            </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="locName" class="col-md-4 control-label">Location Name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="LocationName"
                                           name="locName" placeholder="LocationName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="locDesc" class="col-md-4 control-label">Description:</label>                          
                                <div class="col-md-8">
                                    <textarea type="text" class="form-control" id="locDesc"
                                              name="locDesc" placeholder="Location Description"/></textarea>
                                </div>

                            </div>

                            <div class="form-group">
                                <label for="streetAddress" class="col-md-4 control-label">Street Address</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="streetAddress"
                                           name="streetAddress" placeholder="Street Address">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="zip" class="col-md-4 control-label">Zip Code:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="zip"
                                           name="zip" placeholder="Zip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lat" class="col-md-4 control-label">Lat:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="lat"
                                           name="lat" placeholder="Latitude">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lon" class="col-md-4 control-label">Lon:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="lon"
                                           name="lon" placeholder="Longitude">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="dateTime" class="col-md-4 control-label">Date and Time</label>
                                <div class="col-md-8">
                                    Leave Blank if Sighting is Now!</br>
                                    We'll set it for ya!
                                    <input type="datetime-local" class="form-control" id="dateTime"
                                           name="dateTime" placeholder="Auto-generate on submit"/></a>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-success" value="Submit"/>

                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </form> 
                    </div>
                </div>
            </div>
        </div>

        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCIrN6BGMXAwTrzuTPuz1Rv3UGppyGw8R8&callback=initMap" async defer></script>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/superHeroSightings.js"></script>

    </body>
</html>