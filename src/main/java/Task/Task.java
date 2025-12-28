package Task;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Task {
    private String id;
    private String description;
    private TaskStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd") // annotation to make sure when task obj --> json object it follows this format
    private LocalDate dateCreated;
    private LocalDate dateUpdated;

    public Task(String id, String description, TaskStatus status) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.dateCreated = LocalDate.now();
        this.dateUpdated = null;
    }
    public Task(Task other) {
        this.id = other.getId();
        this.description = other.getDescription();
        this.status = other.getStatus();
        this.setDateCreated(other.getDateCreated());
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDate getDateCreated() {
        return this.dateCreated;
    }

    public LocalDate getDateUpdated() {
        return this.dateUpdated;
    }

    public void setId(String id) {
        // call task service to validate the ID
        this.id = id;
    }
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setDateUpdated(LocalDate updatedAt) {
        dateUpdated = updatedAt;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        boolean statusComparison = this.status.compareTo(task.getStatus()) == 0;
        return Objects.equals(description, task.description) && statusComparison;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", description = '" + description + '\'' +
                ", status = '" + this.getStatus().toString().toLowerCase().replace("_", " ") + '\'' +
                ", created at = '" + dateCreated + '\'' +
                ", updated at = '" + dateUpdated + "'}";
    }
}