package com.jeremy.jarch;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityUtils {
    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             DiariesFragment fragment,
                                             @IdRes int id) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(id,fragment);
        transaction.commit();
    }
}
