package com.glp.service;

import com.glp.dao.UserMapper;
import com.glp.pojo.PageBean;
import com.glp.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;



@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;


    @Override
    public int register(User registerUser) {
        return userMapper.register(registerUser);
    }

    @Override
    public User login(User loginUser) {
        return userMapper.login(loginUser);
    }

    @Override
    public int add(User addUser) {
        return userMapper.add(addUser);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public User find(int id) {
        return userMapper.find(id);
    }

    @Override
    public int update(User updateUser) {
        return userMapper.update(updateUser);
    }


    @Override
    public int findAllRecord(HashMap<String,Object> map) {
        return userMapper.findAllRecord(map);
    }

    //在service层做逻辑整合
    @Override
    public PageBean<User> findAllByPage(HashMap<String,Object> map) {
        PageBean<User> pageBean=new PageBean<>();

        int rows = (int)map.get("rows");
        int currentPage = (int)map.get("currentPage");

        //查询当前搜索中共有多少条记录
        int allRecord = findAllRecord(map);

        //计算总共的页数
        int totalPage;
        if(allRecord%rows==0){
            totalPage=allRecord/rows;
        }else{
            totalPage=allRecord/rows+1;
        }

        //分页查询:返回用户数据，及总页数，当前页数，一页的行数。
        int start=(currentPage-1)*rows;
        map.put("startIndex",start);
        List<User> users = userMapper.findByPage(map);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(users);
        pageBean.setRows(rows);
        pageBean.setTotalCount(allRecord);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }


}
