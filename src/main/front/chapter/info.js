import {request, RequestMethod} from "../common/request";
import Quill from "quill/core";
import {richText} from "../richText";

window.addEventListener('load', () => {
    const edit = document.getElementById('chapter-description');
    const content = document.getElementById('content').innerText;
    richText(edit, content);

    const btnDelete = document.getElementById('button-delete')
    btnDelete.onclick = () => {
        const url = require('url');
        const currentURL = url.parse(window.location.href);
        const path = currentURL.pathname.split('/');
        const id = path[2];
        request('/chapter/' + id, {
            method: RequestMethod.DELETE
        }).then(response => {
            switch (response.status) {
                case 400:
                    alert('존재하지 않는 챕터입니다');
                    break;
                default:
                    console.log('알 수 없는 에러');
                    break;
            }
        })
    }
});
