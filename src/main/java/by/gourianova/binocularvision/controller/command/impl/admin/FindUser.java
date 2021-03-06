package by.gourianova.binocularvision.controller.command.impl.admin;

import by.gourianova.binocularvision.bean.User;
import by.gourianova.binocularvision.controller.command.Command;
import by.gourianova.binocularvision.service.ServiceException;
import by.gourianova.binocularvision.service.UserService;
import by.gourianova.binocularvision.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.gourianova.binocularvision.util.PageOfConstants.*;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class FindUser implements Command {
    private final static String USER_ID = "userId";
    private final static String USER = "userOne";
    private final static String EMPTY_USER = "emptyUser";
    private final static String MESSAGE = "message";
    private UserService userService = new UserServiceImpl();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int userId = Integer.parseInt(request.getParameter(USER_ID));
            User user = userService.findUserById(userId);

            if (user != null){
                request.setAttribute(USER, user);
              //TODO:fix to get id (separate bean?)
              // request.getSession().setAttribute(USER_ID, user.getId());

                request.setAttribute(USER_ID, user.getId());
                request.setAttribute(MESSAGE,"Shown one user");
                log.println(request.getAttribute(USER_ID) + " id");
            } else {
                request.setAttribute(EMPTY_USER, Boolean.TRUE);
                request.setAttribute(MESSAGE,"Couldn't find user");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ONE_USER);
            requestDispatcher.forward(request, response);
            log.println(" FindUser ok");

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
