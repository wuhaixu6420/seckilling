package com.zq.fin.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zq.fin.seckill.service.stock.GlscService;

/**
 * 作为定时器，主要定时任务作用
 * @author wuhaixu
 * 暂时不开启定时器任务
 *
 */
@Service
@Component
public class TimerService {
	
	@Autowired
	private GlscService glscService;
	
//	@Scheduled(cron = "0 0/1 9,10,11,13,14,15 * * MON-FRI")
	public void Timing_Glsc_Stock(){
		glscService.automaticDocumentary();
	}

}
