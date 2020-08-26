import {request, RequestMethod} from '../common/request';
import {createQuillEditorWrite} from "../richText";
import Quill from "quill";

/**
 * 챕터 생성 요청을 보냄
 * @param title 챕터 타이틀
 * @param description 챕터 설명
 * @param isPublic public인지 아닌지 boolean
 */
function createChapter(title, description, isPublic) {
    const body = {
        title: title,
        description: description,
        isPublic: isPublic
    };
    request('/chapter', {
        method: RequestMethod.POST,
        body: JSON.stringify(body)
    }).then(response => {
        switch (response.status) {
            case 403 :
                alert('권한이 없습니다');
                break;
            default:
                console.log('예상치 못한 에러');
                break;
        }
    });
}

window.addEventListener('load', () => {
    const editContainer = document.getElementById('editor-container')
    const quill = createQuillEditorWrite(editContainer);

    document.getElementById('btnSubmit').onclick = () => {
        const title = document.getElementById('input').value;
        const content = JSON.stringify(quill.getContents());
        const isPublic = document.getElementById('isPrivate').checked ? 0 : 1;

        createChapter(title, content, isPublic);
    };
});

