import {request} from "../common/request";
import {RequestMethod} from "../common/request";
import {now, redirect} from "../common/utils";
import {registerComment} from "../elements/comment";

import Quill from 'quill/core';

import Snow from 'quill/themes/snow';

import Bold from 'quill/formats/bold';
import Italic from 'quill/formats/italic';
import Header from 'quill/formats/header';
import Underline from 'quill/formats/underline';
import CodeBlock from 'quill/formats/code';

Quill.register({
    'themes/snow': Snow,
    'formats/bold': Bold,
    'formats/italic': Italic,
    'formats/header': Header,
    'formats/underline': Underline,
    'formats/code-block': CodeBlock,
});

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
        request('/quest/remove/' + id, {
            method: RequestMethod.DELETE
        })
        //    request 결과 받아서 성공, 실패 처리
    };

    const quill = createQuillEditor();
    const delta = JSON.parse(document.getElementById('content').innerText);
    quill.setContents(delta);
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
    request(`/comment?questId=${questId}`, {
        method: RequestMethod.GET
    }).then(response => {
        const commentArea = document.getElementById('comment-area');
        commentArea.innerHTML = '';
        response.body.list.map(e => {
            const element = document.createElement('quest-comment');
            const commentId = e['id'];
            element.setAttribute('comment-id', e['id']);
            element.setAttribute('quest-id', e['questId']);
            element.setAttribute('nickname', e['authorDetails'].nickName);
            element.setAttribute('content', e['content']);
            element.setAttribute('reg-time', e['regTime']);
            return element;
        }).forEach(e => commentArea.appendChild(e));
    });

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
                loadComments();
                break;
            case 400:
                console.log('댓글 등록 실패');
                break;
            case 401:
                alert('로그인이 필요합니다');
                redirect('/login');
                break;
            default:
                console.log('알 수 없는 에러');
                break;
        }
    });
}

function createQuillEditor() {
    return new Quill('#editor-container', {
        readOnly: true
    });
}

registerComment();