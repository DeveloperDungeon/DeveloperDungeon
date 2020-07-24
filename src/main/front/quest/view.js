import {request, RequestMethod} from "../common/request";

window.addEventListener('load', () => {
    const bntDelete = document.getElementById('bntRemove');
    bntDelete.onclick = () => {
        const url = require('url');
        const currentURL = url.parse(window.location.href);
        const path = currentURL.pathname.split('/');
        const id = path[2];
        request('/delete/' + id, {
            method:RequestMethod.DELETE
        })
    //    request 결과 받아서 성공, 실패 처리
    }
});