// 使用http对象来引用http模块。
var http = require('http');
/**
 * 使用http模块的createServer方法来创建用于接收HTTP客户端
 * 请求并返回响应的HTTP服务器应用程序
 */
http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write('<head><meta charset="utf-8"/></head>');
    res.end('你好\n');
}).listen(1337, "127.0.0.1");

console.log('Server running at http://127.0.0.1:1337/');
// 控制台输入命令会将 log 内容输出到 info.log 文件中，1代表重定向标准输出流
// node server.js 1>info.log

console.log("%s","malf", "foo") // malf foo
console.log("%s", "malf", {foo:"FOO"})  // malf { foo: 'FOO'}
console.log("%d", 10, 10.5) // 10 10.5
console.log("%d", "malf")   // NaN
console.log("%%", "malf")   // % malf

console.error("错误信息");  // 显示为红色
// 错误信息输出到 error.log 文件中，2代表重定向标准错误输出流
// node server.js 2>error.log

var user = new Object();
user.name = "malf"
user.getName = function () {return this.name}
user.setName = function (name) { this.name = name}
console.dir(user)

console.time("a")
console.timeEnd("a")    // a: 0.075ms

// console.trace方法中使用参数，参数值可以为任何字符串，用于标识
// 此处输出的标准错误信息。
console.trace("trace")