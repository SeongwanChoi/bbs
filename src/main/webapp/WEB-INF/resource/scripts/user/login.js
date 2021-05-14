window.addEventListener('DOMContentLaded', ()=> {
   const loginForm = window.document.getElementById('login-form');
   const emailWarning = loginForm.querySelector('[rel="email-warning"]');
   const passwordWarning = loginForm.querySelector('[rel="password-warning"]');
   loginForm.onsubmit = () => {
      if (loginForm['email'].value === '' || loginForm['email'].value === null) {
         emailWarning.classList.add('visible');
         loginForm['email'].focus();
      } else {
         emailWarning.classList.remove('visible');
      }

      if (loginForm['password'].value === '' || loginForm['password'].value === null) {
         passwordWarning.classList.add('visible');
         loginForm['password'].focus();
      } else {
         passwordWarning.classList.remove('visible');
      }
   }
});