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
        Ajax.request('POST', '/apis/count', callback, () => {}, formData);
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
        Ajax.request('POST', '/apis/count', callback, ()=>{}, formData);
    });

    registerForm.onsubmit = () => {
        const emailRegex = new RegExp('^(?=.{8,50}$)([0-9a-z]([_]?[0-9a-z])*?)@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$');
        const passwordRegex = new RegExp('^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'\",<.>/?]{4,100})$');
        const nicknameRegex = new RegExp('^([0-9a-zA-Z가-힣]{2,10})$');
        const nameFirstRegex = new RegExp('^([가-힣]{1,10})$');
        const nameOptionalRegex = new RegExp('^([가-힣]{0,10})$');
        const nameLastRegex = new RegExp('^([가-힣]{1,10})$');
        const contactFirstRegex = new RegExp('^(010|070)$');
        const contactSecondRegex = new RegExp('^([0-9]{4})$');
        const contactThirdRegex = new RegExp('^([0-9]{4})$');
        const addressPostRegex = new RegExp('^([0-9]{5})$');
        const addressPrimaryRegex = new RegExp('^([0-9a-zA-Z가-힣\\- ]{10,100})$');
        const addressSecondaryRegex = new RegExp('^([0-9a-zA-Z가-힣\\- ]{0,100})$');
        if (!emailRegex.test(registerForm['email'].value)) {
            alert('올바른 이메일을 입력해주세요.');
            registerForm['email'].focus();
            return false;
        }
        if (!passwordRegex.test(registerForm['password'].value)) {
            alert('올바른 비밀번호를 입력해주세요.');
            registerForm['password'].focus();
            return false;
        }
        if (!nicknameRegex.test(registerForm['nickname'].value)) {
            alert('올바른 닉네임을 입력해주세요.');
            registerForm['nickname'].focus();
            return false;
        }
        if (!nameFirstRegex.test(registerForm['nameFirst'].value)) {
            alert('올바른 이름을 적어주세요.');
            registerForm['nameFirst'].focus();
            return false;
        }
        if (!nameLastRegex.test(registerForm['nameLast'].value)) {
            alert('올바른 이름을 적어주세요.');
            registerForm['nameLast'].focus();
            return false;
        }
        if (!contactFirstRegex.test(registerForm['contactFirst'].value)) {
            alert('올바른 연락처를 입력해주세요.');
            registerForm['contactFirst'].focus();
            return false;
        }
        if (!contactSecondRegex.test(registerForm['contactSecond'].value)) {
            alert('올바른 연락처를 입력해주세요.');
            registerForm['contactSecond'].focus();
            return false;
        }
        if (!contactThirdRegex.test(registerForm['contactThird'].value)) {
            alert('올바른 연락처를 입력해주세요.');
            registerForm['contactThird'].focus();
            return false;
        }
        if (!addressPostRegex.test(registerForm['addressPost'].value)) {
            alert('주소를 입력해주세요.');
            registerForm['addressPost'].focus();
            return false;
        }
        if (!addressPrimaryRegex.test(registerForm['addressPrimary'].value)) {
            alert('주소를 입력해주세요.');
            registerForm['addressPrimary'].focus();
            return false;
        }


        if (registerForm['password'].value !== registerForm['passwordCheck'].value) {
            alert('비밀번호가 서로 일치하지 않습니다.')
            registerForm['passwordCheck'].focus();
            return false;
        }
    }

});