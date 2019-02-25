package org.daac.sw.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.daac.sw.entity.Result;
import org.daac.sw.entity.ResultCode;
import org.daac.sw.entity.StatusCode;
import org.daac.sw.service.UpdateVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by  HASEE on 2019-01-24
 */
@RestController
@Slf4j
@RequestMapping("/service")
public class UpdateVersionController {
    @Autowired
    private UpdateVersionService updateVersionService;

    @GetMapping("/list")
    @ApiOperation(value="获取cloud所有分支版本号",response=Result.class)
    public Result findAllVersion(HttpServletRequest request) throws Exception {
        return new Result(true,StatusCode.OK, ResultCode.SUCCESS.getMsg(),updateVersionService.findALLVersion(request));
    }

    @GetMapping("/version")
    @ApiOperation(value="获取当前运行版本",response=Result.class)
    public Result findCurrent() throws Exception {
        return new Result(true,StatusCode.OK,ResultCode.SUCCESS.getMsg(),updateVersionService.findCurrent());
    }

    @GetMapping("/update/{version}")
    @ApiImplicitParam(value="版本号",name="version")
    @ApiOperation(value="跟新当前版本",response=Result.class)
    public Result updateVersion(@PathVariable("version") String version, HttpServletRequest request) throws Exception {
        return new Result(true,StatusCode.OK,ResultCode.SUCCESS.getMsg(),updateVersionService.updateVersionBat(version,request));
    }


}
