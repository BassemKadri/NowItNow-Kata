package now.It.now.exception;

import lombok.Builder;

/**
 * Exception representing an invalid file content.
 */
public class FileContentException extends RuntimeException {

  @Builder
  public FileContentException(final String message, Throwable cause) {
    super(message, cause);
  }
}
