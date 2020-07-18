import {request, RequestMethod} from './common/request.js';

window.addEventListener('load', () => {
    const button = document.getElementById('btnSignUp');
    button.onclick = () => {
        const id = document.getElementById('inputId').value;
        const pw = document.getElementById('inputPw').value;
        const nick = document.getElementById('inputNickname').value;
        const email = document.getElementById('inputEmail').value;

        const body = {
            id: id,
            password: pw,
            nickName: nick,
            email: email
        };

        request(`/signup`, {
            // 디폴트 헤더인 Content-Type: application/json 을 사용하므로 헤더 명시 안해도 됨
            method: RequestMethod.POST,
            body: JSON.stringify(body)
        }).then(xhr => {
            switch (xhr.status) {
                case 200: /* Redirected */
                    break;
                case 400:
                    console.log('실패');
                    alert('실패');
                    break;
                default:
                    console.log('예상치 못한 에러');
                    alert('예상치 못한 에러');
                    break;
            }
        });
    };
});