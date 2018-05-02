package org.bestqa.selenium.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class FileUtils {
	
	/**
	 * Cut picture
	 * 
	 * @param src
	 * @param point
	 * @param dimension
	 */
	public static byte[] cutFromPng(File src, Point point, Dimension dimension) {

		FileInputStream is = null;
		ImageInputStream iis = null;

		try {
			// Read picture file
			is = new FileInputStream(src);

			/*
			 * Returns the Iterator containing all the currently registered ImageReader, 
			 * the ImageReader claims to be able to decode the specified format
			 * Parameters: formatName - contains informal format name.(For example "JPEG" and "TIFF")
			 */
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName("png");
			ImageReader reader = it.next();
			// Get the photo stream
			iis = ImageIO.createImageInputStream(is);

			/*
			 * <p>iis:read the source.true:forward search only</p>.To mark it as 'forward search only'
			 * This setting means that the image contained in the input source will only read in order, 
			 * may allow the reader to avoid the cache to contain data associated with the previously read image input
			 */
			reader.setInput(iis, true);

			/*
			 * <p>Describes how to decode the stream classes<p>.
			 * Is used to specify how the input from the I/O framework of Java image in the context of a flow 
			 * switch to an image or a set of images.
			 * The plug-in for particular image format from the ImageReaderGetDefaultReadParam method 
			 * returns the instance of ImageReadParam
			 */
			ImageReadParam param = reader.getDefaultReadParam();

			/*
			 * Picture cropping area. 
			 * An area of the Rectangle specifies the coordinate space, 
			 * It can be defined in this area  
			 * by Rectangle object's top-left coordinates of the vertex (x, y), width and height
			 */
			Rectangle rect = new Rectangle(point.x, point.y, dimension.width,
					dimension.height);

			// Provide a BufferedImage, it is used as the target for decoding pixel data of .
			param.setSourceRegion(rect);

			/*
			 * Using the provided ImageReadParam read by the specified object of index imageIndex, 
			 * and return it as a complete BufferedImage.
			 */
			BufferedImage bi = reader.read(0, param);

	        File tmpFile = File.createTempFile("captureElement", ".png");
	        tmpFile.deleteOnExit();
			// Save a new picture        
			ImageIO.write(bi, "png", tmpFile);

			return getByte(tmpFile);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * To convert a file into bytes
	 * 
	 * @param file
	 * @return byte[]
	 */
	public static byte[] getByte(File file) {
		InputStream is = null;
		try {
			byte[] bytes = null;
			is = new FileInputStream(file);

			int length = (int) file.length();
			// When the length of the file exceeds the maximum of int
			if (length > Integer.MAX_VALUE) 
			{
				throw new RuntimeException("File is too large.");
			}
			bytes = new byte[length];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			// It may be wrong if the length inconsistency between the byte length and the file one
			if (offset < bytes.length) {
				System.out.println("file length is error");
				return null;
			}
			is.close();
			
			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
