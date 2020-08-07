package com.jkinvest.jkl.core.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

import com.jkinvest.jkl.core.file.entity.Attachment;

/**
 * 附件返回实体
 *
 * @author zuihou
 * @date 2018/12/12
 */
@Data
@ApiModel(value = "AttachmentResult", description = "附件列表")
public class AttachmentResultDTO {
    @ApiModelProperty(value = "业务id")
    private String bizId;
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    @ApiModelProperty(value = "附件列表")
    private List<Attachment> list;
}



