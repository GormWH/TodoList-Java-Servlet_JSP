package com.suhong.jdbcex;

public enum DatabaseIpAddress {
    IP_ADDRESS("192.168.1.19");

    String dbIpAddress;

    DatabaseIpAddress(String ip) {
        this.dbIpAddress = ip;
    }

    public String getDbIpAddress() {
        return this.dbIpAddress;
    }
}
