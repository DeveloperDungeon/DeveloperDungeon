import Quill from 'quill/core';

import Toolbar from 'quill/modules/toolbar';
import Snow from 'quill/themes/snow';

import Bold from 'quill/formats/bold';
import Italic from 'quill/formats/italic';
import Header from 'quill/formats/header';
import Underline from 'quill/formats/underline';
import CodeBlock from 'quill/formats/code';
import {request, RequestMethod} from '../common/request';

Quill.register({
    'modules/toolbar': Toolbar,
    'themes/snow': Snow,
    'formats/bold': Bold,
    'formats/italic': Italic,
    'formats/header': Header,
    'formats/underline': Underline,
    'formats/code-block': CodeBlock,
});

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
    const quill = createQuillEditor();

    document.getElementById('btnSubmit').onclick = () => {
        const title = document.getElementById('input').value;
        const content = JSON.stringify(quill.getContents());
        const isPrivate = document.getElementById('isPrivate').value;

        createChapter(title, content, !isPrivate);
    };
});

function createQuillEditor() {
    return new Quill('#editor-container', {
        modules: {
            toolbar: [
                [{header: [1, 2, false]}],
                ['bold', 'italic', 'underline'],
                ['image', 'code-block']
            ]
        },
        theme: 'snow'
    });
}