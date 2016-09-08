
const COLLECTION_USER = "user";
const CODE_USER_EXISTS = "CODE_USER_EXISTS";
const CODE_USER_ADDED = "CODE_USER_ADDED";
const CODE_ERROR_INSERT = "CODE_ERROR_INSERT";
const CODE_ERROR_QUERY = "CODE_ERROR_QUERY";
const CODE_ERROR_LOGIN = "CODE_ERROR_LOGIN";
const CODE_SUCCESS = "CODE_SUCCESS";

exports.registerUser = function registerUser(db, pUser, pCallback){
  queryUser(db, pUser, function(err, result, pUser){
    if (err){
      pCallback(err, pUser);
      return;
    }

    if (result === null){
      db.open(function(err, db){
    		var collection = db.collection(COLLECTION_USER);
    		collection.insert(pUser, function(err, result){
    			db.close();

          if (err){
            pCallback(CODE_ERROR_INSERT, pUser);
          } else {
            pCallback(CODE_USER_ADDED, pUser);
          }

    		});
      });
    } else {
      pCallback(CODE_USER_EXISTS, pUser);
    }
  });
}

exports.login = function login(db, pUser, pCallback){
  queryUser(db, pUser, function(err, result, pUser){
    if (err){
      pCallback(err, null);
      return;
    }

    if (result === null){
      pCallback(CODE_ERROR_LOGIN, null);
    } else {

      if (result.password === pUser.password){
        pCallback(CODE_SUCCESS, pUser);
      } else {
        pCallback(CODE_ERROR_LOGIN, null);
      }

    }
  });
}

function queryUser(db, pUser, pCallback){
  db.open(function(err, db){
    var collection = db.collection(COLLECTION_USER);
    collection.findOne({ username: pUser.username }, function(err, result){
      db.close();

      if (err){
        pCallback(CODE_ERROR_QUERY, result, pUser);
      } else {
        pCallback(err, result, pUser);
      }
    });
  });
}
