package com.youran.generate.pojo.mapper;

import com.youran.generate.pojo.dto.MetaConstAddDTO;
import com.youran.generate.pojo.dto.MetaConstUpdateDTO;
import com.youran.generate.pojo.po.MetaConstPO;
import com.youran.generate.pojo.vo.MetaConstShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 常量映射
 * <p>基于mapstruct来实现，编译器自动生成实现类
 *
 * @author: cbb
 * @date: 2017/5/12
 */
@Mapper
public interface MetaConstMapper {

    MetaConstMapper INSTANCE = Mappers.getMapper(MetaConstMapper.class);

    /**
     * addDTO映射po
     *
     * @param addDTO
     * @return
     */
    MetaConstPO fromAddDTO(MetaConstAddDTO addDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param metaConstPO
     * @param metaConstUpdateDTO
     */
    void setPO(@MappingTarget MetaConstPO metaConstPO, MetaConstUpdateDTO metaConstUpdateDTO);

    /**
     * po映射showVO
     *
     * @param metaConstPO
     * @return
     */
    MetaConstShowVO toShowVO(MetaConstPO metaConstPO);

    @Mappings({@Mapping(target = "constId", ignore = true), @Mapping(target = "detailList", ignore = true)})
    MetaConstPO copy(MetaConstPO constPO);
}
