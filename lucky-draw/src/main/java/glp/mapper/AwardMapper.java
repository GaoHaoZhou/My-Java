package glp.mapper;

import glp.base.BaseMapper;
import glp.model.Award;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AwardMapper extends BaseMapper<Award> {

    List<Award> query(Award award);
}