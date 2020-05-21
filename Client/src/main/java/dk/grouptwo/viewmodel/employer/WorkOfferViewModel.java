package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.EmployerModel;
import dk.grouptwo.model.WorkerModel;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.utility.WorkersTableData;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class WorkOfferViewModel {
    private EmployerModel model;
    private StringProperty title;
    private DoubleProperty salary;
    private IntegerProperty startHour;
    private IntegerProperty startMinutes;
    private ObjectProperty<LocalDate> startDate;
    private IntegerProperty endHour;
    private IntegerProperty endMinutes;
    private ObjectProperty<LocalDate> endDate;
    private StringProperty country;
    private StringProperty postCode;
    private StringProperty city;
    private StringProperty street;
    private StringProperty description;
    private IntegerProperty workersNeeded;

    private ObservableList<WorkersTableData> list;
    private StringProperty workerDateOfBirth;
    private StringProperty workerGender;
    private StringProperty workerLanguages;
    private StringProperty workerDescription;
    private StringProperty workerLicenses;

    private StringProperty error;

    private Job job;
    private WorkTableData data;

    public WorkOfferViewModel(EmployerModel model) {
        this.model = model;
        title = new SimpleStringProperty("");
        salary = new SimpleDoubleProperty(0);
        startHour = new SimpleIntegerProperty(0);
        startMinutes = new SimpleIntegerProperty(0);
        startDate = new SimpleObjectProperty<>(null);
        endHour = new SimpleIntegerProperty(0);
        endMinutes = new SimpleIntegerProperty(0);
        endDate = new SimpleObjectProperty<>(null);
        country = new SimpleStringProperty("");
        city = new SimpleStringProperty("");
        street = new SimpleStringProperty("");
        postCode = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        workersNeeded = new SimpleIntegerProperty(0);

        workerDateOfBirth = new SimpleStringProperty("");
        workerGender = new SimpleStringProperty("");
        workerLanguages = new SimpleStringProperty("");
        workerDescription = new SimpleStringProperty("");
        workerLicenses = new SimpleStringProperty("");
        list = FXCollections.observableArrayList();

        error = new SimpleStringProperty("");
    }

    public boolean validData() {
        //todo
        return true;
    }

    public boolean save() {
        try {
//            job.setJobID(data.getJobId());
            job.setJobTitle(title.get());
            job.setSalary(salary.get());
            job.setShiftStart(LocalDateTime.of(startDate.get(), LocalTime.of(startHour.get(), startMinutes.get())));
            job.setShiftEnd(LocalDateTime.of(endDate.get(), LocalTime.of(endHour.get(), endMinutes.get())));
            job.setLocation(new Address(country.get(), city.get(), street.get(), postCode.get()));
            job.setDescription(description.get());
            job.setWorkersNeeded(workersNeeded.get());
            job.setSelectedWorkers(getSelectedWorkers());
            if (job.getSelectedWorkers().size() > workersNeeded.get())
                throw new Exception("too many workers selected");
            model.updateWorkOffer(job);
            data.update(job); //updates the table data system-wide todo needs tested, probably doesn't work
            return true;
        } catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
    }

    public ArrayList<Worker> getSelectedWorkers() {
        ArrayList<Worker> selectedWorkers = new ArrayList<Worker>();
        for (WorkersTableData data : list) {
            if (data.selectedForWorkProperty().get())
                selectedWorkers.add(model.getWorkerByJob(this.data.getJobId(), data.CPRProperty().get()));
        }
        return selectedWorkers;
    }

    public void reset(WorkTableData data) {
        title.set(data.jobTitleProperty().get());
        salary.set(data.salaryProperty().get());
        startHour.set(data.startTimeProperty().get().getHour());
        startMinutes.set(data.startTimeProperty().get().getMinute());
        startDate.set(data.startTimeProperty().get().toLocalDate());
        endHour.set(data.endTimeProperty().get().getHour());
        endMinutes.set(data.endTimeProperty().get().getMinute());
        endDate.set(data.endTimeProperty().get().toLocalDate());
        country.set(data.getAddress().getCountry());
        city.set(data.getAddress().getCity());
        postCode.set(data.getAddress().getZip());
        street.set(data.getAddress().getStreet());
        description.set(data.getDescription());
        workersNeeded.set(data.numberOfWorkersProperty().get());
        error.set("");
        job = model.getJobById(data.getJobId());
        this.data = data;
        createList();
    }

    public ObservableList<WorkersTableData> createList() {
        list.clear();
        ArrayList<Worker> workers = job.getApplicants();
        for (Worker worker : workers) {
            WorkersTableData data = new WorkersTableData(worker);
            if (job.getSelectedWorkers().contains(worker))
                data.selectedForWorkProperty().setValue(true);
            list.add(data);
        }
        return list;
    }

    public void selectWorker(WorkersTableData workersTableData) {
        Worker worker = model.getWorkerByJob(job.getJobID(), workersTableData.CPRProperty().get());
        try {
            workerDateOfBirth.set(worker.getBirthday().toString());
            workerGender.set(worker.getGender());
            workerLanguages.set(worker.getLanguages());
            workerDescription.set(worker.getDescription());
            workerLicenses.set(worker.getLicenses().toString());
        } catch (NullPointerException e) {
            //
        }

    }

    public StringProperty titleProperty() {
        return title;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public IntegerProperty startHourProperty() {
        return startHour;
    }

    public IntegerProperty startMinutesProperty() {
        return startMinutes;
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public IntegerProperty endHourProperty() {
        return endHour;
    }

    public IntegerProperty endMinutesProperty() {
        return endMinutes;
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty postCodeProperty() {
        return postCode;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public IntegerProperty workersNeededProperty() {
        return workersNeeded;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public ObservableList<WorkersTableData> getList() {
        return list;
    }

    public StringProperty workerDateOfBirthProperty() {
        return workerDateOfBirth;
    }

    public StringProperty workerGenderProperty() {
        return workerGender;
    }

    public StringProperty workerLanguagesProperty() {
        return workerLanguages;
    }

    public StringProperty workerDescriptionProperty() {
        return workerDescription;
    }

    public StringProperty workerLicensesProperty() {
        return workerLicenses;
    }
}
