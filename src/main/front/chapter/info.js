import {request, RequestMethod} from "../common/request";

window.addEventListener('load', () => {
    console.log('test');

});
function clickDelete(id) {
    request('api/chapter/' + id, {
        method: RequestMethod.DELETE
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
