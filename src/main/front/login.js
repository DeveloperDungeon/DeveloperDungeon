import {request, RequestMethod} from './common/request';

window.addEventListener('load', () => {
    const button = document.getElementById('bntLogin');
    button.onclick = () => {
        const id = document.getElementById('inputId').value;
        const pw = document.getElementById('inputPw').value;
        const body = {
            id: id,
            password: pw
        };

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
                        break;
                }
        });
    }
});