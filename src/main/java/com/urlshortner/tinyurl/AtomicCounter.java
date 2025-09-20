package com.urlshortner.tinyurl;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter {
    private final AtomicLong counter;
    private final long nodeId;

    private final static long NODE_ID_BITS = 10L; // take 10 bits for nodeId
    private final static long SEQUENCE_BITS = 12L; // take 12 bits for sequence

    private final static long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1; // max node id 1023
    private final static long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1; // max sequence 4095

    private long lastTimestamp = -1L;
    private long sequence = 0L;


    public AtomicCounter(long initialValue, long nodeId) {
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException("NodeId must be between 0 and " + MAX_NODE_ID);
        }
        this.counter = new AtomicLong(initialValue);
        this.nodeId = nodeId;
    }

    //this nextID is also can be used as it's simple and usefull

    // public long nextId() {
    //     return counter.getAndIncrement();
    // }

    // We are using the nextId generate algorithm similar to snowflake id generation
    // which is based on timestamp, nodeId and sequence number to generate unique ids
    // This will ensure that ids are unique across multiple nodes and also in the same node
    // even if multiple ids are generated in the same millisecond
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // sequence overflow -> wait for next millisecond
                while ((timestamp = System.currentTimeMillis()) <= lastTimestamp) {}
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        // build ID: [timestamp | nodeId | sequence]
        return (timestamp << (NODE_ID_BITS + SEQUENCE_BITS)) |
               (nodeId << SEQUENCE_BITS) |
               sequence;
    }

     public long current() {
        return counter.get();
    }
}
