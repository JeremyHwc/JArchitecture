package com.jeremy.jarch;

import java.util.List;

public interface DataCallback<T> {
     void onError();

     void onSuccess(T data) ;
}
