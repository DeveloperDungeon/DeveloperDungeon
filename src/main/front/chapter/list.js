import {request, RequestMethod} from "../common/request";

function clickDelete(id) {
    request('api/chapter/' + id, {
        method: RequestMethod.DELETE,
        body: JSON.stringify(id)
    }).then(response => {
        switch (response.status) {
            case 400:
                alert('존재하지 않는 챕터입니다');
                break;
            default:
                console.log('알 수 없는 에러');
                break;
        }
    })
}