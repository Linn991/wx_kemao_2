package org.fkjava.weixin.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.fkjava.weixin.domain.InMessage;
import org.fkjava.weixin.domain.event.EventInMessage;
import org.fkjava.weixin.domain.image.ImageInMessage;
import org.fkjava.weixin.domain.link.LinkInMessage;
import org.fkjava.weixin.domain.location.LocationInMessage;
import org.fkjava.weixin.domain.shortvideo.ShortvideoInMessage;
import org.fkjava.weixin.domain.text.TextInMessage;
import org.fkjava.weixin.domain.video.VideoInMessage;
import org.fkjava.weixin.domain.voice.VoiceInMessage;

public class MessageConvertHelper {
	//1.使用一个Map来记录消息类型和Java类型的关系
	private static final Map<String, Class<? extends InMessage>> typeMap = new ConcurrentHashMap();
	static {
		typeMap.put("text",TextInMessage.class);
		typeMap.put("image",ImageInMessage.class);
		typeMap.put("voice",VoiceInMessage.class);
		typeMap.put("video",VideoInMessage.class);
		typeMap.put("shortvideo",ShortvideoInMessage.class);
		typeMap.put("location",LocationInMessage.class);
		typeMap.put("link",LinkInMessage.class);
		typeMap.put("event",EventInMessage.class);
	}
	
	public static Class<? extends InMessage> getClass(String xml) {
		//获取类型
		String type=xml.substring(xml.indexOf("<MsgType><![CDATA[")+18);
		type=type.substring(0,type.indexOf("]"));
				
		//获取Java类
		Class<? extends InMessage> c= typeMap.get(type);
		return c;
	}
//	//2.提供一个静态的方法，可以传入XML，把XML转换为Java对象
//	public <T extends InMessage> T convert(String xml) {
//		//获取Java类
//		Class<? extends InMessage> c= getClass(xml);
//	if(c==null) {
//		return null;
//	}
//		return null ;
	}

