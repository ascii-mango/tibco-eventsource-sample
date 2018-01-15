package com.eventsource;


import java.io.Serializable;

public final class MessageProcessor implements Serializable{
    public String getMessageBodyAsString(final Object message) {
        final MessageDataType messageDataType = (MessageDataType) message;
        return messageDataType.toString();
    }
}
