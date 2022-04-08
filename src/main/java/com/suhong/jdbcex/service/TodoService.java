package com.suhong.jdbcex.service;

import com.suhong.jdbcex.dao.TodoDAO;
import com.suhong.jdbcex.domain.TodoVO;
import com.suhong.jdbcex.dto.TodoDTO;
import com.suhong.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        this.dao = new TodoDAO();
        this.modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        dao.insert(todoVO);
    }

    public List<TodoDTO> listAll() throws Exception {
        List<TodoVO> voList = dao.getAll();

        log.info("voList......");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    public TodoDTO get(Long tno) throws Exception {
        log.info("tno: " + tno);
        TodoVO todoVO = dao.getOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    public void remove(Long tno) throws Exception {
        log.info("tno: " + tno);
        dao.deleteOne((tno));
    }

    public void modify(TodoDTO todoDTO) throws Exception {
        log.info("todoDTO: " + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }
}
