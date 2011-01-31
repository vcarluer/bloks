package gamers.associate.framework;

import static java.lang.Math.PI;
import java.util.Random;


/**
 * Utility class that provide methods for normalizing angles.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */

public class Util {
  private final static double TWO_PI = 2 * PI;
  /**
   * Normalizes an angle to a relative angle.
   * The normalized angle will be in the range from -PI to PI, where PI
   * itself is not included.
   *
   * @param angle the angle to normalize
   * @return the normalized angle that will be in the range of [-PI,PI[
   */
  public static double normalRelativeAngle(double angle) {
    return (angle %= TWO_PI) >= 0 ? (angle < PI) ? angle : angle - TWO_PI : (angle >= -PI) ? angle : angle + TWO_PI;
  }
}
