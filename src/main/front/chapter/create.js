import {request, RequestMethod} from "../common/request";

/**
 * 챕터 생성 요청을 보냄
 * @param title 챕터 타이틀
 * @param description 챕터 설명
 * @param isPublic public인지 아닌지 boolean
 */
function createChapter(title, description, isPublic) {
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