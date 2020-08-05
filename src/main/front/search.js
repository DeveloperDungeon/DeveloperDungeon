import {redirect} from "./common/utils";

window.addEventListener('load', () => {
    const bntSearch = document.getElementById('bntSearch');
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