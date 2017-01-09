/**
 * 
 */
package com.gluonapplication.views;

import com.gluonhq.charm.glisten.application.MobileApplication;

/**
 * @author yotti
 *
 */
public abstract class GluonPresenter<T extends MobileApplication> {
	 
    @SuppressWarnings("unchecked")
	private final T app = (T) MobileApplication.getInstance();
 
    protected T getApp() {
        return app;
    }
 
}