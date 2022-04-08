package com.suhong.dao;

import com.suhong.jdbcex.dao.TodoDAO;
import com.suhong.jdbcex.domain.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {
    private TodoDAO todoDAO;

    @BeforeEach
    public void ready() {
        this.todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime());
    }

    @Test
    public void testInsert() throws Exception {
        TodoVO todo = TodoVO.builder()
                .title("study")
                .dueDate(LocalDate.of(2022, 4, 7))
                .build();
        todoDAO.insert(todo);
    }

    @Test
    public void testGetAll() throws Exception {
        List<TodoVO> todoList = todoDAO.getAll();
        todoList.forEach(todoVO -> System.out.println(todoVO));
    }

    @Test
    public void testGetOne() throws Exception {
        TodoVO todo = todoDAO.getOne(2L);
        System.out.println(todo);
    }

    @Test
    public void testDeleteOne() throws Exception {
        System.out.println("Before deletion");
        List<TodoVO> todoList = todoDAO.getAll();
        todoList.forEach(todoVO -> System.out.println(todoVO));
        System.out.println("==================");

        System.out.println("After deletion");
        todoDAO.deleteOne(4L);
        todoList = todoDAO.getAll();
        todoList.forEach(todoVO -> System.out.println(todoVO));

    }

    @Test
    public void testUpdateOne() throws Exception {
        System.out.println("Before update");
        List<TodoVO> todoList = todoDAO.getAll();
        todoList.forEach(todoVO -> System.out.println(todoVO));
        System.out.println("==================");

        System.out.println("After update");
        TodoVO todo = TodoVO.builder().tno(2L).title("new todo").dueDate(LocalDate.of(2030,3,20)).build();
        todoDAO.updateOne(todo);
        todoList = todoDAO.getAll();
        todoList.forEach(todoVO -> System.out.println(todoVO));
    }
}
