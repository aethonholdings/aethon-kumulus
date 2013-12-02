package com.scannerapp.apps.desktopleftpanel.view;

import javax.swing.tree.DefaultMutableTreeNode;

public class CustomMutableTreeNode extends DefaultMutableTreeNode
{

	private String nodeId="";

	public CustomMutableTreeNode(Object userObject) {
		super(userObject);
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}
