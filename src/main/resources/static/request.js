const ServerUrl = 'http://localhost:8080';

const RequestMethod = {
    GET: 'GET',
    POST: 'POST',
    PUT: 'PUT',
    DELETE: 'DELETE'
};
Object.freeze(RequestMethod);

const ContentType = {
    APPLICATION_JSON: 'application/json'
};
Object.freeze(ContentType);

const RequestException = {
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
 * @return 요청을 처리하는 Promise
 */
async function request(url, config) {
    if (config.method == null) throw RequestException.MissingMethodException;

    if (config.header == null) config.header = {};

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
    return xhr;
}