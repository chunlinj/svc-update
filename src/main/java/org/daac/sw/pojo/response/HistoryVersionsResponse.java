package org.daac.sw.pojo.response;

import com.zen.parking.bootstrap.pojo.MongoResponseEntity;
import lombok.Data;
import org.daac.sw.pojo.Versions;

import java.util.List;

/**
 * Created by  HASEE on 2019-01-24
 */
@Data
public class HistoryVersionsResponse extends MongoResponseEntity {
    private List<Versions> versions;
}
