package com.kpkdev.youlocaltask.ui.networking.events;

import com.kpkdev.youlocaltask.ui.models.User;
import com.kpkdev.youlocaltask.ui.models.YouLocalError;

/**
 * Created by krasimir.karamazov on 1/7/2016.
 */
public class UserDetailsReadyEvent {
    private User mUser;

    private YouLocalError mError;

    public UserDetailsReadyEvent(User user, YouLocalError error) {
        mUser = user;
        mError = error;
    }

    public User getUser() {
        return mUser;
    }

    public YouLocalError getError() {
        return mError;
    }
}
