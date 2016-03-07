package com.tonikamitv.loginregister;

interface GetUserImageCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(Image returnedUser);
}
