import Quill from "quill";
import {deleteMetaDiv} from "./richTextSelectorFunction";

export function applyRichText(container, delta, readonly=true) {
    if (readonly === true) {
        const quill = createQuillEditorRead(container);
        const metaDivContainer = document.getElementById('meta');
        quill.setContents(delta);
        deleteMetaDiv(metaDivContainer);
        container.style.border = 'none';
    } else {
        return createQuillEditorWrite(container);
    }
}

export function applyRichTextToAll(selectorFunction) {
    const elemArray = selectorFunction();
    Array.from(elemArray).forEach(c => {
        applyRichText(c[0], c[1]);
    })
}

function createQuillEditorRead(edit) {
    return new Quill(edit, {
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