package br.com.agencialove.tpa;
/*

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.calib3d.Calib3d;
import org.opencv.calib3d.StereoSGBM;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point3;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.xfeatures2d.SURF;

public class FeatureRecognitionTest {

	private static final Size BOARD_SIZE = new Size(10,7);

	static {
		System.load("/home/mendes/eclipse-jee-workspace/CamTest/lib/libopencv_java343.so");
	}


 * <Calibrates cameras individually>

	private static final List<Mat> objectPoints = new ArrayList<>();
	private static final List<Mat> lImagePoints = new ArrayList<>();
	private static final List<Mat> rImagePoints = new ArrayList<>();


	private static final Mat lCameraMatrix = new Mat(3, 3, CvType.CV_32FC1);
	private static final Mat rCameraMatrix = new Mat(3, 3, CvType.CV_32FC1);
	private static final Mat lDistCoeffs = new Mat();
	private static final Mat rDistCoeffs = new Mat();
	private static final List<Mat> lRvecs = new ArrayList<>();
	private static final List<Mat> rRvecs = new ArrayList<>();
	private static final List<Mat> lTvecs = new ArrayList<>();
	private static final List<Mat> rTvecs = new ArrayList<>();


	public static void main(final String[] args) throws IOException, InterruptedException {

		final boolean save = true;
		final boolean probeFlags = false;
		final boolean takePhotos = false;

		final VideoCapture lVc = new VideoCapture("/dev/video1");
		final VideoCapture rVc = new VideoCapture("/dev/video2");
		lVc.open("/dev/video1");
		rVc.open("/dev/video2");

		Thread.sleep(2000);

		Mat lImg = new Mat();
		Mat rImg = new Mat();

		//calibrate cans
		for(int i = 0; i < 50;) {

			if(save) {
				lVc.read(lImg);
				rVc.read(rImg);
				final Size s = new Size(lImg.height(), lImg.width() * 2);
				final Mat concatImg = new Mat(s, CvType.CV_32FC1);
				final List<Mat> imgs = new ArrayList<>();
				imgs.add(lImg);
				imgs.add(rImg);
				Core.hconcat(imgs, concatImg);
				HighGui.imshow("Capturated", concatImg);
				HighGui.waitKey(1);
			}else {
				lImg = Imgcodecs.imread("calibration/img/calibration" + i + "_L.png");
				rImg = Imgcodecs.imread("calibration/img/calibration" + i + "_R.png");
			}

			final boolean found = FeatureRecognitionTest.calibrate(lImg, rImg, i, save);

			if(found)
				++i;
		}

		//calibrate
		if(save)
			System.out.println("Images were captured!");
		else
			System.out.println("Images were recovered!");

		Size imageSize = new Size(lImg.width(), lImg.height());

		long startTime = System.currentTimeMillis();
		Calib3d.calibrateCamera(FeatureRecognitionTest.objectPoints, FeatureRecognitionTest.lImagePoints, imageSize , FeatureRecognitionTest.lCameraMatrix, FeatureRecognitionTest.lDistCoeffs, FeatureRecognitionTest.lRvecs, FeatureRecognitionTest.lTvecs);
		Calib3d.calibrateCamera(FeatureRecognitionTest.objectPoints, FeatureRecognitionTest.rImagePoints, imageSize , FeatureRecognitionTest.rCameraMatrix, FeatureRecognitionTest.rDistCoeffs, FeatureRecognitionTest.rRvecs, FeatureRecognitionTest.rTvecs);
		System.out.println("Calibrated in " + (float)(System.currentTimeMillis() - startTime)/1000 + " seconds!");

		Mat rotationMatrix = new Mat();
		Mat translationMatrix = new Mat();
		Mat essentialMatrix = new Mat();
		Mat fundamentalMatrix = new Mat();
		Mat Q = new Mat();
		Mat R1 = new Mat();
		Mat R2 = new Mat();
		Mat P1 = new Mat();
		Mat P2 = new Mat();
		//Calib3d.stereoCalibrate(FeatureRecognitionTest.objectPoints, FeatureRecognitionTest.lImagePoints, FeatureRecognitionTest.rImagePoints, FeatureRecognitionTest.lCameraMatrix, FeatureRecognitionTest.lDistCoeffs, FeatureRecognitionTest.rCameraMatrix, FeatureRecognitionTest.rDistCoeffs, lSize, rotationMatrix, translationMatrix, essentialMatrix, fundamentalMatrix);

		if(save) {
			FeatureRecognitionTest.save(FeatureRecognitionTest.lCameraMatrix, FeatureRecognitionTest.rCameraMatrix, FeatureRecognitionTest.lDistCoeffs, FeatureRecognitionTest.rDistCoeffs, FeatureRecognitionTest.lRvecs, FeatureRecognitionTest.rRvecs, FeatureRecognitionTest.lTvecs, FeatureRecognitionTest.rTvecs);
		}



 * </Calibrates cameras individually>



 * <Take new photos>


		if(takePhotos) {
			lVc.read(lImg);
			rVc.read(rImg);
		}else {
			lImg = Imgcodecs.imread("calibration/img/calibration" + 49 + "_L.png");
			rImg = Imgcodecs.imread("calibration/img/calibration" + 49 + "_R.png");
		}

		lVc.release();
		rVc.release();


 * </Take new photos>



 * <Undistorts, stereo calibrates and rectifies images>

		//undistort image
		final Mat lImgUndistorted = new Mat();
		final Mat rImgUndistorted = new Mat();
		Imgproc.undistort(lImg, lImgUndistorted, FeatureRecognitionTest.lCameraMatrix, FeatureRecognitionTest.lDistCoeffs);
		Imgproc.undistort(rImg, rImgUndistorted, FeatureRecognitionTest.rCameraMatrix, FeatureRecognitionTest.rDistCoeffs);

		final Size s = new Size(lImg.height(), lImg.width() * 2);
		final Mat imgConcatUndistorted = new Mat(s, CvType.CV_32FC1);
		List<Mat> imgs = new ArrayList<>();
		imgs.add(lImgUndistorted);
		imgs.add(rImgUndistorted);
		Core.hconcat(imgs, imgConcatUndistorted);

		HighGui.imshow("Undistorted images", imgConcatUndistorted);
		HighGui.waitKey(5000);

		System.exit(0);

		//stereo calibration
		imageSize = new Size(lImg.width(), lImg.height());
		rotationMatrix = new Mat();
		translationMatrix = new Mat();
		essentialMatrix = new Mat();
		fundamentalMatrix = new Mat();
		final TermCriteria criteria = new TermCriteria(TermCriteria.EPS | TermCriteria.MAX_ITER, 100, 1e-5f);

		if(probeFlags) {
			FeatureRecognitionTest.probeFlags();
		}

		final int flags = 0
				| Calib3d.CALIB_FIX_INTRINSIC
				| Calib3d.CALIB_USE_INTRINSIC_GUESS
				| Calib3d.CALIB_FIX_PRINCIPAL_POINT
				| Calib3d.CALIB_FIX_FOCAL_LENGTH
				| Calib3d.CALIB_FIX_ASPECT_RATIO
				| Calib3d.CALIB_SAME_FOCAL_LENGTH
				| Calib3d.CALIB_ZERO_TANGENT_DIST
				| Calib3d.CALIB_FIX_K1
				| Calib3d.CALIB_FIX_K2
				| Calib3d.CALIB_FIX_K3
				| Calib3d.CALIB_FIX_K4
				| Calib3d.CALIB_FIX_K5
				| Calib3d.CALIB_FIX_K6
				| Calib3d.CALIB_RATIONAL_MODEL
				| Calib3d.CALIB_THIN_PRISM_MODEL
				| Calib3d.CALIB_FIX_S1_S2_S3_S4
				;

		startTime = System.currentTimeMillis();

		Calib3d.stereoCalibrate(FeatureRecognitionTest.objectPoints, FeatureRecognitionTest.lImagePoints, FeatureRecognitionTest.rImagePoints, FeatureRecognitionTest.lCameraMatrix, FeatureRecognitionTest.lDistCoeffs, FeatureRecognitionTest.rCameraMatrix, FeatureRecognitionTest.rDistCoeffs, imageSize, rotationMatrix, translationMatrix, essentialMatrix, fundamentalMatrix, flags, criteria);

		Q = new Mat();
		R1 = new Mat();
		R2 = new Mat();
		P1 = new Mat();
		P2 = new Mat();
		Calib3d.stereoRectify(FeatureRecognitionTest.lCameraMatrix, FeatureRecognitionTest.lDistCoeffs, FeatureRecognitionTest.rCameraMatrix, FeatureRecognitionTest.rDistCoeffs, imageSize, rotationMatrix, translationMatrix, R1, R2, P1, P2, Q);

		//		final Mat lMap1 = new Mat();
		//		final Mat lMap2 = new Mat();
		//		Imgproc.initUndistortRectifyMap(lCameraMatrix, lDistCoeffs, R1, P1, imageSize, CvType.CV_32FC1, lMap1, lMap2);
		final Mat rMap1 = new Mat();
		final Mat rMap2 = new Mat();
		Imgproc.initUndistortRectifyMap(FeatureRecognitionTest.rCameraMatrix, FeatureRecognitionTest.rDistCoeffs, R2, P2, imageSize, CvType.CV_32FC1, rMap1, rMap2);


		//		final Mat lImgRectified = new Mat();
		//		Imgproc.remap(lImgUndistorted, lImgRectified, lMap1, lMap2, Imgproc.INTER_LANCZOS4);
		final Mat rImgRectified = new Mat();
		Imgproc.remap(rImgUndistorted, rImgRectified, rMap1, rMap2, Imgproc.INTER_LANCZOS4);

		System.out.println("Stereo calibration, undistortion and rectification processed in " + ((float)(System.currentTimeMillis() - startTime)/1000));

		final Mat imgConcatRectified = new Mat(s, CvType.CV_8UC3);
		imgs = new ArrayList<>();
		imgs.add(lImgUndistorted);
		imgs.add(rImgUndistorted);
		Core.hconcat(imgs, imgConcatRectified);

		HighGui.imshow("Stereo calibration, undistort and rectification", imgConcatRectified);
		HighGui.waitKey(5000);


 * </Undistorts, calibrates and rectifies images>



 * <Detect features>

		final Mat lDescriptor = new Mat();
		final Mat rDescriptor = new Mat();
		final MatOfKeyPoint lKeypoint = new MatOfKeyPoint();
		final MatOfKeyPoint rKeypoint = new MatOfKeyPoint();

		final double hessianThreshold = 400;
		final int nOctaves = 4, nOctaveLayers = 3;
		final boolean extended = false, upright = false;
		final SURF surf = SURF.create(hessianThreshold, nOctaves, nOctaveLayers, extended, upright);
		surf.detectAndCompute(lImgUndistorted, new Mat(), lKeypoint, lDescriptor);
		surf.detectAndCompute(rImgRectified, new Mat(), rKeypoint, rDescriptor);

		final DescriptorMatcher dMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
		final List<MatOfDMatch> knnMatches = new ArrayList<>();

		dMatcher.knnMatch(lDescriptor, rDescriptor, knnMatches, 2);

		final float ratioThresh = 0.4f;
		final List<DMatch> listOfGoodMatches = new ArrayList<>();
		for (int i = 0; i < knnMatches.size(); i++) {
			if (knnMatches.get(i).rows() > 1) {
				final DMatch[] matches = knnMatches.get(i).toArray();
				if (matches[0].distance < ratioThresh * matches[1].distance) {
					listOfGoodMatches.add(matches[0]);
				}
			}
		}
		final MatOfDMatch goodMatches = new MatOfDMatch();
		goodMatches.fromList(listOfGoodMatches);
		//-- Draw matches
		final Mat imgMatches = new Mat();
		Features2d.drawMatches(lImgUndistorted, lKeypoint, rImgRectified, rKeypoint, goodMatches, imgMatches, Scalar.all(-1), Scalar.all(-1), new MatOfByte(), Features2d.NOT_DRAW_SINGLE_POINTS);
		//-- Show detected matches
		HighGui.imshow("Good Matches", imgMatches);
		HighGui.waitKey(5000);

		System.out.println("Press a key to compute disparity:");
		System.in.read();


 * </Detect features>



 * <Compute disparity>


		final StereoSGBM stereo = StereoSGBM.create(16, 5);
		stereo.setPreFilterCap(61);
		stereo.setMinDisparity(-10);
		stereo.setNumDisparities(16*5);
		stereo.setUniquenessRatio(5);
		stereo.setSpeckleWindowSize(0);
		stereo.setSpeckleRange(8);

		final Mat lGrayUndistorted = new Mat();
		final Mat rGrayUndistorted = new Mat();
		Imgproc.cvtColor(lImgUndistorted, lGrayUndistorted, Imgproc.COLOR_RGB2GRAY);
		Imgproc.cvtColor(rImgUndistorted, rGrayUndistorted, Imgproc.COLOR_RGB2GRAY);

		HighGui.imshow("lUndistorted", lGrayUndistorted);
		HighGui.imshow("rUndistorted", rGrayUndistorted);
		HighGui.waitKey(2000);

		final Mat disparity = new Mat();
		stereo.compute(lGrayUndistorted, rGrayUndistorted, disparity);
		final Mat normalizedDisparity = new Mat();
		Core.normalize(disparity, normalizedDisparity, 0, 255, Core.NORM_MINMAX, CvType.CV_8U);

		HighGui.imshow("Disparity", normalizedDisparity);
		HighGui.waitKey(0);


 * <Compute disparity>


		System.out.println("Press a key to project 3D image:");


		final Mat image3D = new Mat();
		final Mat normalizedImage3D = new Mat();
		Calib3d.reprojectImageTo3D(disparity, image3D, Q, false, CvType.CV_32F);
		Core.normalize(image3D, normalizedImage3D, 0, 255, Core.NORM_MINMAX, CvType.CV_8U);

		HighGui.imshow("Image 3D", normalizedImage3D);
		HighGui.waitKey(0);
	}

	private static boolean calibrate(final Mat lImg, final Mat rImg, final int i, final boolean save) {
		final Mat lGray = new Mat();
		final Mat rGray = new Mat();
		Imgproc.cvtColor(lImg, lGray, Imgproc.COLOR_RGB2GRAY);
		Imgproc.cvtColor(rImg, rGray, Imgproc.COLOR_RGB2GRAY);
		final MatOfPoint3f obj = new MatOfPoint3f();
		final MatOfPoint2f lImageCorners = new MatOfPoint2f();
		final MatOfPoint2f rImageCorners = new MatOfPoint2f();

		final boolean lFound = Calib3d.findChessboardCorners(lGray, FeatureRecognitionTest.BOARD_SIZE, lImageCorners, Calib3d.CALIB_CB_ADAPTIVE_THRESH + Calib3d.CALIB_CB_NORMALIZE_IMAGE + Calib3d.CALIB_CB_FAST_CHECK);
		final boolean rFound = Calib3d.findChessboardCorners(rGray, FeatureRecognitionTest.BOARD_SIZE, rImageCorners, Calib3d.CALIB_CB_ADAPTIVE_THRESH + Calib3d.CALIB_CB_NORMALIZE_IMAGE + Calib3d.CALIB_CB_FAST_CHECK);


		final boolean ret = lFound && rFound;
		if(lFound && rFound) {

			if(save) {
				Imgcodecs.imwrite("calibration/img/calibration" + i + "_L.png", lImg);
				Imgcodecs.imwrite("calibration/img/calibration" + i + "_R.png", rImg);
			}

			final TermCriteria term = new TermCriteria(TermCriteria.EPS | TermCriteria.MAX_ITER, 30, 0.1);
			Imgproc.cornerSubPix(lGray, lImageCorners, new Size(11, 11), new Size(-1, -1), term);
			Imgproc.cornerSubPix(rGray, rImageCorners, new Size(11, 11), new Size(-1, -1), term);

			final int numOfPoints = (int) (FeatureRecognitionTest.BOARD_SIZE.height * FeatureRecognitionTest.BOARD_SIZE.width);
			for (int j = 0; j < numOfPoints; j++) {
				obj.push_back(new MatOfPoint3f(new Point3(j / (int)FeatureRecognitionTest.BOARD_SIZE.width, j % (int)FeatureRecognitionTest.BOARD_SIZE.height, 0.0f)));
			}

			FeatureRecognitionTest.objectPoints.add(obj);
			FeatureRecognitionTest.lImagePoints.add(lImageCorners);
			FeatureRecognitionTest.rImagePoints.add(rImageCorners);

			System.out.println("Recognized " + i);
		}

		return ret;
	}

	private static void probeFlags() throws IOException {


 * <Calibrates cameras individually>

		final List<Mat> objectPoints = new ArrayList<>();
		final List<Mat> lImagePoints = new ArrayList<>();
		final List<Mat> rImagePoints = new ArrayList<>();

		Mat lImg = new Mat();
		Mat rImg = new Mat();

		Mat lCameraMatrix = new Mat(3, 3, CvType.CV_32FC1);
		Mat rCameraMatrix = new Mat(3, 3, CvType.CV_32FC1);
		Mat lDistCoeffs = new Mat();
		Mat rDistCoeffs = new Mat();
		new ArrayList<>();
		new ArrayList<>();
		new ArrayList<>();
		new ArrayList<>();


		for(int i = 0; i < 50;) {
			lImg = Imgcodecs.imread("calibration/img/calibration" + i + "_L.png");
			rImg = Imgcodecs.imread("calibration/img/calibration" + i + "_R.png");

			final boolean found = FeatureRecognitionTest.calibrate(lImg, rImg, i, false);

			if(found)
				++i;
		}

		final Map<String,String> parameters = FeatureRecognitionTest.load();
		lCameraMatrix = ImageUtils.matFromJson(parameters.get(ImageUtils.INTRINSIC_L));
		lDistCoeffs = ImageUtils.matFromJson(parameters.get(ImageUtils.DISTCOEFFS_L));
		rCameraMatrix = ImageUtils.matFromJson(parameters.get(ImageUtils.INTRINSIC_R));
		rDistCoeffs = ImageUtils.matFromJson(parameters.get(ImageUtils.DISTCOEFFS_L));


 * </Calibrates cameras individually>


		//undistort image
		final Mat lImgUndistorted = new Mat();
		final Mat rImgUndistorted = new Mat();
		Imgproc.undistort(lImg, lImgUndistorted, lCameraMatrix, lDistCoeffs);
		Imgproc.undistort(rImg, rImgUndistorted, rCameraMatrix, rDistCoeffs);


		for(int i = 0; i < 1 65537; ++i) {
			final int flags = 0
					| (i & 0x0001) & Calib3d.CALIB_FIX_INTRINSIC
					| (i & 0x0002) & Calib3d.CALIB_USE_INTRINSIC_GUESS
					| (i & 0x0004) & Calib3d.CALIB_FIX_PRINCIPAL_POINT
					| (i & 0x0008) & Calib3d.CALIB_FIX_FOCAL_LENGTH
					| (i & 0x0010) & Calib3d.CALIB_FIX_ASPECT_RATIO
					| (i & 0x0020) & Calib3d.CALIB_SAME_FOCAL_LENGTH
					| (i & 0x0040) & Calib3d.CALIB_ZERO_TANGENT_DIST
					| (i & 0x0080) & Calib3d.CALIB_FIX_K1
					| (i & 0x0100) & Calib3d.CALIB_FIX_K2
					| (i & 0x0200) & Calib3d.CALIB_FIX_K3
					| (i & 0x0400) & Calib3d.CALIB_FIX_K4
					| (i & 0x0800) & Calib3d.CALIB_FIX_K5
					| (i & 0x1000) & Calib3d.CALIB_FIX_K6
					| (i & 0x2000) & Calib3d.CALIB_RATIONAL_MODEL
					| (i & 0x4000) & Calib3d.CALIB_THIN_PRISM_MODEL
					| (i & 0x8000) & Calib3d.CALIB_FIX_S1_S2_S3_S4;

			//stereo calibration
			final Size imageSize = new Size(lImg.width(), lImg.height());
			final Mat rotationMatrix = new Mat();
			final Mat translationMatrix = new Mat();
			final Mat elementarMatrix = new Mat();
			final Mat fundamentalMatrix = new Mat();
			new TermCriteria(TermCriteria.EPS | TermCriteria.MAX_ITER, 30, 0.1);


			final long startTime = System.currentTimeMillis();

			Calib3d.stereoCalibrate(objectPoints, lImagePoints, rImagePoints, lCameraMatrix, lDistCoeffs, rCameraMatrix, rDistCoeffs, imageSize, rotationMatrix, translationMatrix, elementarMatrix, fundamentalMatrix, flags);

			final Mat QMatrix = new Mat();
			final Mat R1 = new Mat();
			final Mat R2 = new Mat();
			final Mat P1 = new Mat();
			final Mat P2 = new Mat();
			Calib3d.stereoRectify(lCameraMatrix, lDistCoeffs, rCameraMatrix, rDistCoeffs, imageSize, rotationMatrix, translationMatrix, R1, R2, P1, P2, QMatrix);

			//final Mat lMap1 = new Mat();
			//final Mat lMap2 = new Mat();
			//Imgproc.initUndistortRectifyMap(lCameraMatrix, lDistCoeffs, R2, P2, imageSize, CvType.CV_32FC1, lMap1, lMap2);
			final Mat rMap1 = new Mat();
			final Mat rMap2 = new Mat();
			Imgproc.initUndistortRectifyMap(rCameraMatrix, rDistCoeffs, R2, P2, imageSize, CvType.CV_32FC1, rMap1, rMap2);


			//		final Mat lImgRectified = new Mat();
			//		Imgproc.remap(lImgUndistorted, lImgRectified, lMap1, lMap2, Imgproc.INTER_LANCZOS4);
			final Mat rImgRectified = new Mat();
			Imgproc.remap(rImgUndistorted, rImgRectified, rMap1, rMap2, Imgproc.INTER_LANCZOS4);

			System.out.println("Operation " + i + " processed in " + ((float)(System.currentTimeMillis() - startTime)/1000));

			final Size s = new Size(imageSize.height, imageSize.width * 2);
			final Mat imgConcat = new Mat(s, CvType.CV_8UC3);
			final List<Mat> imgs = new ArrayList<>();
			imgs.add(lImgUndistorted);
			imgs.add(rImgRectified);
			Core.hconcat(imgs, imgConcat);
			Imgcodecs.imwrite("calibration/flags/test_" + i + ".png", imgConcat);

			final File f = new File("calibration/flags/test_" + i + ".txt");
			f.createNewFile();
			final FileOutputStream fos = new FileOutputStream(f);

			final StringBuilder sb = new StringBuilder();
			if((i & 0x0001) != 0) {sb.append("CALIB_FIX_INTRINSIC\r\n");};
			if((i & 0x0002) != 0) {sb.append("CALIB_USE_INTRINSIC_GUESS\r\n");};
			if((i & 0x0004) != 0) {sb.append("CALIB_FIX_PRINCIPAL_POINT\r\n");};
			if((i & 0x0008) != 0) {sb.append("CALIB_FIX_FOCAL_LENGTH\r\n");};
			if((i & 0x0010) != 0) {sb.append("CALIB_FIX_ASPECT_RATIO\r\n");};
			if((i & 0x0020) != 0) {sb.append("CALIB_SAME_FOCAL_LENGTH\r\n");};
			if((i & 0x0040) != 0) {sb.append("CALIB_ZERO_TANGENT_DIST\r\n");};
			if((i & 0x0080) != 0) {sb.append("CALIB_FIX_K1\r\n");};
			if((i & 0x0100) != 0) {sb.append("CALIB_FIX_K2\r\n");};
			if((i & 0x0200) != 0) {sb.append("CALIB_FIX_K3\r\n");};
			if((i & 0x0400) != 0) {sb.append("CALIB_FIX_K4\r\n");};
			if((i & 0x0800) != 0) {sb.append("CALIB_FIX_K5\r\n");};
			if((i & 0x1000) != 0) {sb.append("CALIB_FIX_K6\r\n");};
			if((i & 0x2000) != 0) {sb.append("CALIB_RATIONAL_MODEL\r\n");};
			if((i & 0x4000) != 0) {sb.append("CALIB_THIN_PRISM_MODEL\r\n");};
			if((i & 0x8000) != 0) {sb.append("CALIB_FIX_S1_S2_S3_S4\r\n");};
			fos.write(sb.toString().getBytes());
			fos.flush();
			fos.close();
		}

	}


	private static void save(final Mat lCameraMatrix, final Mat rCameraMatrix, final Mat lDistCoeffs, final Mat rDistCoeffs, final List<Mat> lrvecs2, final List<Mat> rrvecs2, final List<Mat> ltvecs2, final List<Mat> rtvecs2) throws IOException {
		final FileOutputStream fosLCameraMatrix = new FileOutputStream(ImageUtils.FILE_INTRINSIC_L);
		final FileOutputStream fosLDistCoeffs = new FileOutputStream(ImageUtils.FILE_DISTCOEFFS_L);
		final FileOutputStream fosRCameraMatrix = new FileOutputStream(ImageUtils.FILE_INTRINSIC_R);
		final FileOutputStream fosRDistCoeffs = new FileOutputStream(ImageUtils.FILE_DISTCOEFFS_R);

		fosLCameraMatrix.write(ImageUtils.matToJson(lCameraMatrix).getBytes());
		fosLDistCoeffs.write(ImageUtils.matToJson(lDistCoeffs).getBytes());
		fosRCameraMatrix.write(ImageUtils.matToJson(rCameraMatrix).getBytes());
		fosRDistCoeffs.write(ImageUtils.matToJson(rDistCoeffs).getBytes());

		fosLCameraMatrix.close();
		fosLDistCoeffs.close();
		fosRCameraMatrix.close();
		fosRDistCoeffs.close();
	}

	private static Map<String, String> load() throws IOException {
		final Map<String,String> ret = new HashMap<>();

		ret.put(ImageUtils.INTRINSIC_L,new String(Files.readAllBytes(Paths.get(ImageUtils.FILE_INTRINSIC_L))));
		ret.put(ImageUtils.DISTCOEFFS_L,new String(Files.readAllBytes(Paths.get(ImageUtils.FILE_DISTCOEFFS_L))));
		ret.put(ImageUtils.INTRINSIC_R,new String(Files.readAllBytes(Paths.get(ImageUtils.FILE_INTRINSIC_R))));
		ret.put(ImageUtils.DISTCOEFFS_R,new String(Files.readAllBytes(Paths.get(ImageUtils.FILE_DISTCOEFFS_R))));

		return ret;
	}

}


public Mat drawEpiLines(final Mat outImg) {

	final int epiLinesCount = epilinesSrc.rows();

	double a, b, c;

	for (int line = 0; line < epiLinesCount; line++) {
		a = epilinesSrc.get(line, 0)[0];
		b = epilinesSrc.get(line, 0)[1];
		c = epilinesSrc.get(line, 0)[2];

		final int x0 = 0;
		final int y0 = (int) (-(c + a * x0) / b);
		final int x1 = outImg.cols() / 2;
		final int y1 = (int) (-(c + a * x1) / b);

		final Point p1 = new Point(x0, y0);
		final Point p2 = new Point(x1, y1);
		final Scalar color = new Scalar(255, 255, 255);
		Core.line(outImg, p1, p2, color);

	}

	for (int line = 0; line < epiLinesCount; line++) {
		a = epilinesDst.get(line, 0)[0];
		b = epilinesDst.get(line, 0)[1];
		c = epilinesDst.get(line, 0)[2];

		final int x0 = outImg.cols() / 2;
		final int y0 = (int) (-(c + a * x0) / b);
		final int x1 = outImg.cols();
		final int y1 = (int) (-(c + a * x1) / b);

		final Point p1 = new Point(x0, y0);
		final Point p2 = new Point(x1, y1);
		final Scalar color = new Scalar(255, 255, 255);
		Core.line(outImg, p1, p2, color);

	}
	return outImg;
}

 */