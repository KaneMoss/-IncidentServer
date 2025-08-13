package org.itscen.utils;

public enum EHTTPStatusCode
{
    OK(200);


    private int mCode;

    EHTTPStatusCode(int codeNum) {
        mCode = codeNum;
    }

    public int getCode() {
        return mCode;
    }
}

