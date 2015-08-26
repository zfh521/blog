package org.zhuc.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhuc.mybatis.domain2.Flow;
import org.zhuc.mybatis.mapper2.FlowMapper;

/**
 * @author zhuc
 * @create 2013-3-11 下午1:19:03
 */
@Service("flowService")
@Transactional(value = "insurance", rollbackFor = Exception.class)
public class FlowService {

	@Autowired
	private FlowMapper flowMapper;

	/**
	 * @param id
	 * @return
	 */
	public Flow get(String id) {
		return flowMapper.get(id);
	}

}
