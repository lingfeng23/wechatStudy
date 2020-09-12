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