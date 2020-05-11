package dk.grouptwo.viewmodel;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.viewmodel.employer.*;
import dk.grouptwo.viewmodel.worker.*;

public class ViewModelFactory {
    //worker view models
    private CreateWorkerAccountViewModel createWorkerAccountViewModel;
    private FindWorkViewModel findWorkViewModel;
    private UpcomingWorkViewModel upcomingWorkViewModel;
    private WorkerProfileViewModel workerProfileViewModel;
    private WorkerWorkHistoryViewModel workerWorkHistoryViewModel;
    private SignInWorkerViewModel signInWorkerViewModel;
    private WorkOfferViewModel workOfferViewModel;

    //employer view models
    private CreateEmployerAccountViewModel createEmployerAccountViewModel;
    private EmployerProfileViewModel employerProfileViewModel;
    private EmployerWorkHistoryViewModel employerWorkHistoryViewModel;
    private WorkViewModel workViewModel;
    private SignInEmployerViewModel signInEmployerViewModel;

    public ViewModelFactory(ModelManager model) {
        //worker
        signInWorkerViewModel = new SignInWorkerViewModel(model);
        createWorkerAccountViewModel = new CreateWorkerAccountViewModel(model);

        //employer
        signInEmployerViewModel = new SignInEmployerViewModel(model);
        createEmployerAccountViewModel = new CreateEmployerAccountViewModel(model);
    }

    public CreateWorkerAccountViewModel getCreateWorkerAccountViewModel() {
        return createWorkerAccountViewModel;
    }

    public FindWorkViewModel getFindWorkViewModel() {
        return findWorkViewModel;
    }

    public UpcomingWorkViewModel getUpcomingWorkViewModel() {
        return upcomingWorkViewModel;
    }

    public WorkerProfileViewModel getWorkerProfileViewModel() {
        return workerProfileViewModel;
    }

    public WorkerWorkHistoryViewModel getWorkerWorkHistoryViewModel() {
        return workerWorkHistoryViewModel;
    }

    public CreateEmployerAccountViewModel getCreateEmployerAccountViewModel() {
        return createEmployerAccountViewModel;
    }

    public EmployerProfileViewModel getEmployerProfileViewModel() {
        return employerProfileViewModel;
    }

    public EmployerWorkHistoryViewModel getEmployerWorkHistoryViewModel() {
        return employerWorkHistoryViewModel;
    }

    public WorkViewModel getWorkViewModel() {
        return workViewModel;
    }

    public SignInWorkerViewModel getSignInWorkerViewModel() {
        return signInWorkerViewModel;
    }

    public SignInEmployerViewModel getSignInEmployerViewModel() {
        return signInEmployerViewModel;
    }

    public WorkOfferViewModel getWorkOfferViewModel() {
        return workOfferViewModel;
    }
}
