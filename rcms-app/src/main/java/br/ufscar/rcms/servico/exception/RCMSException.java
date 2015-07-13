package br.ufscar.rcms.servico.exception;

@SuppressWarnings("serial")
public class RCMSException extends Exception {

    public RCMSException(String message) {
        super(message);
    }

    public RCMSException(String message, Throwable throwable) {
        super(message, throwable);
    }
}