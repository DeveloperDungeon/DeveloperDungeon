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

export function richText() {
    Array.from(document.getElementsByClassName('quest-card')).forEach(c => {
        const editContainer = c.getElementsByClassName('edit-container')[0];
        const delta = JSON.parse(c.getElementsByClassName('card-content')[0].innerText);
        const quill = createQuillEditor(editContainer);
        quill.setContents(delta);
    });
}

function createQuillEditor(edit) {
    return new Quill(edit, {
        readOnly: true
    });
}