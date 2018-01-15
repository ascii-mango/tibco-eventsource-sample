package com.eventsource;



import java.io.Serializable;

public final class MessageDataType implements Serializable {
    private final String process;
    private final int value;

    public MessageDataType(String process, int value) {
        this.process = process;
        this.value = value;
    }

    @Override
    public String toString() {
        return process + ":" +  value;
    }
}
