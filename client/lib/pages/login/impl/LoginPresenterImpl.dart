import 'package:auction_trainer_client_v2/inject_config/DependenciesConfiguration.dart';
import 'package:auction_trainer_client_v2/pages/login/api/LoginPresenter.dart';
import 'package:auction_trainer_client_v2/pages/login/api/SignInView.dart';
import 'package:auction_trainer_client_v2/pages/login/api/SignUpView.dart';
import 'package:auction_trainer_client_v2/security/api/AuthService.dart';
import 'package:auction_trainer_client_v2/security/model/requests/LoginRequest.dart';
import 'package:auction_trainer_client_v2/security/model/requests/RegisterRequest.dart';
import 'package:injectable/injectable.dart';

@LazySingleton(as: LoginPresenter)
class LoginPresenterImpl implements LoginPresenter {
  var emailValid = RegExp(
      r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+");

  SignInView? signIn;
  SignUpView? signUp;

  @override
  void login(String password, String email) async {
    if (!emailValid.hasMatch(email)) {
      signIn?.showEmailError("Wrong email format");
      return;
    }
    if (password.length < 4) {
      signIn?.showPasswordError("Password is too short");
      return;
    }

    signIn?.showLoading();
    LoginRequest request = LoginRequest(email: email, password: password);
    await getIt<AuthService>()
        .login(request)
        .then((value) => signIn?.openMainPage())
        .onError((error, stackTrace) => signIn
            ?.showGlobalError("Wrong credentials/Server is unaccessable"));
    signIn?.hideLoading();
  }

  @override
  void register(String password, String email, String username) async {
    if (!emailValid.hasMatch(email)) {
      signUp?.showEmailError("Wrong email format");
      return;
    }
    if (password.length < 4) {
      signUp?.showPasswordError("Password is too short");
      return;
    }
    if (username.length < 2) {
      signUp?.showUsernameError("Username is too short");
      return;
    }
    signUp?.showLoading();
    RegisterRequest request =
        RegisterRequest(email: email, password: password, username: username);
    await getIt<AuthService>()
        .register(request)
        .then((value) => signUp?.openMainPage())
        .onError((error, stackTrace) =>
            signUp?.showGlobalError("Server is unaccessable"));
    signUp?.hideLoading();
  }

  @override
  void setSignInView(SignInView view) {
    signIn = view;
  }

  @override
  void setSignUpView(SignUpView view) {
    signUp = view;
  }
}
