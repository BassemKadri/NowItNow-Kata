package exception;

import lombok.Builder;

/**
 * Exception representing an invalid format for the init position.
 */
public class InitialPositionException extends RuntimeException {

  @Builder
  public InitialPositionException(final String message, Throwable cause) {
    super(message, cause);
  }
}
