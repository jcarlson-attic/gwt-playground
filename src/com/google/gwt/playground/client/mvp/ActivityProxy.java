package com.google.gwt.playground.client.mvp;

import java.util.LinkedList;
import java.util.Queue;

import com.google.gwt.user.client.Command;

public abstract class ActivityProxy implements Activity {

    private Activity impl;
    private Queue<Command> queue;
    private boolean asyncCalled;

    protected ActivityProxy(boolean eager) {
        this.queue = new LinkedList<Command>();
        if (eager) {
            ensureActivity();
        }
    }

    @Override
    public final void onCancel() {
        queue(new Command() {
            public void execute() {
                ActivityProxy.this.impl.onCancel();
            }
        });
    }

    @Override
    public final String mayStop() {
        return this.impl == null ? null : this.impl.mayStop();
    }

    @Override
    public final void start(final Display display) {
        queue(new Command() {
            public void execute() {
                ActivityProxy.this.impl.start(display);
            }
        });
    }

    @Override
    public final void onStop() {
        queue(new Command() {
            public void execute() {
                ActivityProxy.this.impl.onStop();
            }
        });
    }

    protected final void onAsyncFailure(Throwable reason) {
        // TODO
    }

    protected final void onAsyncSuccess(Activity impl) {
        this.impl = impl;

        while (!this.queue.isEmpty()) {
            this.queue.poll().execute();
        }
    }

    protected abstract void runAsync();

    void ensureActivity() {
        if (!this.asyncCalled) {
            this.asyncCalled = true;
            runAsync();
        }
    }

    void queue(Command cmd) {
        ensureActivity();
        if (this.impl != null) {
            cmd.execute();
        } else {
            this.queue.add(cmd);
        }
    }

}
