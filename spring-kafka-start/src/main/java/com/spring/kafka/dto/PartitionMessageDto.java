package com.spring.kafka.dto;

public class PartitionMessageDto {
    private String message;
    private int partition;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }
}
