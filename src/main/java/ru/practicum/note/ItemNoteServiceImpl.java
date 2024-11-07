package ru.practicum.note;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.item.Item;
import ru.practicum.item.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemNoteServiceImpl implements ItemNoteService {

    private final ItemNoteRepository itemNoteRepository;

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemNoteDto addNewItemNote(long userId, ItemNoteDto itemNoteDto) {
        Item item = itemRepository.findById(itemNoteDto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        ItemNote itemNote = itemNoteRepository.save(ItemNoteMapper.toItemNote(itemNoteDto, item));
        return ItemNoteMapper.toItemNoteDto(itemNote);
    }

    @Override
    public List<ItemNoteDto> searchNotesByUrl(String url, Long userId) {
        List<ItemNote> itemNotes = itemNoteRepository.findAllByItemUserIdAndItemUrlContaining(userId, url);
        return ItemNoteMapper.toItemNoteDto(itemNotes);
    }

    @Override
    public List<ItemNoteDto> searchNotesByTag(long userId, String tag) {
        List<ItemNote> itemNotes = itemNoteRepository.findAllByUserIdAndTags(userId, tag);
        return ItemNoteMapper.toItemNoteDto(itemNotes);
    }

    @Override
    public List<ItemNoteDto> listAllItemsWithNotes(long userId, int from, int size) {
        PageRequest page = PageRequest.of(from > 0 ? from / size : 0, size);
        return itemNoteRepository.findAllByItemUserId(userId, page)
                .map(ItemNoteMapper::toItemNoteDto)
                .getContent();
    }
}
