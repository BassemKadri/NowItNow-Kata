package exception;

import lombok.Builder;

/**
 * Exception representing an invalid format for the steps.
 */
public class StepException extends RuntimeException {

  @Builder
  public StepException(final String message, Throwable cause) {
    super(message, cause);
  }
}
