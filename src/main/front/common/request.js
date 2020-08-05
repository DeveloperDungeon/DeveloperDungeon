import '@babel/polyfill';
import {redirect} from "./utils";

export const RequestMethod = {
    GET: 'GET',
    POST: 'POST',
    PUT: 'PUT',
    DELETE: 'DELETE'
};
Object.freeze(RequestMethod);

export const ContentType = {
    APPLICATION_JSON: 'application/json'
};
Object.freeze(ContentType);

export const RequestException = {
    MissingMethodException: "Missing method exception",
    NoSuchMethodException: "No such method exception"
};
Object.freeze(RequestException);

/**
 * Http 요청을 보냄.
 * @param url 요청을 보낼 url
 * @param config 요청 정보 {
 *     method: RequestMethod, // Http 메서드
 *     header: { // 요청의 헤더
 *         contentType: string // Content-Type 헤더 (default ContentType.APPLICATION_JSON)
 *     },
 *     body: string // 요청의 body
 * }
 * @return {!Promise<!Response>}
 */
export async function request(url, config) {
    if (config.method == null) throw RequestException.MissingMethodException;

    if (config.header == null) config.header = {};
    if (config.doRedirection == null) config.doRedirection = true;

    const method = RequestMethod[config.method];
    if (method == null) throw RequestException.NoSuchMethodException;

    const xhr = new XMLHttpRequest();
    xhr.open(method, url, false);

    switch (method) {
        // Get, Delete 에는 request body 없음
        case RequestMethod.GET: case RequestMethod.DELETE: break;
        // Post, Put 에는 request body 명시
        case RequestMethod.POST: case RequestMethod.PUT:
            const contentType = config.header.contentType || ContentType.APPLICATION_JSON;
            xhr.setRequestHeader("Content-Type", contentType);
            break;
    }

    xhr.send(config.body);

    const body = JSON.parse(xhr.response || '{}');
    if (config.doRedirection && xhr.status === 300)
        redirect(body.url);

    return new Response(xhr.status, body);
}

/**
 * @class Response Response class for the request() method
 */
export class Response {
    constructor(status, body) {
        /** @private @const {number} 응답 상태 코드 */
        this.status = status;
        /** @private @const {!Object} 응답 내용 */
        this.body = body;
    }
}