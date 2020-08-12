import Quill from "quill/core";
import Snow from "quill/themes/snow";
import Bold from "quill/formats/bold";
import Italic from "quill/formats/italic";
import Header from "quill/formats/header";
import Underline from "quill/formats/underline";
import CodeBlock from "quill/formats/code";
Quill.register({
    'themes/snow': Snow,
    'formats/bold': Bold,
    'formats/italic': Italic,
    'formats/header': Header,
    'formats/underline': Underline,
    'formats/code-block': CodeBlock,
});

window.addEventListener('load', () => {
    const content = document.getElementsByClassName('quest-card');
    for (let i = 0; i < content.length; i++) {
        const editContainer = content[i].getElementsByClassName('edit-container')[0];
        const delta = JSON.parse(content[i].getElementsByClassName('card-content')[0].innerText);
        const quill = createQuillEditor(editContainer);
        quill.setContents(delta);
    }
});

function createQuillEditor(edit) {
    return new Quill(edit, {
        readOnly: true
    });
}