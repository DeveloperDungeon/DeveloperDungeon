import {redirect} from "../common/utils";

window.addEventListener('load', () => {
    const id = document.getElementById('quest_id').value;
    document.getElementById('bntEdit').onclick = () => {
        redirect('/quest/edit/' + id);
    }

    document.getElementById('bntRemove').onclick = () => {
        redirect('quest/remove/' + id)
    }
});