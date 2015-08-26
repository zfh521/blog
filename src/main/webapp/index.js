var express = require('express');
var bodyParser     =         require("body-parser"); 
var nodemailer = require('nodemailer');
var app = express();
app.use('/css',express.static(__dirname + '/css'));
app.use('/img',express.static(__dirname + '/img'));
app.use('/js',express.static(__dirname + '/js'));
app.use('/script',express.static(__dirname + '/script'));

app.use(bodyParser.urlencoded({ extended: false }));  
app.post('/api/register',function(req,res){
	//sendTo();
	res.send('user ' + req.para.callback);
})
app.get(['/','/index.html'], function(req, res){
	res.sendfile('./index.html');
});
app.get(['/register.html'], function(req, res){
	res.sendfile('./login.html');
});
app.get('/mysql',function  (req,res) {
   var mysql = require('mysql');
	var conn = mysql.createConnection({
	    host: 'localhost',
	    user: 'root',
	    password: 'admin123',
	    database:'meilein',
	    port: 3306
	});
	var text="-1";
	var ended=false;
	conn.connect();
	conn.query('select * from account', function(err, rows, fields) {
	    if (err) throw err;
	    console.log(rows);
	    text=rows.length;
	    ended=true;
	});
	conn.end();
	while(!ended){}
	res.send(text)
	res.end();
});
function insert(end,back){
   if(end){
   	return back;
   }
}
function sendTo(){
	 var transporter = nodemailer.createTransport({
	    host: 'smtp.qq.com',
	    auth: {
	        user: '316547926@qq.com',
	        pass: 'zfh7758521_'
	    }
	});

	var mailOptions = {
	    from: '316547926@qq.com ', // sender address
	    to: 'mefly@qq.com', // list of receivers
	    subject: 'Hello ✔', // Subject line
	    text: 'Hello world ✔', // plaintext body
	    html: '<b>Hello world ✔</b>' // html body
	};

	transporter.sendMail(mailOptions, function(error, info){
	    if(error){
	        console.log(error);
	    }else{
	        console.log('Message sent: ' + info.response);
	    }
	});
}
app.listen(3000);