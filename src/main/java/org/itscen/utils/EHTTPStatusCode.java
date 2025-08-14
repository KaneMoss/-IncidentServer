package org.itscen.utils;

public enum EHTTPStatusCode
{
    METHOD_NOT_ALLOWED(405),
    NOT_FOUND(404),
    OK(200);


    private int mCode;

    EHTTPStatusCode(int codeNum) {
        mCode = codeNum;
    }

    public int getCode() {
        return mCode;
    }
}

