package org.uab.dedam.todoman;

import java.util.List;

public interface HomeView {
    
    void setTasks(List<task> tasks);

    void showMessage(String message);

}
