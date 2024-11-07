package ru.practicum.note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemNoteDto {

    private Long id;
    private Long itemId;
    private String text;
    private String dateOfNote;
    private String itemUrl;
}
