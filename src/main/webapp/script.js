let loginBtn = document.querySelector('.login-btn');
let registerBtn = document.querySelector('.register-btn');
let logoutBtn = document.querySelector('.logout-btn');
let cartBtn = document.querySelector('.cart-mes');

let reduceBtn = document.querySelectorAll('.reduceBtn');
let modalContainer = document.querySelector('.modal-container');

let DOMmodal = `<div class="overlay">
				<header>
					<h2 class="modal-title">Alert</h2>				
				</header>
				<div class="modal-content">
					Are you sure you want to remove this item from your cart?
				</div>
				<footer>
					<a class="close-modal confirm-remove" href="">Yes</a>
					<a class="close-modal not-remove" href="">No</a>
				</footer>
			</div>`


if (loginBtn) {
	loginBtn.addEventListener('click', () => {
		window.location.href = './LoginServlet?action=login';
	});
	registerBtn.addEventListener('click', () => {
		window.location.href = './register.jsp'
	});
}

if (logoutBtn) {
	logoutBtn.addEventListener('click', () => {
		window.location.href = './LoginServlet?action=logout';
	});
	
	cartBtn.addEventListener('click', () => {
		window.location.href = "./CartServlet?action=view";
	})
}

if (reduceBtn) {
	reduceBtn.forEach((btn) => {
		btn.addEventListener('click', (e) => {
			e.preventDefault();
			const API_URL = e.target.getAttribute('href');
			const dataCarId = e.target.getAttribute('data-id');
			let carNum = document.getElementById(dataCarId).textContent;
			if (carNum == 1) {
				// Open modal
				modalContainer.innerHTML = DOMmodal;
				
				//Set GET URL for remove btn
				const API_REMOVE = `CartServlet?action=remove&carId=${dataCarId}`;
				document.querySelector('.confirm-remove').setAttribute('href', API_REMOVE);
			} else {
				fetch(API_URL)
				.then(res => {
					// Same as response.sendRedirect
					window.location.assign("CartServlet?action=view")
				})
				.catch(err => console.log(err));
			}
		})
	})
}



