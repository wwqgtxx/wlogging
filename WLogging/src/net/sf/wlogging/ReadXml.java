package net.sf.wlogging;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * 读取XML文件的实用类库
 * 
 */
public class ReadXml {

	/**
	 * 取得一个XML文件的Document对象
	 * 
	 * @param dir
	 *            XML文件的位置
	 * @return 这个属性的Document
	 * @throws FileNotFoundException
	 *             没有找到文件
	 * @throws Exception
	 *             其他错误
	 */
	public static Document getXmlDocument(String dir)
			throws FileNotFoundException, Exception {

		Document doc = null;

		File f = new File(dir);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(f);

		return doc;
	}

	/**
	 * 取得一个XML文件的Document对象
	 * 
	 * @param dir
	 *            XML文件的位置
	 * @param object
	 *            调用这个类的类
	 * @return 这个属性的Document
	 * @throws FileNotFoundException
	 *             没有找到文件
	 * @throws Exception
	 *             其他错误
	 */
	public static Document getXmlDocumentWithClass(String dir, Object object)
			throws FileNotFoundException, Exception {
		try {
			return getXmlDocument(object.getClass().getClassLoader()
					.getResource(dir).getPath());
		} catch (NullPointerException e) {
			throw new FileNotFoundException();
		}
	}

	/**
	 * 取得一个XML文件的 第一个出现的这个属性的String
	 * 
	 * @param name
	 *            你要查找的属性
	 * @param dir
	 *            XML文件的位置
	 * @return 第一个出现的这个属性的String
	 * @throws FileNotFoundException
	 *             没有找到文件
	 * @throws Exception
	 *             其他错误
	 */
	public static String getXmlString(String name, String dir)
			throws FileNotFoundException, Exception {

		String s = null;

		File f = new File(dir);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		builder = factory.newDocumentBuilder();

		Document doc = null;

		doc = builder.parse(f);

		s = doc.getElementsByTagName(name).item(0).getFirstChild()
				.getNodeValue();

		return s;
	}

	/**
	 * 取得一个XML文件的 第一个出现的这个属性的String
	 * 
	 * @param name
	 *            你要查找的属性
	 * @param dir
	 *            XML文件的位置
	 * @param object
	 *            调用这个类的类
	 * @return 第一个出现的这个属性的String
	 * @throws FileNotFoundException
	 *             没有找到文件
	 * @throws Exception
	 *             其他错误
	 */
	public static String getXmlStringWithClass(String name, String dir,
			Object object) throws FileNotFoundException, Exception {
		try {
			return getXmlString("XML", object.getClass().getClassLoader()
					.getResource(dir).getPath());
		} catch (NullPointerException e) {
			throw new FileNotFoundException();
		}
	}

	public static Node[] getXmlNodeWithclass(String xmlFile, Object object)
			throws Exception {
		return getXmlNode(object.getClass().getResource(xmlFile).getPath());
	}

	public static Node[] getXmlNode(String xmlFile) throws Exception {
		Document doc = getXmlDocument(xmlFile);
		Element element = doc.getDocumentElement();
		NodeList nodeList = doc.getElementsByTagName(element.getTagName());
		Node fatherNode = nodeList.item(0);

		NodeList childNodes = fatherNode.getChildNodes();

		Node[] nl = new Node[childNodes.getLength()];

		for (int j = 0; j < childNodes.getLength(); j++) {
			Node childNode = childNodes.item(j);
			// if (childNode instanceof Element) {
			// System.out.println("子节点名为:" + childNode.getNodeName()
			// + "相对应的值为" + childNode.getFirstChild().getNodeValue());
			// }
			nl[j] = childNode;
		}

		return nl;

	}

	public static String getString(String needString, Node[] node) {
		try {
			String class_name = null;

			for (int i = 0; i < node.length; i++) {
				if (node[i].getNodeName().equalsIgnoreCase(needString)) {
					NodeList nl = node[i].getChildNodes();
					class_name = nl.item(0).getNodeValue();

				}
			}
			return class_name;

		} catch (Exception e) {
			return null;
		}
	}
}