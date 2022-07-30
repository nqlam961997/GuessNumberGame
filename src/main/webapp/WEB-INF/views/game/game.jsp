<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<style>
.custom-container {
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	position: absolute;
	width: 100%;
}
</style>
</head>
<body>
	<div class="container vh-100" style="position: relative">
		<section class="custom-container">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-6 text-black">

						<div class="px-5 ms-xl-4">
							<span class="h1 fw-bold mb-0">Guess Number</span>
						</div>

						<div
							class="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">

							<form style="width: 23rem;">

								<h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Input
									your number</h3>

								<div class="form-outline mb-4">
									<input type="guessNumber" class="form-control form-control-lg"
										required />
									<div class="invalid-feedback">Please fill out the field</div>
								</div>

								<div class="form-outline mb-4">
									<span class="text-danger">Incorrect</span>
								</div>

								<div class="pt-1 mb-4">
									<button class="btn btn-info btn-lg btn-block" type="submit">Guess</button>
								</div>
							</form>

						</div>

					</div>
					<div class="col-sm-6 px-0 d-none d-sm-block">
						<table class="table caption-top">
							<caption>List of results</caption>
							<thead>
								<tr>
									<th scope="col">Round</th>
									<th scope="col">Your Number</th>
									<th scope="col">Status</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>30</td>
									<td>Incorrect</td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>45</td>
									<td>Incorrect</td>
								</tr>
								<tr>
									<th scope="row">3</th>
									<td>100</td>
									<td>Incorrect</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>