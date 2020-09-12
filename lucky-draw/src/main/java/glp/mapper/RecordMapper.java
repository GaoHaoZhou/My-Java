package glp.mapper;

import glp.base.BaseMapper;
import glp.model.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    int batchAdd(@Param("awardId") Integer awardId, @Param("memberIds") List<Integer> memberIds);

    void deleteByCondition(Record r);

    int deleteBySettingId(Integer id);
}