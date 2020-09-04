import Quill from "quill";

export function richTextQuestCard() {
    Array.from(document.getElementsByClassName('quest-card')).forEach(c => {
        const editContainer = c.getElementsByClassName('edit-container')[0];
        const delta = JSON.parse(c.getElementsByClassName('card-content')[0].innerText);
        const quill = createQuillEditorRead(editContainer);
        quill.setContents(delta);
        editContainer.style.border = 'none';
    });
}

export function richText(edit, content) {
    const quill = createQuillEditorRead(edit);
    const delta = JSON.parse(content);
    quill.setContents(delta);
    edit.style.border = 'none';
}

function createQuillEditorRead(container) {
    return new Quill(container, {
        readOnly: true,
        theme: 'snow',
        modules: {
            toolbar: false
        }
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