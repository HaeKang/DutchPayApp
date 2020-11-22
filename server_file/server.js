const express = require('express')
const app = express()

const request = require('request');

// auth 라이브러리
const auth = require('auth');

//----------------- 토큰 발행 패키지 ----------------------
const jwt = require('jsonwebtoken');


//------------------ database 연결 ----------------------
var mysql_info = require("./mysql_info.js");
var mysql = require('mysql');
var connection = mysql.createConnection({
	host: mysql_info[0],
	user: mysql_info[1],
	password: mysql_info[2],
	port: mysql_info[3],
	database: mysql_info[4]
});
connection.connect();

                    
//---------------------- 로그인 ----------------------
app.post('/login', function(req, res){
    var userEmail = req.body.userEmail;
    var userPassword = req.body.userPassword;

    var searchEmailSql = "SELECT * FROM user WHERE email = ?";

    connection.query(searchEmailSql, [userEmail, userPassword] , function(error, results, fields){

        if(error){
            throw error;
        }else {
            if(results.length == 0){

                res.json("존재하지 않는 회원입니다.");

            } else {

                // 비밀번호 확인
                var storedUserPw = results[0].password;
                if(storedUserPw == userPassword){

                    // 로그인 완료 & 토큰발행 -> auth 인증 기능
                    var tokenKey = "f@i#n%tne#ckfhlafkd0102test!@#%";
                    jwt.sign(
                      {
                        userId: results[0].id,
                        userEmail: results[0].email,
                      },
                      tokenKey,
                      {
                        expiresIn: "10d",
                        issuer: "fintech.admin",
                        subject: "user.login.info",
                      },
                      function (err, token) {
                        console.log("로그인 성공", token);
                        res.json({
                            "jwtToken" : token
                        });
                      }
                    );

                } else {
                    // 로그인 실패
                    res.send("비밀번호를 잘못 입력하셨습니다.");
                }

            }
        }

    });

});


//---------------------- 유저 인증  ----------------------

app.post('/userTokenInfo', auth, function(req, res){
    var userId = req.decoded.userId;
    var userSelectSql = "SELECT * FROM user WHERE id = ?";
    
    connection.query(userSelectSql, [userId], function(err, results){
        if(err){
            throw err;
        } else {

            var userAccessToken = results[0].accesstoken;
            var userSeqNo = results[0].userseqno;

            console.log(userAccessToken);
            console.log(userSeqNo);

            res.json({
                "userAccessToken" : userAccessToken,
                "userSeqNo" : userSeqNo
            });
        }
    });

});
