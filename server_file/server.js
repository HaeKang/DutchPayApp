const express = require('express')
const app = express()
// auth 라이브러리
const auth = require('./lib/auth');


// JSON 타입의 데이터를 받기위한 설정
app.use(express.json());
// urlencoded 타입의 데이터를 받기위한 설정
app.use(express.urlencoded({ extended: false }));

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
    console.log(req.body);

    var userEmail = req.body.userEmail;
    var userPassword = req.body.userPassword;


    var searchEmailSql = "SELECT * FROM user WHERE email = ?";

    connection.query(searchEmailSql, [userEmail] , function(error, results, fields){

        if(error){
            throw error;
        }else {
            if(results.length == 0){
                console.log("존재하지 않는 회원입니다.");

            } else {

                // 비밀번호 확인
                var storedUserPw = results[0].password;
                console.log(storedUserPw+ " ," + userPassword);
                
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


// 더치페이 테이블 데이터 삽입
app.post('/dutchinsert', auth, function(req, res){
    var userId = req.decoded.userId;    // 총무id
    var dutchname = req.body.dutchname;      // 더치페이명
    var finnum = req.body.finnum;       // 총무핀넘버
    var dutchinfoname = req.body.dutchinfoname; // 더치 소비이름
    var money = req.body.money;
    var personnum = req.body.personnum;
    var personnames = req.body.personnames;

    console.log("여기요 : " + personnum);

    var dutchInsertSql = "insert into dutch(name, id, finnum) values(?,?,?)";
    var dutchInfoInsertSql = "insert into dutchinfo(dutchid, name, money, person) values(?,?,?,?)";
    var dutchPersonInsertSql = "insert into dutchperson(dutchinfoid, name) values(?,?)";
    
    connection.query(dutchInsertSql, [dutchname,userId,finnum], function(err, results){
        if(err){
            console.log("여기에러1", err);
            res.json({
                "state" : "error"
            });
        } else {
            console.log(results);
            var dutchid = results.insertId;

            connection.query(dutchInfoInsertSql, [dutchid, dutchinfoname, money, personnum], function(err, results2){
                if(err){
                    console.log("여기에러2", err);
                    res.json({
                        "state" : "error"
                    });
                } else {
                    console.log(results2);
                    var dutchinfoid = results2.insertId;
                    for(var i=0; i<personnames.length; i++){
                        var personname = personnames[i]
                        connection.query(dutchPersonInsertSql, [dutchinfoid, personname], function(err, results3){
                            if(err){
                                console.log("여기에러3", err);
                                res.json({
                                    "state" : "error"
                                });
                            }
                        });
                    }
                    res.json({
                        "state" : "성공"
                    });
                }
            });

            
        }
    });

});

// 내가 총무인 더치페이 목록 불러오기
app.get('/mydutchlist', auth,  function(req, res){
    var userId = req.decoded.userId;
    var myDutchListSql = "select * from dutch where id = ?";
    
    console.log(userId);

    connection.query(myDutchListSql, [userId], function(err, results){
        if(err){
            console.log("여기에러4 " , err);
        } else {
            res.json(results);
        }
    });
});

// 내가 지불해야할 더치페이 목록 불러오기
app.get('/mypaylist', auth,  function(req, res){
    var userEmail = req.decoded.userEmail;
    var myPayListSql = "select d.name, finnum, money, person from dutch d, (select money, person, i.dutchinfoid, i.dutchid from dutchperson p, dutchinfo i where p.name = ?) a where a.dutchid = d.dutchid";

    connection.query(myPayListSql, [userEmail], function(err, results){
        if(err){
            console.log("여기에러5" , err);
        } else {
            res.json(results);
            console.log(results);
        }
    });
});


// 입금 시 테이블에서 제거
app.post('/deletemypay', auth, function(req, res){
    var userEmail = req.decoded.userEmail;
    var DeleteSql = "delete from dutchperson where name = ?";

    connection.query(DeleteSql, [userEmail], function(err, results){
        if(err){
            console.log("여기에러6", err);
        } else {
            res.json(results);
        }
    });
});


// qr코드 생성을 위한 데이터
app.post('/makeqr', function(req, res){
    console.log("makeqr 시작");
    var dutchid = req.body.dutchid;
    console.log("더치아이디 : " + dutchid);
    var selectSql = "select finnum, money, person from dutch d, dutchinfo i where d.dutchid = i.dutchid and d.dutchid = ?";

    connection.query(selectSql, [dutchid], function(err, results){
        if(err){
            console.log("여기에러7", err);
        } else {
            console.log(results);
            res.json(results);
        }
    });
});

//---------------------- 서버 실행 ----------------------
app.listen(3000, function(){
    console.log('서버가 3000번 포트에서 실행중 입니다.');
})
