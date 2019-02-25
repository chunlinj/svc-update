package org.daac.sw.dao;

import org.daac.sw.pojo.HistoryVersions;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by  HASEE on 2019-01-23
 */
public interface HistoryVersionRepository extends MongoRepository<HistoryVersions, String> {
}
