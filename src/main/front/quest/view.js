import {request} from "../common/request";
import {RequestMethod} from "../common/request";
import {now} from "../common/utils";

window.addEventListener('load', () => {
    checkRedirectionIssue();

    const button = document.getElementById('button-comment');
    button.onclick = onCommentButtonClick;
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
    const input = document.getElementById('input-comment');
    const content = input.value;



    request('/reply', {
        method: RequestMethod.POST,
        body: JSON.stringify({content: content, time: now()})
    });
}