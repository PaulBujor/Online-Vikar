module dk.grouptwo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
  requires java.rmi;
  //requires com.google.gson;

    opens dk.grouptwo to javafx.fxml;
    opens dk.grouptwo.view to javafx.fxml;
    opens dk.grouptwo.view.worker to javafx.fxml;
    opens dk.grouptwo.view.employer to javafx.fxml;
    exports dk.grouptwo;
    exports dk.grouptwo.view.worker to javafx.fxml;
    exports dk.grouptwo.view.employer to javafx.fxml;
}