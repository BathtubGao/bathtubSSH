package com.bathtub.core.exception;

public class AopException extends Exception
{
	private static final long serialVersionUID = 1L;

	public AopException(String error)
	{
		super(error);
	}

	public AopException(String error, Throwable cause)
	{
		super(error, cause);
	}
}
