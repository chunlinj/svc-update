package org.daac.sw.pojo;

import com.zen.parking.bootstrap.domain.MongoItem;
import lombok.Data;

/**
 * Created by  HASEE on 2019-01-23
 */
@Data
public class File extends MongoItem {
    private String url;
    private String version;
}
