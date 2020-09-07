export function questCardRichText() {
    const elemArray = [];
    Array.from(document.getElementsByClassName('quest-card')).forEach(c => {
        const editContainer = c.getElementsByClassName('edit-container')[0];
        const content = c.getElementsByClassName('card-content')[0];
        const delta = JSON.parse(content.innerText);
        elemArray.push([editContainer, delta]);
        editContainer.style.border = 'none';
    });
    return elemArray;
}

export function deleteMetaDiv(container) {
    const parent = container.parentNode;
    parent.removeChild(container);
}