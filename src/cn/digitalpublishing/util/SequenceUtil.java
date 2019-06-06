package cn.digitalpublishing.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SequenceUtil<T> {
	
	private static final String SORT_ASC = "asc";
	
	private static final String SORT_DESC = "desc";
	
	private static final String ITEM_KEY = "key";
	
	private static final String ITEM_VAL = "val";
	
	/**
	 * 对Map进行排序
	 * @param map
	 * @param sort
	 * @param item
	 * @return
	 */
	public LinkedHashMap<String,String> paramOrder(Map<T,T> map,final Sort sort,final Item item){
		List<Map.Entry<T, T>> params =new ArrayList<Map.Entry<T, T>>(map.entrySet());
		//排序 
		Collections.sort(params, new Comparator<Map.Entry<T, T>>() { 
			public int compare(Map.Entry<T, T> o1, Map.Entry<T, T> o2) {
				String s1 = "";
				String s2 = "";
				if(item.getName().equals(ITEM_VAL)){
					s1=String.valueOf((o1.getValue()));
					s2=String.valueOf(o2.getValue());
				}
				if(item.getName().equals(ITEM_KEY)){
					s1=String.valueOf(o1.getKey());
					s2=String.valueOf(o2.getKey());
				}
				if(SORT_ASC.equals(sort.getValue())){
					if(s1.compareTo(s2)>0)//asc
						return 1;
				    if(s1.compareTo(s2)==0)
				    	return 0;
				    else
				        return -1;
				}else if(SORT_DESC.equals(sort.getValue())){
					if(s1.compareTo(s2)<0)//desc
						return 1;
				    if(s1.compareTo(s2)==0)
				    	return 0;
				    else
				        return -1;
				}else{
					return 0;
				}
			}
		});            
		/*转换成新map输出*/        
		LinkedHashMap<String, String> newMap = new LinkedHashMap <String, String>();
        for(Map.Entry<T,T> entity : params){
        	 newMap.put(String.valueOf(entity.getKey()), String.valueOf(entity.getValue()));         
        }                   
        return newMap;     
    }
	
	/**
	 * 将list转换成map
	 * @param <T>
	 * @param list
	 * @param keyName
	 * @param valName
	 * @return
	 * @throws Exception
	 */
	public static <T> LinkedHashMap<Object,Object> getMap(List<T> list,String keyName,String valName)throws Exception{
		LinkedHashMap<Object,Object> map=new LinkedHashMap<Object,Object>();
		if(list!=null&&!list.isEmpty()){
			for(Object obj:list){
				try{
					Method keyMethod=obj.getClass().getDeclaredMethod("get"+keyName.substring(0,1).toUpperCase()+keyName.substring(1));
					Method valMethod=obj.getClass().getDeclaredMethod("get"+valName.substring(0,1).toUpperCase()+valName.substring(1));
					map.put(keyMethod.invoke(obj), valMethod.invoke(obj));
				}catch(Exception e){
					throw e;
				}
			}
		}
		return map;
	}
	
}
