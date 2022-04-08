package com.suhong.jdbcex.dao;

import com.suhong.jdbcex.domain.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public String getTime() {
        String now = null;

        try (
                Connection connection = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select now()");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.next();
            now = resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public void insert(TodoVO todo) throws Exception {
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todo.getDueDate()));
        preparedStatement.setBoolean(3, todo.isFinished());

        preparedStatement.executeUpdate();
    }

    public List<TodoVO> getAll() throws Exception {
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVO> result = new ArrayList<>();
        while (resultSet.next()) {
            TodoVO todo = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
//            TodoVO todo = new TodoVO(resultSet);
            result.add(todo);
        }
        return result;
    }

    public TodoVO getOne(Long tno) throws Exception {
        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, tno);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        TodoVO todo = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .finished(rs.getBoolean("finished"))
                .build();
//        TodoVO todo = new TodoVO(rs);

        return todo;
    }

    public void deleteOne(Long tno) throws Exception {
        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, tno);

        pstmt.executeUpdate();

    }

    public void updateOne(TodoVO todo) throws Exception {
        String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, todo.getTitle());
        pstmt.setDate(2, Date.valueOf(todo.getDueDate()));
        pstmt.setBoolean(3, todo.isFinished());
        pstmt.setLong(4, todo.getTno());

        pstmt.executeUpdate();
    }

}
