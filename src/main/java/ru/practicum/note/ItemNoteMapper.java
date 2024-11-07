package ru.practicum.note;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.item.Item;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemNoteMapper {

    public static ItemNote toItemNote(ItemNoteDto itemNoteDto, Item item) {
        return ItemNote.builder()
                .id(itemNoteDto.getId())
                .text(itemNoteDto.getText())
                .item(item)
                .dateOfNote(Instant.parse(itemNoteDto.getDateOfNote()))
                .build();
    }

    public static ItemNoteDto toItemNoteDto(ItemNote itemNote) {
        return ItemNoteDto.builder()
                .id(itemNote.getId())
                .itemId(itemNote.getItem().getId())
                .text(itemNote.getText())
                .dateOfNote(itemNote.getDateOfNote().toString())
                .itemUrl(itemNote.getItem().getUrl())
                .build();
    }

    public static List<ItemNoteDto> toItemNoteDto(Iterable<ItemNote> itemNotes) {
        List<ItemNoteDto> itemNoteDtos = new ArrayList<>();
        itemNotes.forEach(itemNote -> itemNoteDtos.add(toItemNoteDto(itemNote)));
        return itemNoteDtos;
    }
}
