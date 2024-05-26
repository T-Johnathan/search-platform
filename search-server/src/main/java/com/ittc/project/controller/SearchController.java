package com.ittc.project.controller;

import com.ittc.project.common.BaseResponse;
import com.ittc.project.common.ResultUtils;
import com.ittc.project.manager.SearchFacade;
import com.ittc.project.model.dto.search.SearchRequest;
import com.ittc.project.model.vo.SearchVO;
import com.ittc.project.service.PictureService;
import com.ittc.project.service.PostService;
import com.ittc.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片接口
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }

}
