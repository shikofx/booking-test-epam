package by.booking.pkt.utils;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.LoggerContext;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

//import org.apache.log4j.BasicConfigurator;

public class VideoMaker {


  private static final long FRAME_RATE = 25;
   private static final Dimension SCREEN_BOUNDS = Toolkit.getDefaultToolkit()
          .getScreenSize();

  private File outputFile;
  private AtomicBoolean pleaseStop = new AtomicBoolean(false);
  private boolean stoppedCreation = true;

  public VideoMaker() {

  }

   VideoMaker(File outputFile) {
//    BasicConfigurator.configure();
     BasicConfigurator basicConfigurator = new BasicConfigurator();
     basicConfigurator.configure(new LoggerContext());
    this.outputFile = outputFile;
  }

   private static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
      BufferedImage image;

    if (sourceImage.getType() == targetType) {
      image = sourceImage;
    } else {
      image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), targetType);
      image.getGraphics().drawImage(sourceImage, 0, 0, null);
    }

    return image;
  }

   void createVideoFromScreens() {
    IMediaWriter writer = null;
    try {
      if (getOutputFile() == null) {
        throw new IllegalStateException(
                "Output video file cannot be null");
      }

      setStoppedCreation(false);

      writer = ToolFactory.makeWriter(getOutputFile()
              .getAbsolutePath());
       writer.addVideoStream(0, 0, SCREEN_BOUNDS.width,
               SCREEN_BOUNDS.height);

      long startTime = System.nanoTime();

      while (!getPleaseStop()) {
         BufferedImage screen = getDesktopScreenshots();
        BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);

        writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

        try {
           Thread.sleep(1000 / FRAME_RATE);
        } catch (InterruptedException e) {
          // Ignore
        }
      }
      setStoppedCreation(true);
    } finally {
      if (writer != null) {
        writer.flush();
        writer.close();
      }

    }
  }

   private BufferedImage getDesktopScreenshots() {
    try {
      Robot robot = new Robot();
       Rectangle captureSize = new Rectangle(SCREEN_BOUNDS);
      return robot.createScreenCapture(captureSize);
    } catch (AWTException e) {
      throw new RuntimeException(
              "Error occurred while getting desktop screenshots", e);
    }
  }

   private File getOutputFile() {
    return outputFile;
  }

  public void setOutputFile(File outputFile) {
    this.outputFile = outputFile;
  }

   private boolean getPleaseStop() {
    return pleaseStop.get();
  }

   void setPleaseStop() {
      this.pleaseStop.set(true);
  }

   boolean isStoppedCreation() {
    return stoppedCreation;
  }

  private void setStoppedCreation(boolean stoppedCreation) {
    this.stoppedCreation = stoppedCreation;
  }
}

