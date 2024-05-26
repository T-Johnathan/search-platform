package com.ittc.project.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tong
 * @version 1.0
 * @date 2023/5/22 14:07
 */
@Data
public class VideoVO implements Serializable {

    private String arcurl;

    private String pic;

    private String title;

    private String description;

    private String author;

    private Integer pubdate;

    private String upic;

    private static final long serialVersionUID = 7037843325406822290L;

}
