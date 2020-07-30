/**
 * 获取当前路径
 * @returns {string}
 */
function getPath() {

    var protocol = location.protocol; //获取http或https
    var host = window.location.host; //获取IP和端口号
    var context = window.location.pathname.split("/"); //获取项目名
    var basePath = protocol + "//" + host;
    // 此处要获取到哪一层 自己进行判断选择
    for (var i = 0; i < context.length - 1; i++) {
        basePath += context[i] + '/';
        if (context[i] === "") {
            break;
        }

    }
    return basePath;
}
