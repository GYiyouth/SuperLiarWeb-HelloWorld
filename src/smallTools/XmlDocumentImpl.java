package smallTools;
import java.io.*;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmlHandleBean.ActionBean;
import xmlHandleBean.ControllerBean;

/**
 * Created by geyao on 2016/12/2.
 */
public class XmlDocumentImpl implements XmlDocument {


	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	public Document parse(String filePath) {
		Document document = null;
		try {
			//DOM parser instance
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			//parse an XML file into a DOM tree
			document = builder.parse(new File(filePath));
			Element actionController = document.getDocumentElement();
			NodeList actions = actionController.getChildNodes();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return document;
	}

	public static void main(String[] args) {
//		new XmlDocumentImpl().parserXml("/Users/geyao/IdeaProjects/Web/HelloWorld/src/controller.xml");
//		new this.parserXml("/Users/geyao/IdeaProjects/Web/HelloWorld/src/controller.xml");

//		XmlDocumentImpl parser = new XmlDocumentImpl();
//		Document document = parser.parse("/Users/geyao/IdeaProjects/Web/HelloWorld/src/controller.xml");
//		//get root element
//		Element rootElement = document.getDocumentElement();
//
//		//traverse child elements
//		NodeList nodes = rootElement.getChildNodes();
//		for (int i=0; i < nodes.getLength(); i++)
//		{
//			Node action = nodes.item(i);
//			if (action.getNodeType() == Node.ELEMENT_NODE) {
//				Element actionInfo = (Element) action;
//				System.out.println(actionInfo.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
//				Element classInfo = (Element) actionInfo.getElementsByTagName("class").item(0);
//				System.out.println(classInfo.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
//				System.out.println(classInfo.getElementsByTagName("method").item(0).getFirstChild().getNodeValue());
//
//				NodeList results = actionInfo.getElementsByTagName("result");
//				for (int j = 0; j < results.getLength(); j ++){
//					Node result = results.item(j);
//					if (result.getNodeType() == Node.ELEMENT_NODE){
//						Element resultInfo = (Element) result;
//						System.out.println(resultInfo.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
//						System.out.println(resultInfo.getElementsByTagName("type").item(0).getFirstChild().getNodeValue());
//						System.out.println(resultInfo.getElementsByTagName("value").item(0).getFirstChild().getNodeValue());
//					}
//				}
//			}
//		}
//		System.out.println(1);
//		NodeList nodeList = rootElement.getElementsByTagName("book");
//		if(nodeList != null)
//		{
//			for (int i = 0 ; i < nodeList.getLength(); i++)
//			{
//				Element element = (Element)nodeList.item(i);
//				String id = element.getAttribute("id");
//				System.out.println(id);
//			}
//		}
		XmlDocumentImpl a = new XmlDocumentImpl();
		System.out.println(a.getControllerBean());


	}
	public ControllerBean getControllerBean(){

		ControllerBean controllerBean = new ControllerBean();

		XmlDocumentImpl parser = new XmlDocumentImpl();
		Document document = parser.parse("/Users/geyao/IdeaProjects/Web/HelloWorld/src/controller.xml");
		//get root element
		Element rootElement = document.getDocumentElement();

		//traverse child elements
		NodeList nodes = rootElement.getChildNodes();
		for (int i=0; i < nodes.getLength(); i++)
		{
			Node action = nodes.item(i);
			if (action.getNodeType() == Node.ELEMENT_NODE) {
				Element actionInfo = (Element) action;
				String actionName = actionInfo.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				System.out.println(actionName);

				ActionBean actionBean = new ActionBean();
				actionBean.setName(actionName);
				/**
				 * 这里先做好ActionBean，再添加进ControllerBean
				 * actionBean需要3个HashMap，第一个放置classInfo，第二个放置results的具体信息，第三个放置results
				 */
				Element classInfo = (Element) actionInfo.getElementsByTagName("class").item(0);
				String class_name = classInfo.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				String class_method = classInfo.getElementsByTagName("method").item(0).getFirstChild().getNodeValue();
				System.out.println(class_name);
				System.out.println(class_method);
				/**
				 * 做classInfo的哈希表
				 */
				HashMap<String, String> classHash = new HashMap<>();
				classHash.put("name", class_name);
				classHash.put("method", class_method);
				actionBean.setClassInfo(classHash);

				HashMap<String, HashMap<String, String>> resultsHash = new HashMap<>();


				NodeList results = actionInfo.getElementsByTagName("result");
				for (int j = 0; j < results.getLength(); j ++){
					Node result = results.item(j);
					if (result.getNodeType() == Node.ELEMENT_NODE){
						Element resultInfo = (Element) result;
						String result_name = resultInfo.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
						String result_type = resultInfo.getElementsByTagName("type").item(0).getFirstChild().getNodeValue();
						String result_value = resultInfo.getElementsByTagName("value").item(0).getFirstChild().getNodeValue();
						System.out.println();
						System.out.println();
						System.out.println();
						/**
						 * 做一个result的具体信息
						 */
						HashMap<String, String>resultInfoHash = new HashMap<>();
						resultInfoHash.put("name", result_name);
						resultInfoHash.put("type", result_type);
						resultInfoHash.put("value", result_value);

						/**
						 * 做results的哈希表
						 */
						resultsHash.put(result_name, resultInfoHash);
						actionBean.setResults(resultsHash);
					}
				}
				controllerBean.getActions().put(actionName, actionBean);
			}

		}


		return controllerBean;
	}
}
