package com.ict.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.model.Ans_writeCommand;
import com.ict.model.Ans_writeOKCommand;
import com.ict.model.Command;
import com.ict.model.DeleteCommand;
import com.ict.model.DeleteOKCommand;
import com.ict.model.ListCommand;
import com.ict.model.OneListCommand;
import com.ict.model.UpdateCommand;
import com.ict.model.UpdateOKCommand;
import com.ict.model.WriteCommand;
import com.ict.model.WriteOKCommand;

@WebServlet("/MyController")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String cmd = request.getParameter("cmd");
		Command comm = null;
		if(cmd.equalsIgnoreCase("list")) {
			comm = new ListCommand();
		} else if(cmd.contentEquals("write")) {
			comm = new WriteCommand();
		} else if(cmd.contentEquals("write_ok")) {
			comm = new WriteOKCommand();
		} else if(cmd.contentEquals("onelist")) {
			comm = new OneListCommand();
		} else if(cmd.contentEquals("ans_write")) {
			comm = new Ans_writeCommand();
		} else if(cmd.contentEquals("ans_write_ok")) {
			comm = new Ans_writeOKCommand();
		} else if(cmd.contentEquals("update")) {
			comm = new UpdateCommand();
		} else if(cmd.contentEquals("update_ok")) {
			comm = new UpdateOKCommand();
		}else if(cmd.contentEquals("delete")) {
			comm = new DeleteCommand();
		} else if(cmd.contentEquals("delete_ok")) {
			comm = new DeleteOKCommand();
		}
		
		String path = comm.exec(request, response);
		request.getRequestDispatcher(path).forward(request, response);
	}

}
