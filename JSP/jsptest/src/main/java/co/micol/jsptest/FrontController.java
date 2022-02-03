package co.micol.jsptest;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.jsptest.command.ACommand;
import co.micol.jsptest.command.BCommand;
import co.micol.jsptest.command.MemberJoin;
import co.micol.jsptest.command.MemberList;
import co.micol.jsptest.command.MemberLogin;
import co.micol.jsptest.command.MemberSearch;
import co.micol.jsptest.command.memberLoginForm;
import co.micol.jsptest.common.Command;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<String, Command>(); 
    
  
    public FrontController() {
        super();
        
    }
    public void init(ServletConfig config) throws ServletException{
    	// 초기값 등록
    	
    	map.put("/a.do", new ACommand()); // a.do로 요청 HashMap<key, value>방식
    	map.put("/b.do", new BCommand()); // b.do로 요청
    	map.put("/memberList.do", new MemberList()); // 멤버목록
    	map.put("/memberSearch.do", new MemberSearch()); // 멤버조회
    	map.put("/memberLoginForm.do", new memberLoginForm()); // 로그인 폼 호출
    	map.put("/memberLogin.do", new MemberLogin()); // 로그인
    	map.put("/memberJoin.do", new MemberJoin()); // 멤버 회원가입
    	
    
    }

    protected void service(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	//실제 요청을 처리하는 곳
    	request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지를 위해
    	String uri = request.getRequestURI(); // URI 값을 구함.
    	String contextPath = request.getContextPath(); // 루트를 구함(contextPath)
    	String page = uri.substring(contextPath.length()); // 실제 요청한 페이지를 구함
    	
    	Command command = map.get(page); // 요청을 map에서 찾아온다.
    	String viewPage = command.run(request, response); // 요청을 처리하는 command 호출
    	
    	if(viewPage != null && !viewPage.endsWith(".do")) { // 서버로 접근할수 있게 만드는 간단한 viewResolve // endsWith(마지막 문자열)이 .do로 끝나지 않으면
    		viewPage = "WEB-INF/views/" + viewPage + ".jsp";
    				
    	}
    	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //서버에서 접근하여 브라우저로 결과 돌림
    	dispatcher.forward(request, response);  // RequestDispatcher, forward :  권한 위임(페이지를 돌려버린다.) A가 B에게 일을 시켰는데 B가 C에게 일을다시 넘겨서 C가 A에게 반환
    	
    }
}

