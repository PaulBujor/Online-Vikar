package dk.grouptwo.viewmodel;

import dk.grouptwo.viewmodel.employer.CreateEmployerAccountViewModel;
import dk.grouptwo.viewmodel.employer.EmployerProfileViewModel;
import dk.grouptwo.viewmodel.employer.EmployerWorkHistoryViewModel;
import dk.grouptwo.viewmodel.employer.WorkViewModel;
import dk.grouptwo.viewmodel.worker.*;

public class ViewModelFactory {
    //worker view models
    private CreateWorkerAccountViewModel createWorkerAccountViewModel;
    private FindWorkViewModel findWorkViewModel;
    private UpcomingWorkViewModel upcomingWorkViewModel;
    private WorkerProfileViewModel workerProfileViewModel;
    private WorkerWorkHistoryViewModel workerWorkHistoryViewModel;

    //employer view models
    private CreateEmployerAccountViewModel createEmployerAccountViewModel;
    private EmployerProfileViewModel employerProfileViewModel;
    private EmployerWorkHistoryViewModel employerWorkHistoryViewModel;
    private WorkViewModel workViewModel;

    public ViewModelFactory(Model model) {
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
}
