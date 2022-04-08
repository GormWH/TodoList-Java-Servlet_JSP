package com.suhong.jdbcex.controller;

import com.suhong.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "todoRemoveController", value = "/todo/remove")
@Log4j2
public class TodoRemoveController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long tno = Long.parseLong(request.getParameter("tno"));
        log.info("tno: " + tno);

        try {
            todoService.remove(tno);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("remove error");
        }
        response.sendRedirect("/todo/list");
    }
}
