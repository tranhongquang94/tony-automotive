let loginBtn = document.querySelector('.login-btn');
let registerBtn = document.querySelector('.register-btn');
let logoutBtn = document.querySelector('.logout-btn');

if (loginBtn) {
	loginBtn.addEventListener('click', () => {
		window.location.href = './login.jsp';
	});
	registerBtn.addEventListener('click', () => {
		window.location.href = './index.jsp'
	});
}

if (logoutBtn) {
	logoutBtn.addEventListener('click', () => {
		fetch('handleLogout.jsp').then(res => {
			window.location.href = './index.jsp';
		})
	});
}

