package glp.mapper;

import glp.base.BaseMapper;
import glp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}