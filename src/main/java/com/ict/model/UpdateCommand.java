package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCommand implements Command{
@Override
public String exec(HttpServletRequest request, HttpServletResponse response) {
	request.setAttribute("cPage", request.getParameter("cPage"));
	return "view/update.jsp";
	}
}
