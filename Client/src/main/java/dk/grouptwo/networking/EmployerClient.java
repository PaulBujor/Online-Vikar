package dk.grouptwo.networking;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.networking.remote.RemoteEmployerClient;

public class EmployerClient implements RemoteEmployerClient {
    private ModelManager model;

    public EmployerClient(ModelManager model) {
        this.model = model;
    }
}
