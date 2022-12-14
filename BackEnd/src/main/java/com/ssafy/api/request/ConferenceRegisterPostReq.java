package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("ConferenceRegisterPostReq")
public class ConferenceRegisterPostReq {

    @ApiModelProperty(name = "반 식별자", example = "1")
    Long classId;

    @ApiModelProperty(name = "방장 아이디", example = "ssafy")
    String ownerId;

    @ApiModelProperty(name = "썸네일 경로", example = "/path/..")
    String thumbnailPath;

    @ApiModelProperty(name = "방제", example = "1주차 알고리즘 수업")
    String conferenceName;

}
