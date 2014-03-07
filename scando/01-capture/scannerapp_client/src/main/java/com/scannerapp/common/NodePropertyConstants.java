package com.scannerapp.common;

import java.io.File;

public interface NodePropertyConstants {
	// Node Type
	String DOCUMENT = "P";
	String BOX = "B";
	String CONTAINER = "C";

	// Node Status
	String IN_PROGRESS = "0";
	String DONE = "1";
	String SEALED = "2";

	String THUMBNAIL_DIR = "thumbnail" + File.separator;
}
