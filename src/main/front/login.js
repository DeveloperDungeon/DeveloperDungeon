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
          if (response.status === 400) {
            if (response.body.errorCode === 2) alert('아이디나 비밀번호를 확인해주세요.');
          }
        });
    };
});