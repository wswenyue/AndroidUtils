package org.iti.eyescare.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.iti.eyescare.constants.Constants;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtil {

	/**
	 * 上传图片
	 * 
	 * @param filePath
	 *            文件路径
	 * @param count
	 *            失败重传次数
	 * @return
	 */
	public static String uploadPic(File file, int count) {
		String filePathServer = "";
		if (count > 0) {
			try {
				String result = ApacheHttpUtil.post(Constants.UPLOAD_FILE_URL,
						file);
				Response resp = Response.convert(result);
				filePathServer = resp.getResponResult();
			} catch (IOException e1) {
				e1.printStackTrace();
				uploadPic(file, count--);
			}
		}
		return filePathServer;
	}

	/**
	 * 根据图片在服务器上的地址加载图片
	 * 
	 * @param urlStr
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Bitmap decodeSampledBitmapFromUrl(String urlStr,
			int reqWidth, int reqHeight) throws MalformedURLException,
			IOException {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory
				.decodeStream(getInputStreamFromUrl(urlStr), null, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;
		options.inInputShareable = true;
		return BitmapFactory.decodeStream(getInputStreamFromUrl(urlStr), null,
				options);
	}

	/**
	 * 从url中得到流
	 * 
	 * @param urlStr
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static InputStream getInputStreamFromUrl(String urlStr)
			throws MalformedURLException, IOException {
		URL url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStream inputStream = urlConn.getInputStream();
		return inputStream;
	}

	/**
	 * 计算inSampleSize
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

}
