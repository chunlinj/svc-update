package org.daac.sw.pojo;

import com.zen.parking.bootstrap.domain.MongoItem;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by  HASEE on 2019-01-23
 */
@Data
@ToString
@Document(collection = "history_versions")
public class HistoryVersions extends MongoItem {
    private List<Versions> versions;
}
