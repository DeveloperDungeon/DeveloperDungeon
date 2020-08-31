import {request, RequestMethod} from "../common/request";
import Quill from "quill/core";

window.addEventListener('load', () => {
    const descriptionArea = document.getElementById('chapter-description');

    const quill = createQuillEditor(descriptionArea);
    const delta = JSON.parse(document.getElementById('content').innerText);
    quill.setContents(delta);

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

function clickDelete(id) {
    console.log(id);

}

function createQuillEditor(edit) {
    return new Quill(edit, {
        readOnly: true
    });
}