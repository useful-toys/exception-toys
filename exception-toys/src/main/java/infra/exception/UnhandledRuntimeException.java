package infra.exception;

import org.slf4j.Logger;


public final class UnhandledRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnhandledRuntimeException(Throwable cause) { super(cause); }
}
