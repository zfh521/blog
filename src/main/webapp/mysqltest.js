var mysql = require('mysql');
var conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'admin123',
    database:'meilein',
    port: 3306
});
conn.connect();
conn.query('select * from account', function(err, rows, fields) {
    if (err) throw err;
    console.log(rows);
});
conn.end();