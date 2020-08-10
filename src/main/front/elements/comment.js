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
        this.bntModify = null;
        /** @type ?HTMLElement */
        this.bntDelete = null;
        /** @type ?HTMLElement */
        this.bntConfirm = null;
        /** @type ?HTMLElement */
        this.bntCancle = null;
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

        this.bntModify = document.createElement('button');
        this.appendChild(this.bntModify);

        this.bntDelete = document.createElement('button');
        this.appendChild(this.bntDelete);

        this.bntConfirm = document.createElement('button');
        this.appendChild(this.bntConfirm);
        this.bntConfirm.style.display = 'none';

        this.bntCancle = document.createElement('button');
        this.appendChild(this.bntCancle);
        this.bntCancle.style.display = 'none';

        // 수정 버튼
        this.bntModify.onclick = function () {
            this.parentElement.bntModify.style.display = 'none';
            this.parentElement.bntDelete.style.display = 'none';
            this.parentElement.contentDiv.style.display = 'none';
            this.parentElement.bntConfirm.style.display = 'block';
            this.parentElement.bntCancle.style.display = 'block';
            this.parentElement.textBox.style.display = 'block';
            
            // 확인 버튼
            this.parentElement.bntConfirm.onclick = function () {
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
            this.parentElement.bntCancle.onclick = function () {
                this.parentElement.bntModify.style.display = 'block';
                this.parentElement.bntDelete.style.display = 'block';
                this.parentElement.contentDiv.style.display = 'block';
                this.parentElement.bntConfirm.style.display = 'none';
                this.parentElement.bntCancle.style.display = 'none';
                this.parentElement.textBox.style.display = 'none';
            }
        }
        // 삭제 버튼
        this.bntDelete.onclick = function () {
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
        this.bntModify.innerText = '수정';
        this.bntDelete.innerText = '삭제';
        this.bntConfirm.innerText = '확인';
        this.bntCancle.innerText = '취소';
        this.textBox.setAttribute('value', this.content);
    }
}

export function registerComment() {
    customElements.define('quest-comment', Comment);
}