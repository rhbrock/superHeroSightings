<%-- 
    Document   : loc
    Created on : Nov 13, 2018, 5:06:42 PM
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
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/supers">Supers</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/orgs">Organizations</a>
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/locs">Locations</a>
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

            <ul class="list-group text-center">               
                <li class="list-group-item ">
                    <h3>Name: <c:out value="${loc.locName}"/></h3>
                </li>
                <li class="list-group-item">
                    Description: <c:out value="${loc.locDesc}"/>
                </li>
                <li class="list-group-item">
                    Street Address: <c:out value="${loc.streetAddress}"/>
                </li>
                <li class="list-group-item ">
                    Zip <c:out value="${loc.zip}"/>
                </li>
                <li class="list-group-item">
                    Lat: <c:out value="${loc.lat}"/>
                </li>
                <li class="list-group-item ">
                    Lon: <c:out value="${loc.lon}"/>
                </li>
            </ul>

        </div>

        <!--Top button is to open form modal-->
        <div class="container">
            <sec:authorize access="hasRole('ROLE_SIDEKICK')"> <button type="button" class="btn btn-warning  btn-lg" data-toggle="modal" data-target="#editModal">Edit Location</button></sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')"> <button type='button' class='btn btn-danger btn-lg' data-toggle="modal" data-target="#deleteModal">Delete</button></sec:authorize>
            <a href="${pageContext.request.contextPath}/locs">
                <button type='button' class='btn btn-standard btn-lg' id='backToOrgs'>Back</button></a>         
        </div>

        <!--Edit Window-->  
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Edit Location</h5>
                    </div>
                    <div class="modal-body">

                        <sf:form class="form-horizontal" role="form"
                                 action="editLoc" method="POST" modelAttribute="loc">
                            <div class="form-group">
                                <label for="locName" class="col-md-4 control-label">Location Name:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="locName"
                                              path="locName" placeholder="Location Name"/>
                                    <sf:errors id="nameError" path="locName" cssclass="error"></sf:errors>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="locDesc" class="col-md-4 control-label">Description:</label>
                                    <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="locDesc"
                                              path="locDesc" placeholder="Description"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="streetAddress" class="col-md-4 control-label">Street Address:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="streetAddress"
                                              path="streetAddress" placeholder="Street Address"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="zip" class="col-md-4 control-label">Zip Code:</label>                          
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="zip"
                                              path="zip" placeholder="Zip Code"/>

                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lat" class="col-md-4 control-label">Lat:</label>                          
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="lat"
                                              path="lat" placeholder="Latitude"/>

                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lon" class="col-md-4 control-label">Lon:</label>                          
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="lon"
                                              path="lon" placeholder="Longitude"/>

                                    <sf:hidden path="locId"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-success" value="Edit Location"/>
                                    <a href="loc?locId=${loc.locId}">
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
                        <h5 class="modal-title" id="exampleModalLabel">Delete Location</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body text-center">
                        <h3>Are you sure?</h3>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <a href="deleteLoc?locId=${loc.locId}">
                                <button type='button' class='btn btn-danger btn-lg' id='delete'>Delete</button></a>
                            <a href="${pageContext.request.contextPath}/locs">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--New Sighting Window-->     
        <div class="modal fade" id="newSighting" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                    <a href="${pageContext.request.contextPath}/">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button></a>
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