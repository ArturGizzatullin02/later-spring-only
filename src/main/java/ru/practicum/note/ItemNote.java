package ru.practicum.note;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.item.Item;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "item_notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ItemNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    // исключаем все поля с отложенной загрузкой из
    // метода toString, чтобы не было случайных обращений
    // базе данных, например при выводе в лог.
    @ToString.Exclude
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "note_date")
    private Instant dateOfNote = Instant.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemNote itemNote = (ItemNote) o;
        return Objects.equals(id, itemNote.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
