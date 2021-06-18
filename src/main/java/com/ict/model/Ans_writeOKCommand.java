package com.ict.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Ans_writeOKCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = request.getServletContext().getRealPath("/upload");
			MultipartRequest mr = 
					new MultipartRequest(request, path, 100*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			String cPage = mr.getParameter("cPage"); // multipart는 hidden으로 보낸걸 mr로 저장해야 저장됨
			
			// 원글과 관련된 기본 정보 업데이트 (step, lev을 1씩 증가)
			// 16, 0, 0
			VO vo = (VO)request.getSession().getAttribute("vo");
			int groups = Integer.parseInt(vo.getGroups());
			int step = Integer.parseInt(vo.getStep());
			int lev = Integer.parseInt(vo.getLev());
			
			// step, lev을 1씩 증가
			// 16, 1, 1
			lev++;
			step++;
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			// 기존 댓글 lev update하는 곳 (첫 댓글은 의미가 없음)
			int result = DAO.getUp_lev(map); // 첫 댓글은 이걸로 가도 조건이 만족되지 않으므로 update 실행 x
											 // 그래서 첫 댓글은 업뎃이 실행되지 않는 것
			
			// 댓글 삽입
			VO ins_vo = new VO();
			ins_vo.setWriter(mr.getParameter("writer"));
			ins_vo.setTitle(mr.getParameter("title"));
			ins_vo.setContent(mr.getParameter("content"));
			ins_vo.setPwd(mr.getParameter("pwd"));
				// 이 아래의 3개는 현재 이 페이지에서 1증가시킨 lev와 step이 들어가게된다. 
				// 그래서 댓글은 16, 1, 1 이됨 (현재 원글은 16, 0, 0)
			ins_vo.setGroups(String.valueOf(groups));
			ins_vo.setStep(String.valueOf(step));
			ins_vo.setLev(String.valueOf(lev));
			
			if(mr.getFile("file_name") != null) {
				ins_vo.setFile_name(mr.getFilesystemName("file_name"));
			} else {
				ins_vo.setFile_name("");
			}
			
			result = DAO.getAnsInsert(ins_vo);
			
			// 댓글 원리
			/*
			 	16, 0, 0 의 댓글 => 16, 1, 1 / 16, 0, 0 의 댓글2  => 16, 1, 1 (전의 것이 16, 1, 2)처럼 오래된 댓글의 lev이 증가하는 방식
			 	16, 1, 1 의 댓글 => 16, 2, 2 / 16, 2, 2 의 댓글 => 16, 3, 3
			 	
			 	즉, step이 댓글의 댓글인지 구별하는 것, lev는 댓글의 수와 댓글 생성순서를 알 수 있게함 (lev가 높을수록 최근)
			 	
			 	주의 : 이러한 방식은 삭제할 때 댓글을 삭제하면 댓글의 댓글은 남아있음. 그래서 삭제하면 그 밑은 다 삭제하던가 하는 처리를 해줘야함
			 */
			
			
			return "MyController?cmd=list&cPage=" + cPage;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
