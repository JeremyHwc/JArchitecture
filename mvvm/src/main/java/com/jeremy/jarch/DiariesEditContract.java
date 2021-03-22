package com.jeremy.jarch;

public interface DiariesEditContract {
    interface View extends BaseView<DiariesEditContract.Presenter>{

        boolean isActive();

        void showError();

        void setTitle(String title);

        void setDescription(String description);

        void showDiariesList();
    }

    interface Presenter extends BasePresenter{

        void saveDiary(String title, String description);
    }
}
