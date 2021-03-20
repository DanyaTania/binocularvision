package by.gourianova.binocularvision.controller.command.impl;

//import by.gourianova.binocularvision.bean.News;

import by.gourianova.binocularvision.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gourianova.binocularvision.util.PageOfConstants.INDEX_PAGE;

public class GoToIndexPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(INDEX_PAGE);
			requestDispatcher.forward(request, response);
			
		} catch (//Service
				Exception e) {
			// TODO перейти на глобальную страницу ошибок
			e.printStackTrace();
		}	
	

	}

}
