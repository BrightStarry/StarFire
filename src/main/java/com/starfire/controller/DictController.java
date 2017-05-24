package com.starfire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starfire.domain.TDict;
import com.starfire.service.TDictService;

/**
 * 字典 控制器
 */
@Controller
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private TDictService tDictService;
	
	/**
	 * 获取指定类型的所有字典
	 */
	@PostMapping("/queryByType")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public String[] queryByType(String type){
		List lists = tDictService.queryAllByType(type);
		String[] strings = null;
		if(lists != null && lists.size() != 0){
			int size = lists.size();
			strings = new String[size];
			for(int i = 0;i < size;i++){
				strings[i] = ((TDict)lists.get(i)).getDictValue();
			}
		}
		System.out.println(strings);
		return  strings;
	}
	
	/**
	 * 指定类型和key，获取value
	 */
	@PostMapping("/queryByTypeAndKey")
	@ResponseBody
	public String queryByTypeAndKey(@RequestParam("type")String type,@RequestParam("key")String key){
		return tDictService.queryByTypeAndKey(type, key);
	}

}
