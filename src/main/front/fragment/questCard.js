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
    const quill = createQuillEditor();
    const delta = JSON.parse(document.getElementById('content').innerText);
    console.log(document.getElementById('content').innerText);
    quill.setContents(delta);
});

function createQuillEditor() {
    return new Quill('#editor-container', {
        readOnly: true
    });
}