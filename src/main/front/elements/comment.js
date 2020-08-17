import {millisToTimeString, now, redirect, setStyle} from "../common/utils";
import {request, RequestMethod} from "../common/request";

export class Comment extends HTMLElement {
    constructor() {
        super();

        /** @type ?number */
        this.commentId = null;
        /** @type ?number */
        this.questId = null;
        /** @type ?String */
        this.nickname = null;
        /** @type ?String */
        this.content = null;
        /** @type ?number */
        this.regTime = null;

        /** @type ?HTMLElement */
        this.authorDiv = null;
        /** @type ?HTMLElement */
        this.timeDiv = null;
        /** @type ?HTMLElement */
        this.contentDiv = null;
        /** @type ?HTMLElement */
        this.btnModify = null;
        /** @type ?HTMLElement */
        this.btnDelete = null;
        /** @type ?HTMLElement */
        this.btnConfirm = null;
        /** @type ?HTMLElement */
        this.btnCancle = null;
        /** @type ?HTMLElement */
        this.textBox = null;
    }

    connectedCallback() {
        this.commentId = parseInt(this.getAttribute('comment-id'));
        this.questId = parseInt(this.getAttribute('quest-id'));
        this.nickname = this.getAttribute('nickname');
        this.content = this.getAttribute('content');
        this.regTime = parseInt(this.getAttribute('reg-time'));

        this.build_();
        this.setData_();
    }

    build_() {
        const headerRow = document.createElement('div');

        this.authorDiv = document.createElement('span');
        headerRow.appendChild(this.authorDiv);

        this.timeDiv = document.createElement('span');
        headerRow.appendChild(this.timeDiv);
        this.appendChild(headerRow);

        this.contentDiv = document.createElement('div');
        this.appendChild(this.contentDiv);

        this.textBox = document.createElement('input');
        this.textBox.setAttribute('type', 'text');
        this.textBox.style.display = 'none';
        this.appendChild(this.textBox);

        this.btnModify = document.createElement('button');
        this.appendChild(this.btnModify);

        this.btnDelete = document.createElement('button');
        this.appendChild(this.btnDelete);

        this.btnConfirm = document.createElement('button');
        this.appendChild(this.btnConfirm);
        this.btnConfirm.style.display = 'none';

        this.btnCancle = document.createElement('button');
        this.appendChild(this.btnCancle);
        this.btnCancle.style.display = 'none';

        // 수정 버튼
        this.btnModify.onclick = function () {
            this.parentElement.btnModify.style.display = 'none';
            this.parentElement.btnDelete.style.display = 'none';
            this.parentElement.contentDiv.style.display = 'none';
            this.parentElement.btnConfirm.style.display = 'block';
            this.parentElement.btnCancle.style.display = 'block';
            this.parentElement.textBox.style.display = 'block';

            // 확인 버튼
            this.parentElement.btnConfirm.onclick = function () {
                request('/comment/' + this.parentElement.commentId, {
                    method: RequestMethod.PUT,
                    body: JSON.stringify(this.parentElement.commentId)
                }).then(response => {
                    switch (response.status) {
                        case 200 :
                            console.log('성공');
                            break;
                        case 400:
                            console.log('실패');
                            break;
                        case 401:
                            console.log('로그인이 필요합니다');
                            alert('로그인이 필요합니다');
                            // 로그인 페이지로 redirect
                            redirect('/login');
                            break;
                        case 403:
                            console.log('권한이 없습니다');
                            alert('권한이 없습니다');
                            break;
                        default :
                            console.log('예상치 목한 에러');
                            break;
                    }
                });
            }
            // 취소 버튼
            this.parentElement.btnCancle.onclick = function () {
                this.parentElement.btnModify.style.display = 'block';
                this.parentElement.btnDelete.style.display = 'block';
                this.parentElement.contentDiv.style.display = 'block';
                this.parentElement.btnConfirm.style.display = 'none';
                this.parentElement.btnCancle.style.display = 'none';
                this.parentElement.textBox.style.display = 'none';
            }
        }
        // 삭제 버튼
        this.btnDelete.onclick = function () {
            request('/comment/' + this.commentId, {
                method: RequestMethod.DELETE,
                body: JSON.stringify(this.commentId)
            }).then(response => {
                switch (response.status) {
                    case 200 :
                        console.log('성공');
                        break;
                    case 400:
                        console.log('실패');
                        break;
                    case 401:
                        console.log('로그인이 필요합니다');
                        alert('로그인이 필요합니다');
                        // 로그인 페이지로 redirect
                        redirect('/login');
                        break;
                    case 403:
                        console.log('권한이 없습니다');
                        alert('권한이 없습니다');
                        break;
                    default :
                        console.log('예상치 목한 에러');
                        break;
                }
            });
        }
    }

    setData_() {
        this.authorDiv.innerText = this.nickname;
        this.timeDiv.innerText = millisToTimeString(this.regTime);
        this.contentDiv.innerText = this.content;
        this.btnModify.innerText = '수정';
        this.btnDelete.innerText = '삭제';
        this.btnConfirm.innerText = '확인';
        this.btnCancle.innerText = '취소';
        this.textBox.setAttribute('value', this.content);
    }
}

export function registerComment() {
    customElements.define('quest-comment', Comment);
}