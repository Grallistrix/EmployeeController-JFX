module com.example.zad3 {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.example.zad3 to javafx.fxml;
    exports com.example.zad3;

}