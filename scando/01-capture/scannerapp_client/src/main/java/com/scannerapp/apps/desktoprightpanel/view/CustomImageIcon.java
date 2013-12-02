package com.scannerapp.apps.desktoprightpanel.view;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.scannerapp.shared.NodeProperties;

public class CustomImageIcon extends ImageIcon
{
	private String actualImagePath="";
	private String fullScreenImagePath="";
	private NodeProperties nodeProperty;
	
	public CustomImageIcon() 
	{
		super();
	}
	
	public CustomImageIcon(Image thumb) 
	{
		super(thumb);
	}
	
	public String getActualImagePath() {
		return actualImagePath;
	}

	public void setActualImagePath(String actualImagePath) {
		this.actualImagePath = actualImagePath;
	}

	public NodeProperties getNodeProperty() {
		return nodeProperty;
	}

	public void setNodeProperty(NodeProperties nodeProperty) {
		this.nodeProperty = nodeProperty;
	}

	/**
	 * @return the fullScreenImagePath
	 */
	public String getFullScreenImagePath() {
		return fullScreenImagePath;
	}

	/**
	 * @param fullScreenImagePath the fullScreenImagePath to set
	 */
	public void setFullScreenImagePath(String fullScreenImagePath) {
		this.fullScreenImagePath = fullScreenImagePath;
	}
	
	
	
	
	
}
