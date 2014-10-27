package infra.exception;


public final class UnhandledRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnhandledRuntimeException(Throwable cause) { super(cause); }
}
