import {request, RequestMethod} from '../common/request';
import {redirect} from '../common/utils';
import {createQuillEditorWrite} from "../richText";

window.addEventListener('load', () => {
    const editContainer = document.getElementById('editor-container')
    const quill = createQuillEditorWrite(editContainer);

    const [type, id, content] = getMeta();

    if (type === 'edit') {
        const delta = JSON.parse(content);
        quill.setContents(delta);
    }

    document.getElementById('btnSubmit').onclick = () => {
        const title = document.getElementById('input').value;
        const content = JSON.stringify(quill.getContents());
        if (type === 'edit') requestEditQuest(id, title, content);
        else requestNewQuest(title, content);
    };
});

function getMeta() {
    const typeDiv = document.getElementById('type');
    const idDiv = document.getElementById('id');
    const contentDiv = document.getElementById('content');

    return [
        typeDiv.innerText,
        idDiv.innerText,
        contentDiv.innerText
    ];
}

function requestNewQuest(title, content) {
    const chapter = document.getElementById('chapterId');
    const chapterId = chapter.value;

    const body = {
        title: title,
        content: content,
        regTime: (new Date()).getTime(),
        chapterId: chapterId
    };

    request('/quest', {
        method: RequestMethod.POST,
        body: JSON.stringify(body)
    });
}

function requestEditQuest(id, title, content) {
    const body = {
        title: title,
        content: content
    };

    request('/quest/' + id, {
        method: RequestMethod.PUT,
        body: JSON.stringify(body)
    }).then(response => {
        if (response.status === 401) redirect('/login', {
            'prevUrl': window.location.pathname
        });
    });
}
