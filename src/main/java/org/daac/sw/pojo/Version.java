package org.daac.sw.pojo;

import lombok.Data;

import java.util.Map;

/**
 * Created by  HASEE on 2019-01-25
 */
@Data
public class Version {
    private String version;
    private Map<String,String> fileUrl;
}
