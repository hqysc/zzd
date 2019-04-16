//截取地址栏中url的参数值
function getUrlVal(param) {
    var search = location.search; // 截取页面Url中'?'后面的部分
    var arrParam = new Array(); // 参数数组
    var arrVal = new Array(); // 参数值数组

    if(search != null) {
        var index = 0;
        search = search.substr(1); // 把'?'去掉 从第二个字符开始
        arrParam = search.split('&'); // 分割url
    }
    for(i in arrParam) {
        var paramPre = param + "="; // 参数前缀
        // 字符串值在字符串中首次出现的位置
        if(arrParam[i].indexOf(paramPre) == 0 && paramPre.length < arrParam[i].length) {
            arrVal[index] = decodeURI(arrParam[i].substr(paramPre.length)); // url解码
            index++;
        }
    }

    if(arrVal.length >= 0) {
        return arrVal;
    }else {
        return null;
    }
}