import {request, RequestMethod} from './common/request';

window.addEventListener('load', () => {
    const button = document.getElementById('bntLogin');
    button.onclick = () => {
        let errorMessage = '';
        let isValid = true;
        const id = document.getElementById('inputId').value;
        const pw = document.getElementById('inputPw').value;
        const body = {
            id: id,
            password: pw
        };
        if (id.length === 0) {
            errorMessage += '아이디를 입력해 주세요\n';
            isValid = false;
        }
        if (pw.length === 0) {
            errorMessage += '비밀번호를 입력해 주세요\n';
            isValid = false;
        }
        if (isValid) {
            request('/login', {
                method: RequestMethod.POST,
                body: JSON.stringify(body)
            }).then(response => {
                if (response.status === 400)
                    switch (response.body.errorCode) {
                        case 1 :
                            alert('아이디 또는 비밀번호를 입력하세요');
                            break;
                        case 2 :
                            alert('아이디 또는 비밀번호가 틀렸습니다');
                    }
            });
        } else {
            alert(errorMessage);
        }

    }
});