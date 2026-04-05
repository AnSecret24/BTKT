package tests.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    // Getter và Setter
    public String getName() { return name; }
    public String getJob() { return job; }
    public String getId() { return id; }
    public String getCreatedAt() { return createdAt; }
}