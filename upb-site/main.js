const DEFAULT_PORT = 3001;
var PORT = DEFAULT_PORT;
const DB_NAME = "upbdb";

//  ./mongod --dbpath="/Users/dean/Documents/Mix/upb-mongodb" --port 27017
//  ./mongo "mongodb://localhost:27017"
//  ipconfig getifaddr en0

//  Dependencies
var express = require("express");
var app = express();
var bodyParser = require("body-parser")
var dbhelper = require("./upb-dbhelper")

// var assert = require("assert");
var mongodb = require("mongodb");
var Db = mongodb.Db;
// var MongoClient = mongodb.MongoClient;
var Server = mongodb.Server;

var db = new Db(DB_NAME, new Server("localhost", 27017), {w: 1});

var portInput = process.argv[2];
if (isNumeric(portInput)){
  PORT = portInput;
}


app.use(bodyParser.json());

app.listen(PORT, function(){
  console.log("Started UPB on Port: %d", PORT);
});

app.get("/", function(req, res){
  res.send("Hello!");
});


app.post("/api/register", function(req, res){
  if (!req.body) return res.sendStatus(400);
  var body = req.body;

  dbhelper.registerUser(db, {username: body.username, password: body.password}, function(resultCode, pUser){
    console.log(resultCode + " " + pUser);
    res.json({resultCode: resultCode, user: pUser});
  });
});


app.post("/api/login", function(req, res){
  if (!req.body) return res.sendStatus(400);
  var body = req.body;

  dbhelper.login(db, {username: body.username, password: body.password}, function(resultCode, pUser){
    console.log(resultCode + " " + pUser);
    res.json({resultCode: resultCode, user: pUser});
  });
});

// function isJsonString(str) {
//     try {
//         JSON.parse(str);
//     } catch (e) {
//         return false;
//     }
//     return true;
// }

function isNumeric(input){
  return !isNaN(parseFloat(input)) && isFinite(input);
}
