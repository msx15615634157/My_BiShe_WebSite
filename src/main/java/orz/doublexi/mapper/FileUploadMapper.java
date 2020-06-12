package orz.doublexi.mapper;

import org.apache.ibatis.annotations.Param;
import orz.doublexi.pojo.UploadInfoBean;

public interface FileUploadMapper {
      Integer insertUploadInfoBean(UploadInfoBean bean);
      Integer updateAccontImgByAcccountid(@Param("accountid") Integer accountid, @Param("imgname")String imgname);
}
