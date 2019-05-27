package by.vasilenka.domain;

import by.vasilenka.repository.Identified;

import java.util.Objects;

public class ReleaseForm implements Identified<Integer> {
    private int id;
    private String description;

    public ReleaseForm(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public ReleaseForm() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReleaseForm that = (ReleaseForm) o;
        return id == that.id &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "ReleaseForm{" +
                "id=" + id +
                ", name='" + description + '\'' +
                '}';
    }
}
