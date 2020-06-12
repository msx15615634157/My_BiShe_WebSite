package orz.doublexi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.doublexi.service.VideoService;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    VideoService indexService;

    @RequestMapping("queryvideo.do")
    @ResponseBody
    public List queryVideo(@RequestParam(value = "index") int index) {
       return indexService.queryVideo(index, 20);

    }
    @RequestMapping("queryvideobykey.do")
    @ResponseBody
    public List queryVideoByKey(@RequestParam(value = "index") int index,@RequestParam(value = "key") String key) {
       return indexService.queryVideoByKey(index, 20,key);

    }
}
