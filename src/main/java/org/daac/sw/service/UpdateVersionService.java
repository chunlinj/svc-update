package org.daac.sw.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by  HASEE on 2019-01-24
 */
public interface UpdateVersionService {
    /**
     * 查询所有版本
     * @return
     * @throws Exception
     * @param request
     */
    List<String> findALLVersion(HttpServletRequest request) throws Exception;

    /**
     *  获取当前运行版本
     * @return
     * @throws Exception
     */
    Map<String,String> findCurrent() throws Exception;

    /**
     * 进行更新
     * old
     */
    Boolean updateVersion(String version,HttpServletRequest request) throws Exception;
    /**
     * 进行更新
     * new
     */
    Boolean updateVersionBat(String version,HttpServletRequest request) throws Exception;
}
