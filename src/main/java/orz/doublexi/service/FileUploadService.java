package orz.doublexi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.doublexi.mapper.FileUploadMapper;
import orz.doublexi.pojo.UploadInfoBean;
@Service
public class FileUploadService {
    @Autowired
    FileUploadMapper fileUploadMapper;
public boolean upload(UploadInfoBean bean){
    return fileUploadMapper.insertUploadInfoBean(bean)>0;
}
public boolean updatetouxiaog(Integer accountid,String imagename) {
    return fileUploadMapper.updateAccontImgByAcccountid( accountid, imagename)>0;
}
}
