package com.github.hatimiti.dosm;

//import org.apache.cassandra.service.CassandraDaemon;

import lombok.val;

import java.io.File;

public class DosmCassandraDaemon /*extends CassandraDaemon*/ {

    public void init() {
        val dir = new File("/Users/m-kakimi/cassandra-data");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        System.setProperty("cassandra.config", "cassandra-embed.yml");
        System.setProperty("cassandra-foreground", "true");
        System.setProperty("cassandra.storagedir", dir.getAbsolutePath());
    }

}
