package com.isa.platform.u201919295.monitoring.interfaces.acl;


import com.isa.platform.u201919295.monitoring.domain.model.commands.CreateSnapshotCommand;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotBySnapshotIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.SnapshotId;
import com.isa.platform.u201919295.monitoring.domain.services.SnapshotCommandService;
import com.isa.platform.u201919295.monitoring.domain.services.SnapshotQueryService;

//capa de anticorrupción para comunicarse con otros bounden context
public class SnapshotContextFacade {
    private final SnapshotCommandService snapshotCommandService;
    private final SnapshotQueryService snapshotQueryService;

    public SnapshotContextFacade(SnapshotCommandService snapshotCommandService, SnapshotQueryService snapshotQueryService) {
        this.snapshotCommandService = snapshotCommandService;
        this.snapshotQueryService = snapshotQueryService;
    }

    public Long createSnapshot(String snapshotId, String productSerialNumber, Double temperature, Double energy, Integer leakage){
        var createsnapshotCommand = new CreateSnapshotCommand(snapshotId,productSerialNumber,temperature,energy,leakage);
        return snapshotCommandService.handle(createsnapshotCommand);
    }

    public Long getSnapshotIdBySnapshotId(String snapshotId){
        var getSnapshotIdBySnapshotId = (new GetSnapshotBySnapshotIdQuery(new SnapshotId(snapshotId)));
        var snapshot = snapshotQueryService.handle(getSnapshotIdBySnapshotId);
        if(snapshot.isEmpty())return 0L;
        return snapshot.get().getId();
    }
}
