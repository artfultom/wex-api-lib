package com.github.artfultom.wexapi.exception;

public class NonceInvalidException extends RuntimeException {

    public NonceInvalidException(int nonce) {
        super("Invalid nonce parameter: " + nonce + "!");
    }
}
