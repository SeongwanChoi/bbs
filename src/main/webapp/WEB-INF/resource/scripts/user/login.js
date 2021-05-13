window.addEventListener('DOMContentLaded', ()=> {
   const loginForm = window.document.getElementById('login-form');
   const emailWarning = loginForm.querySelector('[rel="email-warning"]');
   loginForm.onsubmit = () => {
      if (loginForm['email'] === '') {
         emailWarning.classList.add('visible');
         loginForm['email'].focus();
      } else {
         emailWarning.classList.remove('visible');
      }
   }
});