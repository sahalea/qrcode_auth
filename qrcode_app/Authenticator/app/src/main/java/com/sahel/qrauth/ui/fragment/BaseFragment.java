package com.sahel.qrauth.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sahel.qrauth.data.DataProcessController;
import com.sahel.qrauth.ui.fragment.stack.BaseKey;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The class Base fragment created to be extended by every fragment in this application.
 * This class provides dependency injection configuration, ButterKnife Android
 * library configuration and some methods common to every fragment.
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public abstract class BaseFragment extends Fragment {

    /**
     * The fragment load Callback.
     */
    protected FragmentLoadListener callback = null;

    /**
     * The Data process controller instance.
     */
    protected final DataProcessController dataProcessController = DataProcessController
            .getDataProcessController();
    /**
     * The butter knife Un binder.
     */
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.callback = (FragmentLoadListener) context;
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.getClass().getName()
                    + " must implement FragmentLoadListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        onBeforeView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startPostponedEnterTransition();
        callback.onFragmentLoaded(this);
        injectViews(view);
        initializeUi();
        setEventListeners();
        doTask();
    }


    @Override
    public void onDestroyView() {
        destroyViews();
        super.onDestroyView();
    }

    public final <T extends BaseKey> T getKey() {
        return getArguments() != null ? getArguments().<T>getParcelable("KEY") : null;
    }

    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView
     * with the proper value.
     *
     * @param view to extract each widget injected in the fragment.
     */
    private void injectViews(final View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    /**
     * Destroy views.
     */
    private void destroyViews() {
        unbinder.unbind();
    }


    /**
     * Every fragment has to inflate a layout in the onCreateView method. We
     * have added this method to avoid duplicate all the inflate code in every
     * fragment. You only have to return the layout to inflate in this method
     * when extends BaseFragment.
     *
     * @return the fragment layout
     */
    protected abstract int getFragmentLayout();

    /**
     * Initialize ui.
     */
    protected abstract void initializeUi();

    /**
     * Sets event listeners.
     */
    protected abstract void setEventListeners();

    protected abstract void doTask();

    protected void onBeforeView(View view) {
    }
}
