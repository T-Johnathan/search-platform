package com.ittc.project.datasource;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ittc.project.model.vo.VideoVO;
import org.springframework.stereotype.Component;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VideoDataSource implements DataSource<VideoVO> {

    @Override
    public Page<VideoVO> doSearch(String searchText, long pageNum, long pageSize) {
        String url1 = "https://www.bilibili.com/";
        String url2 = String.format("https://api.bilibili.com/x/web-interface/search/type?search_type=video&keyword=%s",searchText);
        HttpCookie cookie = HttpRequest.get(url1).execute().getCookie("buvid3");
        String body = HttpRequest.get(url2)
                .cookie(cookie)
                .execute().body();
        Map map = JSONUtil.toBean(body, Map.class);
        Map data = (Map)map.get("data");
        JSONArray videoList = (JSONArray) data.get("result");
        Page<VideoVO> page = new Page<>(pageNum,pageSize);
        List<VideoVO> videoVoList = new ArrayList<>();
        for(Object video:videoList){
            JSONObject tempVideo = (JSONObject)video;
            VideoVO videoVo = new VideoVO();
            videoVo.setUpic(tempVideo.getStr("upic"));
            videoVo.setAuthor(tempVideo.getStr("author"));
            videoVo.setPubdate(tempVideo.getInt("pubdate"));
            videoVo.setArcurl(tempVideo.getStr("arcurl"));
            videoVo.setPic("http:"+tempVideo.getStr("pic"));
            videoVo.setTitle(tempVideo.getStr("title"));
            videoVo.setDescription(tempVideo.getStr("description"));
            videoVoList.add(videoVo);
            if(videoVoList.size()>=pageSize){
                break;
            }
        }
        page.setRecords(videoVoList);
        return page;
    }
}
