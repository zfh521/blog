var net = require('net');
var SmtpClient = exports.SmtpClient =function(host,port,useSecure){
	this.host = host;
	this.port = port||25;
	this.socket = new net.Stream();
	var self = this;
	this.operationing = [];
	this.socket.addListener('data',function(data){
		var dataStr = data.toString();
		console.log(dataStr);
		var msg = !/([^ ]+)$/.test(dataStr)||RegExp.$1;
		var code = (dataStr.match(/^\d+/g)||[""])[0];
		switch(code) {
			case "250":
				var operation = self.operationing.pop();
				if(operation == "connect") {
					self.connected = true;
					self.handleEvent("connect");
				} else if(operation == "from") {
					self.handleEvent("from");
				} else if(operation == "lastrcpt") {
					self.handleEvent("rcptok");
				} else if(operation == 'transferdata') {
					self.handleEvent("exit");
				}
			break;
			case "334":
				var operation = self.operationing.pop();
				if(operation == "dataCommand") {
					self.handleEvent("startData");
				} else {
					self.operationing.push(operation);
				}
				var key = new Buffer(msg,"base64").toString().toLowerCase();
				self.handleEvent(key);
			break;
			case "235" :
				self.authed = true;
				self.handleEvent("authed");
			break;
			case "221" :
				self.socket.end();
			break;
		}
	});
	if(useSecure) {
		this.socket.setSecure();
	}
	this.listeners = {};
	
}
SmtpClient.prototype = {
	send : function() {
		this.socket.connect(this.port,this.host);
		var self = this;
		this.socket.addListener('connect',function() {
			self.operationing.push("connect");
			self.socket.write("HELO\r\n");
		});
	},
	auth : function(username,password) {
		var needAuth = !!username;
		this.options = {
			username:username,
			password:password
		};
		if(needAuth) {
			var self = this;
			this.addListener("connect",function() {
				if(!self.authed) {
					self.socket.write("AUTH LOGIN\r\n");
				}
			});
		} else {
			this.authed = true;
			this.handleEvent('authed');
		}
	},
	addListener : function(eventName,listenerFunc) {
		var ary = this.listeners[eventName]?this.listeners[eventName]:(this.listeners[eventName]=[]);
		ary.push(listenerFunc);
		this.handleEvent(eventName,listenerFunc)
	},
	sendTo : function(nameAry) {
		var self = this;
		for(var i=0;i<nameAry.length;i++) {
			var name = nameAry[i];
			(function(name,isLast) {
				self.addListener("from",function() {
					if(isLast) {
						self.operationing.push("lastrcpt");
					} else {
						self.operationing.push("notlastrcpt");
					}
					self.socket.write("RCPT TO:"+name+"\r\n");
				});
			})(name,i==nameAry.length-1);
		}
	},
	from : function(addr) {
		var self = this;
		this.addListener("authed",function() {
			self.operationing.push("from");
			self.socket.write("MAIL FROM:"+addr+"\r\n");
		});
	},
	handleEvent : function(eventName,func) {
		if(eventName.indexOf("username")>=0) {
			this.socket.write(new Buffer(this.options.username).toString("base64")+"\r\n");
		}
		if(eventName.indexOf("password")>=0) {
			this.socket.write(new Buffer(this.options.password).toString("base64")+"\r\n");
		}
		if(eventName == "exit") {
			this.socket.write("QUIT\r\n");
		}
		if((eventName == "connect"||
			eventName == "authed"||
			eventName == "from"||
			eventName == "rcptok"||
			eventName =="startData")&&this.connected) {
			if(func) {
				func();
			} else {
				var funcs = this.listeners[eventName];
				for(var i=0;funcs&&(i<funcs.length);i++) {
					funcs[i]();
				}
			}
		}
	},
	data : function(data) {
		var self = this;
		this.addListener("rcptok",function() {
			self.operationing.push("dataCommand");
			self.socket.write("DATA\r\n");
			self.addListener("startData",function() {
				self.operationing.push("transferdata");
				self.socket.write(data+"\r\n.\r\n");
			});
		});
	}
}
