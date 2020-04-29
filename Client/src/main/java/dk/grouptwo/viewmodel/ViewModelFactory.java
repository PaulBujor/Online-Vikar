package dk.grouptwo.viewmodel;

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

    //employer view models
    private CreateEmployerAccountViewModel createEmployerAccountViewModel;
    private EmployerProfileViewModel employerProfileViewModel;
    private EmployerWorkHistoryViewModel employerWorkHistoryViewModel;
    private WorkViewModel workViewModel;
    private SignInEmployerViewModel signInEmployerViewModel;

    public ViewModelFactory() {
        //todo construct new viewmodels
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
}
