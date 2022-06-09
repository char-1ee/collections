import 'dart:core';

class UserInfo {
  final String username;
  final String password;
  bool isValid = false;

  UserInfo(this.username, this.password);
}

Future<UserInfo> _loadUserFromDB() {
  return Future.delayed(Duration(seconds: 2), () => UserInfo('Char', '123456'));
}

Future<String> _fetchSessionToken(UserInfo userInfo) {
  return Future.delayed(Duration(seconds: 2), () => '3424324sfdsfsdf24324234');
}

Future<String> _fetchData(String token) {
  return Future.delayed(Duration(seconds: 2),
      () => token.isNotEmpty ? Future.value('data') : Future.error('error'));
}

void main() {
  _loadUserFromDB().then((userInfo) {
    return _fetchSessionToken(userInfo);
  }).then((token) {
    return _fetchData(token);
  }).then((data) {
    print('$data');
  }); // (8 seconds after)
  print('main execution done');
}

/**
 * output: 
 * $ dart run future.dart
 * main execution done
 * data 
 */
