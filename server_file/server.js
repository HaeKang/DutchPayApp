app.post("/test_app", function(req, res){

        var userId =req.body.userId;
        console.log(test);

        var userSelectSql = "SELECT * FROM user where email=?";

        connection.query(userSelectSql, [userId], function(err, results){
            if(err){
                throw err;
            } else {
                console.log(results);
                var userAccessToken = results[0].accesstoken;
                var userSeqNo = results[0].userseqno;

                res.json({
                    "userAccessToken" : userAccessToken,
                    "userSeqNo" : userSeqNo
                });

            }
        });
});
