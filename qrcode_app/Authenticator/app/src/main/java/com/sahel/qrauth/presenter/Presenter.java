package com.sahel.qrauth.presenter;


import com.sahel.qrauth.data.DataProcessController;
import com.sahel.qrauth.view.View;

/**
 * The class Presenter
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public interface Presenter<T extends View> {

    DataProcessController dataProcessController = DataProcessController.getDataProcessController();
    void onAttachView(T view);
    void onDeAttachView();
}
