import {redirect} from './common/utils';
import {richText} from "./quest/richTextQuestCard";

window.addEventListener('load', () => {
    richText();
    const bntWrite = document.getElementById('btnWrite');
    const bntSearch = document.getElementById('bntSearch');

    bntWrite.onclick = () => redirect('/quest/write');
    bntSearch.onclick = () => {
        const methods = document.getElementById('type');
        const methodValue = methods.options[methods.selectedIndex].value;
        if (methodValue === '') {
            alert('옵션을 선택해 주세요');
        } else {
            const text = document.getElementById('searchContent').value;
            const url = '/search?q=' + text + '&type=' + methodValue;
            redirect('/search?q=' + text + '&type=' + methodValue);
        }
    }
});


