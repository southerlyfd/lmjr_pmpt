package com.pmpt;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Scheduler
 * @Description: 定时器DEMO
 * @Author: 汪洋
 * @Date: 2017年9月6日 上午10:55:58
 */
@Component
public class Scheduler {
//	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
	@Scheduled(cron = "0 0 12 * * ?") // 每天中午12点执行一次
	public void statusCheck() {
		System.out.println("每天中午12点执行一次。开始……");
		// statusTask.healthCheck();
		System.out.println("每天中午12点执行一次。结束。");
	}

//	@Scheduled(fixedRate = 20000)
//	public void testTasks() {
//		System.out.println("每20秒执行一次。开始……");
//		// statusTask.healthCheck();
//		System.out.println("每20秒执行一次。结束。");
//	}
}
