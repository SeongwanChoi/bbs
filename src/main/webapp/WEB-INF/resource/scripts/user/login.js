window.addEventListener('DOMContentLoaded', ()=> {
   const loginForm = window.document.getElementById('login-form');
   const emailWarning = loginForm.querySelector('[rel="email-warning"]');
   const passwordWarning = loginForm.querySelector('[rel="password-warning"]');
   const errorWarning = loginForm.querySelector('[rel="error-warning"]');
   loginForm.onsubmit = () => {
      if (loginForm['email'].value === '' && loginForm['password'].value === '') {
         emailWarning.classList.add('visible');
         passwordWarning.classList.remove('visible');
         loginForm['email'].focus();
         return false;
      } else if (loginForm['email'].value !== '' && loginForm['password'].value === '') {
         emailWarning.classList.remove('visible');
         passwordWarning.classList.add('visible');
         loginForm['password'].focus();
         return false;
      } else if (loginForm['email'].value === '' && loginForm['password'].value !== '') {
         emailWarning.classList.add('visible');
         passwordWarning.classList.remove('visible');
         loginForm['email'].focus();
         return false;
      } else {
         emailWarning.classList.remove('visible');
         passwordWarning.classList.remove('visible');
      }

      const callback = (resp) => {
         const respJson = JSON.parse(resp);
         if (respJson['select'] === 'NONE') {
            errorWarning.classList.add('visible');
            return false;
         } else {
            errorWarning.classList.remove('visible');
         }
      };
    const formData = new FormData;
    formData.append()
    Ajax.request('POST', '/apis/select', callback, ()=>{}, formData);
   }
});