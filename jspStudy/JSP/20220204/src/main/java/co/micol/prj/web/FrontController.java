package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.command.Loginform;
import co.micol.prj.command.MainCommand;
import co.micol.prj.command.RegisterForm;
import co.micol.prj.common.Command;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map;
  
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		map = new HashMap<String, Command>(); // 변수를 사용하기위해 map을 초기화(메모리에 map을 올린다는 의미)한다.
		map.put("/main.do", new MainCommand()); // 메인 페이지
		map.put("/loginform.do", new Loginform()); // 로그인 폼 호출
		map.put("/registerForm.do", new RegisterForm()); // 회원가입 폼 호출

	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글깨짐 방지
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length()); 
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do")) {
			viewPage = viewPage + ".tiles";
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
