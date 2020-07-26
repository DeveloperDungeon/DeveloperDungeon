import {redirect} from './common/utils';

window.addEventListener('load', () => {
    const bntWrite = document.getElementById('btnWrite');
    bntWrite.onclick = () => redirect('/quest/write');


});
