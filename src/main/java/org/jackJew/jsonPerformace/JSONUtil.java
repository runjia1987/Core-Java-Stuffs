package org.jackJew.jsonPerformace;

import java.security.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.serializer.JSONSerializerMap;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * 
 * Description: Generate fastJSON output compatible with JSON-Lib
 * @author zhurunjia
 *
 */
@SuppressWarnings("deprecation")
public class JSONUtil {

	private static final JSONSerializerMap mapping;
	
	static {
		mapping = new JSONSerializerMap();
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));  //定制日期格式化输出
		mapping.put(Timestamp.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
	}

	private static final SerializerFeature[] features = {
			SerializerFeature.WriteMapNullValue, 		// 输出空置字段
			SerializerFeature.WriteNullListAsEmpty, 	// list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, 	// Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty 	// 字符类型字段如果为null，输出为""，而不是null
	};

	/**
	 * compatible with JSON-Lib
	 */
	public static String toCompatibleJSONString(Object object) {
		return com.alibaba.fastjson.JSON.toJSONString(object, mapping, features);
	}

}