package com.suhong.jdbcex.controller;

import com.suhong.jdbcex.dto.TodoDTO;
import com.suhong.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long tno = Long.parseLong(request.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);

            request.setAttribute("dto", todoDTO);
            request.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
