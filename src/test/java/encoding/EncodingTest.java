package encoding;

import com.google.common.base.Charsets;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.hash.Hashing;
import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import static javax.xml.XMLConstants.*;

public class EncodingTest {

    @Test
    public void test() {
        String stringToConvertToSHexRepresentation = "<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"pfx746ff909-4823-a484-2d75-99dc096a7825\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>";
        String result = DigestUtils.sha1Hex(stringToConvertToSHexRepresentation);
        System.out.println("sha1 hash: " + result);
        byte[] encodedBytes = Base64.encodeBase64(result.getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        //Assert.assertEquals("vrCNIaIqJd1Ts4dpkIeni20CSjI=", result);
    }

    @Test
    public void test2() {
        String stringToConvertToSHexRepresentation = "<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-91f9d878-9ecd-45dd-8e8d-160f22f24699\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>";
        String result = DigestUtils.sha1Hex(stringToConvertToSHexRepresentation);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:LU0ptAAL+tTYB0Bef6ndE9M9yEk=");
        byte[] encodedBytes = Base64.encodeBase64(result.getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    @Test
    public void test2Old() {
        String stringToConvertToSHexRepresentation = "<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-91f9d878-9ecd-45dd-8e8d-160f22f24699\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>";
        String result = byteArrayToHexString(stringToConvertToSHexRepresentation.getBytes());
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:LU0ptAAL+tTYB0Bef6ndE9M9yEk=");
        byte[] encodedBytes = Base64.encodeBase64(result.getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    //
    @Test
    public void test2Guava() {
        String stringToConvertToSHexRepresentation = "<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-91f9d878-9ecd-45dd-8e8d-160f22f24699\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>";
        String result = Hashing.sha1().hashString( stringToConvertToSHexRepresentation, Charsets.UTF_8 ).toString();
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:LU0ptAAL+tTYB0Bef6ndE9M9yEk=");
        byte[] encodedBytes = Base64.encodeBase64(result.getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }


    @Test
    public void test3() {
        String stringToConvertToSHexRepresentation = "http://webbroker.cetelem.ru/LoanService/createRequest";
        String result = DigestUtils.sha1Hex(stringToConvertToSHexRepresentation);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:LU0ptAAL+tTYB0Bef6ndE9M9yEk=");
        byte[] encodedBytes = Base64.encodeBase64(result.getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    //
    @Test
    public void test4() {
        String stringToConvertToSHexRepresentation = "<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-91f9d878-9ecd-45dd-8e8d-160f22f24699\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>";
        String result = DigestUtils.sha1Hex(stringToConvertToSHexRepresentation);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:LU0ptAAL+tTYB0Bef6ndE9M9yEk=");
        byte[] encodedBytes = Base64.encodeBase64(result.getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    @Test
    public void test5WithApacheCanonicalization() throws InvalidCanonicalizerException, IOException, SAXException, ParserConfigurationException, CanonicalizationException {
        String stringToConvertToSHexRepresentation = "<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"pfx746ff909-4823-a484-2d75-99dc096a7825\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>";
        org.apache.xml.security.Init.init();
        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/2001/10/xml-exc-c14n#");
        byte canonXmlBytes[] = canon.canonicalizeSubtree(stringToDom(stringToConvertToSHexRepresentation), "soap");
        String canonXmlString = new String(canonXmlBytes);
        System.out.println("canon Xml String: " + canonXmlString);
        String result = DigestUtils.sha1Hex(canonXmlString);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:vrCNIaIqJd1Ts4dpkIeni20CSjI=");
        String encodedBytes = new String(Base64.encodeBase64(result.getBytes()));
        System.out.println("encodedBytes " + encodedBytes);
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    @Test
    public void testHashOfFullXmlWithApacheCanonicalization() throws InvalidCanonicalizerException, IOException, SAXException, ParserConfigurationException, CanonicalizationException, XPathExpressionException {
        File xml = Paths.get("C:\\Workfolder\\project\\Java8TP\\src\\test\\resources\\example.xml").toFile();
        Assert.assertTrue(xml.exists());
        org.apache.xml.security.Init.init();
        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/2001/10/xml-exc-c14n#");
        Document document = xmlFileToDom(xml);
        final XPath xPath = XPathFactory.newInstance().newXPath();

        Map<String, String> mappings = new HashMap<>();
        mappings.put("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");

        SimpleNamespaceContext context = new SimpleNamespaceContext(mappings);

        xPath.setNamespaceContext(context);
        final Node node = document.getElementsByTagName("Action").item(0);
        byte canonXmlBytes[] = canon.canonicalizeSubtree(node, "soap");
        //byte canonXmlBytes[] = canon.canonicalizeSubtree(document, "soap");
        String canonXmlString = new String(canonXmlBytes);
        System.out.println("canon Xml String: " + canonXmlString);
        String result = DigestUtils.sha1Hex(canonXmlString);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:vrCNIaIqJd1Ts4dpkIeni20CSjI=");
        String encodedBytes = new String(Base64.encodeBase64(result.getBytes()));
        System.out.println("encodedBytes " + encodedBytes);
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    @Test
    public void testFavoritHashOfFullXmlWithApacheCanonicalization() throws InvalidCanonicalizerException, IOException, SAXException, ParserConfigurationException, CanonicalizationException, XPathExpressionException {
        File xml = Paths.get("C:\\Workfolder\\project\\Java8TP\\src\\test\\resources\\favorit_example.xml").toFile();
        Assert.assertTrue(xml.exists());
        org.apache.xml.security.Init.init();
        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/2001/10/xml-exc-c14n#");
        Document document = xmlFileToDom(xml);
        final Node node = document.getElementsByTagName("Action").item(0);
        byte canonXmlBytes[] = canon.canonicalizeSubtree(node, "soap");
        //byte canonXmlBytes[] = canon.canonicalizeSubtree(document, "soap");
        String canonXmlString = new String(canonXmlBytes);
        System.out.println("canon Xml String: " + canonXmlString);
        String result = DigestUtils.sha1Hex(canonXmlString);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:vrCNIaIqJd1Ts4dpkIeni20CSjI=");
        String encodedBytes = new String(Base64.encodeBase64(result.getBytes()));
        System.out.println("encodedBytes " + encodedBytes);
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    @Test
    public void testFaultHashOfFullXmlWithApacheCanonicalization() throws InvalidCanonicalizerException, IOException, SAXException, ParserConfigurationException, CanonicalizationException, XPathExpressionException {
        File xml = Paths.get("C:\\Workfolder\\project\\Java8TP\\src\\test\\resources\\resp-08.12.16_fault.xml").toFile();
        Assert.assertTrue(xml.exists());
        org.apache.xml.security.Init.init();
        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/2001/10/xml-exc-c14n#");
        Document document = xmlFileToDom(xml);
        final Node node = document.getElementsByTagName("Action").item(0);
        byte canonXmlBytes[] = canon.canonicalizeSubtree(node, "soap");
        //byte canonXmlBytes[] = canon.canonicalizeSubtree(document, "soap");
        String canonXmlString = new String(canonXmlBytes);
        System.out.println("canon Xml String: " + canonXmlString);
        String result = DigestUtils.sha1Hex(canonXmlString);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:2WNkPZGfLSM1TAh0fz8E45vnbEE=");
        String encodedBytes = new String(Base64.encodeBase64(result.getBytes()));
        System.out.println("encodedBytes " + encodedBytes);
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    @Test
    public void testDate() throws InvalidCanonicalizerException, IOException, SAXException, ParserConfigurationException, CanonicalizationException, XPathExpressionException {
        //System.out.println("TimeZone.getDefault()=" + TimeZone.getDefault().ge);
        System.out.println("time=" + new Date());
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.getProperty("user.timezone");

    }

    private Document buildExampleDocumentWithNamespaces(Document document) {
        Element element = document.createElementNS("http://another.namespace",
                "element");
        document.getDocumentElement().appendChild(element);
        return document;
    }

    @Test
    public void test5WithJava8Canonicalization() throws InvalidCanonicalizerException, IOException, SAXException, ParserConfigurationException, CanonicalizationException {
        String stringToConvertToSHexRepresentation = "<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"pfx746ff909-4823-a484-2d75-99dc096a7825\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>";
        Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
        byte canonXmlBytes[] = canon.canonicalizeSubtree(stringToDom(stringToConvertToSHexRepresentation));
        String canonXmlString = new String(canonXmlBytes);
        System.out.println("canon Xml String: " + canonXmlString);
        String result = DigestUtils.sha1Hex(canonXmlString);
        System.out.println("sha1 hash Action result: " + result);
        System.out.println("expected:vrCNIaIqJd1Ts4dpkIeni20CSjI=");
        byte[] encodedBytes = Base64.encodeBase64(result.getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        //Assert.assertEquals("LU0ptAAL+tTYB0Bef6ndE9M9yEk=", result);
    }

    @Test
    public void testValidCanon() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, ParserConfigurationException, SAXException, IOException {
        Key key = new Key() {
            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }
        };
        DOMSignContext dsc = new DOMSignContext(key, getXmlDocExample().getDocumentElement());

        XMLSignatureFactory fac =
                XMLSignatureFactory.getInstance("DOM");

        DigestMethod digestMethod =
                fac.newDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1", null);
        C14NMethodParameterSpec spec = null;
        CanonicalizationMethod cm = fac.newCanonicalizationMethod(
                "http://www.w3.org/2001/10/xml-exc-c14n#",spec);
        SignatureMethod sm = fac.newSignatureMethod(
                "http://www.w3.org/2000/09/xmldsig#rsa-sha1",null);
        ArrayList transformList = new ArrayList();
        TransformParameterSpec transformSpec = null;
        Transform envTransform =   fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature",transformSpec);
        Transform exc14nTransform = fac.newTransform(
                "http://www.w3.org/2001/10/xml-exc-c14n#",transformSpec);

        transformList.add(envTransform);
        transformList.add(exc14nTransform);

        Reference ref = fac.newReference("",digestMethod,transformList,null,null);
        ArrayList refList = new ArrayList();
        refList.add(ref);
        SignedInfo si =fac.newSignedInfo(cm,sm,refList);
    }

    public static Document stringToDom(String xmlSource)
            throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlSource)));
    }

    public static Document xmlFileToDom(File xmlSource)
            throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        factory.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());
        return builder.parse(xmlSource);
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                    Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }

    private Document getXmlDocExample() throws IOException, SAXException, ParserConfigurationException {
        String xml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "\t<soap:Header>\n" +
                "\t\t<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" soap:mustUnderstand=\"1\">\n" +
                "\t\t\t<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"SIG-85262473-3940-410c-8f22-0137efd13add\">\n" +
                "\t\t\t\t<ds:SignedInfo>\n" +
                "\t\t\t\t\t<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\">\n" +
                "\t\t\t\t\t\t<ec:InclusiveNamespaces xmlns:ec=\"http://www.w3.org/2001/10/xml-exc-c14n#\" PrefixList=\"soap\"/>\n" +
                "\t\t\t\t\t</ds:CanonicalizationMethod>\n" +
                "\t\t\t\t\t<ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>\n" +
                "\t\t\t\t\t<ds:Reference URI=\"#id-17620b59-1aa5-47e5-8e88-c7b1c97fcb12\">\n" +
                "\t\t\t\t\t\t<ds:Transforms>\n" +
                "\t\t\t\t\t\t\t<ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n" +
                "\t\t\t\t\t\t</ds:Transforms>\n" +
                "\t\t\t\t\t\t<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n" +
                "\t\t\t\t\t\t<ds:DigestValue>HnKqiRAehvRuiMDdjv5sYhtVm98=</ds:DigestValue>\n" +
                "\t\t\t\t\t</ds:Reference>\n" +
                "\t\t\t\t\t<ds:Reference URI=\"#id-91f9d878-9ecd-45dd-8e8d-160f22f24699\">\n" +
                "\t\t\t\t\t\t<ds:Transforms>\n" +
                "\t\t\t\t\t\t\t<ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\">\n" +
                "\t\t\t\t\t\t\t\t<ec:InclusiveNamespaces xmlns:ec=\"http://www.w3.org/2001/10/xml-exc-c14n#\" PrefixList=\"soap\"/>\n" +
                "\t\t\t\t\t\t\t</ds:Transform>\n" +
                "\t\t\t\t\t\t</ds:Transforms>\n" +
                "\t\t\t\t\t\t<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n" +
                "\t\t\t\t\t\t<ds:DigestValue>LU0ptAAL+tTYB0Bef6ndE9M9yEk=</ds:DigestValue>\n" +
                "\t\t\t\t\t</ds:Reference>\n" +
                "\t\t\t\t\t<ds:Reference URI=\"#id-32d88f3c-1ee9-4bc0-a246-f9437c61ef98\">\n" +
                "\t\t\t\t\t\t<ds:Transforms>\n" +
                "\t\t\t\t\t\t\t<ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\">\n" +
                "\t\t\t\t\t\t\t\t<ec:InclusiveNamespaces xmlns:ec=\"http://www.w3.org/2001/10/xml-exc-c14n#\" PrefixList=\"soap\"/>\n" +
                "\t\t\t\t\t\t\t</ds:Transform>\n" +
                "\t\t\t\t\t\t</ds:Transforms>\n" +
                "\t\t\t\t\t\t<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n" +
                "\t\t\t\t\t\t<ds:DigestValue>0u5pYN78zO+ZBBhQ+ayKAtJHFXU=</ds:DigestValue>\n" +
                "\t\t\t\t\t</ds:Reference>\n" +
                "\t\t\t\t\t<ds:Reference URI=\"#id-fa5f490c-8896-4c15-8595-a7fef558569d\">\n" +
                "\t\t\t\t\t\t<ds:Transforms>\n" +
                "\t\t\t\t\t\t\t<ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\">\n" +
                "\t\t\t\t\t\t\t\t<ec:InclusiveNamespaces xmlns:ec=\"http://www.w3.org/2001/10/xml-exc-c14n#\" PrefixList=\"soap\"/>\n" +
                "\t\t\t\t\t\t\t</ds:Transform>\n" +
                "\t\t\t\t\t\t</ds:Transforms>\n" +
                "\t\t\t\t\t\t<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n" +
                "\t\t\t\t\t\t<ds:DigestValue>w938PwJ0e9q7ahGsDP2nCnHQM5k=</ds:DigestValue>\n" +
                "\t\t\t\t\t</ds:Reference>\n" +
                "\t\t\t\t\t<ds:Reference URI=\"#id-b79faf8e-f806-43a3-a92a-845583dc4a4c\">\n" +
                "\t\t\t\t\t\t<ds:Transforms>\n" +
                "\t\t\t\t\t\t\t<ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\">\n" +
                "\t\t\t\t\t\t\t\t<ec:InclusiveNamespaces xmlns:ec=\"http://www.w3.org/2001/10/xml-exc-c14n#\" PrefixList=\"soap\"/>\n" +
                "\t\t\t\t\t\t\t</ds:Transform>\n" +
                "\t\t\t\t\t\t</ds:Transforms>\n" +
                "\t\t\t\t\t\t<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n" +
                "\t\t\t\t\t\t<ds:DigestValue>zrMG+AvJBrKkLg9uBc1IUQsNi84=</ds:DigestValue>\n" +
                "\t\t\t\t\t</ds:Reference>\n" +
                "\t\t\t\t</ds:SignedInfo>\n" +
                "\t\t\t\t<ds:SignatureValue>PANjuNtEbMrtcJ0o1fYHY0sdOE2xyEgj/Vyk7qqxDve3NQdOJBuotaxc/a4VQy/JH0dknO5dUYHIHFJHeaffF54e8WkpTOhtWf/bcdI9A38JNzntF6DjVo8chYO/7E+eIcF6AE5sfUG5CfqOhJzb1WRAa5iFTOI7ZHFmvKPqHhg=</ds:SignatureValue>\n" +
                "\t\t\t\t<ds:KeyInfo Id=\"KI-11fac47b-774b-4f05-ad52-6c78c2f2b47c\">\n" +
                "\t\t\t\t\t<wsse:SecurityTokenReference xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"STR-563aac98-8070-4dee-80ac-65b7fe16ecd3\">\n" +
                "\t\t\t\t\t\t<ds:X509Data>\n" +
                "\t\t\t\t\t\t\t<ds:X509IssuerSerial>\n" +
                "\t\t\t\t\t\t\t\t<ds:X509IssuerName>CN=SUNCA,OU=JWS,O=SUN,ST=Some-State,C=AU</ds:X509IssuerName>\n" +
                "\t\t\t\t\t\t\t\t<ds:X509SerialNumber>3</ds:X509SerialNumber>\n" +
                "\t\t\t\t\t\t\t</ds:X509IssuerSerial>\n" +
                "\t\t\t\t\t\t</ds:X509Data>\n" +
                "\t\t\t\t\t</wsse:SecurityTokenReference>\n" +
                "\t\t\t\t</ds:KeyInfo>\n" +
                "\t\t\t</ds:Signature>\n" +
                "\t\t</wsse:Security>\n" +
                "\t\t<Action xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-91f9d878-9ecd-45dd-8e8d-160f22f24699\">http://webbroker.cetelem.ru/LoanService/createRequest</Action>\n" +
                "\t\t<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-32d88f3c-1ee9-4bc0-a246-f9437c61ef98\">urn:uuid:420fad1b-ff0f-4289-9f98-a0c93652a44e</MessageID>\n" +
                "\t\t<To xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-fa5f490c-8896-4c15-8595-a7fef558569d\">https://brokertest.cetelem-bank.ru/WebBrokerDirectCredit/LoanService</To>\n" +
                "\t\t<ReplyTo xmlns=\"http://www.w3.org/2005/08/addressing\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-b79faf8e-f806-43a3-a92a-845583dc4a4c\">\n" +
                "\t\t\t<Address>http://www.w3.org/2005/08/addressing/anonymous</Address>\n" +
                "\t\t</ReplyTo>\n" +
                "\t</soap:Header>\n" +
                "\t<soap:Body xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"id-17620b59-1aa5-47e5-8e88-c7b1c97fcb12\">\n" +
                "\t\t<ns2:create xmlns:ns2=\"http://webbroker.cetelem.ru/\">\n" +
                "\t\t\t<input>\n" +
                "\t\t\t\t<authInfo>\n" +
                "\t\t\t\t\t<pointOfSale>200055</pointOfSale>\n" +
                "\t\t\t\t\t<user>WebBroker</user>\n" +
                "\t\t\t\t</authInfo>\n" +
                "\t\t\t\t<parameters>\n" +
                "\t\t\t\t\t<loanData>\n" +
                "\t\t\t\t\t\t<cpiIncludedInCreditAmountCode/>\n" +
                "\t\t\t\t\t\t<creditProductCode>0597</creditProductCode>\n" +
                "\t\t\t\t\t\t<downpaymentRUB>7500</downpaymentRUB>\n" +
                "\t\t\t\t\t\t<durationInMonths>12</durationInMonths>\n" +
                "\t\t\t\t\t\t<good1>\n" +
                "\t\t\t\t\t\t\t<discountCode>Нет</discountCode>\n" +
                "\t\t\t\t\t\t\t<goodCostRUB>25000</goodCostRUB>\n" +
                "\t\t\t\t\t\t\t<goodCode>017</goodCode>\n" +
                "\t\t\t\t\t\t\t<goodCostKOP>0</goodCostKOP>\n" +
                "\t\t\t\t\t\t\t<goodCostWithDiscountRUB>0.0</goodCostWithDiscountRUB>\n" +
                "\t\t\t\t\t\t\t<goodDescription>Планшетный компьютер Apple iPad Air 2 Wi-Fi 9.7\" 64Gb MGKM2RU/A Silver белый</goodDescription>\n" +
                "\t\t\t\t\t\t</good1>\n" +
                "\t\t\t\t\t\t<insuranceOptionCode/>\n" +
                "\t\t\t\t\t\t<lkpInsuranceOptionCode/>\n" +
                "\t\t\t\t\t\t<maxMonthlyPaymentRUB>2263</maxMonthlyPaymentRUB>\n" +
                "\t\t\t\t\t</loanData>\n" +
                "\t\t\t\t\t<personalData>\n" +
                "\t\t\t\t\t\t<advertizingAgreementCode>Нет</advertizingAgreementCode>\n" +
                "\t\t\t\t\t\t<bankAccountsData>\n" +
                "\t\t\t\t\t\t\t<accountCode>Нет</accountCode>\n" +
                "\t\t\t\t\t\t\t<plasticCardCode/>\n" +
                "\t\t\t\t\t\t</bankAccountsData>\n" +
                "\t\t\t\t\t\t<contactPerson>ЧЕРЕЗЗАБОР МУХТАР АЛИОРДЫОГЛЫ</contactPerson>\n" +
                "\t\t\t\t\t\t<creditCardAgreementCode>Нет</creditCardAgreementCode>\n" +
                "\t\t\t\t\t\t<creditCardInsuranceAgreementCode>Нет</creditCardInsuranceAgreementCode>\n" +
                "\t\t\t\t\t\t<dateOfBirthDDMMYYYY>11.11.1980</dateOfBirthDDMMYYYY>\n" +
                "\t\t\t\t\t\t<educationCode>6</educationCode>\n" +
                "\t\t\t\t\t\t<email>ELECTRONICPOCHTA84@YANDEX.RU</email>\n" +
                "\t\t\t\t\t\t<employment>\n" +
                "\t\t\t\t\t\t\t<durationOfWorkOnPreviousPlaceInYears>82</durationOfWorkOnPreviousPlaceInYears>\n" +
                "\t\t\t\t\t\t\t<holderPositionCode>48</holderPositionCode>\n" +
                "\t\t\t\t\t\t\t<organizationActivityCode>PR</organizationActivityCode>\n" +
                "\t\t\t\t\t\t\t<organizationActivitySector375PCode/>\n" +
                "\t\t\t\t\t\t\t<organizationActivitySectorCode>1300</organizationActivitySectorCode>\n" +
                "\t\t\t\t\t\t\t<organizationAddress>\n" +
                "\t\t\t\t\t\t\t\t<regionCode>77</regionCode>\n" +
                "\t\t\t\t\t\t\t\t<regionName>Москва</regionName>\n" +
                "\t\t\t\t\t\t\t\t<regionType>103</regionType>\n" +
                "\t\t\t\t\t\t\t\t<districtCode/>\n" +
                "\t\t\t\t\t\t\t\t<districtName/>\n" +
                "\t\t\t\t\t\t\t\t<index>125009</index>\n" +
                "\t\t\t\t\t\t\t\t<townCode>000</townCode>\n" +
                "\t\t\t\t\t\t\t\t<townName>Москва</townName>\n" +
                "\t\t\t\t\t\t\t\t<townType>301</townType>\n" +
                "\t\t\t\t\t\t\t\t<settlementCode/>\n" +
                "\t\t\t\t\t\t\t\t<settlementName/>\n" +
                "\t\t\t\t\t\t\t\t<settlementType/>\n" +
                "\t\t\t\t\t\t\t\t<streetName>Красная</streetName>\n" +
                "\t\t\t\t\t\t\t\t<streetType>516</streetType>\n" +
                "\t\t\t\t\t\t\t\t<house>1</house>\n" +
                "\t\t\t\t\t\t\t\t<building/>\n" +
                "\t\t\t\t\t\t\t\t<corpus/>\n" +
                "\t\t\t\t\t\t\t\t<okato/>\n" +
                "\t\t\t\t\t\t\t</organizationAddress>\n" +
                "\t\t\t\t\t\t\t<organizationName>ОАО Президенция администрата</organizationName>\n" +
                "\t\t\t\t\t\t\t<recordOfServiceInYears>10</recordOfServiceInYears>\n" +
                "\t\t\t\t\t\t\t<workFromMMYYYY>012000</workFromMMYYYY>\n" +
                "\t\t\t\t\t\t</employment>\n" +
                "\t\t\t\t\t\t<expenses>\n" +
                "\t\t\t\t\t\t\t<compulsoryMonthlyPaymentsInRUB>0</compulsoryMonthlyPaymentsInRUB>\n" +
                "\t\t\t\t\t\t\t<familyMonthlyLoansWithoutClientsPaymentsInRUB>0</familyMonthlyLoansWithoutClientsPaymentsInRUB>\n" +
                "\t\t\t\t\t\t\t<loansMonthlyPaymentsInRUB>0</loansMonthlyPaymentsInRUB>\n" +
                "\t\t\t\t\t\t\t<utilitiesMonthlyPaymentsInRUB>0</utilitiesMonthlyPaymentsInRUB>\n" +
                "\t\t\t\t\t\t</expenses>\n" +
                "\t\t\t\t\t\t<factAddress>\n" +
                "\t\t\t\t\t\t\t<regionCode>77</regionCode>\n" +
                "\t\t\t\t\t\t\t<regionName>Москва</regionName>\n" +
                "\t\t\t\t\t\t\t<regionType>103</regionType>\n" +
                "\t\t\t\t\t\t\t<districtCode/>\n" +
                "\t\t\t\t\t\t\t<districtName/>\n" +
                "\t\t\t\t\t\t\t<index>125167</index>\n" +
                "\t\t\t\t\t\t\t<townCode>000</townCode>\n" +
                "\t\t\t\t\t\t\t<townName>Москва</townName>\n" +
                "\t\t\t\t\t\t\t<townType>301</townType>\n" +
                "\t\t\t\t\t\t\t<settlementCode/>\n" +
                "\t\t\t\t\t\t\t<settlementName/>\n" +
                "\t\t\t\t\t\t\t<settlementType/>\n" +
                "\t\t\t\t\t\t\t<streetName>Ленинградский</streetName>\n" +
                "\t\t\t\t\t\t\t<streetType>519</streetType>\n" +
                "\t\t\t\t\t\t\t<house>56</house>\n" +
                "\t\t\t\t\t\t\t<building/>\n" +
                "\t\t\t\t\t\t\t<corpus/>\n" +
                "\t\t\t\t\t\t\t<dateFromMMYYYY>012000</dateFromMMYYYY>\n" +
                "\t\t\t\t\t\t\t<housingStatus>P</housingStatus>\n" +
                "\t\t\t\t\t\t\t<okato/>\n" +
                "\t\t\t\t\t\t</factAddress>\n" +
                "\t\t\t\t\t\t<firstName>ТЕСТЖББЕЙ</firstName>\n" +
                "\t\t\t\t\t\t<homePhone>\n" +
                "\t\t\t\t\t\t\t<code>8</code>\n" +
                "\t\t\t\t\t\t\t<extension>-1</extension>\n" +
                "\t\t\t\t\t\t\t<number/>\n" +
                "\t\t\t\t\t\t</homePhone>\n" +
                "\t\t\t\t\t\t<income>\n" +
                "\t\t\t\t\t\t\t<clientsAdditionalMonthlyIncomeInRUB>0</clientsAdditionalMonthlyIncomeInRUB>\n" +
                "\t\t\t\t\t\t\t<clientsEmploymentMonthlyIncomeInRUB>80000</clientsEmploymentMonthlyIncomeInRUB>\n" +
                "\t\t\t\t\t\t\t<familyMonthlyIncomeWithoutClientsIncomeInRUB>100000</familyMonthlyIncomeWithoutClientsIncomeInRUB>\n" +
                "\t\t\t\t\t\t\t<totalFamilyMonthlyIncomeInRUB>180000</totalFamilyMonthlyIncomeInRUB>\n" +
                "\t\t\t\t\t\t</income>\n" +
                "\t\t\t\t\t\t<lastName>ТЕСТББДГГЕЦ</lastName>\n" +
                "\t\t\t\t\t\t<localPassport>\n" +
                "\t\t\t\t\t\t\t<dateOfIssueDDMMYYYY>19.04.2004</dateOfIssueDDMMYYYY>\n" +
                "\t\t\t\t\t\t\t<issuedBy>ТП УФМС РФ ПО ОМСКОЙ ОБЛ. В ОДЕССКОМ Р-НЕ</issuedBy>\n" +
                "\t\t\t\t\t\t\t<number>497901</number>\n" +
                "\t\t\t\t\t\t\t<series>2011</series>\n" +
                "\t\t\t\t\t\t\t<subdivisionCode>360-007</subdivisionCode>\n" +
                "\t\t\t\t\t\t</localPassport>\n" +
                "\t\t\t\t\t\t<mailingAddressFlag>1</mailingAddressFlag>\n" +
                "\t\t\t\t\t\t<maritalStatusCode>M</maritalStatusCode>\n" +
                "\t\t\t\t\t\t<middleName>ОДОБРЕВИЧ</middleName>\n" +
                "\t\t\t\t\t\t<mobilePhone>\n" +
                "\t\t\t\t\t\t\t<code>8980</code>\n" +
                "\t\t\t\t\t\t\t<extension>-1</extension>\n" +
                "\t\t\t\t\t\t\t<number>2511435</number>\n" +
                "\t\t\t\t\t\t</mobilePhone>\n" +
                "\t\t\t\t\t\t<mothersMaidenName>Одобревич</mothersMaidenName>\n" +
                "\t\t\t\t\t\t<nameChangeCode>Нет</nameChangeCode>\n" +
                "\t\t\t\t\t\t<numberOfChildren>1</numberOfChildren>\n" +
                "\t\t\t\t\t\t<numberOfDependents>1</numberOfDependents>\n" +
                "\t\t\t\t\t\t<numberOfFamilyMembers>3</numberOfFamilyMembers>\n" +
                "\t\t\t\t\t\t<occupationCode>D</occupationCode>\n" +
                "\t\t\t\t\t\t<placeOfBirth>МОСКВА</placeOfBirth>\n" +
                "\t\t\t\t\t\t<previousFirstName>Нет</previousFirstName>\n" +
                "\t\t\t\t\t\t<previousLastName>Нет</previousLastName>\n" +
                "\t\t\t\t\t\t<previousMiddleName>Нет</previousMiddleName>\n" +
                "\t\t\t\t\t\t<registrationAddress>\n" +
                "\t\t\t\t\t\t\t<regionCode>77</regionCode>\n" +
                "\t\t\t\t\t\t\t<regionName>Москва</regionName>\n" +
                "\t\t\t\t\t\t\t<regionType>103</regionType>\n" +
                "\t\t\t\t\t\t\t<districtCode/>\n" +
                "\t\t\t\t\t\t\t<districtName/>\n" +
                "\t\t\t\t\t\t\t<index>125167</index>\n" +
                "\t\t\t\t\t\t\t<townCode>000</townCode>\n" +
                "\t\t\t\t\t\t\t<townName>Москва</townName>\n" +
                "\t\t\t\t\t\t\t<townType>301</townType>\n" +
                "\t\t\t\t\t\t\t<settlementCode/>\n" +
                "\t\t\t\t\t\t\t<settlementName/>\n" +
                "\t\t\t\t\t\t\t<settlementType/>\n" +
                "\t\t\t\t\t\t\t<streetName>Ленинградский</streetName>\n" +
                "\t\t\t\t\t\t\t<streetType>519</streetType>\n" +
                "\t\t\t\t\t\t\t<house>56</house>\n" +
                "\t\t\t\t\t\t\t<building/>\n" +
                "\t\t\t\t\t\t\t<corpus/>\n" +
                "\t\t\t\t\t\t\t<dateFromMMYYYY>112011</dateFromMMYYYY>\n" +
                "\t\t\t\t\t\t\t<okato/>\n" +
                "\t\t\t\t\t\t</registrationAddress>\n" +
                "\t\t\t\t\t\t<relativePhone>\n" +
                "\t\t\t\t\t\t\t<code>8926</code>\n" +
                "\t\t\t\t\t\t\t<extension>-1</extension>\n" +
                "\t\t\t\t\t\t\t<number>2556456</number>\n" +
                "\t\t\t\t\t\t</relativePhone>\n" +
                "\t\t\t\t\t\t<sexCode>MAL</sexCode>\n" +
                "\t\t\t\t\t\t<spouseDateOfBirthDDMMYYYY>31.12.1990</spouseDateOfBirthDDMMYYYY>\n" +
                "\t\t\t\t\t\t<spouseFirstName>МАМАЙ</spouseFirstName>\n" +
                "\t\t\t\t\t\t<spouseLastName>КАКАЕВ</spouseLastName>\n" +
                "\t\t\t\t\t\t<spouseMiddleName>УВАЕВИЧ</spouseMiddleName>\n" +
                "\t\t\t\t\t\t<workingPhone>\n" +
                "\t\t\t\t\t\t\t<code>8495</code>\n" +
                "\t\t\t\t\t\t\t<extension>-1</extension>\n" +
                "\t\t\t\t\t\t\t<number>1651651</number>\n" +
                "\t\t\t\t\t\t</workingPhone>\n" +
                "\t\t\t\t\t\t<hasCustomer2NDFLCode>Нет</hasCustomer2NDFLCode>\n" +
                "\t\t\t\t\t\t<prevPassport>\n" +
                "\t\t\t\t\t\t\t<dateOfIssueDDMMYYYY/>\n" +
                "\t\t\t\t\t\t</prevPassport>\n" +
                "\t\t\t\t\t</personalData>\n" +
                "\t\t\t\t\t<visualAppraisalData>\n" +
                "\t\t\t\t\t\t<suspiciousPerson>0</suspiciousPerson>\n" +
                "\t\t\t\t\t\t<suspiciousBehavior>0</suspiciousBehavior>\n" +
                "\t\t\t\t\t\t<suspiciousLoan>0</suspiciousLoan>\n" +
                "\t\t\t\t\t\t<suspiciousPersonData>0</suspiciousPersonData>\n" +
                "\t\t\t\t\t\t<thirdPersonInfluence>0</thirdPersonInfluence>\n" +
                "\t\t\t\t\t\t<suspiciousPassport>0</suspiciousPassport>\n" +
                "\t\t\t\t\t\t<suspiciousAdditionalDocuments>0</suspiciousAdditionalDocuments>\n" +
                "\t\t\t\t\t\t<suspiciousFinanceDocument>0</suspiciousFinanceDocument>\n" +
                "\t\t\t\t\t</visualAppraisalData>\n" +
                "\t\t\t\t</parameters>\n" +
                "\t\t\t\t<attachments>\n" +
                "\t\t\t\t\t<photo>\n" +
                "\t\t\t\t\t\t<content>iVBORw0KGgoAAAANSUhEUgAABKIAAAQWCAIAAACIXYe+AAAOLklEQVR4nO3BgQAAAADDoPlT3+AEVQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwDdEiAAHFtqOuAAAAAElFTkSuQmCC</content>\n" +
                "\t\t\t\t\t\t<extension>png</extension>\n" +
                "\t\t\t\t\t</photo>\n" +
                "\t\t\t\t</attachments>\n" +
                "\t\t\t</input>\n" +
                "\t\t</ns2:create>\n" +
                "\t</soap:Body>\n" +
                "</soap:Envelope>\n";
        return stringToDom(xml);
    }

    public final class SimpleNamespaceContext implements NamespaceContext {
        private final Map<String, String> namespaces; // [{ "foo": "http://foo" }]
        private final Multimap<String, String> prefixes; // [{ "http://foo": ["foo"] }]

        /**
         * Constructor for SimpleNamespaceContext
         * @param namespaces A map where the key is a prefix, and the value is a namespace.
         */
        public SimpleNamespaceContext(Map<String, String> namespaces){
            this.namespaces = addConstants(namespaces);
            prefixes = Multimaps.invertFrom(Multimaps.forMap(this.namespaces), ArrayListMultimap.create());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getNamespaceURI(String prefix) {
            checkNotNull(prefix);
            return namespaces.getOrDefault(prefix, NULL_NS_URI);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getPrefix(String namespaceURI) {
            checkNotNull(namespaceURI);
            return prefixes.get(namespaceURI).stream().findFirst().orElse(null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Iterator<String> getPrefixes(String namespaceURI){
            checkNotNull(namespaceURI);
            return prefixes.get(namespaceURI).iterator();
        }

        private String checkNotNull(String reference) {
            if (reference == null) {
                throw new IllegalArgumentException();
            }
            return reference;
        }

        private Map<String, String> addConstants(Map<String, String> map){
            Map<String, String> namespaces = Maps.newHashMap(map);
            namespaces.putIfAbsent(XML_NS_PREFIX, XML_NS_URI);
            namespaces.putIfAbsent(XMLNS_ATTRIBUTE, XMLNS_ATTRIBUTE_NS_URI);
            return Collections.unmodifiableMap(namespaces);
        }
    }
}
