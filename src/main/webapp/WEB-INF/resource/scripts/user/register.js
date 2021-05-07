window.addEventListener('DOMContentLoaded', () => {
    const registerForm = window.document.getElementById('register-form');
    registerForm['addressPostButton'].addEventListener('click', () => {
      new daum.Postcode({
          oncomplete: (data) => {
              registerForm['addressPost'].value = data['zonecode'];
              registerForm['addressPrimary'].value = data['address'];
              registerForm['addressSecondary'].value = '';
              registerForm['addressSecondary'].focus();
          }
      }).open();
    });


});