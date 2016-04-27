package com.gaos.tree_view;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.gaos.bean.FileBean;
/**
 * pull解析xml文件
 * @author gaos
 *
 */
public class PullParser {

	public static List<FileBean> parseXML(InputStream is) {
		XmlPullParser parser = Xml.newPullParser();
		List<FileBean> mDatas = new ArrayList<FileBean>();
		try {
			parser.setInput(is, "utf-8");// 设置数据源编码
			int eventCode = parser.getEventType();// 获取事件类型
			FileBean bean = null;
			while (eventCode != XmlPullParser.END_DOCUMENT) {
				String tagName = "";
				switch (eventCode) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					break;
				case XmlPullParser.START_TAG:// 开始读取某个标签
					tagName = parser.getName();
					// 实例化集合类
					if (tagName.equalsIgnoreCase("org")) {
						// 组织
						bean = new FileBean(Integer.parseInt(parser.getAttributeValue(0)), 0,parser.getAttributeValue(1));
						mDatas.add(bean);
					} else if (tagName.equalsIgnoreCase("d")) {
						// 部门
						bean = new FileBean(Integer.parseInt(parser.getAttributeValue(0)), Integer.parseInt(parser.getAttributeValue(3)),parser.getAttributeValue(1));
						mDatas.add(bean);
					} else if (tagName.equalsIgnoreCase("u")) {
						// 用户
						bean = new FileBean(Integer.parseInt(parser.getAttributeValue(0)), Integer.parseInt(parser.getAttributeValue(6)),parser.getAttributeValue(1));
						mDatas.add(bean);
					}
					break;
				case XmlPullParser.END_TAG:// 读完一个Person，可以将其添加到集合类中
//					tagName = parser.getName();
					// if (tagName.equalsIgnoreCase("u")) {
					// userArray.put(user.getUser_i(), user);
					// } else if (tagName.equalsIgnoreCase("d")) {
					// deptArray.put(dept.getDept_id(), dept);
					// } else if (tagName.equalsIgnoreCase("org")) {
					// }
					break;
				}
				eventCode = parser.next();
			}
			is.close();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mDatas;
	}
}
