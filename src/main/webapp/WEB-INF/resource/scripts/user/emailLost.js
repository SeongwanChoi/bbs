window.addEventListener('DOMContentLoaded', () => {
        const emailLostForm = window.document.getElementById('emailLost-Form')
        emailLostForm['sendCodeButton'].addEventListener('click', () => {
            let inputs = emailLostForm.querySelectorAll('input');
            for (let i = 0; i < inputs.length; i++) {
                let input = inputs[i];
                if (input.dataset.regex !== undefined) {
                    let regex =new RegExp(input.dataset.regex);
                    let name = input.getAttribute('placeholder');
                    if (!regex.test(input.value)) {
                        alert(`올바른${name}값을 입력해주세요.`);
                        input.focus();
                        return;
                    }
                }
            }
            const callback = (resp) =>{
                const respJson = JSON.parse(resp);
                switch (respJson['result']) {
                    case 'SENT' :
                        emailLostForm.querySelectorAll('input').forEach(input => {
                            if (input.getAttribute('name') !== 'authCode' &&
                                input.getAttribute('name') !== 'submit') {
                                input.setAttribute('disabled', 'disabled');
                            }
                        });
                        emailLostForm['contactFirst'].setAttribute('disabled', 'disabled');
                        emailLostForm['submit'].removeAttribute('hidden');
                        emailLostForm.querySelector('[rel="authCode"]').removeAttribute('hidden');
                        emailLostForm['key'].value = respJson['key'];
                        emailLostForm['authCode'].focus();
                        alert('5분 안에 적어주세요');
                        alert(respJson['code']);
                        break;
                    default :
                        alert('입력하신 정보와 일치하는 계정이 없습니다.');
                }
            };
            const fallback = (status) => {
                alert('알 수 없는 이유로 동작 중지');
            };
            const formData = new FormData(emailLostForm);
            Ajax.request('POST', 'lost_email/send-code', callback, fallback, formData);
    });

            emailLostForm.onsubmit = () => {
                const callback = (resp) => {
                    const respJson = JSON.parse(resp);
                    const email = respJson['email'];
                    if (email === '') {
                        alert('인증번호가 올바르지 않습니다.')
                        emailLostForm['authCode'].focus();
                    } else {
                        alert(`이메일은 ${email}입니다 확인을 누르면 로그인페이지로 넘어갑니다.`);
                        window.location.href = `login?email=${email}`;
                    }
                };
                const fallback = (status) => {
                    alert('알 수 없는 이유로 서버통신 안됨');
                };
                const formData = new FormData();
                formData.append('authCode', emailLostForm['authCode'].value);
                formData.append('key', emailLostForm['key'].value);
                Ajax.request('POST', window.location.href, callback, fallback, formData);
                return false;
            };
});