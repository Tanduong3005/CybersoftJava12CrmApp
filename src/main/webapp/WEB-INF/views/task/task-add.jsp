<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>Add New Task</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.TASK_DASHBOARD%>" />">Task
									Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Add
								New Task</li>
						</ol>
					</nav>
					<h1 class="m-0">Add New Task</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
			<div class="row no-gutters">
				<div class="col-lg-4 card-body">
					<p>
						<strong class="headings-color">Rules</strong>
					</p>
					<p class="text-muted">There is no rule!</p>
				</div>
				<div class="col-lg-8 card-form__body card-body">
					<form action="<c:url value="<%=UrlConst.TASK_ADD%>" />"
						method="post">
						<div class="form-group">
							<label for="name">Name:</label> <input type="text"
								class="form-control" name="name" id="name">
						</div>
						<div class="form-group">
							<label for="start_date">Start_date:</label> <input type="date"
								class="form-control" name="start_date" id="start_date">
						</div>
						<div class="form-group">
							<label for="end_date">End_date:</label> <input type="date"
								class="form-control" name="end_date" id="end_date">
						</div>
						<div class="form-group">
							<label for="address">Address:</label>
							<textarea type="text" class="form-control" name="address"
								id="address" aria-label="With textarea"></textarea>
						</div>
						<div class="form-group">
							<label for="role">Role</label> <input type="text"
								class="form-control" name="role" id="role"
								value="${user.role.id }">


						</div>
						<button class="btn btn-primary w-25 justify-content-center"
							type="submit" class="btn btn-primary">Add</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
