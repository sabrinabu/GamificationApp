package com.tonikamitv.loginregister;

import android.content.Context;

/**
 * Created by sabrina on 2/7/2016.
 */
public class DbPreRetrieve {

    Context ctx;
    DbPreRetrieve(Context ctx){this.ctx=ctx;}

    public void RetrieveDataEarlier()
    {
        DbEventRetrieve retrieveDb = new DbEventRetrieve(this.ctx);
        retrieveDb.execute();
    }
}
