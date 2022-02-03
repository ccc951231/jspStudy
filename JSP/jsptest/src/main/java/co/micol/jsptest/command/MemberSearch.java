package co.micol.jsptest.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.jsptest.common.Command;
import co.micol.jsptest.member.service.MemberService;
import co.micol.jsptest.member.service.MemberVO;
import co.micol.jsptest.member.serviceImpl.MemberServiceImpl;

public class MemberSearch implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 한명 데이터 조회
		MemberService memberDao = new MemberServiceImpl(); // MemberSerivceImpl : 구현체를 통해 초기화
		MemberVO vo = new MemberVO();
		
		vo.setId("hong");
		request.setAttribute("member", memberDao.memberSelect(vo));  // memberDao에 있는 memberSelect(vo) 한명데이터 조회를 호출해온다.
		return null;
	}

}
