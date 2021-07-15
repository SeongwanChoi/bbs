window.addEventListener('DOMContentLoaded', () => {
        const lostPasswordForm = window.document.getElementById('passwordLost-Form');
        lostPasswordForm['sendCodeButton'].addEventListener('click', () => {
        const callback = (resp) => {
            const respJson = JSON.parse(resp);
            switch (respJson['result']) {
                case 'SENT' :
                    lostPasswordForm.querySelectorAll('input').forEach(input => {
                        if (input.getAttribute('name') !== 'authCode' &&
                            input.getAttribute('name') !== 'code') {
                            input.setAttribute('disabled', 'disabled');
                        }
                    });
                    lostPasswordForm['button'].removeAttribute('hidden');
                    lostPasswordForm.querySelector('[rel="authCode"]').removeAttribute('hidden');
                    lostPasswordForm['key'].value = respJson['key'];
                    lostPasswordForm['authCode'].focus();
                    alert('5분 안에 적어주세요');
                    alert(respJson['code']);
                    break;
                default :
                    alert('입력하신 정보와 일치하는 계정이 없습니다.');
                }
            };
            const fallback = () => {
                alert('알 수 없는 이유로 동작 중지');
            };
            const formData = new FormData(lostPasswordForm);
            Ajax.request('POST', 'lost_password/send-code', callback, fallback, formData);

        });
});