import Quill from "quill";

export function applyRichText(edit, delta) {
    const quill = createQuillEditorRead(edit);
    quill.setContents(delta);
    deleteTempContent();
}

export function applyRichTextToAll(selectorFunction) {
    const elemArray = selectorFunction();
    Array.from(elemArray).forEach(c => {
        const editContainer = c[0];
        const delta = c[1];
        const quill = createQuillEditorRead(editContainer);
        quill.setContents(delta);
    })
}

export function questCardRichText() {
    const elemArray = [];
    Array.from(document.getElementsByClassName('quest-card')).forEach(c => {
        const editContainer = c.getElementsByClassName('edit-container')[0];
        const content = c.getElementsByClassName('card-content')[0];
        deleteTempContent();
        const delta = JSON.parse(content.innerText);
        elemArray.push([editContainer, delta]);
    });
    return elemArray;
}

function createQuillEditorRead(edit) {
    return new Quill(edit, {
        readOnly: true
    });
}

function deleteTempContent() {
    const content = document.getElementById('meta');
    const parent = content.parentNode;
    parent.removeChild(content);
}

export function createQuillEditorWrite(container) {
    return new Quill(container, {
        modules: {
            toolbar: [
                [{header: [1, 2, false]}],
                ['bold', 'italic', 'underline'],
                ['image', 'code-block']
            ]
        },
        theme: 'snow'
    });
}