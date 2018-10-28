/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import com.sun.xml.internal.fastinfoset.stax.events.EndElementEvent;
import com.sun.xml.internal.stream.events.XMLEventAllocatorImpl;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author adler
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            cleanXML(getDataFromWeb("https://fptshop.com.vn/may-tinh-xach-tay/tu-25-30-trieu"), "div", "class", "mf-plti");
        } catch (NullPointerException e) {
            System.out.println("end");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static StreamSource getDataFromWeb(String url) throws IOException {
        StringBuffer sb = new StringBuffer();
        URL oracle = new URL(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            inputLine = inputLine
                    .replace("&", "&#$@;")
                    .replace("&#$@;npjs", "")
                    .replace("\t", "")
                    .replace("<br>", "")
                    .replace("</br>", "")
                    .replaceAll("\\w+=\\w+", "");
            sb.append(inputLine.trim() + "\n");
        }
        br.close();
        InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        return new StreamSource(is);
    }

    private static void cleanXML(StreamSource source, String desElement, String desAttr, String desAttrValue)
            throws XMLStreamException {
        XMLInputFactory xif = XMLInputFactory.newInstance();
        xif.setEventAllocator(new XMLEventAllocatorImpl());
        XMLEventReader reader = null;
        reader = xif.createXMLEventReader(source.getInputStream());
        if (reader != null) {
            while (reader.hasNext()) {
                try {
                    XMLEvent event = null;
                    event = reader.nextEvent();
                    if (event != null) {
                        if (event.isStartElement()) {
                            StartElement element = (StartElement) event;
                            if (element.getName().toString().equals(desElement)) {
                                Attribute a = element.getAttributeByName(new QName(desAttr));
                                if (a != null) {
                                    String value = a.getValue();
                                    if (value.equals(desAttrValue)) {
                                        printAllMatchData(reader);
                                    }
                                }
                            }
                        }
                    }
                } catch (XMLStreamException e) {

                }
            }
        }
    }

    private static Iterator<XMLEvent> autoAddMissingEndTag(XMLEventReader reader) {
        ArrayList<XMLEvent> lEvents = new ArrayList<>();
        int endTagMarker = 0;
        while (endTagMarker >= 0) {
            XMLEvent event = null;
            try {
                event = reader.nextEvent();
            } catch (XMLStreamException e) {
                String msg = e.getMessage();
                String msgErrString = "The element type \"";
                if (msg.contains(msgErrString)) {
                    String missingTagName
                            = msg.substring(msg.indexOf(msgErrString) + msgErrString.length(), msg.indexOf("\" must be terminated"));
                    EndElement missingTag = new EndElementEvent(new QName(missingTagName));
                    event = missingTag;
                }
            } catch (NullPointerException e) {
                break;
            }
            if (event != null) {
                if (event.isStartElement()) {
                    endTagMarker++;
                } else if (event.isEndElement()) {
                    endTagMarker--;
                }
                if (endTagMarker >= 0) {
                    lEvents.add(event);
                }
            }
        }
        return lEvents.iterator();
    }

    private static void printAllMatchData(Iterator<XMLEvent> iterator) {
        String result = "";
        while (iterator.hasNext()) {
            XMLEvent event = iterator.next();
            if (event.isStartElement()) {
                StartElement se = (StartElement) event;
                result += "<" + se.getName().toString();
                Iterator childIter = se.getAttributes();
                while (childIter.hasNext()) {
                    Attribute attr = (Attribute) childIter.next();
                    String value = attr.getValue().replace("&", "&#$@");
                    result += " " + attr.getName().toString() + "\"" + value + "\"";
                }
                result += ">";
            }
            if (event.isCharacters()) {
                Characters chars = (Characters) event;
                if (!chars.isWhiteSpace()) {
                    result += chars.getData().replace("&", "&#$@").trim();
                }
            }
            if (event.isEndElement()) {
                EndElement ee = (EndElement) event;
                result += ee.toString();
            }
        }
        System.out.println(result);
    }
}
