package com.scannerapp.apps.desktopleftpanel.view;

import java.awt.Component;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.scannerapp.apps.application.ApplicationConstants;
import com.scannerapp.apps.utils.FileLoader;
import com.scannerapp.common.NodePropertyConstants;
import com.scannerapp.resources.IconRepository;
import com.scannerapp.shared.NodeProperties;

public class NodeRender  extends DefaultTreeCellRenderer{
	
	HashMap<String, NodeProperties> nodeMap=null;
	public NodeRender(){
		super();
	}
	public NodeRender(HashMap<String, NodeProperties> nodePropertiesMap) {
		this();
		nodeMap=nodePropertiesMap;
	}

	public Component getTreeCellRendererComponent(JTree tree,
	        Object value, boolean selected, boolean expanded,
	        boolean leaf, int row, boolean hasFocus){

	        super.getTreeCellRendererComponent(tree, value,
	        selected, expanded, leaf, row, hasFocus);

	        CustomMutableTreeNode node = (CustomMutableTreeNode)value;
			NodeProperties nodeProperties = nodeMap.get(node.getNodeId());
	        
			JLabel label = (JLabel) this ;			
			
			if(nodeProperties!=null){
				if(nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.DOCUMENT)){								       
			        label.setIcon(IconRepository.ICON_DOCUMENT) ;
				}
				else if(nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.BOX)){					
			        label.setIcon(IconRepository.ICON_BOX) ;
				}
				else if(nodeProperties.getType().equalsIgnoreCase(NodePropertyConstants.CONTAINER)){									
			        label.setIcon(IconRepository.ICON_CONTAINER) ;
				}
			}
	        return this;
	    }
}
