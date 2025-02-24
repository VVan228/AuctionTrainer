import 'package:auction_trainer_client_v2/security/api/ServerDataProvider.dart';
import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';

import '../../inject_config/DependenciesConfiguration.dart';
import '../api/TokenService.dart';

@LazySingleton(as: ServerDataProvider)
class RetrofitDataProvider implements ServerDataProvider {
  String baseUrl = const String.fromEnvironment('SERVER_URL', defaultValue: '');
  String msgUrl =
      const String.fromEnvironment('MESSAGING_URL', defaultValue: '');

  @override
  String getBaseUrl() {
    return baseUrl;
  }

  @override
  Future<Dio> getDioInstance(bool auth) async {
    if (!auth) {
      return Dio();
    }
    Dio dio = Dio();
    String? token = await getIt<TokenService>().getAccessToken();
    dio.options.headers['Authorization'] = token;
    return dio;
  }

  @override
  String getMessagingUrl() {
    return msgUrl;
  }

  @override
  String getChannelName(int roomId, bool admin) {
    //return "room:" + roomId.toString() + (admin?":admin":"");
    return "test";
  }
}
