package org.daac.sw.service;

import com.zen.parking.bootstrap.service.MongoIService;
import org.daac.sw.pojo.HistoryVersions;
import org.daac.sw.pojo.request.HistoryVersionsRequest;
import org.daac.sw.pojo.response.HistoryVersionsResponse;

import java.util.List;

/**
 * Created by  HASEE on 2019-01-23
 */
public interface HistoryVersionService extends MongoIService<HistoryVersionsRequest, HistoryVersionsResponse> {
    List<HistoryVersions> findAllHistory();
}
