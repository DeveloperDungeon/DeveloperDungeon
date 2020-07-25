import {request, RequestMethod} from './common/request.js';

window.addEventListener('load', () => {
    const button = document.getElementById('btnSignUp');
    let isValidID = true;
    let isValidPassword = true;
    let isValidNickname = true;
    let isValidEmail = true;
    button.onclick = () => {
        const id = document.getElementById('inputId').value;
        const pw = document.getElementById('inputPw').value;
        const nick = document.getElementById('inputNickname').value;
        const email = document.getElementById('inputEmail').value;
        let errorMessage = '';

        function isEmail(asValue) {
            let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
            return regExp.test(asValue); // 형식에 맞는 경우 true 리턴
        }

        function isJobPassword(asValue) {
            let regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,20}$/; //  8 ~ 20자 영문, 숫자 조합
            return regExp.test(asValue); // 형식에 맞는 경우 true 리턴
        }

        if (id.length < 8 || id.length > 20) {
            errorMessage += '아이디가 잘못되었습니다\n';
            isValidID = false;
        }
        if (!isJobPassword(pw)) {
            errorMessage += '비밀번호가 잘못되었습니다\n';
            isValidPassword = false;
        }
        if (nick.length < 3 || nick.length > 25) {
            errorMessage += '닉네임이 잘못되었습니다\n';
            isValidNickname = false;
        }
        if (!isEmail(email)) {
            errorMessage += '이메일이 잘못되었습니다\n';
            isValidEmail = false;
        }

        if (isValidNickname && isValidEmail && isValidID && isValidPassword) {
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
                        let ErrMsg = JSON.parse(xhr.responseText).errMsg;
                        let ErrCode = JSON.parse(xhr.responseText).errCode;
                        console.log(ErrMsg);
                        alert(ErrMsg);
                        break;
                    default:
                        console.log('예상치 못한 에러');
                        alert('예상치 못한 에러');
                        break;
                }
            });
        }

        else {
            alert(errorMessage);
            isValidPassword = true;
            isValidID = true;
            isValidEmail = true;
            isValidNickname = true;
            errorMessage = '';
        }


    };
});