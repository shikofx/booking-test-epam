package by.booking.pkt.utils;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Date;

public class ScreenVideoListener implements IInvokedMethodListener {
   private VideoMaker screenwriter;
  private Thread videoThread;
  private String videoPath;
  private String fileName;


  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    videoPath = "target\\"+testResult.getTestContext().getOutputDirectory() + "\\logs\\" + method.getTestMethod().getMethodName() + "\\";
    fileName = videoPath + method.getTestMethod().getMethodName() + new Date().getTime() + ".mp4";
    if (method.isTestMethod()) {
      new File(videoPath).mkdirs();
      File videoFile = new File(fileName);
       screenwriter = new VideoMaker(videoFile);
       videoThread = new Thread(() -> screenwriter.createVideoFromScreens());
      videoThread.start();
    }
  }

  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
     if (method.isTestMethod() && screenwriter != null) {
      try {
        Thread.sleep(2000);
         screenwriter.setPleaseStop();
         while (!screenwriter.isStoppedCreation()) {
          Thread.sleep(500);
        }
        videoThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
        screenwriter = null;
      videoThread = null;
    }
  }
}
