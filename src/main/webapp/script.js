let loginBtn = document.querySelector('.login-btn');
let registerBtn = document.querySelector('.register-btn');
let logoutBtn = document.querySelector('.logout-btn');

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
}

