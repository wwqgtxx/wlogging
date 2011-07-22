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
 * ��ȡXML�ļ���ʵ�����
 * 
 */
public class ReadXml {

	/**
	 * ȡ��һ��XML�ļ���Document����
	 * 
	 * @param dir
	 *            XML�ļ���λ��
	 * @return ������Ե�Document
	 * @throws FileNotFoundException
	 *             û���ҵ��ļ�
	 * @throws Exception
	 *             ��������
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
	 * ȡ��һ��XML�ļ���Document����
	 * 
	 * @param dir
	 *            XML�ļ���λ��
	 * @param object
	 *            ������������
	 * @return ������Ե�Document
	 * @throws FileNotFoundException
	 *             û���ҵ��ļ�
	 * @throws Exception
	 *             ��������
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
	 * ȡ��һ��XML�ļ��� ��һ�����ֵ�������Ե�String
	 * 
	 * @param name
	 *            ��Ҫ���ҵ�����
	 * @param dir
	 *            XML�ļ���λ��
	 * @return ��һ�����ֵ�������Ե�String
	 * @throws FileNotFoundException
	 *             û���ҵ��ļ�
	 * @throws Exception
	 *             ��������
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
	 * ȡ��һ��XML�ļ��� ��һ�����ֵ�������Ե�String
	 * 
	 * @param name
	 *            ��Ҫ���ҵ�����
	 * @param dir
	 *            XML�ļ���λ��
	 * @param object
	 *            ������������
	 * @return ��һ�����ֵ�������Ե�String
	 * @throws FileNotFoundException
	 *             û���ҵ��ļ�
	 * @throws Exception
	 *             ��������
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
			// System.out.println("�ӽڵ���Ϊ:" + childNode.getNodeName()
			// + "���Ӧ��ֵΪ" + childNode.getFirstChild().getNodeValue());
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