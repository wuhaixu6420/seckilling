package com.zq.fin.seckill.web.xw;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zq.fin.seckill.dto.DataResult;
import com.zq.fin.seckill.entity.xw.Journalism;
import com.zq.fin.seckill.service.xw.JournalismService;
import com.zq.fin.seckill.web.BaseController;


/**
 * 
 * 描述：新闻
 * @author wuhaixu
 * @created 2017年3月27日 下午5:02:52
 * @since
 */
@Controller
@RequestMapping(value = "/news")
public class JournalismController extends BaseController {
	
	/**
	 * 用于业务逻辑操作
	 */
	@Resource
	private JournalismService journalismService;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String showJournalismListHtml(){
		return "xw/list";
	}
	
	/**
	 * 
	 * 描述：展示静态页面信息(单独页面)
	 * @author wuhaixu
	 * @created 2017年3月27日 下午5:09:02
	 * @since 
	 * @return
	 */
	@RequestMapping(value = "/{journalismId}/show", method=RequestMethod.GET)
	public String showJournalismOneHtml(Model model, @PathVariable("journalismId") String journalismId){
		model.addAttribute("journalismId", journalismId);
		return "xw/index";
	}
	
	/**
	 * 
	 * 描述：根据页面传递的id，查询对应的新闻
	 * 没有id的时候，查询时间最近的新闻
	 * @author wuhaixu
	 * @created 2017年4月7日 下午3:35:50
	 * @since 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/nowOne", produces="text/html;charset=UTF-8", method=RequestMethod.GET)
	@ResponseBody
	public String showDbJournalismOneForJson(String id){
		DataResult<?> dataResult = journalismService.queryJournalismOne(id);
		String result = JSON.toJSONString(dataResult);
		return result;
	}
	
	@RequestMapping(value = "/editOne", produces="text/html;charset=UTF-8", method=RequestMethod.POST)
	public String editJournalismOneForJson(String froala){
		
		return null;
	}
	
	/**
	 * 
	 * 描述：静态页面ajax获取新闻列表
	 * @author wuhaixu
	 * @created 2017年3月27日 下午5:12:31
	 * @since 
	 * @return
	 */
	@RequestMapping(value = "/nowList", produces="text/html;charset=UTF-8", method=RequestMethod.GET)
	@ResponseBody
	public String getJournalismForJson(String pageNo){
		//查询新闻信息
		DataResult<List<Journalism>> dataResult = journalismService.queryJournalismPageList(pageNo, null);
		//转化json
		String jsonToString = JSON.toJSONString(dataResult);
		return jsonToString;
	}
	
	
	
	@RequestMapping(value = "/test", produces="text/html;charset=UTF-8", method=RequestMethod.GET)
	@ResponseBody
	public String getJournalismForJson_test(String pageNo){
		//查询新闻信息
		List<Journalism> journalisms = journalismService.queryJournalismList();
		//转化json
		String jsonToString = JSON.toJSONString(journalisms);
		return jsonToString;
	}
	
	
}
