package com.dga.equiz.model.loading;

import com.dga.equiz.model.event.IEvent;
import javafx.concurrent.Task;

public class LoadingTask extends Task<Void> {

    private final IEvent onCall;

    public LoadingTask(IEvent event) {
        this.onCall = event;
    }

    @Override
    protected Void call() throws Exception {
        if (onCall != null) {
            onCall.handle();
        }
        return null;
    }
}
