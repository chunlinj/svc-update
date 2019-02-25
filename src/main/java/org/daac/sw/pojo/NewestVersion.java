package org.daac.sw.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by  HASEE on 2019-01-23
 */
@Data
@ToString
@Document(collection = "newest_version")
public class NewestVersion implements Serializable {
    private String update;
    private File file;
    private String type;
}
