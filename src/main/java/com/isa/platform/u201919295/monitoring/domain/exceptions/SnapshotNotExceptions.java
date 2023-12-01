package com.isa.platform.u201919295.monitoring.domain.exceptions;

public class SnapshotNotExceptions extends RuntimeException{
     public SnapshotNotExceptions(){
        super("Snapshots not found");
    }
}
