import Quill from "quill";

export function richTextQuestCard() {
    Array.from(document.getElementsByClassName('quest-card')).forEach(c => {
        const editContainer = c.getElementsByClassName('edit-container')[0];
        const delta = JSON.parse(c.getElementsByClassName('card-content')[0].innerText);
        const quill = createQuillEditorRead(editContainer);
        quill.setContents(delta);
    });
}

export function richText(edit, content) {
    const quill = createQuillEditorRead(edit);
    const delta = JSON.parse(content);
    quill.setContents(delta);
}

function createQuillEditorRead(edit) {
    return new Quill(edit, {
        readOnly: true
    });
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