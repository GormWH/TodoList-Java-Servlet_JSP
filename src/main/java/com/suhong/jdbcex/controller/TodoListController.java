package com.suhong.jdbcex.controller;

import com.suhong.jdbcex.dto.TodoDTO;
import com.suhong.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("todo list........");

        try {
            List<TodoDTO> dtoList = todoService.listAll();
            request.setAttribute("dtoList", dtoList);
            request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("list error");
        }
    }

}
