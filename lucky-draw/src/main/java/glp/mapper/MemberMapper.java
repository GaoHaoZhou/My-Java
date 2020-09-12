package glp.mapper;

import glp.base.BaseMapper;
import glp.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}