<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Registration Form</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="./index.css" type="text/css"/>
    <script src="script.js" defer></script>
  </head>
  
  <body>
  
  <jsp:include page="./header.jsp" />
  <main class="login-and-register-page">
    <div class="modal-container">
      <section class="side-img-container">
        <img src="./images/leftCover.jpg" alt="coding-pic" class="left-cover" />
      </section>
      
      <form class="form-container" action="#">
        <h2 class="mb-5">Hi, Welcome to <span class="title">Tony's Automotive</span>:</h2>
        <div class="mb-3">
          <label for="fullName" class="form-label">Full Name:</label>
          <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Enter your full name" required/>
        </div>
        <div class="mb-3">
          <label for="username" class="form-label">Username: </label>
          <input type="text" class="form-control" name="username" id="username" placeholder="Username" required/>
        </div>
        <div class="mb-3">
          <label for="passWord" class="form-label">Password: </label>
          <input type="password" class="form-control" name="password" id="passWord" placeholder="Enter your password"/>
        </div>
        <div class="mb-3">
          <label for="birthDate" class="form-label">Birth Date: </label>
		  <input type="date" class="form-control" name="birthdate" id="birthDate">
        </div>

		<div class="mb-4">
		<label for="gender" class="form-label">Gender: </label>
		<select class="form-select" aria-label="Gender Options" name="gender">
			<option selected disabled>Gender</option>
			<option value="Male">Male</option>
			<option value="Female">Female</option>
		  </select>
		</div>

        <label class="mb-2">Which class do you want to enroll?</label>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" name="courses" value="Front-end Intensive"/>
          <label class="form-check-label" for="frontEnd"
            >Front-end Intensive</label
          >
        </div>

        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" name="courses" value="Back-end Intensive"/>
          <label class="form-check-label" for="backEnd"
            >Back-end Intensive</label
          >
        </div>

        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" name="courses" value="Full Stack Intensive"/>
          <label class="form-check-label" for="fullStack"
            >Full Stack Intensive</label
          >
        </div>

        <button type="submit" class="btn btn-submit">Get Started</button>
      </form>
      
    </div>
    </main>
    <jsp:include page="footer.jsp" />
  </body>
  
</html>
