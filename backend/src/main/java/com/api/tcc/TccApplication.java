package com.api.tcc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api.tcc.faceRecognition.Recognition.faceDetection;

@SpringBootApplication
@RestController
public class TccApplication {

	private static String[] args;
	public static void main(String[] args) {
		TccApplication.args = args;
		//new Thread(threadSpringApplication).start();
		//new Thread(threadFaceRecognition).start();

		try {
			SpringApplicationBuilder builder = new SpringApplicationBuilder(TccApplication.class);

			builder.headless(false);

			ConfigurableApplicationContext context = builder.run(args);
		} catch (Exception e) {
			System.out.println("threadSpringApplication - Error" + e);
		}

		faceDetection();


	}
/*
	private static final Runnable threadSpringApplication = () -> {
		try {
			SpringApplicationBuilder builder = new SpringApplicationBuilder(TccApplication.class);

			builder.headless(false);

			ConfigurableApplicationContext context = builder.run(args);
		} catch (Exception e) {
			System.out.println("threadSpringApplication - Error" + e);
		}
	};

	private static final Runnable threadFaceRecognition = () -> {
		try {
			OpenCVFrameConverter.ToMat converterMat = new OpenCVFrameConverter.ToMat();
			OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
			camera.start();

			CanvasFrame canvasFrame = new CanvasFrame("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());
			Frame capturedFrame = null;

			while ((capturedFrame = camera.grab()) != null) {
				if (canvasFrame.isVisible()) {
					canvasFrame.showImage(capturedFrame);
				}
			}

			canvasFrame.dispose();
			camera.stop();
		} catch (Exception e) {
			System.out.println("threadFaceRecognition - Error" + e);
		}
	};
*/
	@GetMapping("/")
	public String index(){
		return "hello world!";
	}
}
