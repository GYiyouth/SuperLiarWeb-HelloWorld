package smallTools;

import java.io.*;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import xmlHandleBean.InterceptorBean;

/**
 * Created by geyao on 2016/12/10.
 */
public class XmlWriter {

	public void Writelog(HashMap<String, String> log){

		File file = new File("/Users/geyao/IdeaProjects/Web/HelloWorld/src/log.xml");

		if(file.exists()) {

			SAXReader reader = new SAXReader();
			try {

				org.dom4j.Document document = reader.read(new File("/Users/geyao/IdeaProjects/Web/HelloWorld/src/log.xml"));
				Element root = document.getRootElement();
				Element action = root.addElement("action");
				Element actionName = action.addElement("name");
				actionName.setText(log.get("actionName"));
				Element sTime = action.addElement("s-time");
				sTime.setText(log.get("startTime"));
				Element eTime = action.addElement("e-time");
				eTime.setText(log.get("endTime"));
				Element result = action.addElement("result");
				result.setText(log.get("result"));
				writer(document);
			}catch(Exception e){
				e.printStackTrace();
			}

		}else {
			Document document = DocumentHelper.createDocument();
			Element logE = document.addElement("log");
			Element action = logE.addElement("action");
			Element actionName = action.addElement("name");
			actionName.setText(log.get("actionName"));
			Element sTime = action.addElement("s-time");
			sTime.setText(log.get("startTime"));
			Element eTime = action.addElement("e-time");
			eTime.setText(log.get("endTime"));
			Element result = action.addElement("result");
			result.setText(log.get("result"));
			try {
				writer(document);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


		public void writer(Document document) throws Exception {


			OutputFormat format = OutputFormat.createPrettyPrint();

			format.setEncoding("UTF-8");

			XMLWriter writer = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream("/Users/geyao/IdeaProjects/Web/HelloWorld/src/log.xml"), "UTF-8"), format);

			writer.write(document);

			writer.flush();

			writer.close();
		}


	}
