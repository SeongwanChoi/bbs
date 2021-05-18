window.addEventListener('DOMContentLoaded', ()=> {
   const loginForm = window.document.getElementById('login-form');
   const emailWarning = loginForm.querySelector('[rel="email-warning"]');
   const passwordWarning = loginForm.querySelector('[rel="password-warning"]');
   const errorWarning = loginForm.querySelector('[rel="error-warning"]');
   loginForm.onsubmit = () => {
       const emailRegex = new RegExp('^(?=.{8,50}$)([0-9a-z]([_]?[0-9a-z])*?)@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$');
       const passwordRegex = new RegExp('^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'\",<.>/?]{4,100})$');

      if (!emailRegex.test(loginForm['email'].value) && !passwordRegex.test(loginForm['password'].value)) {
         emailWarning.classList.add('visible');
         passwordWarning.classList.remove('visible');
         loginForm['email'].focus();
         return false;
      } else if (emailRegex.test(loginForm['email'].value) && !passwordRegex.test(loginForm['password'].value)) {
         emailWarning.classList.remove('visible');
         passwordWarning.classList.add('visible');
         loginForm['password'].focus();
         return false;
      } else if (!emailRegex.test(loginForm['email'].value) && passwordRegex.test(loginForm['password'].value)) {
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
          if (respJson['select'] === 'NOTPASSWORD') {
             errorWarning.classList.add('visible');
             loginForm['password'].focus();
             return false;
          } else {
             errorWarning.classList.remove('visible');
          }
      };
    const formData = new FormData;
    formData.append('submit', 's');
    formData.append('ViewEmail', loginForm['email'].value);
    formData.append('ViewPassword', loginForm['password'].value);
    Ajax.request('POST', '/apis/select', callback, ()=>{}, formData);


   }
});