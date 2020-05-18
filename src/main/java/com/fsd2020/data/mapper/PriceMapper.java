package com.fsd2020.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fsd2020.data.entity.PriceInfoEntity;

@Mapper
@Repository
public interface PriceMapper {

	int insertPrice(@Param("prices") List<PriceInfoEntity> prices);
}
