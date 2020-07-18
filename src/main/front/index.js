import {redirect} from './common/utils';

window.addEventListener('load', () => {
    const button = document.getElementById('btnWrite');
    button.onclick = () => redirect('/quest/write');
});
