window.addEventListener('DOMContentLoaded', () => {
        const lostPasswordForm = window.document.getElementById('passwordLost-Form');
        const callback = (resp) => {
            const respJson = JSON.parse(resp);
            switch (respJson['result']) {
                case 'SENT' :
                    lostPasswordForm.querySelectorAll('input').forEach(input => {
                        if (input.getAttribute('name') !== 'authCode' &&
                            input.getAttribute('name') !== 'password' &&
                            input.getAttribute('name') !== 'passwordCheck' &&
                            input.getAttribute('name') !== 'submit') {
                            input.setAttribute()
                        }
                    });
            }
        };
});