import {request, RequestMethod} from "../common/request";

function createChapter(title, description, whileList) {
    request('/chapter', {
        method: RequestMethod.POST,
        body: JSON.stringify(body)
    }).then(response => {
        switch (response.status) {
            case 403 :
                alert('권한이 없습니다');
                break;
            default:
                console.log('예상치 못한 에러');
                break;
        }
    });
}