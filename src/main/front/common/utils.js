/**
 * Set style to element
 * @param element {!HTMLElement}
 * @param style {!Object}
 */
export function setStyle(element, style) {
    Object.entries(style).forEach(([key, styleValue]) => {
        const [value, priority = null] = styleValue.split('!');
        element.style.setProperty(key, value, priority);
    });
}

export function millisToTimeString(millis = now(), format = "yyyy년 MM월 dd일 a/p h시 mm분") {
    const weekKorName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    const weekKorShortName = ["일", "월", "화", "수", "목", "금", "토"];

    const date = new Date(millis);
    return format.replace(/(yyyy|yy|MM|dd|KS|KL|HH|hh|h|mm|ss|a\/p)/gi, function ($1) {
        switch ($1) {
            case "yyyy": return date.getFullYear(); // 년 (4자리)
            case "yy": return (date.getFullYear() % 1000).zeroFill(2); // 년 (2자리)
            case "MM": return (date.getMonth() + 1).zeroFill(2); // 월 (2자리)
            case "dd": return date.getDate().zeroFill(2); // 일 (2자리)
            case "KS": return weekKorShortName[date.getDay()]; // 요일 (짧은 한글)
            case "KL": return weekKorName[date.getDay()]; // 요일 (긴 한글)
            case "HH": return date.getHours().zeroFill(2); // 시간 (24시간 기준, 2자리)
            case "hh": return _getHour(date).zeroFill(2); // 시간 (12시간 기준, 2자리)
            case "h": return _getHour(date).toString(); // 시간
            case "mm": return date.getMinutes().zeroFill(2); // 분 (2자리)
            case "ss": return date.getSeconds().zeroFill(2); // 초 (2자리)
            case "a/p": return date.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
            default: return $1;
        }
    });
}

function _getHour(date) {
    const h = date.getHours() % 12;
    if (h === 0) return 12;
    return h;
}

/**
 * Returns current time millis
 * @returns {number} current time millis
 */
export function now() {
    return new Date().getTime();
}

export function redirect(url, params) {
    if (params) url = _appendQueryParams(url, params);
    window.location.href = url;
}

function _appendQueryParams(url, params) {
    const [baseUrl, rawParams] = url.split('?');
    if (rawParams) {
        const originalParams = {};
        rawParams.split('&').forEach(p => {
            const [key, value] = p.split('=');
            originalParams[key] = value;
        });
        params = {...originalParams, ...params};
    }
    const paramKeys = Object.keys(params);
    if (paramKeys.length === 0) return baseUrl;

    const paramString = '?' + paramKeys.map(key => `${key}=${params[key]}`).join('&');
    return baseUrl + paramString;
}

String.prototype.zeroFill = function (len) { return "0".repeat(len - this.length >= 0? len - this.length : 0) + this; };
Number.prototype.zeroFill = function (len) { return this.toString().zeroFill(len); };