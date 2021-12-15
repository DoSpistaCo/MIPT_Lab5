package com.example.madt1116;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.util.ArrayList;

public class XmlParser {
    public static String getRateFromECB() throws IOException {
        //ArrayList<String> result = new ArrayList<>();
        String results = "";
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(DataManager.downloadUrl(Constants.ECB_URL));

            NodeList rateNodes = doc.getElementsByTagName(Constants.CUBE_NODE);
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Element cube = (Element) rateNodes.item(i);
                if(cube.hasAttribute("currency")){
                    //result.add(cube.getAttribute("rate"));
                    //result.add(cube.getAttribute("currency"));
                    results = results+cube.getAttribute("rate")+" "+cube.getAttribute("currency")+" ";
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        //String[] resultsArray = new String[result.size()];
        //result.toArray(resultsArray);
        return results;
    }
}
