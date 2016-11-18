package org.uab.dedam.todoman;

import java.util.List;

public interface TaskView {
    
    void clearForm();

void fillForm(task myTask);

boolean validityForm();

void closeForm();

    void showMessage(String message);

}
