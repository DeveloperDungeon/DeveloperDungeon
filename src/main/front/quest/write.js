import {request, RequestMethod} from "../common/request";
import {now} from "../common/utils";

window.addEventListener('load', () => {
    document.getElementById('btnCancel').onclick = () => {
        window.history.back();
    };

    const ta = document.getElementById('ta');
    const tf = document.getElementById('input');

    document.getElementById('btnConfirm').onclick = () => {
        const title = tf.value;
        const content = ta.value;

        const body = {
            title: title,
            content: content,
            regTime: now()
        };

        request('/quest/write', {
            method: RequestMethod.POST,
            body: JSON.stringify(body)
        }).then(res => console.log(res));
    };
});