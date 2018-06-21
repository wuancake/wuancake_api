package com.wuan.weekly.mapper;

import com.wuan.weekly.entity.Version;
import org.apache.ibatis.annotations.Select;

public interface VersionMapper {

    /**
     * 查找最新的app版本和下载路径
     *
     * @return
     */
    @Select("select v,url " +
            "from version " +
            "order by v desc " +
            "limit 0,1")
    public Version getLateVersion();
}
