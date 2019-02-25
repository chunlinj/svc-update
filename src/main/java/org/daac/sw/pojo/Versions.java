package org.daac.sw.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by  HASEE on 2019-01-23
 */
@Data
public class Versions implements Serializable {
    private String version;
    private Map<String,String> fileUrl;
}
