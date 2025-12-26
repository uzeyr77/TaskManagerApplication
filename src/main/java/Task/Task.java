package Task;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Task {
    private String id;
    private String description;
    private TaskStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd") // annotation to make sure when task obj --> json object it follows this format
    private LocalDate createdAt;
    static int count = 0;
    private LocalDate updateDate;

    public Task(String description, TaskStatus status) {
        this.id = TaskService.setTaskId();
        this.status = status;
        this.description = description;
        this.createdAt = LocalDate.now();
        this.updateDate = LocalDate.now();
    }
    public Task(Task other) {
        this.id = other.getId();
        this.description = other.getDescription();
        this.status = TaskStatus.valueOf(other.getStatus().toString().toUpperCase());
        this.setCreatedAt(other.getCreatedAt());
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

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt() {
        return this.updateDate.toString();
    }
    public void setUpdatedAt(LocalDate updatedAt) {
        //this.updatedAt = updatedAt;
        updateDate = updatedAt;
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
        return Objects.hash(id, description, status);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + this.getStatus().toString().toLowerCase().replace("_", " ") + '\'' +
                ", created at= '" + createdAt + '\'' +
                ", updated at= '" + updateDate + "'";
    }
}