window.addEventListener('load', () => {
   const loginForm = window.document.getElementById('login-form');
   const params = (new URL(window.location.href)).searchParams;
   if (params.get('email') !== null) {
       loginForm['email'].value = params.get('email');
       loginForm['password'].focus();
   }
});