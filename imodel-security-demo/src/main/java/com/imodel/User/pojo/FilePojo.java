package com.imodel.User.pojo;

import io.swagger.annotations.ApiModelProperty;

public class FilePojo {
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
