import {redirect} from "../common/utils";
console.log('jsfile');
window.addEventListener('load', () => {

    document.getElementsByClassName("quest-card").onclick = () => {
        redirect('/quest/' + id);
    }
});