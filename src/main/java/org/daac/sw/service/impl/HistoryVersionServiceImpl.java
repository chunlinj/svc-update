package org.daac.sw.service.impl;

import com.zen.parking.bootstrap.service.MongoSimpleService;
import org.daac.sw.dao.HistoryVersionRepository;
import org.daac.sw.pojo.HistoryVersions;
import org.daac.sw.pojo.request.HistoryVersionsRequest;
import org.daac.sw.pojo.response.HistoryVersionsResponse;
import org.daac.sw.service.HistoryVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by  HASEE on 2019-01-23
 */
@Service
public class HistoryVersionServiceImpl extends MongoSimpleService<HistoryVersionsRequest, HistoryVersionsResponse, HistoryVersions> implements HistoryVersionService {
    @Autowired
    private HistoryVersionRepository historyVersionRepository;



    @Override
    protected MongoRepository<HistoryVersions, String> getRepository() {
        return historyVersionRepository;
    }

    @Override
    public List<HistoryVersions> findAllHistory() {
        return historyVersionRepository.findAll();
    }
}
