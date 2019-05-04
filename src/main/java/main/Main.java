package main;


import accounts.AccountService;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {

        DBService dbService = new DBService();
        dbService.printConnectInfo();
        try {
            //long userId = dbService.addUser("tully");
            long userId = dbService.addUser("root");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

            //dbService.cleanUp(); очищающее
        } catch (DBException e) {
            e.printStackTrace();
        }

        AccountService accountService = new AccountService();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        org.eclipse.jetty.server.Server server = new Server(8080);
        //server.setHandler(handlers);
        server.setHandler(context);//ТЕПЕРЬ мы в хендлер добавиали сервер т е соединили сервер с сервлетом

        server.start();
        System.out.println("Server started");
        server.join();



    }
}
