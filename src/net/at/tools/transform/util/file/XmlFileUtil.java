package net.at.tools.transform.util.file;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML�t�@�C���Ɋւ���Utility
 */
public class XmlFileUtil extends BaseTextFileUtil{
	/**
	 * �w�肵���q�v�f���擾
	 * @param parentNode �e�v�f
	 * @param nodeName �w�肷��v�f��
	 * @return �w�肵���q�v�f
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
	 * �w�肵���q�v�f�ꗗ���擾
	 * @param parentNode �e�v�f
	 * @param nodeName �w�肷��v�f��
	 * @return �w�肵���q�v�f�ꗗ
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
