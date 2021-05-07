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

    if (registerForm['password'].value !== registerForm['passwordCheck'].value) {
        alert('비밀번호가 서로 일치하지 않습니다.');
        registerForm['passwordCheck'].focus();
        return false;
    }
    if (registerForm['addressPost'].value === '') {
        alert('우편번호를 입력해주세요');
        return false;
    }

});