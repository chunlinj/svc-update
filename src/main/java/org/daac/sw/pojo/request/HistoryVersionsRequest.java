package org.daac.sw.pojo.request;

import com.zen.parking.bootstrap.pojo.MongoRequestEntity;
import lombok.Data;
import org.daac.sw.pojo.Versions;

import java.util.List;

/**
 * Created by  HASEE on 2019-01-24
 */
@Data
public class HistoryVersionsRequest extends MongoRequestEntity {
    private List<Versions> versions;
}
