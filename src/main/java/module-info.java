module com.mycompany.javaquiz {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.javaquiz to javafx.fxml;
    exports com.mycompany.javaquiz;
}
