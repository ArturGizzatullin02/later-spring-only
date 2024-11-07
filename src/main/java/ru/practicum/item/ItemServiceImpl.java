package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<ItemDto> getItems(long userId) {
        List<Item> userItems = itemRepository.findByUserId(userId);
        return ItemMapper.toItemDto(userItems);
    }

    @Override
    @Transactional
    public ItemDto addNewItem(long userId, ItemDto itemDto) {
        Item item = itemRepository.save(ItemMapper.toItem(itemDto, userId));
        return ItemMapper.toItemDto(item);
    }

    @Override
    @Transactional
    public void deleteItem(long userId, long itemId) {
        itemRepository.deleteByUserIdAndId(userId, itemId);
    }
}
