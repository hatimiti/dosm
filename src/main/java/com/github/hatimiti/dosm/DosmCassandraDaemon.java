package com.github.hatimiti.dosm;

//import org.apache.cassandra.service.CassandraDaemon;

import java.io.File;

public class DosmCassandraDaemon /*extends CassandraDaemon*/ {

    public void init() {
        var dir = new File("/Users/m-kakimi/cassandra-data");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        System.setProperty("cassandra.config", "cassandra-embed.yml");
        System.setProperty("cassandra-foreground", "true");
        System.setProperty("cassandra.storagedir", dir.getAbsolutePath());
    }

}
