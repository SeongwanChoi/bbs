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

    registerForm['email'].addEventListener('focusout', () => {
        const callback = (resp) => {
            const respJson = JSON.parse(resp);
            const emailWarning = registerForm.querySelector('[rel="email-warning"]');
            if (respJson['count'] === 0) {
                emailWarning.classList.remove('visible');
            } else {
                emailWarning.classList.add('visible');
            }
        };
        const formData = new FormData();
        formData.append('field', 'e');
        formData.append('value', registerForm['email'].value);
        Ajax.request('POST', '/apis/register/count', callback, () => {}, formData);
    });

    registerForm['nickname'].addEventListener('focusout', () => {
        const callback = (resp) => {
            const respJson = JSON.parse(resp);
            const nicknameWarning = registerForm.querySelector('[rel="nickname-warning"]');
            if (respJson['count'] === 0) {
                nicknameWarning.classList.remove('visible');
            } else {
                nicknameWarning.classList.add('visible');
            }
        };
        const formData = new FormData();
        formData.append('field', 'n');
        formData.append('value', registerForm['nickname'].value);
        Ajax.request('POST', '/apis/register/count', callback, ()=>{}, formData);
    });

    if (registerForm['password'].value !== registerForm['passwordCheck'].value) {
        alert('비밀번호가 서로 일치하지 않습니다.');
        registerForm['passwordCheck'].focus();
        return false;
    }

});