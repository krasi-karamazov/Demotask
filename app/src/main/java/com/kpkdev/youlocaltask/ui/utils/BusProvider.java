package com.kpkdev.youlocaltask.ui.utils;

import com.squareup.otto.Bus;

/**
 * Created by krasimir.karamazov on 7/31/2014.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

}

