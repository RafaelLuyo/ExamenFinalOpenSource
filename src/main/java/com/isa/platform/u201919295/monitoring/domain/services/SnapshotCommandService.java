package com.isa.platform.u201919295.monitoring.domain.services;


import com.isa.platform.u201919295.monitoring.domain.model.commands.CreateSnapshotCommand;

public interface SnapshotCommandService {
    Long handle(CreateSnapshotCommand command);
}
