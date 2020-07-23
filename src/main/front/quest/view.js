import {request} from "../common/request";
import {RequestMethod} from "../common/request";
import {now} from "../common/utils";

document.addEventListener('load', () => {
    const button = document.getElementById('button-comment');
    button.onclick = onCommentButtonClick;
});

function onCommentButtonClick() {
    const input = document.getElementById('input-comment');
    const content = input.value;

    // request('/quest/write', {
    //     method: RequestMethod.POST,
    //     body: JSON.stringify({content: content, time: now()})
    // });
}