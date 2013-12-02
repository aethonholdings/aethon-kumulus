package com.scannerapp.apps.desktoprightpanel.view;

import javax.swing.JLabel;

import com.scannerapp.shared.NodeProperties;

@SuppressWarnings("serial")
public class CustomActualImageViewJLabel extends JLabel
{

	private String actualImagePath="";
	
	private NodeProperties nodeProperty;

	public CustomActualImageViewJLabel()
	{
		super();
	}

	
	public NodeProperties getNodeProperty() {
		return nodeProperty;
	}


	public void setNodeProperty(NodeProperties nodeProperty) {
		this.nodeProperty = nodeProperty;
	}

	public String getActualImagePath() {
		return actualImagePath;
	}

	public void setActualImagePath(String actualImagePath) {
		this.actualImagePath = actualImagePath;
	}

	
}