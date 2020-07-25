import {request} from "../common/request";
import {RequestMethod} from "../common/request";
import {now, redirect} from "../common/utils";
import {registerComment} from "../elements/comment";

window.addEventListener('load', () => {
    checkRedirectionIssue();
    loadComments();

    const button = document.getElementById('button-comment');
    button.onclick = onCommentButtonClick;
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

async function loadComments() {
    const questId = window.location.pathname.split('/')[2];
    const result = await request(`/comment?questId=${questId}`, {
        method: RequestMethod.GET
    });

    const commentArea = document.getElementById('comment-area');
    commentArea.innerHTML = '';

    const json = JSON.parse(result.response);
    json.list.map(e => {
        const element = document.createElement('quest-comment');
        element.setAttribute('comment-id', e['id']);
        element.setAttribute('quest-id', e['questId']);
        element.setAttribute('nickname', e['authorDetails'].nickName);
        element.setAttribute('content', e['content']);
        element.setAttribute('reg-time', e['regTime']);
        return element;
    }).forEach(e => commentArea.appendChild(e));
}

function onCommentButtonClick() {
    const questId = window.location.pathname.split('/')[2];
    const input = document.getElementById('input-comment');
    const content = input.value;

    request('/comment', {
        method: RequestMethod.POST,
        body: JSON.stringify({content: content, regTime: now(), questId: questId}),
        doRedirection: false
    }).then((result) => {
        if (result.response === 'success') {
            loadComments();
            input.value = '';
        } else {
            redirect('/login', {
                prevUrl: window.location.pathname.substring(1)
            });
        }
    });
}

registerComment();