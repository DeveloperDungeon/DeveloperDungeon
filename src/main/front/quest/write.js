import Quill from 'quill/core';

import Toolbar from 'quill/modules/toolbar';
import Snow from 'quill/themes/snow';

import Bold from 'quill/formats/bold';
import Italic from 'quill/formats/italic';
import Header from 'quill/formats/header';
import Underline from 'quill/formats/underline';
import CodeBlock from 'quill/formats/code';
import {request, RequestMethod} from '../common/request';

Quill.register({
  'modules/toolbar': Toolbar,
  'themes/snow': Snow,
  'formats/bold': Bold,
  'formats/italic': Italic,
  'formats/header': Header,
  'formats/underline': Underline,
  'formats/code-block': CodeBlock,
});

window.addEventListener('load', () => {
  const quill = createQuillEditor();

  const [type, id, content] = getMeta();

  if (type === 'edit') {
    console.log(content);
    const delta = JSON.parse(content);
    quill.setContents(delta);
  }

  document.getElementById('btnSubmit').onclick = () => {
    const title = document.getElementById('input').value;
    const content = JSON.stringify(quill.getContents());

    console.log(content);

    if (type === 'edit') requestEditQuest(id, title, content);
    else requestNewQuest(title, content);
  };
});

function createQuillEditor() {
  return new Quill('#editor-container', {
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

function getMeta() {
  const typeDiv = document.getElementById('type');
  const idDiv = document.getElementById('id');
  const contentDiv = document.getElementById('content');

  return [
    typeDiv.innerText,
    idDiv.innerText,
    contentDiv.innerText
  ];
}


function requestNewQuest(title, content) {
  const body = {
    title: title,
    content: content,
    regTime: (new Date()).getTime()
  };

  request('/quest/write', {
    method: RequestMethod.POST,
    body: JSON.stringify(body)
  }).then(res => console.log(res));
}

function requestEditQuest(id, title, content) {
  const body = {
    title: title,
    content: content
  };

  request('/quest/edit/' + id, {
    method: RequestMethod.POST,
    body: JSON.stringify(body)
  }).then(res => console.log(res));
}
