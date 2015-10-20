package net.at.tools.transform.util.file;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XMLファイルに関するUtility
 */
public class XmlFileUtil extends BaseTextFileUtil{
	/**
	 * 指定した子要素を取得
	 * @param parentNode 親要素
	 * @param nodeName 指定する要素名
	 * @return 指定した子要素
	 */
	public static Node getChildNode(Node parentNode, String nodeName) {
		Node node = null;
		NodeList nodeList = parentNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (((Element)childNode).getNodeName().equals(nodeName)) {
					node = childNode;
					break;
				}
			}
		}
		return node;
	}

	/**
	 * 指定した子要素一覧を取得
	 * @param parentNode 親要素
	 * @param nodeName 指定する要素名
	 * @return 指定した子要素一覧
	 */
	public static List<Node> getChildNodeList(Node parentNode, String nodeName) {
		List<Node> childNodeList = new ArrayList<>();
		NodeList nodeList = parentNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (((Element)childNode).getNodeName().equals(nodeName)) {
					childNodeList.add(childNode);
				}
			}
		}
		return childNodeList;
	}
}
