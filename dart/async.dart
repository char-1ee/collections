import 'dart:core';

// To simplify the Future API, Dart promotes async/await

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

void main() async {
  var userInfo = await _loadUserFromDB();
  var token = await _fetchSessionToken(userInfo);
  var data = await _fetchData(token);

  print('$data');
  print('main execution done');
}
