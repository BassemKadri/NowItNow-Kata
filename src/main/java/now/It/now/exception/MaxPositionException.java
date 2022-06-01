package now.It.now.exception;

import lombok.Builder;

/**
 * Exception representing an invalid format for the max position.
 */
public class MaxPositionException extends RuntimeException {

  @Builder
  public MaxPositionException(final String message, Throwable cause) {
    super(message, cause);
  }
}
