import {request, RequestMethod} from "../common/request";
import {now, redirect} from "../common/utils";
import {loadComments, registerComment} from "../elements/comment";
import {richText} from "../richText";

window.addEventListener('load', () => {
    checkRedirectionIssue();
    reloadComments();

    const button = document.getElementById('button-comment');
    button.onclick = onCommentButtonClick;
    const bntDelete = document.getElementById('bntRemove');

    bntDelete.onclick = () => {
        const url = require('url');
        const currentURL = url.parse(window.location.href);
        const path = currentURL.pathname.split('/');
        const id = path[2];
        request('/quest/' + id, {
            method: RequestMethod.DELETE
        })
    };
    const edit = document.getElementById('editor-container');
    const content = document.getElementById('content').innerText
    richText(edit, content);
});

function checkRedirectionIssue() {
    const paramStr = window.location.search;
    if (paramStr == null || paramStr === '') return;

    const issue = paramStr.substr(1)
        .split('&')
        .map(slice => slice.split('='))
        .filter(l => l[0] === 'redirect')
        .map(l => l[1]);

    if (issue.length === 0) return;

    if (issue[0] === 'unauthorized')
        alert('권한이 없습니다.');
}

function onCommentButtonClick() {
    const questId = window.location.pathname.split('/')[2];
    const input = document.getElementById('input-comment');
    const content = input.value;

    request('/comment', {
        method: RequestMethod.POST,
        body: JSON.stringify({content: content, regTime: now(), questId: questId}),
        doRedirection: false
    }).then(response => {
        switch (response.status) {
            case 200:
                reloadComments();
                input.value = '';
                break;
            case 400:
                alert('댓글 등록 실패');
                break;
            default:
                console.log('알 수 없는 에러');
                break;
        }
    });
}

function reloadComments() {
    loadComments(window.location.pathname.split('/')[2]);
}

registerComment();