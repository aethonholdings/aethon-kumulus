package com.scannerapp.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import com.scannerapp.shared.NodeProperties;
import com.scannerapp.ws.common.util.configuration.ApplicationConstants;
import com.scannerapp.ws.common.util.db.QueryReader;
import com.scannerapp.ws.common.util.log.MessageLogger;
import com.sun.jersey.core.util.Base64;

/**
 * A service class to process operations for the scanned images.
 * 
 * @author Gaurav Antal<gaurav.antal@spec-india.com>
 * @since 05/August/2013
 */
public class ImageProcessServiceImpl {

	private Logger logger = MessageLogger.getLogger("ImageProcessServiceImpl");

	private static final String SCANNED_IMAGE_DIR = "scanned_image"
			+ File.separator;
	private static final String THUMBNAIL_DIR = "thumbnail" + File.separator;

	/**
	 * Method to upload the scanned image & thumbnail for the provided image
	 * node properties.
	 * 
	 * @param scannedImageNodeProperties
	 * @return
	 */
	public boolean uploadScannedImage(NodeProperties scannedImageNodeProperties) {

		boolean imageUploadedSuccessfully = false;

		try {
			// CREATING TEMP_DIRECTORY ON SERVER...
			File tempDir = new File(SCANNED_IMAGE_DIR + THUMBNAIL_DIR);
			if (!tempDir.exists()) {
				tempDir.mkdirs();
			}

			// GENERATING BYTE ARRAY OF SCANNED IMAGE...
			String encodedImageString = scannedImageNodeProperties
					.getEncodeStringForImage();
			encodedImageString = encodedImageString.replaceAll(" ", "+");
			encodedImageString = encodedImageString.replaceAll("\n", "");

			byte[] scannedImageBytes = Base64.decode(encodedImageString);

			// UPLOADING ACTUAL & THUMBNAIL IMAGE...
			imageUploadedSuccessfully = uploadActualImage(
					scannedImageNodeProperties, scannedImageBytes)
					& uploadThumbnailImage(scannedImageNodeProperties,
							scannedImageBytes);

		} catch (Exception e) {

			logger.error("Exception : " + e);

			imageUploadedSuccessfully = false;
		}

		return imageUploadedSuccessfully;
	}

	/**
	 * Method to generate the actual scanned image and upload on FTP server.
	 * 
	 * @param scannedImageNodeProperties
	 * @param scannedImageBytes
	 * @return
	 * @throws Exception
	 */
	private boolean uploadActualImage(
			NodeProperties scannedImageNodeProperties, byte[] scannedImageBytes)
			throws Exception {

		FTPClient ftpClient = null;
		boolean imageUploadedSuccessfully = false;

		try {

			// CREATING TEMP FILE OF ACTUAL IMAGE TO UPLOAD ON FTP...
			File scannedImage = new File(SCANNED_IMAGE_DIR
					+ scannedImageNodeProperties.getName());
			scannedImage.createNewFile();

			// WRITING SCANNED IMAGE FILE...
			FileOutputStream scannedImageFileOutputStream = new FileOutputStream(
					scannedImage);
			scannedImageFileOutputStream.write(scannedImageBytes);
			scannedImageFileOutputStream.close();

			// UPLOADING IMAGE...
			ftpClient = getFtpClient();

			imageUploadedSuccessfully = uploadFile(scannedImage.getPath(),
					scannedImage.getName(),
					getImageDirectory(scannedImageNodeProperties
							.getActualImageName()), ftpClient);

			logger.info("Actual Image Name To Upload : "
					+ scannedImageNodeProperties.getActualImageName());
			logger.info("Actual Image Uploaded Successfully ? : "
					+ imageUploadedSuccessfully);

			// REMOVING TEMP SCANNED IMAGE...
			scannedImage.delete();

		} catch (Exception e) {

			logger.error("Error while uploading the actual scanned image via FTP.");
			logger.error("Actual Image Name : "
					+ scannedImageNodeProperties.getActualImageName());

			imageUploadedSuccessfully = false;

			throw e;

		} finally {

			if (ftpClient != null)
				disconnectFTPClient(ftpClient);
		}

		return imageUploadedSuccessfully;
	}

	/**
	 * Method to generate the thumbnail image from actual scanned image and
	 * upload on FTP server.
	 * 
	 * @param scannedImageNodeProperties
	 * @param scannedImageBytes
	 * @return
	 * @throws Exception
	 */
	private boolean uploadThumbnailImage(
			NodeProperties scannedImageNodeProperties, byte[] scannedImageBytes)
			throws Exception {

		FTPClient ftpClient = null;
		boolean imageUploadedSuccessfully = false;

		String thumbnailImageWidth = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.CONF_CONST_FILE, "thumbnailImageWidth");
		String thumbnailImageHeight = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.CONF_CONST_FILE, "thumbnailImageHeight");
		String thumbnailQualityCompressionScale = QueryReader
				.getQueryFromPropertyFile(ApplicationConstants.CONF_CONST_FILE,
						"thumbnailQualityCompressionScale");

		try {

			// CREATING THUMBNAIL
			File thumbnailImageFile = new File(SCANNED_IMAGE_DIR
					+ THUMBNAIL_DIR + scannedImageNodeProperties.getName());

			// IF SAVING IMAGE FOR FIRST TIME, GENERATE THUMBNAIL IMAGE FROM
			// SCANNED IMAGE BYTES & UPLOAD ON FTP.
			if (scannedImageNodeProperties.getEncodeStringForThumbnail() == null
					|| scannedImageNodeProperties.getEncodeStringForThumbnail()
							.length() == 0) {

				// CREATING THUMBNAIL BUFFERED IMAGE
				InputStream inputStream = new ByteArrayInputStream(
						scannedImageBytes);
				BufferedImage actualBufferedImage = ImageIO.read(inputStream);

				BufferedImage thumbnailBufferedImage = Scalr.resize(
						actualBufferedImage, Scalr.Method.ULTRA_QUALITY,
						Scalr.Mode.AUTOMATIC,
						Integer.parseInt(thumbnailImageWidth),
						Integer.parseInt(thumbnailImageHeight),
						Scalr.OP_ANTIALIAS);

				// CREATING OUTPUT STREAM FOR TEMP THUMBNAIL IMAGE
				ImageOutputStream imageOutputStream = ImageIO
						.createImageOutputStream(thumbnailImageFile);

				ImageWriter writer = (ImageWriter) ImageIO
						.getImageWritersByFormatName("jpg").next();
				writer.setOutput(imageOutputStream);

				ImageWriteParam param = new JPEGImageWriteParam(
						Locale.getDefault());
				param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				param.setCompressionQuality(Float
						.parseFloat(thumbnailQualityCompressionScale));

				// WRITING TEMP THUMBNAIL IMAGE TO UPLOAD & CLOSING WRITER
				writer.write(null, new IIOImage(thumbnailBufferedImage, null,
						null), param);

				// CLOSING STREAMS & WRITER
				imageOutputStream.close();
				inputStream.close();
				writer.dispose();
			}

			// IF THUMBNAIL IMAGE STRING IS AVAILABLE, GENERATE IMAGE & UPLOAD
			// ON FTP.
			else {

				thumbnailImageFile.createNewFile();

				// GENERATING BYTE ARRAY OF THUMBNAIL IMAGE...
				String encodedImageString = scannedImageNodeProperties
						.getEncodeStringForThumbnail();
				encodedImageString = encodedImageString.replaceAll(" ", "+");
				encodedImageString = encodedImageString.replaceAll("\n", "");

				byte[] thumbnailImageBytes = Base64.decode(encodedImageString);

				// WRITING SCANNED IMAGE FILE...
				FileOutputStream thumbnailImageFileOutputStream = new FileOutputStream(
						thumbnailImageFile);
				thumbnailImageFileOutputStream.write(thumbnailImageBytes);
				thumbnailImageFileOutputStream.close();
			}

			// UPLOADING FILE
			ftpClient = getFtpClient();

			imageUploadedSuccessfully = uploadFile(
					thumbnailImageFile.getPath(), thumbnailImageFile.getName(),
					getImageDirectory(scannedImageNodeProperties
							.getThumbnailImageName()), ftpClient);

			logger.info("Thumbnail Image Name To Upload : "
					+ scannedImageNodeProperties.getThumbnailImageName());
			logger.info("Thumbnail Image Uploaded Successfully ? : "
					+ imageUploadedSuccessfully);

			// REMOVING TEMP THUMBNAIL IMAGE
			thumbnailImageFile.delete();

		} catch (Exception e) {

			logger.error("Error while uploading the thumbnail image via FTP.");
			logger.error("Thumbnail Image Name : "
					+ scannedImageNodeProperties.getThumbnailImageName());

			imageUploadedSuccessfully = false;

			throw e;

		} finally {

			if (ftpClient != null)
				disconnectFTPClient(ftpClient);
		}

		return imageUploadedSuccessfully;
	}

	/**
	 * Method to upload scanned image file.
	 * 
	 * @param localFileFullName
	 *            - Full image name on local AS.
	 * @param fileName
	 *            - Name of image.
	 * @param hostDir
	 *            - Host directory to store file in.
	 * @param ftpClient
	 * @return
	 * @throws Exception
	 */
	private boolean uploadFile(String localFileFullName, String fileName,
			String hostDir, FTPClient ftpClient) throws Exception {

		InputStream inputStream = null;
		boolean uploadSucessfully = false;

		try {
			inputStream = new FileInputStream(new File(localFileFullName));

			createDirectory(hostDir, ftpClient);

			uploadSucessfully = ftpClient.storeFile(fileName, inputStream);

		} finally {

			if (inputStream != null)
				inputStream.close();
		}

		return uploadSucessfully;
	}

	/**
	 * Method to delete the image with provided path from FTP.
	 * 
	 * @param actualImageName
	 * @return
	 */
	public boolean deleteImageFromFTP(String imagePath) {

		boolean isFileDeleted = false;
		FTPClient ftpClient = null;

		try {
			ftpClient = getFtpClient();

			imagePath = imagePath.replace('\\', File.separatorChar).replace(
					'/', File.separatorChar);

			isFileDeleted = ftpClient.deleteFile(imagePath);
		}

		catch (Exception e) {
			logger.error("Error while deleting the image from FTP. Image Name : "
					+ imagePath);
			logger.error("Exception : " + e);

			isFileDeleted = false;
		}

		finally {
			if (ftpClient != null)
				disconnectFTPClient(ftpClient);
		}

		return isFileDeleted;
	}

	/**
	 * Method to move the scanned image on FTP from one folder (provided
	 * fromFilePath) to another folder (provided toFilePath).
	 * 
	 * @param fromFilePath
	 * @param toFilePath
	 * @return
	 */
	public boolean moveImageOnFTP(String fromFilePath, String toFilePath) {

		boolean isFileMoved = false;
		FTPClient ftpClient = null;

		try {
			ftpClient = getFtpClient();

			// GENERATING FILE PATH AS PER OS...
			fromFilePath = fromFilePath.replace('\\', File.separatorChar)
					.replace('/', File.separatorChar);
			toFilePath = toFilePath.replace('\\', File.separatorChar).replace(
					'/', File.separatorChar);

			String destinationDirectory = getImageDirectory(toFilePath);

			// CREATING DESTINATION DIRECTORY ON FTP...
			createDirectory(destinationDirectory, ftpClient);

			// MOVE TO BASE DIRECTORY ON FTP...
			moveToBaseFTPDirectory(destinationDirectory, ftpClient);

			// MOVING FILE...
			isFileMoved = ftpClient.rename(fromFilePath, toFilePath);
		}

		catch (Exception e) {
			logger.error("Error while moving the scanned image on FTP   FROM : "
					+ fromFilePath + "     TO : " + toFilePath);
			logger.error("Exception : " + e);
		}

		finally {
			if (ftpClient != null)
				disconnectFTPClient(ftpClient);
		}

		return isFileMoved;
	}

	/**
	 * Method to create directory on FTP if it is not existing and change
	 * present working directory to newly created directory. If directory is
	 * already created, this method will just change present working directory
	 * to that directory.
	 * 
	 * @param dirPath
	 *            - Directories separated by file separator (directory path
	 *            relative to FTP)
	 * @param ftpClient
	 * @throws Exception
	 */
	private void createDirectory(String dirPath, FTPClient ftpClient)
			throws Exception {

		if (dirPath == null || dirPath.length() == 0
				|| dirPath.equals(File.separator)) {
			throw new Exception(
					"Error While Creating Directory. No Directory Path Specified.");
		}

		String[] directories = dirPath.split(Pattern.quote(System
				.getProperty("file.separator")));

		if (directories == null || directories.length == 0) {
			throw new Exception(
					"Error While Creating Directory. No Directory Path Specified.");
		}

		for (String dir : directories) {

			if (dir.length() > 0) {

				if (!ftpClient.changeWorkingDirectory(dir)) {

					if (!ftpClient.makeDirectory(dir)) {
						throw new IOException("Unable To Create Directory : "
								+ dir + "   Directory Path Is : " + dirPath);
					}

					if (!ftpClient.changeWorkingDirectory(dir)) {
						throw new IOException("Unable To Open Directory : "
								+ dir + "   Directory Path Is : " + dirPath);
					}
				}
			}
		}
	}

	/**
	 * Method to take the FTP client to base directory (home directory of FTP
	 * user).
	 * 
	 * @param dirPath
	 * @param ftpClient
	 * @throws Exception
	 */
	private void moveToBaseFTPDirectory(String dirPath, FTPClient ftpClient)
			throws Exception {

		String[] directories = dirPath.split(Pattern.quote(System
				.getProperty("file.separator")));

		for (int index = 0; index < directories.length; index++) {
			ftpClient.changeToParentDirectory();
		}
	}

	/**
	 * Method to separate the image name from the provided image path and get
	 * the path of the image directory. Image path contains the relative path of
	 * image on FTP.
	 * 
	 * @param imagePath
	 * @return
	 */
	private String getImageDirectory(String imagePath) {

		if (imagePath == null || imagePath.length() == 0)
			return "";

		imagePath = imagePath.replace('\\', File.separatorChar).replace('/',
				File.separatorChar);

		String imageDir = imagePath.substring(0,
				imagePath.lastIndexOf(File.separator) + 1);

		return imageDir;
	}

	/**
	 * Method to initialize the FTPClient object for uploading file.
	 * 
	 * @return
	 */
	private FTPClient getFtpClient() {

		String ftpURL = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpUrl");
		String ftpUserName = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpUser");
		String ftpPassword = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpPassword");
		String ftpPort = QueryReader.getQueryFromPropertyFile(
				ApplicationConstants.DB_CONN_CONST_FILE, "ftpPort");

		FTPClient ftpClient = new FTPClient();
		int reply;

		try {
			// Adding protocol command listener to FTPClient...
			ftpClient.addProtocolCommandListener(new PrintCommandListener(
					new PrintWriter(System.out)));

			// Connecting FTPClient...
			ftpClient.connect(ftpURL, Integer.parseInt(ftpPort));

			reply = ftpClient.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				throw new Exception(
						"FTPReply Is Not Positive Completion Reply.");
			}

			if (!ftpClient.login(ftpUserName, ftpPassword))
				throw new Exception("Error while logging in for FTP.");

			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setRemoteVerificationEnabled(false);
		}

		catch (Exception e) {

			logger.error("Error while initializing the FTPClient to upload file.");
			logger.error("Exception : " + e);
		}

		return ftpClient;
	}

	/**
	 * Method to disconnect the provided FTP client.
	 * 
	 * @param ftpClient
	 */
	private void disconnectFTPClient(FTPClient ftpClient) {

		try {

			if (ftpClient != null && ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}

		} catch (Exception f) {

			logger.error("Error while disconnecting FTP client.");
			logger.error("Exception : " + f);
		}
	}
}
