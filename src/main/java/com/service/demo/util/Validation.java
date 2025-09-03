package com.service.demo.util;


import java.util.LinkedHashMap;
import java.util.Map;


import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.service.demo.dto.CategoryDto;
import com.service.demo.exception.ValidationException;


@Component
public class Validation {
	
	public void categoryValidation(CategoryDto categoryDto) 
	{
		
		Map<String, Object> map=new LinkedHashMap<>();
		
		if(ObjectUtils.isEmpty(categoryDto))
		{
			throw new IllegalArgumentException("category object shuold 't be null or Empty");
		}
		else
		{
 			//validation name field
			if(ObjectUtils.isEmpty(categoryDto.getName()))
			{
				//throw new IllegalArgumentException("name field is null or Empty");
				map.put("name :","name field shuold 't be null or Empty");
			}
			else
			{
				if(categoryDto.getName().length()<10)
				{
					map.put("name :","name length min 10");
				}
				if(categoryDto.getName().length()>100)
				{
					map.put("name :","name length max 100");
				}
			}
		}
		//validation of Description()
		if(ObjectUtils.isEmpty(categoryDto.getDescription()))
		{
			//throw new IllegalArgumentException("name field is null or Empty");
			map.put("Description :","Description field is null or Empty");
		}
		else
		{
			if(categoryDto.getDescription().length()<10)
			{
				map.put("Description :","Description length min 10");
			}
			if(categoryDto.getDescription().length()>100)
			{
				map.put("Description :","Description length max 100");
			}
		}
		if(ObjectUtils.isEmpty(categoryDto.getIsActive()))
		{
			//throw new IllegalArgumentException("name field is null or Empty");
			map.put("isActive :","isActive field is null or Empty");
		}
		else
		{
			if(categoryDto.getIsActive()!=Boolean.TRUE && categoryDto.getIsActive()!=Boolean.FALSE)
			{
				map.put("isActive :","invalid value isActive field ");
			}
		}
		if(!map.isEmpty())
		{
			throw new ValidationException(map);
		}
	}

}
