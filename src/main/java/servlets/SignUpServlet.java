/*При получении POST запроса на signup сервлет SignUpServlet должн запомнить логин и пароль в AccountService.
* Сервлеты должны слушать POST запросы с параметрами
login
password*/
//signup регистрирую юзера
//signin авторизцю

package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet{

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass  = request.getParameter("password");

        if (login  == null || pass  == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
//регистрирует
        accountService.addNewUser(new UserProfile(login, pass, "someemail"));//я должна созать нового юзера с входными памретрами
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}