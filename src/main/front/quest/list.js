import {applyRichTextToAll} from "../richText/richText";
import {questCardRichText} from "../richText/richTextSelectorFunction";

window.addEventListener('load', () => {
    applyRichTextToAll(questCardRichText);
});
