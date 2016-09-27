package com.zq.fin.seckill.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.fin.seckill.dto.Exposer;
import com.zq.fin.seckill.dto.SeckillExecution;
import com.zq.fin.seckill.dto.SeckillRseult;
import com.zq.fin.seckill.entity.Seckill;
import com.zq.fin.seckill.enums.SeckillStatenum;
import com.zq.fin.seckill.exception.RepeatKillException;
import com.zq.fin.seckill.exception.SeckillCloseException;
import com.zq.fin.seckill.service.SeckillService;
import com.zq.fin.seckill.util.ObjectUtil;

/**
 * 
 * @author admin create Y-Y 2016年8月19日16:44:39
 */
@Controller
// @Service @Component 放入spring容器中
@RequestMapping("/seckill")
// url:/模块/资源/{id}/细分/seckill/list
public class SeckillController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		// 获取列表页
		List<Seckill> seckills = seckillService.getSeckillList();

		model.addAttribute("list", seckills);
		// list.jsp + model = ModelAndView
		return "seckill/list";// /WEB-INF/jsp/"list".jsp
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {

		if (ObjectUtil.isEmpty(seckillId)) {
			return "redirect:/seckill/list";
		}

		Seckill seckill = seckillService.getById(seckillId);

		if (ObjectUtil.isEmpty(seckill)) {
			return "forward:/seckill/list";
		}

		model.addAttribute("seckill", seckill);

		return "seckill/detail";
	}

	// ajax json
	@RequestMapping(value = "/{seckillId}/exposer", 
			method = RequestMethod.POST, 
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillRseult<Exposer> exposer(
			@PathVariable("seckillId") Long seckillId) {

		SeckillRseult<Exposer> result;

		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillRseult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new SeckillRseult<Exposer>(false, e.getMessage());
		}

		return result;
	}
	
	@RequestMapping(value = "/{seckillId}/{md5}/execution", 
			method = RequestMethod.POST, 
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillRseult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId, 
													@PathVariable("md5") String md5, 
													@CookieValue(value = "killPhone", required = false) Long phone){
		//springmvc valid
		if(ObjectUtil.isEmpty(phone)){
			return new SeckillRseult<SeckillExecution>(false, "未注册");
		}
		
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
			return new SeckillRseult<SeckillExecution>(true, seckillExecution);
		} catch (RepeatKillException e){
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatenum.REPEAT_KILL);
			return new SeckillRseult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillCloseException e){
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatenum.END);
			return new SeckillRseult<SeckillExecution>(true, seckillExecution);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatenum.INNER_ERROR);
			return new SeckillRseult<SeckillExecution>(true, seckillExecution);
		}
	}
	
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillRseult<Long> time(){
		Date now = new Date();
		return new SeckillRseult<Long>(true, now.getTime());
	}
	
}
