package code.challenge.springreactdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GroceryItem {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String description;

    @ManyToOne(cascade=CascadeType.MERGE , fetch = FetchType.EAGER)
    private Category category;
}