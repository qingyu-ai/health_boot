package com.qy.service;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qy
 * @since 2022-01-13
 */
public interface IReportService {

    // 运营数据统计
    Map<String,Object> getBusinessReportData() throws Exception;
}
