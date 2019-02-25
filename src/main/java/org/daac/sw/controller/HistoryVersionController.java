package org.daac.sw.controller;

import com.zen.parking.bootstrap.controller.WebBaseController;
import com.zen.parking.bootstrap.service.MongoIService;
import org.daac.sw.dao.HistoryVersionRepository;
import org.daac.sw.entity.Result;
import org.daac.sw.entity.StatusCode;
import org.daac.sw.pojo.request.HistoryVersionsRequest;
import org.daac.sw.pojo.response.HistoryVersionsResponse;
import org.daac.sw.service.HistoryVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hasee
 * @date 2018-11-06 16:16
 */
@RestController
@RequestMapping("repo")
public class HistoryVersionController extends WebBaseController<HistoryVersionsRequest,HistoryVersionsResponse> {
    @Autowired
    private HistoryVersionRepository historyVersionMapper;
    @Autowired
    private HistoryVersionService historyVersionService;

    /**
     * 查询全部版本
     * @return
     */
    @RequestMapping(value = "/versions",method = RequestMethod.GET)
    public Object findAllVersion() {
        return new Result(true,StatusCode.OK,"查询成功",historyVersionService.findAllHistory());
    }


    @Override
    public MongoIService<HistoryVersionsRequest, HistoryVersionsResponse> getService() {
        return historyVersionService;
    }
}
